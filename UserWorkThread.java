package com.test.sku.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserWorkThread extends Thread {
	
	private Socket s;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private static List<FileInfo> list = new ArrayList<>();
	
	public static String savePath = "C:/test/download/";
	public static String path = "list_fileinfo.ser";

	public UserWorkThread(Socket s) {
		
		this.s = s;
		try {			
			 oos = new ObjectOutputStream(s.getOutputStream());
			
			 ois = new ObjectInputStream(s.getInputStream());
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		try {
			while(true) {
				
					
				ChatMsg cm = new ChatMsg("서버","클라이언트","업로드(a),목록(s),검색(f), 수정(u),삭제(d),종료(x)");
				oos.writeObject(cm);
				oos.flush();
			
				cm = (ChatMsg)ois.readObject();	
				
				if(cm.upload) {
					FileOutputStream fout = new FileOutputStream(savePath +cm.fname);
					fout.write(cm.fdata);
					fout.close();
					
					//int num, String fname, String made, Date date, String explain
					Date dd = new Date();										
					FileInfo fi = new FileInfo(list.size()+1,cm.fname,cm.made,cm.flength,dd,cm.explain);
					list.add(fi);
				
					serialiaze(list);	

				}else if(cm.list) {
					deserializalize();						
				
					cm = new ChatMsg();
					cm.flist = list;
					oos.writeObject(cm);
					oos.flush();

				}else if(cm.find) {
					FileInfo key = new FileInfo(cm.num);
					
					if(list.contains(key)) {
						int idx = list.indexOf(key);
						FileInfo fi = list.get(idx);
						deserializalize();
						
						cm= new ChatMsg();
						cm.fi = fi;
						oos.writeObject(cm);
						oos.flush();
												
						}	
					
				}else if(cm.update) {					
					if (list.contains(cm.fi)) {
												
						int idx = list.indexOf(cm.fi);
						FileInfo fi = list.get(idx);
						fi.setExplain(cm.fi.explain);					
						list.set(idx,fi);
						serialiaze(list);	
								              
		            }else {
		            	System.out.println("수정실패");
		            }
				} 
					
					
			}
				
 
			
			
			
			
			
		} catch (Exception e) {
			System.err.println("클라이언트 퇴장");
			e.printStackTrace();
		}
	    
		System.err.println("UserWorlThread 종료");
	}
	
	private static void serialiaze(List<FileInfo> list) {
	//직열화 
	ObjectOutputStream oos;
	try {
		oos = new ObjectOutputStream(new FileOutputStream(savePath + path));
		oos.writeObject(list);
		oos.close();
	} catch (Exception e) {
		
		e.printStackTrace();
	} 
	
	}
	
	private static void deserializalize() {
		//역지열화
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(savePath + path));
			
			list = (List<FileInfo>)ois.readObject();
				
			ois.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	

}
