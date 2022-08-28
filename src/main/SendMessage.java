package main;

import org.smslib.GatewayException;
import org.smslib.Library;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

import java.io.IOException;

import org.apache.log4j.Logger;

public class SendMessage {
	static Logger log = Logger.getLogger(SendMessage.class.getName());
	
	public static void main (String args[])
	{
		System.out.println("send message program start");
		SMSSend smsSend = new SMSSend();
		smsSend.config(log);
	}
}

class SMSSend {
	private String PORT = "COM3"; // Modem Port
	private int bitRate = 57600; // Serial bit rate
	private String modemName = "ZTE"; // Modem Brand Name
	private String modemModel = "E60"; // Modem Model
	private String modemPin = "0000"; // Pin code if any assigned to modem
	
	public void config(Logger log) {
		System.out.println("config");
		
		try {
			SerialModemGateway gateway = new SerialModemGateway("modem.com1", PORT, bitRate, null, null);
			log.debug("Gateway is configured successfully");
			gateway.setInbound(true);
			gateway.setOutbound(true);
//			gateway.setSimPin("0000");
			log.debug("before add gateway");
			
			Service.getInstance().addGateway(gateway);
			Service.getInstance().startService();
			
			System.out.println("Moderm Information");
			System.out.println(gateway.getManufacturer());
		} catch (Exception e) {
			System.out.println("Error in modem information : " + e.toString());
			log.debug("Error in modem config" + e.toString());
		}
	}
}

