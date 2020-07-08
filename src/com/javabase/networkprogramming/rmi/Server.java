package com.javabase.networkprogramming.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * RMI Server
 * @author jls
 *
 */
public class Server {
	public static void main(String[] args) throws RemoteException {
        System.out.println("create World clock remote service...");
        // ʵ����һ��WorldClock:
        WorldClock worldClock = new WorldClockService();
        // ���˷���ת��ΪԶ�̷���ӿ�:
        WorldClock skeleton = (WorldClock) UnicastRemoteObject.exportObject(worldClock, 0);
        
        // ����һ��ע��������RMI����ע�ᵽ1099�˿�:
        Registry registry = LocateRegistry.createRegistry(1099);
        // ע��˷��񣬷�����Ϊ"WorldClock":
        registry.rebind("WorldClock", skeleton);
    }
}
