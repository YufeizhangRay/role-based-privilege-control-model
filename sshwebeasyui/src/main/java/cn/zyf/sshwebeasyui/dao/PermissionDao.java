package cn.zyf.sshwebeasyui.dao;

import java.util.List;

import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.Permission;

public interface PermissionDao extends BaseDao<Permission>{

	public List<Permission> getAllPermissions();
	
	public Pager<Permission> getAllPagerPermission();

	/**
	 * 判断resource是否已经在数据库中，是true，不是false
	 */
	public boolean isExistResource(String resource);
	
}