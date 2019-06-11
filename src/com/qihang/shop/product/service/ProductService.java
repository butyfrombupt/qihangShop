package com.qihang.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qihang.shop.product.dao.ProductDao;
import com.qihang.shop.product.vo.Product;
import com.qihang.shop.utils.PageBean;
@Transactional
public class ProductService {
	private static final String Integer = null;
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public List<Product> findHot() {
		// TODO Auto-generated method stub
		return productDao.findHot();
	}

	public List<Product> findNew() {
		// TODO Auto-generated method stub
		return productDao.findNew();
	}

	public Product findByPid(Integer pid) {
		// TODO Auto-generated method stub
		return productDao.findByPid(pid);
	}

	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		int limit=8;
		pageBean.setLimit(limit);
		int totalCount=productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean=new PageBean<Product>();
		pageBean.setPage(page);
		int limit=8;
		pageBean.setLimit(limit);
		int totalCount=productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if(totalCount%limit==0){
			totalPage=totalCount/limit;
		}
		else{
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		productDao.save(product);
		
	}

	public void delete(Product product) {
		productDao.delete(product);
		
	}

	public void update(Product product) {
		// TODO Auto-generated method stub
		productDao.update(product);
		
	}

	

}
