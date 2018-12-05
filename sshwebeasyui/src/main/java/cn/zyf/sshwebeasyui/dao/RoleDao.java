package cn.zyf.sshwebeasyui.dao;

import java.util.List;

import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.Role;

public interface RoleDao extends BaseDao<Role>{

	/**
	 * 获取所有角色信息
	 */
	public List<Role> getAllRoles();
	
	/**
	 * 
	 */
	public Pager<Role> getAllPagerRoles();
}
