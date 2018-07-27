package cn.ithema.core;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.ithema.domain.Configuation;
import cn.ithema.domain.Mapper;

/**
 * 1.读取配置文件,并封装数据; 2.创建一SqlSessionFactory对象;
 * 
 * @author qiaolongzhen
 *
 */
public class SqlSessionFactoryBuilder {

	public SqlSessionFactory build(InputStream reader) throws Exception {
		// 1.读取配置文件,将数据库的信息封装到Configuration对象中;
		Configuation configuation = createConfiguation(reader);
		return new SqlSessionFactory(configuation);
	}

	@SuppressWarnings("unchecked")
	public Configuation createConfiguation(InputStream reader) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(reader);
		Element rootElement = document.getRootElement();
		List<Element> propertyList = rootElement.selectNodes("//property");
		Configuation configuation = new Configuation();
		for (Element element : propertyList) {
			if ("driver".equals(element.attributeValue("name")))
				configuation.setDriver(element.attributeValue("value"));
			if ("url".equals(element.attributeValue("name")))
				configuation.setUrl(element.attributeValue("value"));
			if ("username".equals(element.attributeValue("name")))
				configuation.setUsername(element.attributeValue("value"));
			if ("password".equals(element.attributeValue("name")))
				configuation.setPassword(element.attributeValue("value"));
		}
		List<Element> selectNodes = rootElement.selectNodes("//mapper");
		for (Element element : selectNodes) {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(element.attributeValue("resource"));
			Map<String, Mapper> mappers = createMappers(inputStream);
			configuation.getMappers().putAll(mappers);
		}
		return configuation;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Mapper> createMappers(InputStream inputStream) throws DocumentException {
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(inputStream);
		Element rootElement = document.getRootElement();
		String namespace = rootElement.attributeValue("namespace");
		List<Element> elements = rootElement.elements("select");
		Map<String, Mapper> mappers = new HashMap<>();
		for (Element element : elements) {
			Mapper mapper = new Mapper();
			String sql = element.getText().trim();
			mapper.setSql(sql);
			String resultType = element.attributeValue("resultType");
			mapper.setResultType(resultType);
			String id = element.attributeValue("id");
			mappers.put(namespace + "." + id, mapper);
		}
		return mappers;
	}

}
