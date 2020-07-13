package com.zcib.domain;

public class CartItem {
	private Product product;
	private int count;
	private float total;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getTotal() {
		return product.getPrice()*count;
	}
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", count=" + count + ", total="
				+ total + "]";
	}
	
	
}
