package com.test.sku.serialization;

import java.io.Serializable;
import java.util.List;

public class ChatMsg implements Serializable  {

	String from;
	String to;			
	String msg;
	String fname;	//파일명
	String made;	//작성자
	String explain;	//설명
	long flength;
	int num;
	

	boolean upload;
	boolean list;
	boolean find;
	boolean update;
	boolean delet;
	byte[] fdata;
	List<FileInfo> flist;
	FileInfo fi;


	@Override
	public String toString() {
		String s = String.format("%s", msg);
		return s;
	}

	public ChatMsg() {
		super();
	}

	public ChatMsg(String from, String to, String msg) {
		super();
		this.from = from;
		this.to = to;
		this.msg = msg;
	}
}
