package com.qihang.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qihang.shop.categorysecond.service.CategorySecondService;
import com.qihang.shop.categorysecond.vo.CategorySecond;
import com.qihang.shop.product.service.ProductService;
import com.qihang.shop.product.vo.Product;
import com.qihang.shop.utils.PageBean;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product> {

	private Product product=new Product();
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	private ProductService productService;
	
	private Integer page;
	
	private CategorySecondService categorySecondService;
	
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	
	private File upload;
	private String uploadFileName;
	private String uploadContextType;
	
	
	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	
	private Integer csid;

	public void setCsid(Integer csid) {
		this.csid = csid;
	}
	
	private CategorySecond categorySecond;

	public void setCategorySecond(CategorySecond categorySecond) {
		this.categorySecond = categorySecond;
	}

	public String findAll(){
		PageBean<Product> pageBean=productService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	public String addPage(){
		List<CategorySecond> csList=categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}
	
	public String save() throws IOException{
		product.setPdate(new Date());
		categorySecond=categorySecondService.findByCsid(csid);
		product.setCategorySecond(categorySecond);
		if(upload != null){
			// 将商品图片上传到服务器上.
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);
	
			product.setImage("products/" + uploadFileName);
		}
		productService.save(product);
		
		return "saveSuccess";
	}
	
	public String delete(){
		product=productService.findByPid(product.getPid());
		String path = ServletActionContext.getServletContext().getRealPath(
				"/" + product.getImage());
		if(path!=null){
		File file = new File(path);
		file.delete();
		// 删除数据库中商品记录:
		productService.delete(product);
		}
		// 页面跳转
		return "deleteSuccess";
		
	}
	public String edit(){
		// 根据商品id查询商品信息
				product = productService.findByPid(product.getPid());
				// 查询所有二级分类
				List<CategorySecond> csList = categorySecondService.findAll();
				ActionContext.getContext().getValueStack().set("csList", csList);
				// 页面跳转到编辑页面:
				return "editSuccess";
	}
	public String update() throws IOException {
		// 将信息修改到数据库
		product.setPdate(new Date());
		categorySecond=categorySecondService.findByCsid(csid);
		product.setCategorySecond(categorySecond);
		// 上传:
		if(upload != null ){
			String delPath = ServletActionContext.getServletContext().getRealPath(
					"/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			// 获得上传图片的服务器端路径.
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products");
			// 创建文件类型对象:
			File diskFile = new File(path + "//" + uploadFileName);
			// 文件上传:
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/" + uploadFileName);
		}
		productService.update(product);
		// 页面跳转
		return "updateSuccess";
	}

	

}
