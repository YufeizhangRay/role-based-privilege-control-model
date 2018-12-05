package cn.zyf.sshwebeasyui.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zyf.sshwebeasyui.dao.PermissionDao;
import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.Permission;

@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService{

	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public List<Permission> getAllPermissions() {
		return permissionDao.getAllPermissions();
	}

	@Override
	public Pager<Permission> getAllPagerPermission() {
		return permissionDao.getAllPagerPermission();
	}

	/**
	 * initWevServlet初始化权限时，获取到的权限的标记的list，将标记写入到数据库
	 * 对于同样的标记我们只写入数据库一次
	 */
	@Override
	public void initPermissions(List<String> resources) {
		//循环遍历list
		//遍历的过程中要判断这个标记是不是已经存在于数据表中
		//存在则不插入
		boolean isExist = false;
		for(String resource : resources) {
			isExist = permissionDao.isExistResource(resource); //返回true，存在，返回false，不存在
			if(!isExist) { //resource不存在 即执行插入
				Permission permission = new Permission();
				permission.setResources(resource);
				permission.setState(1);
				permissionDao.add(permission);//调用dao的类，执行插入记录到数据库
			}
		}
	}

}
