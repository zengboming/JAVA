import javax.swing.plaf.metal.MetalIconFactory.PaletteCloseIcon;

public class HashTable<T extends Comparable>{
	int 	capacity,size=0,maxLoadingFactor,PCTIncrement=20;
	Object 	table[]=null;
	
	protected void init(int cap,int MLF){
		capacity=cap;	maxLoadingFactor=MLF;
		table	=new Object[capacity];
	}
	
	public HashTable(){ init(10,80);}
	public HashTable(int cap,int mlf){ init(cap,mlf);}
	
	public int hash(T obj){
		if((size*100.0/capacity)>=maxLoadingFactor) rehash();
		int homeAddress=obj.hashCode()%capacity;//Member 中重写hashcode
		return homeAddress;
	}
	protected void rehash(){
		
		Object tmp[]=table;
		capacity=(int)(capacity*(1+PCTIncrement/100f));
		table=new Object[capacity];
		size=0;
		for(int i=0;i<tmp.length;i++){
			if(tmp[i]!=null) put((T)tmp[i]);
		}
		
	}
	
	public HashTable<T> put(T obj){
		int home=hash(obj);
		while(table[home]!=null) home=(home+1)%capacity;//循环数组
		table[home]=obj;
		size++;
		return this;
	}
	
	public T get(T obj){
		int home=find(obj);
		return (T) (home<0?null:table[home]);
	}
	
	public T remove(T obj){
		int home=find(obj);
		if(home<0)return null;
		size--;
		T data=(T)table[home];
		table[home]=null;
		shift(home,(home+1)%capacity);
		return data;
	}
	protected int find(T obj){
		int home=hash(obj);
		while(table[home]!=null&&((T)table[home]).compareTo(obj)!=0) home=(home+1)%capacity;
		return table[home]==null?-1:home;
	}
	public void shift(int empty,int cur){
		if(table[cur]==null) return;
		int home=table[cur].hashCode()%capacity;
		int curToHome=distance(home,cur);
		int emptyToHome=distance(home,empty);
		if(emptyToHome<curToHome){
			table[empty]=table[cur];
			table[cur]=null;
			shift(cur,(cur+1)%capacity);
		}else{
			shift(empty,(cur+1)%capacity);
		}
	}
	public int distance(int from,int to){
		return from<=to?to-from:capacity-from+to;
	}
	
	public void show(){
		System.out.printf("\n\tHash Table[%d-%d-%d]\n",capacity,size,maxLoadingFactor);
		int home;
		String str;
		System.out.printf("\t========================= Object ============================ cur home distance ====\n");
		for(int i=0;i<capacity;i++){
			if(table[i]==null){
				home=i;
				str="null";
			}
			else{
				home=hash((T)table[i]);
				str=table[i].toString();
			}
			System.out.printf("\t%20s %4d %4d %4d\n",str,
					i,home,distance(home,i));
		}
	}
}