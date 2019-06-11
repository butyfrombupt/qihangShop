package com.qihang.shop.index.action;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qihang.shop.category.service.CategoryService;
import com.qihang.shop.category.vo.*;
import com.qihang.shop.product.service.ProductService;
import com.qihang.shop.product.vo.Product;
public class IndexAction extends ActionSupport{
	private CategoryService  categoryService;
	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public String execute(){
		List<Category> list=categoryService.findAll();
		ActionContext.getContext().getSession().put("list", list);
		
		List<Product> productlist=productService.findHot();
		ActionContext.getContext().getValueStack().set("productlist", productlist);
		
		List<Product> newlist=productService.findNew();
		ActionContext.getContext().getValueStack().set("newlist", newlist);
		return "index";
	}

}
