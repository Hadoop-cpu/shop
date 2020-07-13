package com.zcib.domain;

import java.util.Date;
import java.util.List;

public class Order {
	private String orderid;//订单编号
	private Date ordertime;//下单时间
	private int status;//订单状态
	private Address address;//订单发货地址
	private float totalprice;//总金额
	private String content;//备注
	private User user;//购买者
	private List<OrderItem> orderItems;//订单条目列表
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public float getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", ordertime=" + ordertime
				+ ", status=" + status + ", address=" + address
				+ ", totalprice=" + totalprice + ", content=" + content
				+ ", user=" + user + ", orderItems=" + orderItems + "]";
	}
	
	
	

}
