package com.javabase.networkprogramming.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * <p>Java编写的UDP Client</p>
 * @author jls
 *
 */
public class Client {
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket();// 客户端创建DatagramSocket实例时并不需要指定端口，而是由操作系统自动指定一个当前未使用的端口
		ds.setSoTimeout(1000);// 后续接收UDP包时，等待时间最多不会超过1秒，否则在没有收到UDP包时，客户端会无限等待下去
		ds.connect(InetAddress.getByName("localhost"), 6666); // 这个connect()方法不是真连接，它是为了在客户端的DatagramSocket实例中保存服务器端的IP和Port，确保这个DatagramSocket实例只能往指定的IP和Port发送UDP包
		
		// 发送:
		String msg = "Hello!";
		byte[] data = msg.getBytes(StandardCharsets.UTF_8);
		DatagramPacket packet = new DatagramPacket(data, data.length);
		ds.send(packet);
		System.out.println(">>> " + msg);
		
		// 接收:
		byte[] buffer = new byte[1024];
		packet = new DatagramPacket(buffer, buffer.length);
		ds.receive(packet);
		String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
		System.out.println("<<< " + resp);
		/*
		 * 客户端认为通信结束，就可以调用disconnect()断开连接；disconnect()也不是真正地断开连接，
		 * 它只是清除了客户端DatagramSocket实例记录的远程服务器地址和端口号，这样，DatagramSocket实例就可以连接另一个服务器端
		 */
		ds.disconnect();
	}
}
