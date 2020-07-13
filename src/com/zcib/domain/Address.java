package com.zcib.domain;

public class Address {
	private int addressid;
	private String addressname;
	private String postcode;
	private String receiver;
	private String phone;
	private int vipid;
	private String province;
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	private String city;
	private String area;
	public int getAddressid() {
		return addressid;
	}
	public void setAddressid(int addressid) {
		this.addressid = addressid;
	}
	public String getAddressname() {
		return addressname;
	}
	public void setAddressname(String addressname) {
		this.addressname = addressname;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getVipid() {
		return vipid;
	}
	public void setVipid(int vipid) {
		this.vipid = vipid;
	}
	@Override
	public String toString() {
		return "Address [addressid=" + addressid + ", addressname="
				+ addressname + ", postcode=" + postcode + ", receiver="
				+ receiver + ", phone=" + phone + ", vipid=" + vipid
				+ ", province=" + province + ", city=" + city + ", area="
				+ area + "]";
	}
	
}
