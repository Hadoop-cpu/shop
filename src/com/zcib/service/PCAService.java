package com.zcib.service;

import java.util.List;
import java.util.Map;

import com.zcib.dao.PCADao;

public class PCAService {
	private PCADao pcaDao = new PCADao();

	public List<Map<String, Object>> getprovinces() {
		return pcaDao.getprovinces();
	}

	public List<Map<String, Object>> getCities(String provinceid) {
		
		return pcaDao.getCities(provinceid);
	}

	public List<Map<String, Object>> getareas(String cityid) {
		
		return pcaDao.getareas(cityid);
	}

	
	
}
