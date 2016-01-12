package view;

public class Faculty extends Employee {
	protected String degreeHeld;
	
	public Faculty(){generat();}
	public void generat(){
		super.generate();
		degreeHeld=Names.degree[rnd.nextInt(Names.degree.length)];
	}
	public String toString(){return toString(true);}
	public String toString(boolean lab){
		return (lab?"Fac.":"")+String.format("%s %-10s", super.toString(false),degreeHeld);}
	public String htmlRow(){ return "<TR>"+htmlColumns()+"</TR>";}
	public String htmlColumns(){
		return String.format("%s <TD>%-10s</TD>",super.htmlColumns(),degreeHeld);
	}
}
