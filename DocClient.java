package com.test.sku.serialization;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import java.net.*;
import java.io.*;
import java.util.*;

public class DocClient {
	static Scanner kbd = new Scanner(System.in);
	
    public static void main(String[] args) {
		try {
			Socket s = new Socket("localhost",1234);
			System.out.println("클라이언트 입니다");
						
			InputStream in = s.getInputStream();
			ObjectInputStream oin = new ObjectInputStream(in);
			
			OutputStream out = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(out); 
			
			boolean go = true;
			while(go) {
			ChatMsg cm = (ChatMsg)oin.readObject();
			System.out.println(cm); 				//업로드(a),목록(s),검색(f), 수정(u),삭제(d),종료(x)
			
            
			String m = kbd.nextLine().trim();
			
			switch(m) {
			     case"a":
			    	 System.out.println("파일명 작성자 설명:");
			    	 String input = kbd.nextLine();
			    	 String[] token = input.split("\\s+");
			    	 
			    	 if(!token[0].equals("") && token[0]!=null) {
						  boolean ok = FileIO.OkObject(token[0]);
						  if(ok = true) {
								System.out.println("존재합니다");	//성공
								byte[] bt = FileIO.upload(token[0]);
								long len = FileIO.size(token[0]);
								ChatMsg cm2 = new ChatMsg();
								cm2.fname = token[0];
								cm2.made = token[1];
								cm2.explain = token[2];
								cm2.fdata = bt;
								cm2.flength =len;
								cm2.upload=true;
								
								oos.writeObject(cm2);
								oos.flush();								
								System.out.println("보냈습니다");
								break;								
							}else {
								System.out.println("사진이 존재하지 않습니다");
								break;
							}						  
					   }
			    	 break;
			     case"s":
			    	 System.out.println("\t역직렬화 후의 리스트 내용보기");
			    	 cm = new ChatMsg();
			    	 cm.list = true;
			    	 oos.writeObject(cm);
			    	 oos.flush();
			    	 
			    	 cm = (ChatMsg)oin.readObject();
			    	 System.out.println(cm.flist);
			    	 break;
			     case"f":
			    	 System.out.println("찾으실 번호:");
			    	 int num = kbd.nextInt(); kbd.nextLine();
			    	 
			    	 cm = new ChatMsg();
			    	 cm.num = num;
			    	 cm.find = true;
			    	 oos.writeObject(cm);
			    	 oos.flush();
			    	 
			    	 cm = (ChatMsg)oin.readObject();
			    	 System.out.println(cm.fi);
			    	 break;
			     case"u":	
			    	 System.out.println("수정할 번호:");
			    	 int num2 = kbd.nextInt(); kbd.nextLine();
			    	 System.out.println("설명 바꾸기");
			    	 String explain = kbd.nextLine();
			    	 
			    	 FileInfo fi = new FileInfo();
			    	 fi.setNum(num2);
			    	 fi.setExplain(explain);

			    	 cm = new ChatMsg();		    	 
			    	 cm.fi = fi;
			    	 cm.update = true;
			    	 oos.writeObject(cm);
			    	 oos.flush();
 
			    	 break;
			     case"d":
			    	 
			    	 break;
			     case"x": go = false; break;
			}
			
			

			
			/*
			System.out.println("올릴 파일명:");
			String fname = kbd.nextLine();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fname)); 
			oos.writeObject(fpath + fname);
			oos.flush();*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
