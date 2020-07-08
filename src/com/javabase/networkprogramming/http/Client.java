package com.javabase.networkprogramming.http;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Java实现简单的HTTP Client
 * @author jls
 *
 */
public class Client {
	public static void main(String[] args) throws Exception {
		URL url = new URL("https://www.baidu.com");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setUseCaches(false);
		conn.setConnectTimeout(5000); // 请求超时5秒
		
		// 设置HTTP头:
		conn.setRequestProperty("Host", "www.baidu.com");
		conn.setRequestProperty("Connection", "keep-alive");
		conn.setRequestProperty("Accept", "*/*");
		conn.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.75 Safari/537.36");
		
		// 连接并发送HTTP请求:
		conn.connect();
		
		// 判断HTTP响应是否200:
		if (conn.getResponseCode() != 200) {
		    throw new RuntimeException("bad response");
		}		
		
		// 获取所有响应Header:
		Map<String, List<String>> map = conn.getHeaderFields();
		for (String key : map.keySet()) {
		    System.err.println(key + ": " + map.get(key));
		}
		
		// 获取响应内容:
		InputStream input = conn.getInputStream();
		byte[] bs = new byte[4096];
		input.read(bs);
		Thread.sleep(10);
		System.out.println(new String(bs));
		
	}
}
