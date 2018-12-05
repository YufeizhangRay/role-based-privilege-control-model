package cn.zyf.sshwebeasyui.service;

import java.util.List;

import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.Role;

public interface RoleService extends BaseService<Role>{

	/**
	 * 获取所有角色信息
	 */
	public List<Role> getAllRoles();
	
	public Pager<Role> getAllPagerRoles();
}
