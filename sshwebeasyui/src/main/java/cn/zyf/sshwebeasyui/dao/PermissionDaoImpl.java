package cn.zyf.sshwebeasyui.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.Permission;

@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDaoImpl<Permission> implements PermissionDao{

	@Override
	public List<Permission> getAllPermissions() {
		String hql = "from Permission";
		return super.list(hql, null, null);
	}

	@Override
	public Pager<Permission> getAllPagerPermission() {
		String hql = "from Permission";
		return super.find(hql, null, null);
	}

	@Override
	public boolean isExistResource(String resource) {
		String hql = "select count(*) from Permission p where p.resources=?";
		long count = (long) super.queryByHql(hql, new Object[] { resource }, null);
		return count > 0 ? true : false;
	}

}
