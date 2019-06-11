package com.qihang.shop.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import com.qihang.shop.user.dao.UserDao;
import com.qihang.shop.user.vo.User;
import com.qihang.shop.utils.EmailUtils;
import com.qihang.shop.utils.PageBean;
import com.qihang.shop.utils.UUIDUtils;
@Transactional
public class UserService {

	private UserDao userDao;
	public void setUserDao(UserDao userDao){
		this.userDao=userDao;
	}
	public User findByName(String username){
		return userDao.findByName(username);
	}
	public void save(User user) {
		user.setState(0);
		String code=UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		EmailUtils.sendMail(user.getEmail(), code);
		
	}
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}
	public void update(User existUser) {
		// TODO Auto-generated method stub
		userDao.update(existUser);
		
	}
	public User login(User user) {
		return userDao.login(user);
	}
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = userDao.findCount();
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
		List<User> list = userDao.findByPage(begin, limit);
		pageBean.setList(list);
		return pageBean;
	}
	public User findByUid(Integer uid) {
		
		return userDao.findByUid(uid);
	}
	public void delete(User user) {
		userDao.delete(user);
		
	}

}
