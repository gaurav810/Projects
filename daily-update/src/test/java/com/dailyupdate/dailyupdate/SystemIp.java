package com.dailyupdate.dailyupdate;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemIp {

	public static void main(String[] args) throws UnknownHostException {
		
		InetAddress localhost = InetAddress.getLocalHost(); 
        System.out.println("System IP Address : " + 
                      (localhost.getHostAddress()).trim()); 
        
        System.out.println(InetAddress.getLocalHost().toString());
	}

}
