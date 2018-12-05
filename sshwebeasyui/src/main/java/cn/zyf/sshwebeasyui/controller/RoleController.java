package cn.zyf.sshwebeasyui.controller;

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
import cn.zyf.sshwebeasyui.model.Role;
import cn.zyf.sshwebeasyui.model.SystemContext;
import cn.zyf.sshwebeasyui.service.RoleService;
import cn.zyf.sshwebeasyui.web.AuthClass;
import cn.zyf.sshwebeasyui.web.AuthMethod;

@AuthClass
@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@ResponseBody
	@RequestMapping(value="/getAllStates",method=RequestMethod.POST)
	public List<Map<String, String>> getAllStates(){
		Map<String, String> state1 = new HashMap<>();
		Map<String, String> state2 = new HashMap<>();
		List<Map<String, String>> stateList = new ArrayList<>();
		state1.put("id", "0");
		state1.put("text", "禁用");
		stateList.add(state1);
		state2.put("id", "1");
		state2.put("text", "正常");
		stateList.add(state2);
		return stateList;
	} 

	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getAllRoles",method=RequestMethod.POST)
	public List<Map<String, String>> getAllRoles(){
		List<Role> roles = roleService.getAllRoles();
		List<Map<String, String>> roleList = new ArrayList<>();
		for(Role role:roles) {
			Map<String, String> roleMap = new HashMap<>();
			roleMap.put("id", role.getId()+"");
//			text为easyui解析的标签 为显示的文本
			roleMap.put("text", role.getRoleName());
			roleList.add(roleMap);
		}
		return roleList;
	}
	
	@AuthMethod
	@RequestMapping(value="/roleManager",method=RequestMethod.GET)
	public String roleManager() {
		return "role_manager";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/getAllPagerRoles",method=RequestMethod.POST)
	public Pager<Role> getAllPagerRoles(Integer page,Integer rows){
		if(page != null && page > 0) SystemContext.setPageOffset((page-1)*rows);
		if(rows != null && rows > 0) SystemContext.setPageSize(rows);
		Pager<Role> roles = roleService.getAllPagerRoles();
		SystemContext.removePageOffset();
		SystemContext.removePageSize();
		return roles;
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/addRole",method=RequestMethod.POST)
	public String addRole(@RequestBody Role role) {
		try {
			roleService.add(role);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/updateRole",method=RequestMethod.POST)
	public String updateRole(@RequestBody Role role) {
		try {
			roleService.update(role);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	@AuthMethod
	@ResponseBody
	@RequestMapping(value="/deleteRole",method=RequestMethod.POST)
	public String deleteRole(@RequestParam(value="ids[]") int[] ids) {
		try {
			for(int id:ids) {
				roleService.delete(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
}
