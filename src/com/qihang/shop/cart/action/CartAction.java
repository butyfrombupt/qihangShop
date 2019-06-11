package com.qihang.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.qihang.shop.cart.vo.Cart;
import com.qihang.shop.cart.vo.CartItem;
import com.qihang.shop.product.service.ProductService;
import com.qihang.shop.product.vo.Product;

public class CartAction extends ActionSupport {
	private Integer pid;
	private Integer count;
	
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	public Integer getPid() {
		return pid;
	}


	public void setPid(Integer pid) {
		this.pid = pid;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public String addCart(){
		//封装一个cartItem对象
		CartItem cartItem =new CartItem();
		cartItem.setCount(count);
		Product product=productService.findByPid(pid);
		cartItem.setProduct(product);
	    Cart cart=getCart();
	    cart.addCart(cartItem);
		return "addCart";
	}
	public String clearCart(){
		Cart cart=getCart();
		cart.clearCart();
		return "clearCart";
	}
	public String removeCart(){
		Cart cart=getCart();
		cart.removeCart(pid);
		return "removeCart";
	}
	public String myCart(){
		return "myCart";
	}


	private Cart getCart() {
		Cart cart=(Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			cart=new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}

}
