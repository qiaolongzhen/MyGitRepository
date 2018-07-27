package cn.itheima.dao;

import java.util.List;

import cn.itheima.domain.User;

public interface IUserDao {

	/**
	 * 插入一条用户记录;
	 * 
	 * @param user
	 * @return
	 */
	Integer insertUser(User user);

	/**
	 * 根据username值删除满足条件的记录;
	 * 
	 * @param username
	 * @return
	 */
	Integer deleteUserLikeUsername(String username);

	/**
	 * 根据id修改一条记录;
	 * 
	 * @param user
	 * @return
	 */
	Integer updateUser(User user);

	/**
	 * 根据id查询一条记录;
	 * 
	 * @param id
	 * @return
	 */
	User selectUserById(Integer id);

	/**
	 * 查询总记录数;
	 * 
	 * @return
	 */
	Integer selectTotalRecord();

	/**
	 * 查询所有记录;
	 * 
	 * @return
	 */
	List<User> selectAllUser();

}
