import java.util.Iterator;

class Node <T>  {

   T		data;
   Node<T> 	next, previous;

   public Node ( T  v ) {   data = v; next = previous = null; }
}

public class List <T> {

  int  count = 0;
  Node<T> front = null, rear = null;

  public int		size() { return count; }
  public boolean	empty() { return count == 0; }

  protected List<T> 	add( T v ) { return add( v, 0 ) ; }

  protected List<T>  	add( T v, int pos ) {
	pos = pos < 0 ? 0 : pos > count ? count : pos;  // limit the pos to a legal value
	
	Node<T> nn = new Node<T> ( v ) ;

	// if list is empty.	
	if ( count == 0 ) {
		front = rear = nn;
		count ++ ;
		return this;
	}

	// add the new to the front.
	if ( pos == 0 ) {
		nn.next = front;	front.previous = nn;
		front = nn;      	count ++;
		return this;
	}

	if ( pos == count ) {
		rear.next = nn;		nn.previous = rear;
		rear      = nn;		count++ ;
		return this;
	}

	Node<T> cur = front;

	// cur will stop at the node to which new node will be attached.
	// There is another after the cur node.	
	for ( int i = 1; i < pos ; i++ ) cur = cur.next;

	nn.next = cur.next;		nn.previous = cur;
	cur.next = nn.next.previous = nn;
	count++;

	return this;
   }

   protected T 	remove( int pos ) { 
	T ptr = front.data;
	if ( count < 1 || pos < 0 || pos >= count ) return null;
	if (  count == 1 ) {
		count--; front = rear = null;
		return ptr;
	}
	if ( pos == 0 ) {
		front = front.next;
		front.previous.next = null;
		front.previous = null;
		count --;
		return ptr;
	}

	if ( pos == count - 1 ) {
		ptr = rear.data;
		rear = rear.previous;
		rear.next.previous = null;
		rear.next = null;
		count --;
		return ptr;
	}
	Node<T> cur = front;
	for ( int i = 0; i < pos; i ++ ) cur = cur.next;
	ptr = cur.data;
	cur.previous.next = cur.next;
	cur.next.previous = cur.previous;
	count--;
	return ptr;
   }

   public Iterator<T> iterator( boolean dir ) {
	return new MyListIterator<T> ( dir, dir? front : rear ) ;
   }

   class MyListIterator<C>  implements Iterator<C> {
	Node<C> cur ;
	boolean fwd ;

	public MyListIterator( boolean dir, Node<C> ptr ) { fwd = dir; cur = ptr ; }

	public boolean  hasNext() { return cur != null ; }
	public C next() {
		C v = cur.data; 
		cur = fwd? cur.next : cur.previous  ; 
		return v;
	}
	public void     remove () { } 
   } 
}
