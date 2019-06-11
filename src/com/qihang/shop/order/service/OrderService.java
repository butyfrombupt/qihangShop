package com.qihang.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qihang.shop.order.dao.OrderDao;
import com.qihang.shop.order.vo.Order;
import com.qihang.shop.order.vo.OrderItem;
import com.qihang.shop.utils.PageBean;
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public void save(Order order) {
		orderDao.save(order);
		
	}

	public PageBean<Order> findByUid(Integer uid, Integer page) {
		PageBean<Order> pagebean=new PageBean<Order>();
		pagebean.setPage(page);
		Integer limit=5;
		pagebean.setLimit(limit);
		Integer totalCount=null;
		totalCount=orderDao.findByCountUid(uid);
		pagebean.setTotalCount(totalCount);
		Integer totalPage=null;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pagebean.setTotalPage(totalPage);
		Integer begin=(page-1)*limit;
		List<Order> list=orderDao.findByPageUid(uid,begin,limit);
		pagebean.setList(list);
		return pagebean;
	}

	public Order findByOid(Integer oid) {
		
		return orderDao.findByOid(oid);
	}

	public void update(Order currOrder) {
		orderDao.update(currOrder);
		
	}

	public PageBean<Order> findByPage(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置参数
		pageBean.setPage(page);
		// 设置每页显示的记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public List<OrderItem> findOrderItem(Integer oid) {
		// TODO Auto-generated method stub
		return orderDao.findOrderItem(oid);
	}

	
	

	

}
