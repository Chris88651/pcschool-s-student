package com.study.springcore.jdbc.template;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDao {
	@Autowired
	private JdbcTemplate  jdbcTemplate;
	
	// 單筆寫入
	public boolean newqueryTime(String td){
		String sql = "insert into log (querytime) values ('" + td + "')";
		jdbcTemplate.update(sql);
		return true;
	}
	
	// 多筆查詢
	public List<Map<String, Object>> queryAll(){
		String sql = "select eid, querytime from log";
		List<Map<String, Object>> logs = jdbcTemplate.queryForList(sql);
		return logs;
	}
}
