package com.qihang.shop.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qihang.shop.category.service.CategoryService;
import com.qihang.shop.product.service.ProductService;
import com.qihang.shop.product.vo.Product;
import com.qihang.shop.utils.PageBean;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	public Product getModel(){
		return product;
	}
	
	// 接收分类cid
	private Integer cid;
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	// 接收二级分类id
	private Integer csid;
	
	public Integer getCsid() {
		return csid;
	}

	private int page;
	public void setPage(int page) {
		this.page = page;
	}
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private ProductService productService;
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public String findByPid(){
		product=productService.findByPid(product.getPid());
		return "findByPid";
	}
	public String findByCid(){
		PageBean<Product> pageBean = productService.findByPageCid(cid, page);// 根据一级分类查询商品,带分页查询
		// 将PageBean存入到值栈中:
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	public String findByCsid(){
		PageBean<Product> pageBean=productService.findByPageCsid(csid, page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}


}
