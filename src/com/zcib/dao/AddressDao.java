package com.zcib.dao;

import java.util.List;
import java.util.Map;

import com.zcib.domain.Address;
import com.zcib.utils.JDBCUtils;

public class AddressDao {

	public void add(Address address) {
		String sql = "insert into address values(null,?,?,?,?,?,?,?,?)";
		Object params[]={
				address.getAddressname(),
				address.getPostcode(),
				address.getReceiver(),
				address.getPhone(),
				address.getVipid(),
				address.getProvince(),
				address.getCity(),
				address.getArea()
		};
		Number n = (Number)JDBCUtils.insert(sql, params);
		address.setAddressid(n.intValue());
	}

	public List<Map<String, Object>> findAll(int vipid) {
		String sql = "select * from address where vipid=?";
		return JDBCUtils.select(sql, vipid);
	}

	public void delete(int id) {
		String sql = "delete  from address where addressid=?";
		JDBCUtils.update(sql, id);
		
	}

	public Address findById(int id) {
		String sql = "select * from address where addressid=?";
		return JDBCUtils.selectToBean(Address.class, sql, id);
		
	}

	public void update(Address address) {
		String sql = "update address set addressname=?,postcode=?,receiver=?,phone=?,province=?,city=?,area=? where addressid=?";
		Object params[]={
				address.getAddressname(),
				address.getPostcode(),
				address.getReceiver(),
				address.getPhone(),
				address.getProvince(),
				address.getCity(),
				address.getArea(),
				address.getAddressid()
		};
		JDBCUtils.update(sql, params);		
	}

}
