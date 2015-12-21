package is;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import is.strings;
import is.Layout;
import is.Flags;
import is.PacketActivity;
import is.Pcap;
import is.TypeActivity;
import is.Activity1;

public class PacketActivity2 extends PcapActivity{
	private PacketActivity packets;
	private ArrayList<Activity1> finish = new ArrayList<Activity1>();
	private HashMap<String, Activity1> resMap = new HashMap<String, Activity1>();
	public PacketActivity2(Layout view){
		super(view);
	}

	@Override
	public void action(ActionEvent arg0) {
		this.packets = view.getAnalyzer().getPackets();
		if(arg0.getActionCommand().equals(strings.Button.EXTRACTTCP)){
			extractUnit(TypeActivity.TCP);
			saveToFile(TypeActivity.TCP);
		}else if(arg0.getActionCommand().equals(strings.Button.EXTRACTUDP)){
			extractUnit(TypeActivity.UDP);
			saveToFile(TypeActivity.UDP);
		}
	}

	private void saveToFile(int type) {
		File file = new File(packets.getName()+"\\"+strings.dir);
		if(!file.isDirectory())
			file.mkdirs();
		FileWriter fw = null;
		if(type == TypeActivity.TCP){
			try {
				fw = new FileWriter(packets.getName()+"\\"+"TCPPcapList.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			HashMap<String, Integer> numMap = new HashMap<String, Integer>();
			for(int i = 0; i < finish.size(); i ++){
				Activity1 se = finish.get(i);
				writeTcpSession(se, numMap, fw);
			}
			Iterator<String> it = resMap.keySet().iterator();
			while(it.hasNext()){
				Activity1 se = resMap.get(it.next());
				writeTcpSession(se, numMap, fw);
			}
		}else if(type == TypeActivity.UDP){
			try {
				fw = new FileWriter(packets.getName()+"\\"+"UDPPcapList.txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			Iterator<String> it = resMap.keySet().iterator();
			while(it.hasNext()){
				Activity1 se = resMap.get(it.next());
				writeUdpSession(se, fw);
			}
		}
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void writeUdpSession(Activity1 se, FileWriter fw) {
		String fileName = "UDP"+"["+se.getIp1()+"]"+"["+se.getPort1()+"]"+"["+se.getIp2()+"]"
				+"["+se.getPort2()+"]"+".pcap";
		try {
			fw.write(fileName+"\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		writeSessionToFile(fileName, se);
	}

	private void writeTcpSession(Activity1 se, HashMap<String, Integer> numMap, FileWriter fw) {
		String title = "TCP"+"["+se.getIp1()+"]"+"["+se.getPort1()+"]"+"["+se.getIp2()+"]"
				+"["+se.getPort2()+"]";
		int count = 0;
		if(numMap.containsKey(title)){
			count = numMap.get(title)+1;
			numMap.put(title, count);
		}else{
			numMap.put(title, 0);
		}
		String fileName = title+"_"+count+".pcap";
		try {
			fw.write(fileName+"\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		writeSessionToFile(fileName, se);		
	}

	private void writeSessionToFile(String fileName, Activity1 se){
		RandomAccessFile sessionRaf = null;
		try {
			sessionRaf = new RandomAccessFile(packets.getName()+"\\"+strings.dir+"\\"+fileName, "rw");
			sessionRaf.write(PacketActivity.getPcapHeader());
			for(int j = 0; j < se.getUnits().size(); j++){
				Pcap unit = se.getUnits().get(j);
				sessionRaf.write(unit.getUnitHeader());
				sessionRaf.write(unit.getUnitBody());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				sessionRaf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void extractUnit(int type) {
		ArrayList<Pcap> units = packets.getPcapUnits();
		Pcap unit = null;
		for(int i = 0; i < units.size(); i ++){
			unit = units.get(i);
			if(unit.getTwoLayerType() == TypeActivity.IP && unit.getThreeLayerType() == type){
				String ip1 = unit.getSrcIpAddr();
				int port1 = unit.getSrcPort();
				String ip2 = unit.getDesIpAddr();
				int port2 = unit.getDesPort();
				boolean exchange = needExchange(ip1, ip2);
				if(exchange){
					String t = null;
					t = ip1;
					ip1 = ip2;
					ip2 = t;
					int tmp = 0;
					tmp = port1;
					port1 = port2;
					port2 = tmp;
				}
				String key = ip1+port1+ip2+port2;
				if(type == TypeActivity.TCP && unit.getTcpFlags() == Flags.TCP_SYN){
					Activity1 se = new Activity1(type, ip1, port1, ip2, port2);
					if(resMap.containsKey(key)){
						finish.add(resMap.get(key));
						resMap.remove(key);
					}
					se.getUnits().add(unit);
					resMap.put(key, se);
				}else{
					if(resMap.containsKey(key)){
						resMap.get(key).getUnits().add(unit);
					}else{//可能不是完整的TCP会话
						Activity1 se = new Activity1(type, ip1, port1, ip2, port2);
						se.getUnits().add(unit);
						resMap.put(key, se);
					}
				}
			}
		}
	}

	private boolean needExchange(String ip1, String ip2) {
		String[] strs1 = ip1.split("\\.");
		String[] strs2 = ip2.split("\\.");
		int p1 = 0, p2 = 0;
		for(int i = 0; i < strs1.length; i++){
			p1 = Integer.parseInt(strs1[i]);
			p2 = Integer.parseInt(strs2[i]);
			if(p1 > p2){
				return true;
			}
			if(p1 < p2){
				return false;
			}
		}
		return false;
	}

}
