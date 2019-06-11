package com.qihang.shop.order.action;

import java.io.IOException;
import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.qihang.shop.cart.vo.Cart;
import com.qihang.shop.cart.vo.CartItem;
import com.qihang.shop.order.service.OrderService;
import com.qihang.shop.order.vo.Order;
import com.qihang.shop.order.vo.OrderItem;
import com.qihang.shop.user.vo.User;
import com.qihang.shop.utils.PageBean;
import com.qihang.shop.utils.PaymentUtil;

public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	private Order order=new Order();
	private OrderService orderService;
	
	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order getModel() {
		return order;
	}
	
	private String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	
	public String p1_MerId;
	public String r0_Cmd; 
	public String r1_Code;
	public String r2_TrxId;
	public String r3_Amt;
	public String r4_Cur; 
	public String r5_Pid;
	public String r6_Order;
	public String r7_Uid;
	public String r8_MP;
	public String r9_BType; 
	public String rb_BankId; 
	public String ro_BankOrderId; 
	public String rp_PayDate; 
	public String rq_CardNo; 
	public String ru_Trxtime;
	public String hmac;
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}

	public void setP1_MerId(String p1_MerId) {
		this.p1_MerId = p1_MerId;
	}

	public void setR0_Cmd(String r0_Cmd) {
		this.r0_Cmd = r0_Cmd;
	}

	public void setR1_Code(String r1_Code) {
		this.r1_Code = r1_Code;
	}

	public void setR2_TrxId(String r2_TrxId) {
		this.r2_TrxId = r2_TrxId;
	}

	public void setR4_Cur(String r4_Cur) {
		this.r4_Cur = r4_Cur;
	}

	public void setR5_Pid(String r5_Pid) {
		this.r5_Pid = r5_Pid;
	}

	public void setR7_Uid(String r7_Uid) {
		this.r7_Uid = r7_Uid;
	}

	public void setR8_MP(String r8_MP) {
		this.r8_MP = r8_MP;
	}

	public void setR9_BType(String r9_BType) {
		this.r9_BType = r9_BType;
	}

	public void setRb_BankId(String rb_BankId) {
		this.rb_BankId = rb_BankId;
	}

	public void setRo_BankOrderId(String ro_BankOrderId) {
		this.ro_BankOrderId = ro_BankOrderId;
	}

	public void setRp_PayDate(String rp_PayDate) {
		this.rp_PayDate = rp_PayDate;
	}

	public void setRq_CardNo(String rq_CardNo) {
		this.rq_CardNo = rq_CardNo;
	}

	public void setRu_Trxtime(String ru_Trxtime) {
		this.ru_Trxtime = ru_Trxtime;
	}

	public void setR3_Amt(String r3_Amt) {
		this.r3_Amt = r3_Amt;
	}

	public void setR6_Order(String r6_Order) {
		this.r6_Order = r6_Order;
	}

	public String save(){
		
		Cart cart=(Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			this.addActionError("亲!您还没有购物!");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		order.setState(1);
		order.setOrdertime(new Date());
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(user==null){
			this.addActionError("亲!您还没有登录!");
			return "login";
		}
		order.setUser(user);
		for (CartItem cartItem : cart.getCartItems()) {
			// 设置订单项集合:
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		orderService.save(order);
		cart.clearCart();
		return "savesuccess";
	}
	public String findByUid(){
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByUid(user.getUid(), page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUid";
	}
	public String findByOid(){
		order=orderService.findByOid(order.getOid());
		return "findByOid";
	}
	public String payOrder() throws IOException{
		Order currOrder=orderService.findByOid(order.getOid());
		currOrder.setAddress(order.getAddress());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		// 付款需要的参数:
				String p0_Cmd = "Buy"; // 业务类型:
				String p1_MerId = "10001126856";// 商户编号:
				String p2_Order = order.getOid().toString();// 订单编号:
				String p3_Amt = "0.01";// 付款金额:
				String p4_Cur = "CNY"; // 交易币种:
				String p5_Pid = "";  // 商品名称:
				String p6_Pcat = ""; // 商品种类:
				String p7_Pdesc = ""; // 商品描述:
				String p8_Url = "http://localhost:8080/Shop/order_callBack.action"; // 商户接收支付成功数据的地址
				String p9_SAF = ""; // 送货地址:
				String pa_MP = ""; // 商户扩展信息:
				String pd_FrpId = this.pd_FrpId;// 支付通道编码:
				String pr_NeedResponse = "1";  // 应答机制:
				String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // ��Կ
				String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
						p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
						pd_FrpId, pr_NeedResponse, keyValue); // hmac
				// 向易宝发送请求:
				StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
				sb.append("p0_Cmd=").append(p0_Cmd).append("&");
				sb.append("p1_MerId=").append(p1_MerId).append("&");
				sb.append("p2_Order=").append(p2_Order).append("&");
				sb.append("p3_Amt=").append(p3_Amt).append("&");
				sb.append("p4_Cur=").append(p4_Cur).append("&");
				sb.append("p5_Pid=").append(p5_Pid).append("&");
				sb.append("p6_Pcat=").append(p6_Pcat).append("&");
				sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
				sb.append("p8_Url=").append(p8_Url).append("&");
				sb.append("p9_SAF=").append(p9_SAF).append("&");
				sb.append("pa_MP=").append(pa_MP).append("&");
				sb.append("pd_FrpId=").append(pd_FrpId).append("&");
				sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
				sb.append("hmac=").append(hmac);
				
				// 重定向:向易宝出发:
				ServletActionContext.getResponse().sendRedirect(sb.toString());
				return NONE;
	}
	public String callBack(){
		// 利用本地密钥和加密算法 加密数据
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 有效
			if (r9_BType.equals("1")) {
				// 浏览器重定向
				Order currOrder = orderService.findByOid(Integer.parseInt(r6_Order));
				// 修改订单的状态:
				currOrder.setState(2);
				orderService.update(currOrder);
				this.addActionMessage("支付成功！订单号： "+r6_Order +"金额： "+r3_Amt);
				
			} 

		} else {
			throw new RuntimeException("数据被篡改！");
		}
		return "msg";
	}
	public String updateState(){
		order=orderService.findByOid(order.getOid());
		order.setState(4);
		orderService.update(order);
		return "updateStateSuccess";
	}

	
}
