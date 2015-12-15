import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

//
public class HashTable<T extends Comparable<T>> {
	
	static Scanner scn = new Scanner( System.in );
	int	 capacity, size = 0, maxLoadingFactor, PCTIncrement = 20;
	Object   table[]	= null;

	protected 	void	init( int cap, int  MLF ) {
		capacity = cap;	maxLoadingFactor = MLF;
		table	= new Object[capacity];
	}

	protected void rehash() {
		Object tmp[]=table;
		capacity=(int)(capacity*(1+PCTIncrement/100f));
		table=new Object[capacity];
		size=0;
		for(int i=0;i<tmp.length;i++){
			if(tmp[i]!=null) put((T)tmp[i]);
		}	
	}
	
	protected int find( T obj ) {
		int home = hash( obj );
		while ( table[home] != null & ((T) table[home]).compareTo( obj ) != 0 )
			home = (home+1) % capacity;
		return table[home] == null ? -1 : home;
	} 
	
	public HashTable() { init(10, 80); }

	public HashTable( int cap, int mlf ) {  init(cap, mlf); }

	public int	hash( T obj ) {
		if ( (size * 100.0f / capacity) > maxLoadingFactor ) rehash();
	
		int homeAddress = obj.hashCode() % capacity ;

		return homeAddress;
	}

	public HashTable<T>  put( T obj ) {
		int home=hash(obj);
		while(table[home]!= null) home=(home+1)%capacity;
		table[home]=obj;
		size++;
		return this;
	}

	public T get( T obj ) {
		int home = find ( obj );
		return home < 0 ? null : (T) table[home];
	}

	public void findMem( T obj ) {
		try{
		int i = find ( obj );
		if ( i < 0 ){
			System.out.println("don't have this member!");
			return;
		}
		int home=hash((T)table[i]);
		int Displacement = distance(home,i);
		System.out.println("the member you search is:");
		System.out.println(
				"+===================================================================================+ \n"+
				"|"+ get(i) +"  |   "+ i +"   |    " + home + "  |  "+ Displacement +"    |          \n" +
				"+===================================================================================+");
		}catch(Exception e){
			System.out.println("\t\tdon't have this member!");
		}
	}
	
	public T remove( T obj ) {
		int home = find ( obj );
		if ( home < 0 ) return null;
		size --;
		T data = (T) table[home];
		table[home] = null;
		shift( home, (home+1) % capacity );
		System.out.println("\t\tSucceed!");
		return data;
	}
	void shift( int empty, int cur ) {
		if ( table[cur] == null ) return;
		int home = table[cur].hashCode() % capacity;
		int curToHome = distance(cur, home),
				emptyToHome = distance(empty, home);
		if ( emptyToHome < curToHome ) {
			table[empty] = table[cur]; table[cur] = null;
			shift( cur, (cur+1) % capacity);
		} else  shift( empty, (cur + 1) % capacity );
	}
   
	int distance( int from, int to ) {
		return	from <= to ? to - from : capacity - from + to;
	}
   
	void statistic()
	{
		int count1=0,count2=0,k;
		for(int i=0;i<capacity;i++){
 			if(table[i]==null) count2++;
 			else count1++;
 		}
		
		for(int i=0;i<capacity;i++){
			if(table[i]!=null){
				System.out.println(
				"====================================================+  \n"+		
				"| "+get(i));
				break;
 			}
 		}

		
 		System.out.println(
 				"+===================================================+ \n"     +             
	            "|       Information on Data and Blank Blocks        | \n"     +             
	            "+===================================================+ \n"     +   
	            "| Block Type |  Starting  |   Ending   |    Size    | \n"     +       
	            "|            |  Address   |  Address   |            | \n"     +        
	            "+------------+------------+------------+------------+   "     );  
 		System.out.println(
 				"|  MEM:Date  |     0      |      2     |      3     | \n" +
 				"|  ID:Date   |     0      |      8     |      10    | \n" +
 				"|  Name:Date |     0      |      19    |      20    | \n");
 		
        System.out.println(
        		"+---------------------------------------------------+ \n"+             
        		"|             Block Type            Count           | \n"+             
	  	        "|                Data                 "+count1+"*3           | \n"+             
	 	        "|                Empty                "+count2+"*3           | \n"+             
        		"+===================================================+ \n");
					       					   	
	}
	public T  get( int k ) { 
		if ( k < 0 || k >= capacity ) return null;
		return (T)table[k];
	}
	
	public void printTablePar() {
		
		int size = 0;
		for (int i = 0; i < capacity; i++)
			if (get(i) != null) {
				++size;
			}	
		System.out.println(
				"+================================================================+\n" +       
				"|                  Parameters of The Hash Table                  |\n" +        
				"+================================================================+\n" +        
				"|  Capacity  |    Size    | Increment  | Specified  |Actual Load |\n" +        
				"|            |            |            |Load Factor |   Factor   |\n" +        
				"+------------+------------+------------+------------+------------+\n" +        
				"|    "+ capacity+"      |     " + size +"      |     " + PCTIncrement + "     |     "
				+ maxLoadingFactor + "     |     " + (float)size / capacity +"    |\n" +        
				"+------------+------------+------------+------------+------------+");
	}
	

