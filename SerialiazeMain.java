package com.test.sku.serialization;

import java.io.*;
import java.util.*;



public class SerialiazeMain {

	public static void main(String[] args) {
		
		//문서관리 공유 시스템
		//문설를 업로드하여 다른 이용자가 목록을 보고 골라서 파일을 다운로드할 수 있다
		//업록드, 다운로드, 검색, 수정, 삭제, 종료
		//Socket, ServerSocket
		//한 문서에 포함되는 속성: 번호, 제목, 파일명, 날짜, 작성자, 파일크기
		//List<Document> 저장할 때 한줄 한줄 객체 말고 직렬화
		//디렉토리에 포함된 파일의 리스트 추출하는 방법
		//listForFiles();
		//searchFile();
		//listSerialiaztion();
		//deserialization();
		//updateList();
		update02();
	}
	
	private static void listForFiles()
	{
		String path = "C:/test";
		File f = new File(path);
		//String[] files = f.list();	//파일명만 배열로
		File[] files = f.listFiles();	//File 오브젝트배열
		for(int i =0; i<files.length;i++) {
			if(files[i].isDirectory()) {
				System.out.println(files[i].getName()+"\t\tDir");
			}else {
				System.out.println(files[i].getName()+"\t\tFile");
			}
		}
	}
	
	private static void searchFile()
	{
		String key = "haha.txt";
		String path = "C:/test";
		
		File f = new File(path);
		File[] files = f.listFiles();
		for(int i=0;i<files.length;i++) {
			if(files[i].getName().equals(key)) {
				System.out.printf("%s\t%d", files[i].getName(), files[i].length());
			}
		}
	}
	
