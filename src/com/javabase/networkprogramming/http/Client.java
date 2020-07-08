package com.javabase.networkprogramming.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Javaʵ�ּ򵥵�HTTP Client
 * @author jls
 *
 */
public class Client {
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://www.baidu.com");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setUseCaches(false);
		conn.setConnectTimeout(5000); // ����ʱ5��
		
		// ����HTTPͷ:
		conn.setRequestProperty("Host", "www.baidu.com");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36");
		
		// ���Ӳ�����HTTP����:
		conn.connect();
		
		// �ж�HTTP��Ӧ�Ƿ�200:
		if (conn.getResponseCode() != 200) {
		    throw new RuntimeException("bad response");
		}		
		
		// ��ȡ������ӦHeader:
		Map<String, List<String>> map = conn.getHeaderFields();
		for (String key : map.keySet()) {
		    System.err.println(key + ": " + map.get(key));
		}
		
		// ��ȡ��Ӧ����:
		InputStream input = conn.getInputStream();
		byte[] bs = new byte[4096];
		input.read(bs);
		Thread.sleep(10);
		System.out.println(new String(bs));
		
	}
}
