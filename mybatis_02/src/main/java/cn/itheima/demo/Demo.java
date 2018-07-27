package cn.itheima.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.itheima.dao.IUserDao;
import cn.itheima.domain.User;

public class Demo {

	/**
	 * 增;
	 * 
	 * @throws IOException
	 */
	@Test
	public void insertUser() throws IOException {
		InputStream reader = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// openSession()会默认开启事务;
		SqlSession session = sessionFactory.openSession();
		IUserDao userDao = session.getMapper(IUserDao.class);
		Integer rows = userDao.insertUser(new User(null, "jingjing", "123456"));
		System.out.println("影响行数:" + rows);
		// 若执行增删改操作时一定要提交事务;
		session.commit();
	}

	/**
	 * 删;
	 * 
	 * @throws IOException
	 */
	@Test
	public void deleteUserLikeUsername() throws IOException {
		InputStream reader = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// openSession()会默认开启事务;
		SqlSession session = sessionFactory.openSession();
		IUserDao userDao = session.getMapper(IUserDao.class);
		Integer rows = userDao.deleteUserLikeUsername("%ing");
		System.out.println("影响行数:" + rows);
		// 若执行增删改操作时一定要提交事务;
		session.commit();
	}

	/**
	 * 改;
	 * 
	 * @throws IOException
	 */
	@Test
	public void updateUser() throws IOException {
		InputStream reader = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sessionFactory.openSession();
		IUserDao userDao = session.getMapper(IUserDao.class);
		Integer rows = userDao.updateUser(new User(2, "mingming", "654321"));
		System.out.println("影响行数:" + rows);
		session.commit();
	}

	/**
	 * 查询一条记录;
	 * 
	 * @throws IOException
	 */
	@Test
	public void selectUserById() throws IOException {
		InputStream reader = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sessionFactory.openSession();
		IUserDao userDao = session.getMapper(IUserDao.class);
		User user = userDao.selectUserById(1);
		System.out.println(user);
	}

	/**
	 * 查询单行单列数据;
	 * 
	 * @throws IOException
	 */
	@Test
	public void selectTotalRecord() throws IOException {
		InputStream reader = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sessionFactory.openSession();
		IUserDao userDao = session.getMapper(IUserDao.class);
		Integer totalRecord = userDao.selectTotalRecord();
		System.out.println("总记录数:" + totalRecord);
	}

	/**
	 * 查询所有记录;
	 * 
	 * @throws IOException
	 */
	@Test
	public void selectAllUser() throws IOException {
		InputStream reader = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = sessionFactory.openSession();
		IUserDao userDao = session.getMapper(IUserDao.class);
		List<User> userList = userDao.selectAllUser();
		for (User user : userList) {
			System.out.println(user);
		}
	}

}
