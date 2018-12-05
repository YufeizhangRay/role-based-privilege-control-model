package cn.zyf.sshwebeasyui.web;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.zyf.sshwebeasyui.service.PermissionService;

public class InitWebServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	//两个初始化，一个初始化：spring的ico容器的引用初始化到InitWebServlet类的一个静态方法
	private static ApplicationContext applicationContext;
	
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	@Override
	public void init() throws ServletException {
		//一个初始化：spring的ico容器 全局作用域
		ServletContext context = getServletContext();
		applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
		
		try {
			//第二个初始化：初始化权限
			//packageName实施权限控制的包全名
			String packageName = "cn.zyf.sshwebeasyui.controller";
			String packageNamePath = packageName.replace(".", "/"); //  ---> cn/zyf/sshwebeasyui/controller
			//拿到packageNamePath，进一步的获取到对应在服务器上磁盘上的绝对路径
			String packageNameRealPath = this.getClass().getClassLoader()
					.getResource(packageNamePath).getPath();
			//System.out.println("=========packageNameRealPath=========" + packageNameRealPath);
			File file = new File(packageNameRealPath); //file就是controller在磁盘的这个文件夹
			//遍历这个文件夹里的文件 只要后缀名为.class的字节码文件
			String[] classFileNames = file.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if(name.endsWith(".class")) {
						return true;
					}
					return false;
				}
			});
			
			List<String> resources = new ArrayList<>();
			
			for(String classFileName : classFileNames) {
				//.class这个后缀给删除掉
				classFileName = classFileName.substring(0,classFileName.indexOf(".class"));
				//拿到纯粹的类的包全名
				String classAllpackageName = packageName + "." + classFileName;
				//拿到纯粹的类的包全名，可以通过反射获取到对应的类的对象
				Class clazz = Class.forName(classAllpackageName);
				//拿到这些controller的对象，获取到在他们身上的注解
				if(!clazz.isAnnotationPresent(AuthClass.class)) continue;
				//剩下的类，都是有@AuthClass这个注解的类，这些类都要进行权限控制的
				//拿到这些类的所有方法
				Method[] methods = clazz.getDeclaredMethods();//包含private方法
				for(Method method : methods) {
					if(!method.isAnnotationPresent(AuthMethod.class)) continue;
					//都是有@AuthMethod的方法,拿到要保存到permission表里resource字段的值
					//两种思路：
					//1:cn.zyf.sshwebeasyui.controller.UserConroller.addUser
					//String resource = classAllpackageName + "." + method.getName(); 第一种思路
					
					//2:/getAllUsers 若用户的权限包含此映射，则可以访问
					RequestMapping reqMapping = method.getAnnotation(RequestMapping.class);//获取注解
					resources.add(reqMapping.value()[0]);//获取注解的内容 即/getAllUsers
					
				}
			}
			//List<String> resources ： 包含了controller包里，所有被@AuthClass和@AuthMethod共同作用的
			//方法上面的@RequestMapping的value值(都在里面!)
			PermissionService permissionService = (PermissionService) applicationContext
					.getBean("permissionService");//拿到permissionService的实现类
			permissionService.initPermissions(resources);//调用初始化方法
			//初始化时候即将所有权限资源注入context域空间
			context.setAttribute("allPermissionResources", resources);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	
	}
	
}
