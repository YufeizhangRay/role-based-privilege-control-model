package cn.zyf.sshwebeasyui.controller;

import java.text.ParseException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.SystemContext;
import cn.zyf.sshwebeasyui.model.User;
import cn.zyf.sshwebeasyui.service.UserService;
import cn.zyf.sshwebeasyui.utils.SecurityUtil;
import cn.zyf.sshwebeasyui.web.AuthClass;
import cn.zyf.sshwebeasyui.web.AuthMethod;

@AuthClass
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@AuthMethod
	@RequestMapping(value="/userManager",method=RequestMethod.GET)
	public String userManager() {
		return "user_manager";
	}
	
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getAllUsers",method=RequestMethod.POST)
	public List<User> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return users;
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getAllPagerUsers",method=RequestMethod.POST)
	public Pager<User> getAllPagerUsers(Integer page,Integer rows,
			@RequestParam(value="username",required=false) String username) {
		if(page!=null&&page>0) {
			SystemContext.setPageOffset((page-1)*rows);
		}
		if(rows!=null&&rows>0) {
			SystemContext.setPageSize(rows);
		}
		User user = new User();
		if(username!=null&&!username.equals("")) {
			user.setUsername(username);
		}
		Pager<User> pager = userService.getAllPagerUsers(user);
		SystemContext.removePageSize();
		SystemContext.removePageOffset();
		return pager;
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public String updateUser(@RequestBody User user) {//前端可能没有填写密码
		try {
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser(@RequestBody User user) throws ParseException {
		String username = user.getUsername();
		String password = user.getPassword();
		password = SecurityUtil.md5(username, password);
		user.setPassword(password);
		try {
			userService.add(user);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	public String deleteUser(@RequestParam(value="ids[]") int[] ids) {
		try {
			for(int id:ids) {
				userService.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
}
