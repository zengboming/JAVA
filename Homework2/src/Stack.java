public class Stack<T> extends List<T> {
	public Stack<T> push( T v ) { add(v, 0); return this; }
	public T pop( ) { return remove(0 ) ; }
	public T top ( ) { return empty() ? null : front.data ; }
}
