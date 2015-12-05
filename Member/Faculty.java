import java.util.Random;

public class Faculty extends Employee{
	String degreeHeld;
	Random rnd=new Random();
	
	public Faculty(){generate1();}
	public void generate1(){
		super.generate();
		degreeHeld=Names.degree[rnd.nextInt(Names.degree.length)];
	}
	public String toString(){return toString(true);}
	public String toString(boolean lab){
		return (lab?"FAC.":"")+String.format("%s %s", super.toString(false),degreeHeld);
	}
	public String htmlRow(){return "<TR>"+htmlColumns()+"</TR>";}
	public String htmlColumns(){
		return String.format("%s <TD>%s</TD>",super.htmlColumns(),degreeHeld);
	}
}