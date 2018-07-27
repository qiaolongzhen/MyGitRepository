package cn.ithema.core;

import cn.ithema.domain.Configuation;

/**
 * 工厂类: 用于生成SqlSession对象;
 * 
 * @author qiaolongzhen
 *
 */
public class SqlSessionFactory {

	private Configuation configuation;

	public SqlSessionFactory(Configuation configuation) {
		this.configuation = configuation;
	}

	public SqlSession openSession() {
		return new DefaultSqlSession(this.configuation);
	}

}
