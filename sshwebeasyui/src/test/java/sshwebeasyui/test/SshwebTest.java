package sshwebeasyui.test;

import java.util.List;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zyf.sshwebeasyui.dao.UserDaoImpl;
import cn.zyf.sshwebeasyui.model.User;
public class SshwebTest {
	
	private UserDaoImpl userDaoImpl = new UserDaoImpl();

	@Test
	public void testJdbc() {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
//		System.out.println(sessionFactory);
//		System.out.println(sessionFactory.openSession());
		List<User> users = userDaoImpl.getAllUsers();
		System.out.println(users);
	}
}
