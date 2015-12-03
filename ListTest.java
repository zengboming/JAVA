import java.lang.*;
import java.util.Iterator ;
import java.util.Random;
import java.util.Scanner;

public class ListTest {

   //List<Integer> 	l 	= new List<Integer>();
	Stack<Integer> 	stk=new Stack<Integer>();
	Queue<Integer> 	que=new Queue<Integer>();
	SQueue<Integer> squ=new SQueue<Integer>();
	Random 			rnd=new Random();

   public static void main ( String   args[] ) {

	   ListTest app = new ListTest();
	   app.fill( );
	  // app.display ( true ) ;
	   app.display();
	   // app.display ( false ) ;
	   app.remove();	
  }

  void  fill() {
	  int	v, pos;
	  for ( int i = 0; i < 10 ; i++ ) {
		  //v = rnd.nextInt(100); pos = rnd.nextInt( i + 1) ;
		  //l.add( v , pos) ;
		  v = rnd.nextInt(100);
		  stk.push(v);
		  que.enque(v);
		  squ.enque(v);
	  } 
	
  }
  void display(List<Integer> l,String name){
	  Iterator<Integer> itr = l.iterator(true);
		//int k = 0;
		System.out.printf("\n======== Begining of %s ========\n",name);
		while ( itr.hasNext() ) 
			System.out.printf("%4d", itr.next() ); 
		System.out.printf("\n======== End of %s ========\n",name);
  }
  
  void display(){
	  display(stk,"Stack");
	  display(que,"Queue");
	  display(squ,"Sorted Queue");
  }
  
  /*
  void display (boolean dir) {
	Iterator<Integer> itr = l.iterator( dir );
	int k = 0;
	System.out.printf("\n======== Begining of list ========\n");
	while ( itr.hasNext() ) 
		System.out.printf("%4d", itr.next() ); 
	System.out.printf("\n======== End of list ========\n");
  }*/


  Scanner scan = new Scanner( System.in ); 
  void remove() {
	/*
	int pos ;
	while ( true ) {
		System.out.printf("Enter a position to remove: ");
		pos = scan.nextInt();
		l.remove( pos ) ;
		
		display( true );
		
	}
	*/
	  Integer vs =null,vq=null,vsq=null,vsq2=null;
	  while(true){
		  if(!stk.empty())	vs=stk.pop();
		  if(!que.empty())	vq=que.deque();
		  if(!squ.empty())	vsq=squ.deque();
		  if(!squ.empty())	vsq2=squ.deque(vs);
		  System.out.printf("\n======== Test   of   remove ========\n");
		  System.out.printf("\n\t\t %s is popped out from stack,\n",vs);
		  System.out.printf("\n\t\t %s is dequed from queue,\n",vq);
		  System.out.printf("\n\t\t %s is smallest value in sorted queue,\n",vsq);
		  System.out.printf("\n\t\t %s is removed from sorted queue,equal to top of stack %s\n",vsq2,vs);
		  System.out.printf("\n======== End of remove Test ========\n");
		  display();
		  //String s=scan.nextLine();
		  if(stk.empty()&&que.empty()&&squ.empty()) break;
	  }
  }
}
