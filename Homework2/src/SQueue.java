public class SQueue<T extends Comparable<T> > extends List<T> {
	public SQueue<T> enque( T v ) {
	   if ( count < 1 || v.compareTo( front.data) <= 0 ) { add(v, 0 ) ; return this; }
	   if ( v.compareTo( rear.data) >= 0 ) { add(v, count ) ; return this; }

	   Node<T> cur = front.next;
	   while ( v.compareTo( cur.data ) > 0 ) cur = cur.next;
	   Node<T> nn = new Node<T>( v );
	   nn.next = cur;  nn.previous = cur.previous;
	   cur.previous = nn; nn.previous.next = nn;
	   count++; return this;
	}
	public T deque ( ) { return remove(0 ) ; }
	public T deque ( T v ) { 
	   if ( count < 1 || v.compareTo( front.data) < 0  || v.compareTo(rear.data) > 0 ) 
		return null;
	   if ( v.compareTo( front.data ) == 0 ) { return remove( 0 ) ; }
	   if ( v.compareTo( rear.data ) == 0 )  { return remove( count - 1 ) ; }

	   Node<T> cur = front.next;
	   while ( v.compareTo(cur.data) > 0 ) cur = cur.next;
	   if ( v.compareTo(cur.data) == 0 ) {
		cur.next.previous = cur.previous;
		cur.previous.next = cur.next;
		cur.next = cur.previous = null;
		count--;
		return cur.data;
	   }
	   return null;
	}
	public T front( ) { return empty() ? null : front.data ; }
}
