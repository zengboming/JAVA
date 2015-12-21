package is;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.List;
import is.OperationActivity;
import is.PacketActivity;
import is.Pcap;
import is.TypeActivity;

public class AnalysisActivity implements Runnable{
	
	private String packagesFile = null;
	private File pFile;
	private PacketActivity packets = null;
	private RandomAccessFile raf = null;
	private long current, fileLength;
	
	public AnalysisActivity(String packagesFile){
		this.packagesFile = packagesFile;
		pFile = new File(packagesFile);
		fileLength = pFile.length();
		packets = new PacketActivity(pFile.getName().split("\\.")[0]);
		this.analyze();
	}
	
	private void analyze(){
		try {
			raf = new RandomAccessFile(packagesFile, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(!readPcapHeader()){
			System.out.println("the file can't be analysis!");
			return;
		}
		Thread th = new Thread(this);
		th.start();
	}
	
	private void generatePcapUnits() {
		List<Pcap> pcapUnits = packets.getPcapUnits();
		try {
			while(raf.getFilePointer() < fileLength){
				byte[] unitHeader = new byte[16];
				raf.read(unitHeader);
				paserUnit(unitHeader, pcapUnits);
				current = raf.getFilePointer();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void paserUnit(byte[] unitHeader, List<Pcap> pcapUnits) {
		Pcap unit = new Pcap();
		unit.setUnitHeader(unitHeader);
		int unitBodyLen = OperationActivity.byteArrayToIntBeforeReverse(OperationActivity.cutBytes(unitHeader, 8, 12));
		byte[] unitBody = new byte[unitBodyLen];
		try {
			raf.read(unitBody);
		} catch (IOException e) {
			e.printStackTrace();
		}
		unit.setUnitBody(unitBody);
		String desMacAddr = getMacAddr(OperationActivity.cutBytes(unitBody, 0, 6));
		unit.setDesMacAddr(desMacAddr);
		String srcMacAddr = getMacAddr(OperationActivity.cutBytes(unitBody, 6, 12));
		unit.setSrcMacAddr(srcMacAddr);
		int netLayerType = OperationActivity.byteArrayToInt(OperationActivity.cutBytes(unitBody, 12, 14));
		
		switch(netLayerType){
		case TypeActivity.ARP :
		case TypeActivity.RARP : unit.setTwoLayerType(netLayerType);break;
		case TypeActivity.IP : unit.setTwoLayerType(netLayerType);paserIPv4TransLayer(unit, unitBody);break;
		case TypeActivity.IPv6 : unit.setTwoLayerType(netLayerType);paserIPv6TransLayer(unit, unitBody);break;
		}
		pcapUnits.add(unit);
	}

	private void paserIPv4TransLayer(Pcap unit, byte[] unitBody) {
		String srcIpAddr = getIPv4Addr(OperationActivity.cutBytes(unitBody, 26, 30));
		unit.setSrcIpAddr(srcIpAddr);
		String desIpAddr = getIPv4Addr(OperationActivity.cutBytes(unitBody, 30, 34));
		unit.setDesIpAddr(desIpAddr);
		int transLayerType = OperationActivity.byteArrayToInt(OperationActivity.cutBytes(unitBody, 23, 24));
		
		switch(transLayerType){
		case TypeActivity.ICMP :  unit.setThreeLayerType(transLayerType);return;
		case TypeActivity.TCP : unit.setTcpFlags(OperationActivity.byteArrayToInt(OperationActivity.cutBytes(unitBody, 47, 48)));
		case TypeActivity.UDP : unit.setThreeLayerType(transLayerType);
								unit.setSrcPort(OperationActivity.byteArrayToInt(OperationActivity.cutBytes(unitBody, 34, 36)));
								unit.setDesPort(OperationActivity.byteArrayToInt(OperationActivity.cutBytes(unitBody, 36, 38)));
								return;
		}
	}
	
	private void paserIPv6TransLayer(Pcap unit, byte[] unitBody) {
		String srcIpAddr = getIPv6Addr(OperationActivity.cutBytes(unitBody, 22, 38));
		unit.setSrcIpAddr(srcIpAddr);
		String desIpAddr = getIPv6Addr(OperationActivity.cutBytes(unitBody, 38, 54));
		unit.setDesIpAddr(desIpAddr);
		int transLayerType = OperationActivity.byteArrayToInt(OperationActivity.cutBytes(unitBody, 20, 21));
		
		switch(transLayerType){
		case TypeActivity.ICMPv6 :  unit.setThreeLayerType(transLayerType);return;
		case TypeActivity.TCP :
		case TypeActivity.UDP : unit.setThreeLayerType(transLayerType);
								unit.setSrcPort(OperationActivity.byteArrayToInt(OperationActivity.cutBytes(unitBody, 54, 56)));
								unit.setDesPort(OperationActivity.byteArrayToInt(OperationActivity.cutBytes(unitBody, 56, 58)));
								return;
		}
	}
	
	private String getIPv6Addr(byte[] ipv6Bytes){
		String ipv6Addr = "";
		String tmp = "";
		for(int i = 0; i < ipv6Bytes.length; i++){
			tmp = String.format("%02X", ipv6Bytes[i]);
			ipv6Addr += (i == ipv6Bytes.length-1) || (i % 2 == 0) ? tmp : tmp+":";
		}
		return ipv6Addr;
	}

	private String getIPv4Addr(byte[] ipBytes) {
		String ipAddr = "";
		String tmp = "";
		for(int i = 0; i < ipBytes.length; i++){
			tmp = String.format("%d", ipBytes[i] & 0xFF);
			ipAddr += i == ipBytes.length-1 ? tmp : tmp+".";
		}
		return ipAddr;
	}

	private String getMacAddr(byte[] macBytes) {
		String macAddr = "";
		String tmp = "";
		for(int i = 0; i < macBytes.length; i++){
			tmp = String.format("%X", macBytes[i]);
			macAddr += i == macBytes.length-1 ? tmp : tmp+":";
		}
		return macAddr;
	}

	private boolean readPcapHeader() {
		byte[] pcapMagic = new byte[4];
		byte[] pcapTemp = new byte[20];
		byte b[] = {(byte) 0xD4,(byte) 0xC3,(byte) 0xB2,(byte) 0xA1};
		int i = 0;
		try {
			i = raf.read(pcapMagic);
			raf.read(pcapTemp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(i != 4)
			return false;
		if(!Arrays.equals(pcapMagic, b))
			return false;
		
		byte[] pcapHeader = OperationActivity.mergeBytes(pcapMagic, pcapTemp);
		PacketActivity.setPcapHeader(pcapHeader);
		return true;
	}

	@Override
	public void run() {
		generatePcapUnits();		
	}

	public String getProgress() {	
		return current+"B/"+fileLength+"B";
	}

	public double getPercentage() {		
		return (double)current/(double)fileLength;
	}
	
	public PacketActivity getPackets(){
		return packets;
	}
}