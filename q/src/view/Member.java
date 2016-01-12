package view;
import java.util.Random;


public class Member implements Comparable{

	protected String firstName,lastName;
	long ID;
	
	static	Random rnd=new Random();
	public Member(){generate();}
	public void generate(){
		//9位数，xxx-xx-xxxx 不够的前面补0.现在产生随机的九位数 123-56-7890
		ID=100000000+rnd.nextInt(899999999);
		firstName=Names.firstName[rnd.nextInt(Names.firstName.length)];
		lastName=Names.lastName[rnd.nextInt(Names.lastName.length)];
	}
	public String toString(){
		return toString(true);
	}
	public String toString(boolean lab){
		return (lab ?"Mem.":"")+String.format("%03d-%02d-%04d  %20s %-20s",ID/1000000,ID/10000%100,ID%10000,firstName,lastName);
	}
	public int compareTo(Member m) {return 	(int)(ID-m.ID);}
	public String htmlRow(){
		return "<TR>"+htmlColumns()+"</TR>";
	}
	public String htmlColumns(){
		return String.format("<TD>%03d-%02d-%04d</TD> <TD>%20s</TD> <TD>%-20s</TD>",ID/1000000,ID/10000%100,ID%10000,firstName,lastName);
	}
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		String.format("%s", "hello");
		return 0;
	}
	
	
}