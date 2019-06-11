package com.qihang.shop.adminuser.service;

import com.qihang.shop.adminuser.dao.AdminUserDao;
import com.qihang.shop.adminuser.vo.AdminUser;

public class AdminUserService {
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	
}
