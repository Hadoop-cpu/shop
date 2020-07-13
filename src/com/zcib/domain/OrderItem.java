package com.zcib.domain;

public class OrderItem {
	private int id;//订单条目编号
	private Product product;//购买的商品
	private int buycount;//购买的数量
	private float total;//购买该商品花的总金额
	private Order order;//所属订单
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getBuycount() {
		return buycount;
	}
	public void setBuycount(int buycount) {
		this.buycount = buycount;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", product=" + product + ", buycount="
				+ buycount + ", total=" + total + ", orderid=" + order.getOrderid() + "]";
	}

}
