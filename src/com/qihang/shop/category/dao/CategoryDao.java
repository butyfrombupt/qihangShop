package com.qihang.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.qihang.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		String hql="from Category";
		List<Category> list=this.getHibernateTemplate().find(hql);
		return list;
	}

	public void save(Category category) {
		System.out.println("!!!!!!"+category.getCname()+"!!!!");
		this.getHibernateTemplate().save(category);
		
	}
	

	public Category findByCid(Integer cid) {
		
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	public void delete(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(category);
		
	}

	public void update(Category category) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(category);
		
	}

}
