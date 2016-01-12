package view;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Handler;

public class MemberTest{
	static int num=100;
	static Vector<Member> ma=new Vector<Member>();
    static MemberTest app=new MemberTest();
    Member members[] = null;

	public static void main(String args[]) throws IOException{

			System.out.println("Enter H/h? for help,or command:");
			 Scanner s1 = new Scanner(System.in); 
			 String line = s1.nextLine(); 
			while(!(line.equals("h")||line.equals("H")||line.equals("?"))){
				System.out.println("Erroe,Please enter H/h? for help,or command:");
				line = s1.nextLine(); 
			}
			app.showMenu();
			line=s1.nextLine();
			char ch=line.trim().toUpperCase().charAt(0);
			app.decide(ch);

	}
	public void decide(char c) throws IOException{
		char ch=c;
		 Scanner s1 = new Scanner(System.in); 
		while(ch!='Q'){
		switch(ch)
		{
		case 'G': System.out.println("Please enter Member class' number:"); num=Integer.parseInt(s1.nextLine()); app.fill(); break;
		case 'S':  app.sort(); System.out.println("Sort is finish.You can enter 'V/v' to show it.");break;
		case 'V': System.out.println("Sort after:"); app.show();break;
		case 'O': app.htmlFile();break;
		case 'F': app.showFile();break;
		case 'L': app.web();break;
		case 'H': app.showMenu();break;
		default:  System.out.println("Eorror,please enter a command again:"); break;
		}
		 System.out.println("Enter H/h/? or Q/q or command:");
		String line=s1.nextLine();
		ch=line.trim().toUpperCase().charAt(0);
		}

	}
	public void web() throws IOException{
		URL url = new URL(null,"file:///C:/out.html"); 
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);  
	}
	public void showMenu(){
		for(int i=0;i<Names.command.length;i++){
			System.out.println(Names.command[i]);
		}
		
	}

	public void fill(){
		members=new Member[num];
		for(int i=0;i<num;i++){
			members[i]=getMember();
		}
	}
	
	Member getMember(){
		switch(Member.rnd.nextInt(5)){
		case 0: return new Member();
		case 1:	return new Student();
		case 2:	return new Employee();
		case 3:	return new Faculty();
		case 4:	return new Staff();
		default:return null;
		}
	}
	public void sort() {
		Member tmp = new Member();
		for (int i = 0; i < members.length; i++) {
			for (int j = i+1; j < members.length; j++) {
				if (members[i].ID > members[j].ID) {
					tmp = members[i];
					members[i] = members[j];
					members[j] = tmp;
				}
			}
		}
		for (int j = 0; j < members.length; j++) 
		ma.add(members[j]);
	}

	public void show() throws IOException{
		String s;
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<ma.size();i++){
			System.out.printf("%4d:%40s\n",i+1,ma.elementAt(i).toString(true));
			if((i+1)%40==0){
				System.out.printf("\n\t\t Enter 'Q/q' to quit, anykey to continue...");
				s=sc.nextLine();
				if(s.toUpperCase().charAt(0)=='Q') continue;
			}
				
		}
		
	}
	public void htmlFile() throws IOException{
		OutputStream out=new FileOutputStream(new File("c://out.html"),true);
		PrintStream p=new PrintStream(out);
		p.println("<html><body><table border=1>");
		p.println("<TR><TD>ID</TD><TD>firstname</TD><TD>lastname</TD><TD>department</TD><TD>hired year</TD><TD>degree</TD><TD>job title</TD><TD>major</TD><TD>GPA</TD></TR>");
		for(int i=0;i<ma.size();i++){
			p.println(ma.elementAt(i).htmlRow());
		}
		p.println("</table></body></html>");
		out.close();
	}
	public void showFile() throws IOException{
		BufferedReader reader=null;
		reader=new BufferedReader(new FileReader("c://out.html"));
		String temp=null;
		int line=1;
		while((temp=reader.readLine())!=null){
			System.out.println(temp);
			line++;
		}
		reader.close();
	}
}
	
	