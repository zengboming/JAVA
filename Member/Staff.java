import java.util.Random;

public class Staff extends Employee{
	String jobTitle;
	Random rnd=new Random();
	
	public Staff(){generat2();}
	public void generat2(){
		super.generate();
		jobTitle=Names.title[rnd.nextInt(Names.title.length)];
	}
	public String toString(){return toString(true);}
	public String toString(boolean lab){
		return (lab?"STA.":"")+String.format("%s %s", super.toString(false),jobTitle);
	}
	public String htmlRow(){return "<TR>"+htmlColumns()+"</TR>";}
	public String htmlColumns(){
		return String.format("%s <TD>%s</TD>",super.htmlColumns(),jobTitle);
	}
}