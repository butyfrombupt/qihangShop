package com.qihang.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qihang.shop.category.dao.CategoryDao;
import com.qihang.shop.category.vo.Category;
@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	public void save(Category category) {
		System.out.println("!!!!!!!"+category.getCname());
		categoryDao.save(category);
		
	}

	public Category findByCid(Integer cid) {
		// TODO Auto-generated method stub
		return categoryDao.findByCid(cid);
	}

	public void delete(Category category) {
		categoryDao.delete(category);
		
	}

	public void update(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
		
	}

	

}
