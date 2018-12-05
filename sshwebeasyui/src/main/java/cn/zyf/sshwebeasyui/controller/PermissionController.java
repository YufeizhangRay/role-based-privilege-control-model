package cn.zyf.sshwebeasyui.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zyf.sshwebeasyui.model.Pager;
import cn.zyf.sshwebeasyui.model.Permission;
import cn.zyf.sshwebeasyui.model.SystemContext;
import cn.zyf.sshwebeasyui.service.PermissionService;
import cn.zyf.sshwebeasyui.web.AuthClass;
import cn.zyf.sshwebeasyui.web.AuthMethod;

@AuthClass
@Controller
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@AuthMethod
	@RequestMapping(value="/permissionManager",method=RequestMethod.GET)
	public String permissionManager() {
		return "permission_manager";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getAllPermissions",method=RequestMethod.POST)
	public List<Map<String, String>> getAllPermissions(){
		List<Permission> permissions = permissionService.getAllPermissions();
		List<Map<String, String>> permissionList = new ArrayList<>();
		for(Permission permission:permissions) {
			Map<String, String> permissionMap = new HashMap<>();
			permissionMap.put("id", permission.getId()+"");
			permissionMap.put("text", permission.getResources());
			permissionList.add(permissionMap);
		}
		return permissionList;
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getAllPagerpermissions",method=RequestMethod.POST)
	public Pager<Permission> getAllPagerpermissions(Integer page,Integer rows){
		if(page != null && page > 0) SystemContext.setPageOffset((page-1)*rows);
		if(rows != null && rows > 0) SystemContext.setPageSize(rows);
		Pager<Permission> permisstions = permissionService.getAllPagerPermission();
		SystemContext.removePageOffset();
		SystemContext.removePageSize();
		return permisstions;
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/updatePermission",method=RequestMethod.POST)
	public String updatePermission(@RequestBody Permission permission) {
		try {
			permissionService.update(permission);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/addPermission",method=RequestMethod.POST)
	public String addPermission(@RequestBody Permission permission) throws ParseException {
		try {
			permissionService.add(permission);
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/deletePermission",method=RequestMethod.POST)
	public String deletePermission(@RequestParam(value="ids[]") int[] ids) {
		try {
			for(int id:ids) {
				permissionService.delete(id);
			}
		} catch (Exception e) {
			return "error";
		}
		return "ok";
	}
}
