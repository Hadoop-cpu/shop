package com.zcib.service;

import java.util.List;
import java.util.Map;

import com.zcib.dao.AddressDao;
import com.zcib.domain.Address;

public class AddressService {
	private AddressDao addressDao = new AddressDao();

	public void add(Address address) {
		addressDao.add(address);
		
	}

	public List<Map<String, Object>> findAll(int vipid) {
		
		return addressDao.findAll(vipid);
	}

	public void delete(int id) {
		addressDao.delete(id);
		
	}

	public Address findById(int id) {
		
		return addressDao.findById(id);
	}

	public void update(Address address) {
		addressDao.update(address);
		
	}

}
