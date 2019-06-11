package com.qihang.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.qihang.shop.product.vo.Product;
import com.qihang.shop.user.vo.User;
import com.qihang.shop.utils.PageHibernateCallback;

public class UserDao extends HibernateDaoSupport {
	public User findByName(String username){
		String hql="from User where username=?";
		List<User> list=this.getHibernateTemplate().find(hql, username);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		else{
			return null;
		}
	}

	public void save(User user) {
		this.getHibernateTemplate().save(user);
		
	}

	public User findByCode(String code) {
		String hql="from User where code=?";
		List<User> uu=this.getHibernateTemplate().find(hql, code);
		if(uu.size()>0&&uu!=null){
			return uu.get(0);
		}
		else
			return null;
		
		
	}

	public void update(User existUser) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(existUser);
		
	}

	public User login(User user) {
		String hql="from User where username=? and password=? and state=1";
		List<User> uu=this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword());
		if(uu.size()>0&&uu!=null){
			return uu.get(0);
		}
		else
			return null;
	}

	public List<User> findByPage(int begin, int limit) {
		String hql = "from User order by uid desc";
		List<User> list =  this.getHibernateTemplate().execute(new PageHibernateCallback<User>(hql, null, begin, limit));
		if(list != null && list.size() > 0){
			return list;
		}
		return null;
	}

	public int findCount() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if(list != null && list.size() > 0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public User findByUid(Integer uid) {
		
		return this.getHibernateTemplate().get(User.class, uid);
	}

	public void delete(User user) {
		this.getHibernateTemplate().delete(user);
		
	}

}
