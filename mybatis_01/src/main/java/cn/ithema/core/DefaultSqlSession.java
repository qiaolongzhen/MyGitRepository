package cn.ithema.core;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import cn.ithema.domain.Configuation;

public class DefaultSqlSession implements SqlSession {

	private Configuation configuation;

	public DefaultSqlSession(Configuation configuation) {
		this.configuation = configuation;
	}

	/**
	 * 查询所有功能: MySQL;
	 * 
	 * @throws ClassNotFoundException
	 */
	@Override
	@SuppressWarnings("unchecked")
	public <E> List<E> selectAll(String mapperId) throws Exception {
		Class.forName(this.configuation.getDriver());
		Connection connection = DriverManager.getConnection(this.configuation.getUrl(), this.configuation.getUsername(), this.configuation.getPassword());
		PreparedStatement statement = connection.prepareStatement(this.configuation.getMappers().get(mapperId).getSql());
		ResultSet resultSet = statement.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		Integer count = metaData.getColumnCount();
		Class<E> clazz = (Class<E>) Class.forName(this.configuation.getMappers().get(mapperId).getResultType());
		List<E> resultList = new ArrayList<>();
		while (resultSet.next()) {
			E e = clazz.newInstance();
			for (int i = 1; i <= count; i++) {
				String filed = metaData.getColumnName(i);
				String methodName = "set" + (filed.charAt(0) + "").toUpperCase() + filed.substring(1);
				Method method = clazz.getMethod(methodName, clazz.getDeclaredField(filed).getType());
				method.invoke(e, resultSet.getObject(i));
			}
			resultList.add(e);
		}
		return resultList;
	}

}
