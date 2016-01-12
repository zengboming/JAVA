package view;

import java.util.Random;
import java.util.Scanner;

public class Employee extends Member {
	protected String department; int yearHired;
	static	Random rnd=new Random();
	public Employee(){generate();}
	public void generate(){
		super.generate();
		department=Names.department[rnd.nextInt(Names.department.length)];
		yearHired=Names.yearHired[rnd.nextInt(Names.yearHired.length)];
	}
	public String toString(){return toString(true);}
	public String toString(boolean lab){
		return (lab?"Emp.":"")+String.format("%s %20s %-10s", super.toString(false),
				department,yearHired);}
	public String htmlRow(){ return "<TR>"+htmlColumns()+"</TR>";}
	public String htmlColumns(){
		return String.format("%s <TD>%20s</TD> <TD>%-10s</TD> ",super.htmlColumns(),department,yearHired);
	}
	
	
}
