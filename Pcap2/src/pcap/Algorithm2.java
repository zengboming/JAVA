package pcap;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Algorithm2 {
	   
		static File file;
	    JFrame jframe=new JFrame("Algorithm 2：");
		JPanel j1=new JPanel();
		JPanel j2=new JPanel();
		JLabel label = new JLabel("曾博铭 M201576117 软件1504班",JLabel.CENTER);	
		JTextField se1=new JTextField(20);
		JTextField out1=new JTextField(20);
		JButton sel=new JButton("choice pcap file");
		JButton out=new JButton("choice output file");
		JButton an=new JButton("Analysis");				  
		public void analysis(){			
			sel.setPreferredSize(new Dimension(140,40));
			out.setPreferredSize(new Dimension(140,40));
			an.setPreferredSize(new Dimension(370,40));
			label.setPreferredSize(new Dimension(370,20));		
			j1.add(se1);j1.add(sel);
			j1.add(out1);j1.add(out);
			j1.add(an);j2.add(label);
			jframe.add(j1,BorderLayout.CENTER);
			jframe.add(j2,BorderLayout.SOUTH);			
			jframe.setResizable(false);
			jframe.setVisible(true);
			jframe.setSize(500,200);
			jframe.setLocationRelativeTo(null);		
			sel.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
				File1 fileFilter=new File1 ();
				JFileChooser jchoose = new JFileChooser();
				jchoose.setFileFilter(fileFilter);
				an.setText("Analysis");
				if (jchoose.showDialog(null, "确定") == 1) { return; }
				file = jchoose.getSelectedFile();
				se1.setText(jchoose.getSelectedFile().toString());  
				}
			});	
			out.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
				JFileChooser jchoose = new JFileChooser();
				jchoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
				if (jchoose.showDialog(null, "确定") == 1) { return; }
				out1.setText(jchoose.getSelectedFile().toString());
				}
			});	
			an.addActionListener(new ActionListener(){ 

				public void actionPerformed(ActionEvent arg0) {
					try {
						analy();
						an.setText("FINISH");
						JOptionPane.showConfirmDialog(null, "FINISH!","提示", JOptionPane.DEFAULT_OPTION);
						} 
					catch (Exception e) {
						e.printStackTrace();
						}
					}
				}
			);			
	}	
