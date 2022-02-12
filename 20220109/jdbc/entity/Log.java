package com.study.springcore.jdbc.entity;

public class Log {
	private Integer eid;
	private String querytime;
	
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getQuerytime() {
		return querytime;
	}
	public void setQuerytime(String querytime) {
		this.querytime = querytime;
	}
	@Override
	public String toString() {
		return "Log [eid=" + eid + ", querytime=" + querytime + "]";
	}
}
