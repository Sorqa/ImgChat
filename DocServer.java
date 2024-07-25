package com.test.sku.serialization;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;



public class DocServer {
	
	public static void main(String[] args) {
		try {
			ServerSocket ss = new ServerSocket(1234);
			while(true) {
				System.out.println("서버대기중...");
				Socket s = ss.accept();
				System.out.println("클라이언트 접속됨");
				
				new UserWorkThread(s).start();
				

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("서버 종료");
		
		
	
		
	}
}
