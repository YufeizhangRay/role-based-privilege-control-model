
package cn.zyf.sshwebeasyui.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.zyf.sshwebeasyui.model.Permission;
import cn.zyf.sshwebeasyui.model.Role;
import cn.zyf.sshwebeasyui.model.User;
import cn.zyf.sshwebeasyui.service.UserService;
/**
 * 登录控制器
 * @author ray_cn
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String username, String password, HttpSession session) {
		// 调用userService来判断登录的用户名和密码对不对
		User loginUser = userService.login(username, password); // 登录成功，返回一个user对象，抛出异常，返回null
		// System.out.println("==========loginUser=========="+loginUser);
		//登录成功的用户保存到session域空间中
		if (loginUser != null)
			session.setAttribute("loginUser", loginUser);
		// 判断登录的用户是普通的用户还是超级管理员
		Set<Role> setRoles = loginUser.getRoles();//获取用户所有的身份
		boolean isadmin = false;
		Set<String> userAllPermissionResources = new HashSet<>();
		for (Role role : setRoles) {
			if ("超级管理员".equals(role.getRoleName())) {
				isadmin = true;
				break;//若是超级管理员，无需循环直接放行
			}
			// 获取登录用户的所有权限的标记
			Set<Permission> permissions = role.getPermissions();
			if (permissions != null && permissions.size() > 0) {
				for (Permission permission : permissions) {
					userAllPermissionResources.add(permission.getResources());
				}
			}
		}//循环结束，至此已获取登录用户的所有权限标记
		
		//将是否为超级管理员放入session域空间
		session.setAttribute("isadmin", isadmin);

		if (!isadmin) {
			// 不是超级管理员，权限控制：获取到登录的用户，他所拥有的所有的权限资源的标记注入session域空间
			session.setAttribute("userAllPermissionResources", userAllPermissionResources);
		}

		return "redirect:/main";
	}

	@RequestMapping(value = "/main")
	public String main() {
		return "/main";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("isadmin");
		session.removeAttribute("loginUser");
		session.removeAttribute("userAllPermissionResources");
		session.invalidate();
		return "/login";
	}
	
	@RequestMapping(value="/main/index",method=RequestMethod.GET)
	public String mainIndex() {
		return "/main_index";
	}
}
