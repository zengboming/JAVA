import java.util.Random;

public class Member implements Comparable<Member> {
	static Random rnd = new Random();

	public int	hashCode() { return ID; }
	public int  compareTo(Member m) { return ID - m.ID ; }
	public Member() { generate() ; }
	public Member(int id ) { generate( id ) ; }
 
	public void generate(int id ) {
		ID = id;
		firstName = Names.firstName[ rnd.nextInt( Names.firstName.length )];
		lastName = Names.lastName[ rnd.nextInt( Names.lastName.length )];
	}

	public void generate() {
		ID = 100000000 + rnd.nextInt( 899999999 );
		firstName = Names.firstName[ rnd.nextInt( Names.firstName.length )];
		lastName = Names.lastName[ rnd.nextInt( Names.lastName.length )];
	}

	public String toString() { return toString( true ) ; }
	public String toString( boolean lab ) {
		return (lab ? "MEM " : "") + 
			String.format("%03d-%02d-%04d %20s %-20s", ID/1000000, ID/10000 % 100, ID % 10000, firstName, lastName);
	}

	public String htmlRow() {
		return	"<TR>" + htmlColumns() + "</TR>";
	}
	public String htmlColumns() {
		return String.format("<TD>%03d-%02d-%04d</TD> <TD>%20s</TD><TD> %-20s</TD>", ID/1000000, ID/10000 % 100, ID % 10000, firstName, lastName);
	}
	protected String firstName, lastName;
	int	ID;
	
	public int getID(){
	return ID;
	}

} 