public static String bTB(byte b) {  
    return ""  
            + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)  
            + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)  
            + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)  
            + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);  
}  
private void analy() throws Exception {
	 List<Node> list1=new ArrayList<Node>();
	 FileInputStream fis = new FileInputStream(file);
	 byte[] pcap_head = new byte[24];
	 fis.read(pcap_head);
	 byte[] packet_head = new byte[16];
	 fis.read(packet_head);      
	 int plength = 0;
	 plength=(((packet_head[8]&0xFF))|((packet_head[9]&0xFF)<<8)|((packet_head[10]&0xFF)<<16)|((packet_head[11]&0xFF)<<24));    
	 byte[] packet_data = new byte[plength];
	 fis.read(packet_data);   
	 int nstart = 24+16+plength; 
	 int ptype = 0;  	 
	 while(fis.read(packet_head)!= -1){
		 PStruct packet=new PStruct();
		 plength = ((packet_head[8]&0xFF)) 
				 |((packet_head[9]&0xFF)<<8)
				 |((packet_head[10]&0xFF)<<16)
				 |((packet_head[11]&0xFF)<<24);	 
		 packet_data=null;  
		 packet_data = new byte[plength];
		 fis.read(packet_data);    
			if((!(packet_data[12]==0x08&&packet_data[13]==0x00))){
				packet.packet_start = nstart + 16;
				packet.packet_end = packet.packet_start + plength;
				nstart = packet.packet_end;
				continue;
			}		 
	 ptype=packet_data[23]&0xFF;                                 
		 if(ptype  ==  6 || ptype  ==  17) 
		 {
			 packet.ip_head_length=Integer.parseInt((bTB(packet_data[14]).substring(4)),2)*4;		
			 packet.data.ip_1 = Integer.toString(packet_data[26]&0xFF)+"."
					 	+Integer.toString(packet_data[27]&0xFF)+"."
					 	+Integer.toString(packet_data[28]&0xFF)+"."
					 	+Integer.toString(packet_data[29]&0xFF);
			 packet.data.ip_2 = Integer.toString(packet_data[30]&0xFF)+"."
					 	+Integer.toString(packet_data[31]&0xFF)+"."
					 	+Integer.toString(packet_data[32]&0xFF)+"."
					 	+Integer.toString(packet_data[33]&0xFF);
			 packet.data.port_1 = ((packet_data[34]&0xFF)<<8)
					 +(packet_data[35]&0xFF);
			 packet.data.port_2 = ((packet_data[36]&0xFF)<<8)
					 +(packet_data[37]&0xFF);
			 long Com1=((((long)packet_data[26])&0xFF)<<24)
					 |((packet_data[27]&0xFF)<<16)
					 |((packet_data[28]&0xFF)<<8)
					 |((packet_data[29]&0xFF));		 
			 long Com2=((((long)packet_data[30])&0xFF)<<24)
					 |((packet_data[31]&0xFF)<<16)
					 |((packet_data[32]&0xFF)<<8)
					 |((packet_data[33]&0xFF));
			 int flag=0;
			 if(Com1>Com2){
					String str = "";
					str = packet.data.ip_1;
					packet.data.ip_1=packet.data.ip_2;
					packet.data.ip_2=str;
					flag=1;
				}
			 if(flag  ==  1){
					int temp = 0;
					temp = packet.data.port_1;
					packet.data.port_1=packet.data.port_2;
					packet.data.port_2=temp;
				}
			 packet.packet_start=nstart + 16; 
			 packet.packet_end=packet.packet_start+plength;
			 nstart=packet.packet_end;
			 if(ptype==6){
					packet.data.protocol_Type=Proe.TCP_type;
					packet.tcp_head_length=Integer.parseInt((bTB(packet_data[14+packet.ip_head_length+13-1]).substring(0,4)),2)*4;			
				}
				else if(ptype==17){
					packet.data.protocol_Type=Proe.UDP_type;
				}		
		 } 
		 else{
			 packet.packet_start=nstart+16; 
			 packet.packet_end=packet.packet_start+plength;
			 nstart=packet.packet_end;
			 packet.data.protocol_Type=Proe.Other_type;//ICMP,IGMP
		}
		 Ele PE=null;
			 int m=1;
			 for(int i=0;i<list1.size();i++){
					PE=list1.get(i).FE_data;
					if(PE.protocol_Type==packet.data.protocol_Type
					 &&PE.ip_1.equals(packet.data.ip_1)
					 &&PE.ip_2.equals(packet.data.ip_2)
					 &&PE.port_1==packet.data.port_1
					 &&PE.port_2==packet.data.port_2)
					{   
						list1.get(i).SC_list.add(packet);
						m=0;
						break;
					}										
			 } 				
			 if(m==1){
				 PE=new Ele();
				 PE.ip_1=packet.data.ip_1;
				 PE.ip_2=packet.data.ip_2;
				 PE.port_1=packet.data.port_1;
				 PE.port_2=packet.data.port_2;
				 PE.protocol_Type=packet.data.protocol_Type;
				 Node SC_node=new Node();
				 SC_node.FE_data=PE;
				 SC_node.SC_list.add(packet);
				 list1.add(SC_node);
			}
	}
	 Node Se=null;
	 Ele PF=null;
	 boolean flag=true; 
	 byte[] ptemp=null;  
	 FileOutputStream Fos=null;
	 for(int i=0;i<list1.size();i++){
		 Se=list1.get(i);  
		 PF=Se.FE_data; 
		 	for(int j=0;j<Se.SC_list.size();j++){  
				PStruct packet=Se.SC_list.get(j); 
				FileInputStream Fis=new FileInputStream(file);
				if(PF.protocol_Type==Proe.TCP_type){
					ptemp=new byte[packet.packet_end-packet.packet_start-14-packet.ip_head_length-packet.tcp_head_length];
					Fis.skip(packet.packet_start+14+packet.ip_head_length+packet.tcp_head_length);
					Fis.read(ptemp);
					if(flag){
						Fos=new FileOutputStream(out1.getText().toString()+"/TCP["+PF.ip_1+"]["+PF.port_1+"]["+PF.ip_2+"]["+PF.port_2+"].pcap.txt",true);
						flag=false;
						}
					}
				else if(PF.protocol_Type==Proe.UDP_type){
					ptemp=new byte[packet.packet_end-packet.packet_start-14-packet.ip_head_length-8];
					Fis.skip(packet.packet_start+14+packet.ip_head_length+8);			
					Fis.read(ptemp);
					if(flag){
						Fos=new FileOutputStream(out1.getText().toString()+"/UDP["+PF.ip_1+"]["+PF.port_1+"]["+PF.ip_2+"]["+PF.port_2+"].pcap.txt",true);
						flag=false;
					}
				}
				else if(PF.protocol_Type==Proe.Other_type){
					ptemp=new byte[packet.packet_end-packet.packet_start];
					Fis.skip(packet.packet_start);
					Fis.read(ptemp);
					if(flag){
						Fos=new FileOutputStream(out1.getText().toString()+"/OtherType["+PF.ip_1+"]["+PF.port_1+"]["+PF.ip_2+"]["+PF.port_2+"].pcap.txt",true);
						flag=false;
					}
				}
				Fos.write(ptemp); 
			}  
			flag=true;  
	 }
	 	Fos.flush();
	}
}