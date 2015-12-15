import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class HashTableTest {

   HashTable<Member> ht = new HashTable<Member>();
  
   String [] menu  = {		
		    "+=============================================================+",
			"|  G :Generate hashtable                                      |",
			"|  T :Perform a successful search on each of object in        |" ,
			"|  A :Add item                                                |",
			"|  B :Display information on blocks formed by contiguous      |",
			"|  R :Remove item                                             |",
			"|  F :Find Object by ID                                       |",
			"|  C :Show table in a tabular way                             |",
			"|  P :List the table parameters.                              |",
			"|  V :Verify whether all non-null elements in table           |",
			"|  I :Remove object at table index to test Verify             |",
			"|  H :Show this menu                                          |",
			"|  Q :Exit the program.                                       |",
			"+=============================================================+"
		};

   public static void main( String args[] ) {

	HashTableTest app = new HashTableTest();
	app.fill ();
	app.testRemoveAndPut();
   }

   void fill ( ) {

	for ( int i = 0; i < 8 ; i++ )
		ht.put( new Member() ) ;
   }

   Scanner scn = new Scanner ( System.in );
   void testRemoveAndPut() {
	   
	   for (int i = 0; i < menu.length; i++ )		
			System.out.printf("\n\t\t%s", menu[i]);

	   String line;
	   char   ch ;
	   while ( true ) {
		   System.out.printf("\n\t\tEnter your choice: ");
		   line = scn.nextLine();
		   ch = line.trim().toUpperCase().charAt(0);
		   switch ( ch ) {
		  	 case 'A':  AddMember(); break;
		  	 case 'R':  RemoveMember(); break;
		  	 case 'B':  Block(); break;
		  	 case 'G':  Generate();break;
		 	 case 'F':  FindMember();break;
		 	 case 'P':  Parameters(); break; 
		 	 case 'V':  Verify(); break; 
		 	 case 'T':  Tcapacity(); break; 
		 	 case 'Q':  Quitcapacity(); break; 
		 	 case 'C':  Showcapacity();break;
		 	 case 'H': 
		 	 case 'h':
		  	 case '?':  testRemoveAndPut();break;
		  	 case 'I':
		  	 case 'X':	TestVerify();
		  	 default :	System.out.printf("\n\t\tIllegal selection !");
		   }
	   }
    }

    void AddMember() {
    	int id = readID ();
    	ht.put ( new Member ( id ) );
    }

    void RemoveMember() {
    	int id = readID ();
    	ht.remove( new Member ( id ) );
    }
    
    void Block(){
    	ht.statistic(); 	
    }
    void Generate()
    {
    	System.out.println("\t\tPlease input two number:");
    	int cou = Integer.parseInt( scn.nextLine());
    	int mlf = Integer.parseInt( scn.nextLine());
		System.out.println("\t\tloading...");
		ht.init(cou, mlf);
		for (int i = 0; i < cou*mlf/100; i++ )
		{
			ht.put( new Member() );
		}
		System.out.println("\t\tSucceed!");
    }
    
    void FindMember()
    {
    	int id = readID();
    	ht.findMem(new Member(id));
    }
    
    void Parameters()
    {
    	ht.printTablePar();
    }
    
    void Verify()
    {
    	ht.verfiy();
    }
    
    void Tcapacity()
    {
    	ht.performance();
    }
    
    void Quitcapacity()
    {
    	System.out.println("see you~~~");
    	System.exit(0);
    }
    
    void Showcapacity()
    {
    	ht.showTable();
    }
    
    void TestVerify(){
    	System.out.println("\t\tremove object at table index");
    	RemoveMember();
    	System.out.println("\t\tfind object to verify");
    	FindMember();
    	System.out.println("\t\tsucceed!");
    }
    
    int readID() {
    	System.out.printf("\n\t\tEnter a member ID : ");
    	int id = Integer.parseInt( scn.nextLine());
    	return id;
    }
}	
