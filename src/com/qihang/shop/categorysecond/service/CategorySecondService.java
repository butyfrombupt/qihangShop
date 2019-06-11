package com.qihang.shop.categorysecond.service;

import java.util.List;

import com.qihang.shop.categorysecond.dao.CategorySecondDao;
import com.qihang.shop.categorysecond.vo.CategorySecond;
import com.qihang.shop.utils.PageBean;

public class CategorySecondService {

	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();

		// 设置参数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置页面显示数据的集合:
		int begin = (page - 1) * limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	public void save(CategorySecond categorySecond){
		categorySecondDao.save(categorySecond);
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
		
	}

	public CategorySecond findByCsid(Integer csid) {
		// TODO Auto-generated method stub
		return categorySecondDao.findByCsid(csid);
	}

	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
		
	}

	public List<CategorySecond>  findAll() {
		return categorySecondDao.findAll();
		
	}

	
	
	

}
