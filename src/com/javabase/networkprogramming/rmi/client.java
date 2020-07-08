package com.javabase.networkprogramming.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;

/**
 * RMI Client
 * @author jls
 *
 */
public class client {
	public static void main(String[] args) throws RemoteException, NotBoundException {
		// ��ȡ��һ��ע���������ӵ�������localhost���˿�1099:
        Registry registry = LocateRegistry.getRegistry("localhost", 1099);
        
        // ��������Ϊ"WorldClock"�ķ���ǿ��ת��ΪWorldClock�ӿ�:
        WorldClock worldClock = (WorldClock) registry.lookup("WorldClock");
        
        // �������ýӿڷ���:
        LocalDateTime now = worldClock.getLocalDateTime("Asia/Shanghai");
        // ��ӡ���ý��:
        System.out.println(now);
	}
}
