package com.qihang.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qihang.shop.adminuser.service.AdminUserService;
import com.qihang.shop.adminuser.vo.AdminUser;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	private AdminUser adminUser=new AdminUser();
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	public String loginPage(){
		return "loginPage";
	}
	
	public String login(){
		
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if (existAdminUser == null) {
			this.addActionError("亲~您的用户名或密码输入有误！");
			return "loginFail";
		} else {
			ServletActionContext.getRequest().getSession()
					.setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
	

	

}
