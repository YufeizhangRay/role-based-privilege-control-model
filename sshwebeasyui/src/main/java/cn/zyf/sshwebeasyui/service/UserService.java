package cn.zyf.sshwebeasyui.service;

import java.util.List;

import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.User;

public interface UserService extends BaseService<User>{

	/**
	 * 获取所有的用户 不支持分页
	 */
	public List<User> getAllUsers();
	
	/**
	 * 获取所有的用户 支持分页
	 */
	public Pager<User> getAllPagerUsers(User user);
	
	public void updateUser(User user);

	/**
	 * 判断填写的用户名和密码是否正确
	 */
	public User login(String username, String password);
}
