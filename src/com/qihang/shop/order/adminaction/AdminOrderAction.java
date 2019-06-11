package com.qihang.shop.order.adminaction;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qihang.shop.order.service.OrderService;
import com.qihang.shop.order.vo.Order;
import com.qihang.shop.order.vo.OrderItem;
import com.qihang.shop.utils.PageBean;

public class AdminOrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order order=new Order();
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	private OrderService orderService;
	private Integer page;
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public String findAll(){
		PageBean<Order> pageBean=orderService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String findOrderItem(){
		List<OrderItem> list=orderService.findOrderItem(order.getOid());
		ActionContext.getContext().getValueStack().set("list", list);
		return "findOrderItem";
	}
	
	public String updateState(){
		order=orderService.findByOid(order.getOid());
		order.setState(3);
		orderService.update(order);
		return "updateStateSuccess";
	}


}
