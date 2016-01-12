package view;

public class Staff extends Employee{
	protected String jobTitle;
	
	public Staff(){generat();}
	public void generat(){
		super.generate();
		jobTitle=Names.title[rnd.nextInt(Names.title.length)];
	}
	public String toString(){return toString(true);}
	public String toString(boolean lab){
		return (lab?"Sta.":"")+String.format("%s %-10s", super.toString(false),jobTitle);}
	public String htmlRow(){ return "<TR>"+htmlColumns()+"</TR>";}
	public String htmlColumns(){
		return String.format("%s <TD>%-10s</TD><TD>%-10s</TD> ",super.htmlColumns(),null,jobTitle);
	}
}
