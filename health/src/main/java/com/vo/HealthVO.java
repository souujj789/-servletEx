package com.vo;

import java.util.Date;

public class HealthVO {
	//테이블의 속성을 참고해서 변수를 선언
	int id;
	Date hdate;
	int hweight;
	int hheight;
	int hblood;
	int eid;
	
	//getter와 setter 생성
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getHdate() {
		return hdate;
	}
	public void setHdate(Date hdate) {
		this.hdate = hdate;
	}
	public int getHweight() {
		return hweight;
	}
	public void setHweight(int hweight) {
		this.hweight = hweight;
	}
	public int getHheight() {
		return hheight;
	}
	public void setHheight(int hheight) {
		this.hheight = hheight;
	}
	public int getHblood() {
		return hblood;
	}
	public void setHblood(int hblood) {
		this.hblood = hblood;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	
	
}
