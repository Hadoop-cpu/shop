package com.zcib.dao;

import java.util.List;
import java.util.Map;

import com.zcib.utils.JDBCUtils;

public class PCADao {

	public List<Map<String, Object>> getprovinces() {
		String sql = "select * from provinces";
		return JDBCUtils.select(sql);
	}

	public List<Map<String, Object>> getCities(String provinceid) {
		String sql = "select * from cities where provinceid=?";
		return JDBCUtils.select(sql, provinceid);
	}

	public List<Map<String, Object>> getareas(String cityid) {
		String sql = "select * from areas where cityid=?";
		return JDBCUtils.select(sql, cityid);
	}

}
