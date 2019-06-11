package com.qihang.shop.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.qihang.shop.adminuser.vo.AdminUser;

public class PrivilegeInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// 判断是否登录,如果登录,放行,没有登录,跳转到登录页面.
				AdminUser adminUser = (AdminUser) ServletActionContext.getRequest()
						.getSession().getAttribute("existAdminUser");
				if(adminUser != null){
					// 已经登录过
					return actionInvocation.invoke();
				}else{
					// 跳转到登录页面:
					ActionSupport support = (ActionSupport) actionInvocation.getAction();
					support.addActionError("您还没有登录!请先去登录!");
					return support.LOGIN;

				}
	}
	

}
//<!-- 配置自定义拦截器 -->

//<interceptors>  
//    <interceptor name="privilegeInterceptor"  
//        class="com.qihang.shop.interceptor.PrivilegeInterceptor">  
//    </interceptor>  
//    <!-- 拦截器栈 -->  
//    <interceptor-stack name="mydefault">  
//        <interceptor-ref name="defaultStack" />  
//        <interceptor-ref name="privilegeInterceptor" />  
//    </interceptor-stack>  
//</interceptors>  