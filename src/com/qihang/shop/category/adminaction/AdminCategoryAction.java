package com.qihang.shop.category.adminaction;

import java.io.UnsupportedEncodingException;
import java.util.List;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qihang.shop.category.service.CategoryService;
import com.qihang.shop.category.vo.Category;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{

	private Category category=new Category();
	public Category getModel() {
		return category;
	}
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public String findAll(){
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	public String save(){
		//category.setCname("哦哈哈哈");
		categoryService.save(category);
		System.out.print(category.getCname());
		return "savesuccess";
	}
	public String delete(){
		category=categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deletesuccess";
	}
	
	public String edit(){
		categoryService.findByCid(category.getCid());
		return "editSuccess";
	}
	public String update(){
		categoryService.update(category);
		return "updatesuccess";
	}
	

	

}
