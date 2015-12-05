import java.util.Random;

public class Employee extends Member{
	int yearHird;
	String department;
	Random rnd=new Random();
	
	public Employee(){generat();}
	public void generat(){
		super.generate();
		department=Names.department[rnd.nextInt(Names.department.length)];
		yearHird=rnd.nextInt(99999);
	}
	public String toString(){return toString(true);}
	public String toString(boolean lab){
		return (lab?"EMP.":"")+String.format("%s %s %d", super.toString(false),
				department,yearHird);
	}
	public String htmlRow(){return "<TR>"+htmlColumns()+"</TR>";}
	public String htmlColumns(){
		return String.format("%s <TD>%s</TD> <TD>%d</TD>",super.htmlColumns(),
				department,yearHird);
	}
}