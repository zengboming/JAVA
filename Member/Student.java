public class Student extends Member{
	String 	major;
	float 	GPA;
	
	public Student(){generat();}
	public void generat(){
		super.generate();
		major=Names.department[rnd.nextInt(Names.department.length)];
		GPA=rnd.nextInt(401)/100.0f;
	}
	public String toString(){return toString(true);}
	public String toString(boolean lab){
		return (lab?"STU.":"")+String.format("%s %10s %1.2f", super.toString(false),
				major,GPA);}
	public String htmlRow(){ return "<TR>"+htmlColumns()+"</TR>";}
	public String htmlColumns(){
		return String.format("%s <TD>%s</TD> <TD>%1.2f</TD>",super.htmlColumns(),major,GPA);
	}
}