package pcap;
import java.util.ArrayList;
import java.util.List;

public class function {
	public static void main(String[] args) {
		Algorithm2 alg=new Algorithm2();
		alg.analysis();
	}
}
class File1 extends javax.swing.filechooser.FileFilter{
  public boolean accept(java.io.File f) {
    if (f.isDirectory())return true;
    return f.getName().endsWith(".pcap");
  } 
  public String getDescription(){
    return ".pcap";
  }
}
class Ele {   
	public String ip_1="127.0.0.1";
	public String ip_2="127.0.0.1";
	public int port_1=0;
	public int port_2=0;
	public Proe protocol_Type = Proe.Other_type;
}
class PStruct implements Cloneable{
	public Ele data = new Ele();
	public int ip_head_length=0;
	public int tcp_head_length=0;
	public int packet_start=0;         
	public int packet_end=0;           
	public Object clone() throws CloneNotSupportedException  
	    {  	        return super.clone();  	    }  
}
enum Proe {   
	TCP_type, UDP_type, Other_type;
}
class Node {  
	Ele FE_data;   
	List<PStruct> SC_list = new ArrayList<PStruct>();
}