	private static void listSerialiaztion()
	{
		//List<String> names = List.of("강호동","이수근","정청래","Smith","Laura","홍길동");
		List<String> names = new ArrayList<>();
		names.add("강호동");
		names.add("이수근");
		names.add("정청래");
		names.add("Smith");
		names.add("Laura");
		names.add("홍길동");
		for(int i =0; i<names.size(); i++) {
			System.out.println(names.get(i));
		}
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:/test/list_names.ser"));
			oos.writeObject(names);

			oos.close();	//close 하기전에 알아서 flush를 함
			System.out.println("리스트 직열화 파일에 저장");
			
		}catch (Exception e) {
			System.err.println("파일에 저장 실패");
			e.printStackTrace();
		}
		
	}
	
	private static void deserialization()
	{
		String path = "C:/test/list_names.ser";
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			List<String> list = (List<String>)ois.readObject();
			ois.close();
			
			System.out.println("\t역직렬화 후의 리스트 내용보기");
			for(int i =0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
			
		}  catch (Exception e) {
			System.err.println("파일 읽기 실패");
			e.printStackTrace();
		}
		
		
	}
	
	
	private static void updateList() {
		
		List<String> list = deserializalize();
        
    
        // Smith -> James 로 변경하여 파일에 저장하고 다시 역직렬화하여 변경된 내용 확인    

            if (list.contains("Smith")) {
                int index = list.indexOf("Smith");
                list.set(index, "James");
                
                if(serialize(list)) {
                	System.out.println("수정성공");
            }else {
            	System.out.println("수정실패");
            }
        } deserialization();
    }

	/**직렬화 즉,overwrite
	 * 
	 * @param list
	 * @return
	 */
	private static boolean serialize(List<String> list) {
		String path = "C:/test/list_names.ser";
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(list);
            oos.close();
            return true;
        } catch (Exception e) {
            System.err.println("파일에 저장 실패");
            e.printStackTrace();
            return false;
        }
		
	}

	private static List<String> deserializalize() {
		List<String> names = new ArrayList<>();
		names.add("강호동");
		names.add("이수근");
		names.add("정청래");
		names.add("Smith");
		names.add("Laura");
		names.add("홍길동");
		return names;
	}
	
	private static void update02()
	{
		List<Emp> list = new ArrayList<>();
		Emp e1 = new Emp(11,"smith",20, 3020);
		Emp e2 = new Emp(12,"james",10, 3300);
		Emp e3 = new Emp(13,"scott",30, 3800);		
		
		list.add(e1);
		list.add(e2);
		list.add(e3);
		
		//James의 sal(급여) 변경하기
		Emp key = new Emp(12);
		if(list.contains(key)) {
			int idx = list.indexOf(key);
			Emp found = list.get(idx);
			found.setSal(found.getSal()+200);
		}
		
		//scott의 부서를 50번으로 변경
		Emp key2 = new Emp(13);
		if(list.contains(key2)) {
			int idx = list.indexOf(key2);
			Emp found2 = list.get(idx);
			found2.setDeptno(found2.getDeptno()+20);
		}
		//emp_list.ser 파일에 직열화해보세요
		String path = "C:/test/emp_list.ser";
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(path));
			oos.writeObject(list);
			oos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//역직렬화
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			list = (List<Emp>)ois.readObject();
			ois.close();
			
			System.out.println("\t역직렬화 후의 리스트 내용보기");
			for(int i =0; i<list.size(); i++) {
				System.out.println(list.get(i));
			}
			
		}  catch (Exception e) {
			System.err.println("파일 읽기 실패");
			e.printStackTrace();
		}
		
		//파일 삭제
		File f = new File("C:/test/haha.txt");
		if(f.exists()) {
			boolean deleted = f.delete();
			if(deleted) {
				System.out.println("파일 삭제 성공");
			}
		}
		
		/*클레스에다가 파일명 업로드emp 꼭 직렬화
         *이용자가 파일을 올리면
         *서버에서 파일이 복사되고 저장
         *그 파일 정보도 직렬화 되서 리스트가 된디ㅏ 더 커져야함  
		 */
		
		//DocClient, DocServer
		/*1. DocServer 
		 * 		+"서버대기중..."
		 * 		+무한대기(accept())
		 * 		+"클라이언트 접속됨"
		 * 		+UserWorkThread.start();
		 *2. DocClient
		 *		+서버에 접속 new Socket
		 *		+클라이언트 종료
		 *3. 업로드(a), 목록(s), 검색(f), 수정(u),삭제(d), 종료(x) ->서버에서 보여준다
		 *		+업로드(a)
		 *		 -파일명: sample.jpg
		 *		 -파일 메모리에 로드(byte[]),파일명(fname)			-->ChatMsg
		 *		 -서버에 전송(upload=true,fname="sample.jpg",fdata=filedata)	
		 *		 -로그인 안해
		 *4. 파일 업로드 성공시마다 속성들을 파일에 저장한다
		 *		+번호,파일명,작성자,파일크기,날짜,설명 번호는 +1 마지막 번호 +1
		 *		+FileInfo 클레스
		 * 		+List<FileInfo>구조로 파일에 저장(직렬화)
		 * 		+파일명: list_fileinfo.ser
		 * 
		 * 5. if(cm.upload){
		 * 		//파일수신/서버시스템에 저장
		 * 		}else if(cm.list){
		 * 		//list_fileinfo.ser을 로드하여 fileList 변수에 할당
		 *      }else if(cm.find){
		 *      //클라이언트가 검색하려는 경우
		 *      }
		 * 6. 서버측 쓰레드(UserWorkThread)에서 메뉴를 클라이언트에게 보여주기
		 * 		+cm.menu = "업로드(a), 목록(s),검색
		 * 7. 이용화가 파일 업로드하는 경우(Client.java)
		 * 	   //서버에서 보여준 메뉴
		 * 		"업로드(a), 목록(s), 검색(f), 수정(u),삭제(d), 종료(x)"
		 * 		String m = kbd.nextLine().trim();
		 * 		if(m.equals("a"){	//파일 업로드
		 * 		파일명 : sample.jpg
		 *      설명 : 홍길동의 증명사진
		 *      작성자 : 홍길동
		 *      ChatMsg cm =new ChatMsg();
		 *      cm.upload = true;
		 *      cm.fname = sample.jpg;
		 *      cm.fdata = fio.load("sample.jpg");
		 *      cm.uid = 홍길동
		 *      cm.desc = 홍길동의 증명사진
		 *      oos.writeObject(cm);
		 *      oos.flush();
		 */
        
	}

}
