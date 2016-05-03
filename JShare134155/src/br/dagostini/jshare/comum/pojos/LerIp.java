package br.dagostini.jshare.comum.pojos;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.UnknownException;

public class LerIp {
	
	public LerIp(){
		InetAddress IP;
		
		try {
			IP = InetAddress.getLocalHost();
			String IPS = IP.getHostAddress();
			System.out.println("Meu IP: " + IP.getHostAddress());
		} catch (UnknownException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new LerIp();
	}

}
