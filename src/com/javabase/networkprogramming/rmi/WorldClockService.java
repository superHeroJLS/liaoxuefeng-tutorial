package com.javabase.networkprogramming.rmi;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * �������˵�ʵ����
 * @author jls
 *
 */
public class WorldClockService implements WorldClock {

	@Override
	public LocalDateTime getLocalDateTime(String zoneId) throws RemoteException {
		return LocalDateTime.now(ZoneId.of(zoneId)).withNano(0);
	}

}
