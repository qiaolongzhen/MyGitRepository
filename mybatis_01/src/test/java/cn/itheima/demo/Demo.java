package cn.itheima.demo;

import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import cn.ithema.core.SqlSession;
import cn.ithema.core.SqlSessionFactory;
import cn.ithema.core.SqlSessionFactoryBuilder;
import cn.ithema.pojo.User;

public class Demo {

	@Test
	public void demo() throws Exception {
		InputStream reader = this.getClass().getClassLoader().getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sessionFactory.openSession();
		List<User> userList = session.selectAll("cn.itheima.mapper.UserMapper.selectAll");
		for (User user : userList) {
			System.out.println(user);
		}
	}

}
