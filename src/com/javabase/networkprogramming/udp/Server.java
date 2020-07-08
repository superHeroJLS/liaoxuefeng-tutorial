package com.javabase.networkprogramming.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

/**
 * <p>Java��д��UDP Server</p>
 * @author jls
 *
 */
public class Server {
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket(6666); // ����ָ���˿�
        System.out.println("server is running...");
		
		for (;;) { // ����ѭ��������Client����
		    // ���ݻ�����:
		    byte[] buffer = new byte[1024];
		    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		    ds.receive(packet); // ��ȡһ��UDP���ݰ�
		    
		    // ��ȡ�������ݴ洢��buffer�У���packet.getOffset(), packet.getLength()ָ����ʼλ�úͳ���
		    // ���䰴UTF-8����ת��ΪString:
		    String s = new String(packet.getData(), packet.getOffset(), packet.getLength(), StandardCharsets.UTF_8);
		    
		    // ��������:
		    byte[] data = "ACK".getBytes(StandardCharsets.UTF_8);
		    packet.setData(data);
		    ds.send(packet);
		}
	}
}
