package com.test.sku.serialization;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileInfo implements Serializable {
	
	int num;
	String fname;
	String made;
	Date date;
	String explain;
	long flength;
	
	public FileInfo() {
		super();
	}
	
	public FileInfo(int num) {
		super();
		this.num = num;
	}


	public FileInfo(int num, String fname, String made,long flength, Date date, String explain) {
		super();
		this.num = num;
		this.fname = fname;
		this.made = made;
		this.date = date;
		this.explain = explain;
		this.flength = flength;
		
	}
		
	@Override
	public boolean equals(Object obj) {
		FileInfo other = (FileInfo) obj;
		return other.getNum() == this.getNum();
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String sdate = sdf.format(date);
		String s = String.format("%d\t%s\t%s\t%d\t%s\t%s", num, fname, made, flength, sdate, explain);	
		return s;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMade() {
		return made;
	}
	public void setMade(String made) {
		this.made = made;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getExplain() {
		return explain;
	}
	public void setExplain(String explain) {
		this.explain = explain;
	}

	public long getFlength() {
		return flength;
	}

	public void setFlength(long flength) {
		this.flength = flength;
	}

	
}
