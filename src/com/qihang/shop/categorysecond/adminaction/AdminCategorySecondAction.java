package com.qihang.shop.categorysecond.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qihang.shop.category.service.CategoryService;
import com.qihang.shop.category.vo.Category;
import com.qihang.shop.categorysecond.service.CategorySecondService;
import com.qihang.shop.categorysecond.vo.CategorySecond;
import com.qihang.shop.utils.PageBean;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond> {

	private CategorySecond categorySecond = new CategorySecond();

	public CategorySecond getModel() {
		
		return categorySecond;
	}
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	private CategorySecondService categorySecondService;

	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	public String findAll() {
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}
	public String addPage(){
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList",cList);
		return "addPage";
	}
	
	public String save(){
		categorySecondService.save(categorySecond);
		return "savesuccess";
	}
	public String delete(){
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deletesuccess";
	}
	public String edit() {
		// 根据id查询二级分类:
		categorySecond = categorySecondService.findByCsid(categorySecond
				.getCsid());
		// 查询所有一级分类:
		List<Category> cList = categoryService.findAll();
		// 将集合存入到值栈中.
		ActionContext.getContext().getValueStack().set("cList", cList);
		// 页面跳转:
		return "editSuccess";
	}
	
	// 修改二级分类的方法:
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}

}
