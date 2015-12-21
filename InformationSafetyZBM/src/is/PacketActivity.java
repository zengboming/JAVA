package is;

import java.util.ArrayList;

public class PacketActivity {
	private static byte[] pcapHeader;
	private ArrayList<Pcap> pcapUnits = null; 
	private String name;
	
	public PacketActivity(String name){
		this.name = name;
		pcapUnits = new ArrayList<Pcap>(); 
	}
	public ArrayList<Pcap> getPcapUnits() {
		return pcapUnits;
	}

	public String getName() {
		return name;
	}
	public void setPcapUnits(ArrayList<Pcap> pcapUnits) {
		this.pcapUnits = pcapUnits;
	}

	public static byte[] getPcapHeader() {
		return pcapHeader;
	}

	public static void setPcapHeader(byte[] pcapHeader) {
		PacketActivity.pcapHeader = pcapHeader;
	}
}

class Activity1 {
	private ArrayList<Pcap> units = null;
	private int type;
	private String ip1;
	private String ip2;
	private int port1;
	private int port2;	

	public Activity1(int type, String ip1, int port1, String ip2, int port2){
		this.type = type;
		units = new ArrayList<Pcap>();
		this.ip1 = ip1;
		this.port1 = port1;
		this.ip2 = ip2;
		this.port2 = port2;
	}

	public ArrayList<Pcap> getUnits() {
		return units;
	}
	public void setUnits(ArrayList<Pcap> units) {
		this.units = units;
	}
	public String getIp1() {
		return ip1;
	}
	public void setIp1(String ip1) {
		this.ip1 = ip1;
	}
	public String getIp2() {
		return ip2;
	}
	public void setIp2(String ip2) {
		this.ip2 = ip2;
	}
	public int getPort1() {
		return port1;
	}
	public void setPort1(int port1) {
		this.port1 = port1;
	}
	public int getPort2() {
		return port2;
	}
	public void setPort2(int port2) {
		this.port2 = port2;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}

