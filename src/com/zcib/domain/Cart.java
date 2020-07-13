package com.zcib.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> map = new LinkedHashMap<String,CartItem>();
	private double carttotal;
	public Map<String, CartItem> getMap() {
		return map;
	}

	public Collection<CartItem> getCartItems(){
		return map.values();
	}

	public double getCarttotal() {
		return carttotal;
	}
	
	//根据id删除购物车中商品
	public void deleteById(int productid){
		map.remove(productid+"");
	}

	
	//添加购物车
	public void addCart(CartItem cartItem){
		//如果购物车中没有该商品，则添加该商品
		int productid = cartItem.getProduct().getProductid();
		if(map.containsKey(productid+"")){
			//如果购物车中有该商品，则增加该商品数量
			CartItem cartItem1 = map.get(productid+"");
			System.out.println("cartitem1:"+cartItem1);
			System.out.println("cartitem:"+cartItem);
			int newcount = cartItem1.getCount()+cartItem.getCount();
			cartItem1.setCount(newcount);
			
		}else{
			map.put(productid+"", cartItem);
		}
		carttotal += cartItem.getTotal();
		
	}
	
	//更新购物车中给定商品id的购买数量
	public void updatebuycount(int productid,int buycount){
		//该商品是否在购物车中
		if(map.containsKey(productid+"")){
			CartItem cartItem = map.get(productid+"");//获取购物车中该商品
			cartItem.setCount(buycount);//更新其购买数量
		}
	}
	
	
	
	

	@Override
	public String toString() {
		return "Cart [map=" + map + ", carttotal=" + carttotal + "]";
	}
	//清空购物车
	public void clear() {
		map.clear();
	}

	//通过id批量删除
	public void delete(String[] ids) {
		for(String id :ids){
			map.remove(id);
		}
	}

	public List<CartItem> getCartItems(String[] ids) {
		List<CartItem> list = new ArrayList<CartItem>();
		for(String id :ids){
			if(map.containsKey(id)){
				CartItem cartItem = map.get(id);
				list.add(cartItem);
			}
		}
		return list;
	}
	
	//获取选中商品的总价
	public float getCartItemsTotal(String[] ids) {
		float total = 0;
		for(String id :ids){
			if(map.containsKey(id)){
				CartItem cartItem = map.get(id);
				total += cartItem.getTotal();
			}
		}
		return total;
	}

	//删除已购买的商品
	public void delete(List<CartItem> cartlist) {
		for(int i=0;i<cartlist.size();i++){
			//循环已购买商品
			CartItem item = cartlist.get(i);//取出第i个已购买的商品
			Product product = item.getProduct();
			int id = product.getProductid();
			if(map.containsKey(id+"")){
				//购物车中找到该商品
				map.remove(id+"");
			}
		}
		
	}
	
}
