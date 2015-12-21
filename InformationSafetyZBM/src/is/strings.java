package is;

import java.util.HashMap;

public final class strings {
	
	public final static class Menu{
		public final static String FILE_NAME = "click to choose";
	}
	
	public final static class MenuItem{
		public final static String FILE_OPEN_NAME = "open file";
	}
	
	public final static class Title{
		public final static String JFRAME_VIEW_TITLE = "Information Safety Homework1";
	}
	
	
	
	public final static class Button{
		public final static String EXTRACTTCP = "extract TCP .pcap";
		public final static String EXTRACTUDP = "extract UDP .pcap";
	}
	
	public final static String dir = "PcapFiles";
	public final static String label = "Ôø²©Ãú M201576117";
}

final class FrameSize {
	public static final int FRAMEWIDHT = 400;
	public static final int FRAMEHEIGHT = 150;
}

class Flags {
	public static final int TCP_SYN = 2;
	public static final int TCP_SYNACK = 18;
	public static final int TCP_ACK = 16;
	public static final int TCP_FINACK = 17;
}

class TypeActivity {
	public static final HashMap<Integer, String> typeMap1 = new HashMap<Integer, String>(){
		{
			put(2054, "ARP");
			put(32821, "RARP");
			put(2048, "IP");
			put(34525, "IPv6");
			put(6, "TCP");
			put(17, "UDP");
			put(1, "ICMP");
			put(58, "ICMPv6");
		}
	};
	
	public static final HashMap<String, Integer> typeMap2 = new HashMap<String, Integer>(){
		{
			put("ARP", 2054);
			put("RARP",32821);
			put("IP",2048);
			put("IPv6",34525);
			put("TCP",6);
			put("UDP",17);
			put("ICMP",1);
			put("ICMPv6",58);
		}
	};
	public static final int ARP = 2054;
	public static final int RARP = 32821;
	public static final int IP = 2048;
	public static final int IPv6 = 34525;
	public static final int TCP = 6;
	public static final int UDP = 17;
	public static final int ICMP = 1;
	public static final int ICMPv6 = 58;
	
	public static final HashMap<Integer, String> portMap1 = new HashMap<Integer, String>(){
		{
			put(21, "FTP");
			put(22, "SSH");
			put(23, "TELNET");
			put(25, "SMTP");
			put(53, "DNS");
			put(69, "TFTP");
			put(80, "HTTP");
			put(110, "POP3");
			put(161, "SNMP");
			put(443, "HTTPS");
		}
	};
	
	public static final HashMap<String, Integer> portMap2 = new HashMap<String, Integer>(){
		{
			put("FTP", 21);
			put("SSH",22 );
			put("TELNET", 23);
			put("SMTP", 25);
			put("DNS", 53);
			put("TFTP", 69);
			put("HTTP", 80);
			put("POP3", 110);
			put("SNMP", 161);
			put("HTTPS", 443);
		}
	};
	
}
