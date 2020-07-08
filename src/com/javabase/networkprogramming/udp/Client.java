package com.javabase.networkprogramming.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

/**
 * <p>Java��д��UDP Client</p>
 * @author jls
 *
 */
public class Client {
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket();// �ͻ��˴���DatagramSocketʵ��ʱ������Ҫָ���˿ڣ������ɲ���ϵͳ�Զ�ָ��һ����ǰδʹ�õĶ˿�
		ds.setSoTimeout(1000);// ��������UDP��ʱ���ȴ�ʱ����಻�ᳬ��1�룬������û���յ�UDP��ʱ���ͻ��˻����޵ȴ���ȥ
		ds.connect(InetAddress.getByName("localhost"), 6666); // ���connect()�������������ӣ�����Ϊ���ڿͻ��˵�DatagramSocketʵ���б���������˵�IP��Port��ȷ�����DatagramSocketʵ��ֻ����ָ����IP��Port����UDP��
		
		// ����:
		String msg = "Hello!";
		byte[] data = msg.getBytes(StandardCharsets.UTF_8);
		DatagramPacket packet = new DatagramPacket(data, data.length);
		ds.send(packet);
		System.out.println(">>> " + msg);
		
		// ����:
		byte[] buffer = new byte[1024];
		packet = new DatagramPacket(buffer, buffer.length);
		ds.receive(packet);
		String resp = new String(packet.getData(), packet.getOffset(), packet.getLength());
		System.out.println("<<< " + resp);
		/*
		 * �ͻ�����Ϊͨ�Ž������Ϳ��Ե���disconnect()�Ͽ����ӣ�disconnect()Ҳ���������ضϿ����ӣ�
		 * ��ֻ������˿ͻ���DatagramSocketʵ����¼��Զ�̷�������ַ�Ͷ˿ںţ�������DatagramSocketʵ���Ϳ���������һ����������
		 */
		ds.disconnect();
	}
}
