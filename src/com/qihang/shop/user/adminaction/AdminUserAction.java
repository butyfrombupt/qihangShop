package com.qihang.shop.user.adminaction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qihang.shop.product.vo.Product;
import com.qihang.shop.user.service.UserService;
import com.qihang.shop.user.vo.User;
import com.qihang.shop.utils.PageBean;

public class AdminUserAction extends ActionSupport implements ModelDriven<User> {

	private User user=new User();
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	private UserService userService;
	
	private Integer page;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String findAll(){
		PageBean<User> pageBean=userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String delete(){
		user=userService.findByUid(user.getUid());
		userService.delete(user);
		return "deleteSuccess";
	}
	
	public String edit(){
		user=userService.findByUid(user.getUid());
		return "editSuccess";
	}
	public String update(){
		userService.update(user);
		return "updateSuccess";
	}

	

}
