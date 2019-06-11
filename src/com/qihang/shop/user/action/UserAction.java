package com.qihang.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qihang.shop.user.service.UserService;
import com.qihang.shop.user.vo.User;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user=new User();
	private String checkcode;
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	public User getModel(){
		return user;
	}
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String registPage() {
		return "registPage";

	}
	public String findByName() throws IOException{
		User u=userService.findByName(user.getUsername());
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		if(u!=null){
			response.getWriter().println("<font color='red'>用户名已经存在</font>");
		}
		else{
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}
	public String regist(){
		String checkcode1=(String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode1.equalsIgnoreCase(checkcode)){
			this.addActionError("验证码输入错误!");
			return "checkcodeerror";
		}
		userService.save(user);
		this.addActionMessage("注册成功!请去邮箱激活!");
		return "msg";
	}
	public String active(){
		User existUser = userService.findByCode(user.getCode());
		// 根据激活码查询用户:
			// 判断
				if (existUser == null) {
					// 激活码错误的
					this.addActionMessage("激活失败:激活码错误!");
				} else {
					// 激活成功
					// 修改用户的状态
					existUser.setState(1);
					existUser.setCode(null);
					userService.update(existUser);
					this.addActionMessage("激活成功:请去登录!");
				}
				return "msg";
	}
	public String loginPage(){
		return "loginPage";
	}
	public String login(){
		User existUser=userService.login(user);
		if (existUser == null) {
			this.addActionMessage("登录失败:用户名或密码错误或用户未激活!");
			return "msg";
		}
		else{
			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			return "loginsuccess";
		}
		
	}
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}

}
