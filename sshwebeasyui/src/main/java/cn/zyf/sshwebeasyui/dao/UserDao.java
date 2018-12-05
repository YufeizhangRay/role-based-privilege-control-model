package cn.zyf.sshwebeasyui.dao;

import java.util.List;

import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.User;

public interface UserDao extends BaseDao<User>{

	/**
	 * 获取所有的用户 不支持分页
	 */
	public List<User> getAllUsers();
	
	/**
	 * 获取所有的用户 支持分页和模糊查询
	 */
	public Pager<User> getAllPagerUsers(User user);

	public User loadUserByUsername(String username);
}
