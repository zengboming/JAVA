
public class Queue<T> extends List<T>{
	public Queue<T> enque(T v){add(v,count);return this;}
	public T deque(){return remove(0);}
	public T front(){return empty()?null:front.data;}
}