	public void verfiy() {
		
		System.out.println(
				"+================================================================+");
		for (int i = 0; i < capacity ; i++) {
			Member member = (Member) get(i);
			if (member != null) {
				if (find((T) member) == i) {
					System.out.println("| "+member + " " + " YES |");
				}
				else {
					System.out.println("| "+member + " " + " NO  |");
				}
			}
		}		
		System.out.println(
				"+================================================================+\n");
	}
	
	public void performance()
	{
		double succFind = (1 + 1 / (1 - maxLoadingFactor*1.0/100)) / 2;
		double unsuccFind = (1 + 1 / ((1 - maxLoadingFactor*1.0/100) * (1-maxLoadingFactor*1.0/100))) / 2;
		double binSearch = (double) Math.log(capacity) * capacity;
		
		NumberFormat nFormat=NumberFormat.getNumberInstance(); 
		nFormat.setMaximumFractionDigits(2);
		
		float sum = 0;
		for (int i = 0; i < capacity; i++)
			if (get(i) != null) {
				Member member = (Member) get(i);
				int home = hash((T) member);
				int current = find((T) member);
				sum += distance(home, current);
			}
		sum /= capacity;
		
		float unsuccSum = 0;
		for (int i = 0; i < capacity; i++)
			if (get(i) != null) {
				Member member = (Member) get(i);
				int home = hash((T) member);
				while (true) {
					if (get(home) != null) {
						home = (1 + home + capacity) % capacity;
						unsuccSum++;
					}
					else {
						break;
					}
				}
			}
		unsuccSum /= capacity;
	       System.out.println("+================================================================+\n"+       
	    	       "|      Time Complexities of Practical & Theoretic Hashtable      | \n"+      
	    	       "|          Search vs. Theoretic Binary Search Algorithm          | \n"+     
	    	       "+================================================================+ \n"+      
	    	       "| Practical  | Practical  | Theoretic  | Theoretic  |Theoretical | \n"+      
	    	       "| Hashtable  | Hashtable  | Hashtable  | Hashtable  |   Binary   | \n"+     
	    	       "| Successful |Unsuccessful| Successful |Unsuccessful|   Search   | \n"+      
	    	       "|   Search   |   Search   |   Search   |   Search   |            | \n"+      
	    	       "+------------+------------+------------+------------+------------+ \n"+      
	    	       "|    2.39    |   10.20    |     " + nFormat.format(succFind)+"      |     "+
	    	       nFormat.format(unsuccFind)+"     |    " + nFormat.format(binSearch) +"   | \n"+      
	    	       "+------------+------------+------------+------------+------------+  ");
	}
	//
	void showTable() {
		System.out.println(
						"+===================================================================================+\n"+            
						"|                                 Contents of Hash Table                            | \n" +          
						"|                     [Capacity-"+capacity +", Size-75, MaxLoad-"+maxLoadingFactor+"%, PCT-"+PCTIncrement+"%]                  | \n" +           
						"+===================================================================================+ \n" +           
						"|                        Object Value                       |Current| Home  |Displac| \n" +           
						"|                                                           |Address|Address| ement | \n" +           
						"+-----------------------------------------------------------+-------+-------+-------+ \n" );  
		int home;
		String str;
				for (int i = 0; i < capacity; i++)
				{
					if(i%10 == 0 && i!=0){
							System.out.println(  "\n\t\tEnter a random letter to continue: \n");
							scn.next().charAt(0);
							continue;
					}
			
					if(get(i) == null){
					System.out.println(
							"|                                                           |   " + i + "   |       |       |   ");
					}
					else{
						home=hash((T)table[i]);
						int Displacement = distance(home,i);
					System.out.println(
							"|"+ get(i) +"  |   "+ i +"   |    " + home + "  |  "+ Displacement +"    |          ");}
					}
				System.out.println("+-----------------------------------------------------------+-------+-------+-------+ \n");
			}
	
	
	public void show() {
		System.out.printf("\n\tHash Table [%d - %d - %d]\n", capacity, size, maxLoadingFactor);
		int home;
		String str ;

		System.out.printf("\n\t===============  Objects  ================= Cur-Home-Distance =====\n");
 
		for ( int i = 0; i < capacity; i ++ ) {
			if ( table[i] == null ) {
				home = i;
				str = "null";
			} else	{
				home = hash((T) table[i]);
				str = table[i].toString() ;
			}

		System.out.printf("\t%40s %4d %4d %4d\n",str, i, home, distance(home, i) );
		System.out.printf("\n");
	}
	
	
    }
}
