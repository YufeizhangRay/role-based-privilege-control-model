package cn.zyf.sshwebeasyui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zyf.sshwebeasyui.dao.UserDao;
import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.User;
import cn.zyf.sshwebeasyui.utils.SecurityUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@Override
	public Pager<User> getAllPagerUsers(User user) {
		return userDao.getAllPagerUsers(user);
	}

	@Override
	public void updateUser(User user) {
		int userid = user.getId();
		User userDB = userDao.load(userid); 
		//load从数据库拿到预修改的用户记录对应的user对象，这个user对象就会直接进入到hibernate的缓存里
		userDB.setRegDate(user.getRegDate());
		userDB.setRoles(user.getRoles());
		userDB.setState(user.getState());
		if(user.getPassword()==null || "".equals(user.getPassword())) {
			
		}else {
			userDB.setPassword(SecurityUtil.md5(user.getUsername(),user.getPassword()));
		}
		
		userDao.update(userDB);		
	}

	@Override
	public User login(String username, String password) {
		User user = userDao.loadUserByUsername(username);
		if(user == null) throw new RuntimeException("用户名或密码有误！");
		if(!SecurityUtil.md5(username,password).equals(user.getPassword())) {
			throw new RuntimeException("用户名或密码有误！");
		}
		if(user.getState() != 1) throw new RuntimeException("用户已被禁用！");
		
		return user;
	}

}
