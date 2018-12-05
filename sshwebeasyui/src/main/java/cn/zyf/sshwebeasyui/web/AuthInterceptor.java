package cn.zyf.sshwebeasyui.web;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.zyf.sshwebeasyui.model.User;


public class AuthInterceptor extends HandlerInterceptorAdapter {

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler)
			throws Exception {
		//获取域空间 用来获取登录成功时注入session域空间的用户信息
		HttpSession session = request.getSession();
		
		String recource = "";
		if (handler instanceof HandlerMethod) {//乱写url 在映射中不存在 则返回 false
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			//获取访问的方法
			Method method = handlerMethod.getMethod();
			//获取方法的注解
			RequestMapping requestMapping  = method.getAnnotation(RequestMapping.class);
			//获取注解内容
			recource = requestMapping.value()[0]; //当前对应的权限标记 例如 /getAllUsers
		}else {
			throw new RuntimeException("您访问的页面不存在！");
		}
		
		//所有需要权限控制的资源 获取初始化时注入context域空间的所有权限资源信息
		List<String> allPermissionResources = (List<String>) request
				.getServletContext().getAttribute("allPermissionResources");
		
		//用户拥有的权限资源
		Set<String> userAllPermissionResources = (Set<String>) session
				.getAttribute("userAllPermissionResources");
		
		//获取当前登录的用户
		User loginUser = (User) session.getAttribute("loginUser");
		if(loginUser == null) {
			response.sendRedirect(request.getContextPath() + "/login");
		}else {
			//获取是否为超级管理员
			boolean isadmin = (boolean) session.getAttribute("isadmin");
			//是否为超级管理员，并且是请求权限资源
			if(!isadmin && allPermissionResources.contains(recource)) {
				//用户的权限是否包含此权限资源
				if(!userAllPermissionResources.contains(recource)) 
					throw new RuntimeException("您没有权限访问该功能！");
			}
		}
		//放行
		return super.preHandle(request, response, handler);
	}
	
}
