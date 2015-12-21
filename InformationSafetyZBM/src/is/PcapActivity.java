package is;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import is.Layout;

public abstract class PcapActivity implements ActionListener{
	protected Layout view;
	
	public PcapActivity(Layout view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(view.isWorking()) {
			JOptionPane.showMessageDialog(view, "task is processing...");
		}
		else action(arg0);
	}
	
	public abstract void action(ActionEvent arg0);
}

class Pcap {
	private int twoLayerType;
	private int threeLayerType;
	private String srcMacAddr;
	private String desMacAddr;
	private String srcIpAddr;
	private int srcPort;
	private String desIpAddr;
	private int desPort;
	private int tcpFlags;
	private byte[] unitHeader;
	private byte[] unitBody;

	public int getTcpFlags() {
		return tcpFlags;
	}
	public void setTcpFlags(int tcpFlags) {
		this.tcpFlags = tcpFlags;
	}
	public String getSrcMacAddr() {
		return srcMacAddr;
	}
	public void setSrcMacAddr(String srcMacAddr) {
		this.srcMacAddr = srcMacAddr;
	}
	public String getDesMacAddr() {
		return desMacAddr;
	}
	public void setDesMacAddr(String desMacAddr) {
		this.desMacAddr = desMacAddr;
	}
	public byte[] getUnitHeader() {
		return  unitHeader;
	}
	public void setUnitHeader(byte[] unitHeader) {
		this.unitHeader = unitHeader;
	}
	
	public byte[] getUnitBody() {
		return  unitBody;
	}
	public void setUnitBody(byte[] unitBody) {
		this.unitBody = unitBody;
	}
	public int getTwoLayerType() {
		return twoLayerType;
	}
	public void setTwoLayerType(int twoLayerType) {
		this.twoLayerType = twoLayerType;
	}
	public int getThreeLayerType() {
		return threeLayerType;
	}
	public void setThreeLayerType(int threeLayerType) {
		this.threeLayerType = threeLayerType;
	}
	public String getSrcIpAddr() {
		return srcIpAddr;
	}
	public void setSrcIpAddr(String srcIpAddr) {
		this.srcIpAddr = srcIpAddr;
	}
	public int getSrcPort() {
		return srcPort;
	}
	public void setSrcPort(int srcPort) {
		this.srcPort = srcPort;
	}
	public String getDesIpAddr() {
		return desIpAddr;
	}
	public void setDesIpAddr(String desIpAddr) {
		this.desIpAddr = desIpAddr;
	}
	public int getDesPort() {
		return desPort;
	}
	public void setDesPort(int desPort) {
		this.desPort = desPort;
	}
	
	public String[] display(int index) {
		if(threeLayerType == 0)
			return new String[]{""+index, srcMacAddr, " ", desMacAddr, " ",TypeActivity.typeMap1.get(twoLayerType)};
		if(srcPort != 0 && desPort != 0){
			String type = null;
			if(TypeActivity.portMap1.containsKey(srcPort)){
				type = TypeActivity.portMap1.get(srcPort);
			}else if(TypeActivity.portMap1.containsKey(desPort)){
				type = TypeActivity.portMap1.get(desPort);
			}else{
				type = TypeActivity.typeMap1.get(threeLayerType);
			}
			return new String[]{""+index, srcIpAddr, srcPort+"", desIpAddr, desPort+"", type};
		}
		return new String[]{""+index, srcIpAddr, "", desIpAddr, "", TypeActivity.typeMap1.get(threeLayerType)};
	}
	
}
