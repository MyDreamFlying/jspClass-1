package kr.or.ddit.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * 
 *	스트림 : 연속성을 가진 일련의 데이터의 흐름이면서 데이터가 전송되는 (단방향) 통로
 *
 *	스트림의 종류
 *	1. 전송 데이터 크기
 *		1) byte stream(1 byte) : XXXInputStream / XXXOutputStream
 *			FileInputStream / FileOutputStream
 *			SocketInputStream / SocketOutputStream
 *			ByteInputStream / ByteOutputStream
 *		2) character stream(1 char) : XXXReader / XXXWriter
 *			FileReader / FileWriter
 *			StringReader / StringWriter
 *	2. stream 생성 방법
 *		1) 1차 stream(단일형 스트림) : 매체를 대상으로 직접 생성(개방)하는 스트림 
 *		2) 2차 stream(연결형 스트림) : 1차 스트림을 대상으로 보조형 스트림
 *			- buffered stream : BufferedReader
 *			- filtered stream : DataInputStream
 *			- object stream : ObjectInputStream
 *				(only Serializable 객체를 대상으로 직렬화 / 역직렬화)
 *			직렬화(Serialization) : 객체를 전송하거나 기록하기 위해 바이트 배열의 형태로 변환하는 작업.
 *			역직렬화(DeSerialization) : 기록되어있거나 전송된 바이트 배열로부터 원래 객체의 상태로 복원하는 작업.
 *
 *		** 매체로부터 데이터를 읽어들이는 단계
 *		1. 매체를 어플리케이션 내에서 핸들링 할 수 있는 객체로 생성
 *			new File(file system path), new ServerSocket(port)
 *		2. 읽어들이기 위한 스트림 생성
 *			new FileInputStream(file), socket.getInputStream()
 *			new InputStreamReader
 *			new BufferedReader()
 *		3. stream end 까지 읽기 반복( EOF : -1, null)
 *		4. 자원 release : close
 *
 */
public class StreamDesc {
	public static void main(String[] args) throws IOException{
    	String folderPATH = "d:/contents";
    	String isMac = System.getProperty("os.name").substring(0, 3).toLowerCase();
    	if("mac".equals(isMac)) {
    		folderPATH = System.getProperty("user.home")+"/Documents/GitHub/jspClass/contents";
    	}
    	File folder = new File(folderPATH);
    	
		String path = "https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";
		File writeFile = new File(folder, "newImage.png");
		URL url = new URL(path);
		
		URLConnection conn = url.openConnection();
		
		try(
			InputStream is = conn.getInputStream();
			FileOutputStream fos = new FileOutputStream(writeFile);
		){
			byte[] buffer = new byte[1024];
			int cnt = -1;
			while((cnt = is.read(buffer)) != -1) {
				fos.write(buffer, 0, cnt);
			}
		}
		
		
	}
	
	private static void readFileSystemResource() {
		
    	String folderPATH = "d:/contents";
    	String isMac = System.getProperty("os.name").substring(0, 3).toLowerCase();
    	if("mac".equals(isMac)) {
    		folderPATH = System.getProperty("user.home")+"/Documents/GitHub/jspClass/contents";
    	}
    	
    	File folder = new File(folderPATH);
    	File readFile = new File(folder,"오래된 노래_utf8.txt" );
    	String temp = null;
    	try(
			FileInputStream fis = new FileInputStream(readFile);
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			BufferedReader br = new BufferedReader(isr);
    	
    	){
    		while((temp=br.readLine()) != null ) {
    			System.out.println(temp);
    		};
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
	}
	
	private static void readClassPathResource() {
		String qualifiedName = "another_day.txt";
//    	ClassLoader loader = Thread.currentThread().getContextClassLoader();
//    	URL url = loader.getResource(qualifiedName);
//		URL url = StreamDesc.class.getResource(qualifiedName);
		String temp = null;
    	try(
			InputStream is = StreamDesc.class.getResourceAsStream(qualifiedName);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
    	){
    		while((temp=br.readLine()) != null ) {
    			System.out.println(temp);
    		};
    		
    	}catch(IOException e) {
    		e.printStackTrace();
    	}
	}
}
