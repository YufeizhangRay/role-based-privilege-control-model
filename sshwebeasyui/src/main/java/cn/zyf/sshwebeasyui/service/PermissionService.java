package cn.zyf.sshwebeasyui.service;

import java.util.List;

import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.Permission;

public interface PermissionService extends BaseService<Permission>{

	public List<Permission> getAllPermissions();
	
	public Pager<Permission> getAllPagerPermission();
	
	public void initPermissions(List<String> resources);
}
