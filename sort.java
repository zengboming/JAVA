//Author: maoyusu
//Student ID: M201576111
//Description: a program that implements heap-sort with multi-thread and piped input and output features
  
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;


class T_merge extends Thread {
	PipedInputStream in1 = null;
	PipedInputStream in2 = null;
	PipedOutputStream out = null;
	int[] array1 = null;
	int len_ary1;
	
	int[] array2 = null;
	int len_ary2;
	
	int[] sorted_array = null;
	int len_sary;
	int[] both = null;
	int is_Base = 0;
	public T_merge(	PipedOutputStream out1,
					int len_ary1,
				   	PipedOutputStream out2,
				   	int len_ary2) {
		super();
		this.len_ary1 = len_ary1;
		
		this.len_ary2 = len_ary2;
		len_sary = len_ary1 + len_ary2;
		in1 = new PipedInputStream();
		in2 = new PipedInputStream();
		out = new PipedOutputStream();
		
		try {
			in1.connect(out1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in2.connect(out2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public T_merge(int[] array) {
		len_sary = array.length;
		sorted_array = array.clone();
		Arrays.sort(sorted_array);
		is_Base = 1;
		out = new PipedOutputStream();
	}
	
	public static void Disp(int[] array) {
		int k = 0;
		int l = 0;
		for(int i = 0; i < array.length; i++) {
			
			if(k == 10) {
				System.out.print("\n");
				k = 0;
				l++;
				if(l == 20) {
					char m;
					try {
						System.out.println("continue display?(Y/n)");
						m = (char) System.in.read();
						if(m == 'N' || m == 'n') {
							System.out.println();
							if(array.length >= 30) {
								int mk = 0;
								for(int last = array.length - 30; last < array.length; last++) {
									if(mk == 10) {
										System.out.print("\n");
										mk = 0;
									}
									System.out.print(Integer.toString(array[last]) + "\t");
									mk++;
								}
							}
							break;
							
						} else {
							System.out.println();
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					l = 0;
				}
			}			
			k = k + 1;
			System.out.print(Integer.toString(array[i]) + "\t");
		}
		
		System.out.print("\n");
	}
	
	void MergeArray(int a[], int b[], int c[])  
	{  
	    int i, j, k;  
	    int n = a.length;
	    int m = b.length;
	    i = j = k = 0;  
	    while (i < n && j < m)  
	    {  
	        if (a[i] < b[j])  
	            c[k++] = a[i++];  
	        else  
	            c[k++] = b[j++];   
	    }  
	  
	    while (i < n)  
	        c[k++] = a[i++];  
	  
	    while (j < m)  
	        c[k++] = b[j++];  
	}  
	
	public PipedOutputStream getOutPipe() {
		return out;
	}
	
	@Override
	public void run() {
		if(is_Base == 1) {
			ByteBuffer byteBuffer = ByteBuffer.allocate(sorted_array.length * 4);        
	        IntBuffer intBuffer = byteBuffer.asIntBuffer();
	        intBuffer.put(sorted_array);

	        byte[] sorted_byte_array = byteBuffer.array();
			try {
				out.write(sorted_byte_array);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			byte[] in_byte1 = new byte[len_ary1 * 4];
			byte[] in_byte2 = new byte[len_ary2 * 4];
			
			
			try {
				int off = 0;
				while(off < (len_ary1 * 4)) {
					in1.read(in_byte1, off, len_ary1 * 4 - off);
					off += 1024;
				}
				off = 0;
				while(off < (len_ary2 * 4)) {
					in2.read(in_byte2, off, len_ary2 * 4 - off);
					off += 1024;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				in1.close();
				in2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			IntBuffer intBuf1 = ByteBuffer.wrap(in_byte1).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
			int[] in_array1 = new int[intBuf1.remaining()];
			intBuf1.get(in_array1);
			
			IntBuffer intBuf2 = ByteBuffer.wrap(in_byte2).order(ByteOrder.BIG_ENDIAN).asIntBuffer();
			int[] in_array2 = new int[intBuf2.remaining()];
			intBuf2.get(in_array2);
			
			both = new int[len_ary1 + len_ary2];
			
			MergeArray(in_array1, in_array2, both);
			
			
			try {
				ByteBuffer byteBuffer = ByteBuffer.allocate(both.length * 4);        
		        IntBuffer intBuffer = byteBuffer.asIntBuffer();
		        intBuffer.put(both);
		        byte[] both_byte_array = byteBuffer.array();
				out.write(both_byte_array);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				//Disp(both);
				//System.out.println("done.");
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class MuiltiThread_Sort {
	T_merge[] A_m = new T_merge[32];
	T_merge[] B_m = new T_merge[16];
	T_merge[] C_m = new T_merge[8];
	T_merge[] D_m = new T_merge[4];
	T_merge[] E_m = new T_merge[2];
	T_merge[] F_m = new T_merge[1];
	
	int[][] arr_ = new int[32][];
	
	int thread_num = 0;
	int per_len = 0;
	int last_len = 0;
	public MuiltiThread_Sort(int[] array) {
		// TODO Auto-generated constructor stub
		per_len = array.length / 31;
		last_len = array.length - per_len * 31;

		for(int i = 0; i < 31; i++) {
			arr_[i] = new int[per_len];
		}
		arr_[31] = new int[last_len];
		int k = 0;
		for(int i = 0; i < 32; i++) {
			//System.out.println(arr_[i].length);
			for(int j = 0; j < arr_[i].length; j++) {
				arr_[i][j] = array[k++];
			}
		}
		//T_merge.Disp(arr_[30]);
		for(int i = 0; i < 32; i++) {
			A_m[i] = new T_merge(arr_[i]);
		}
		for(int i = 0; i < 16; i++) {
			B_m[i] = new T_merge(A_m[2*i].getOutPipe(), A_m[2*i].len_sary,
									A_m[2*i+1].getOutPipe(), A_m[2*i+1].len_sary);
		}
		for(int i = 0; i < 8; i++) {
			C_m[i] = new T_merge(B_m[2*i].getOutPipe(), B_m[2*i].len_sary,
									B_m[2*i+1].getOutPipe(), B_m[2*i+1].len_sary);
		}
		for(int i = 0; i < 4; i++) {
			D_m[i] = new T_merge(C_m[2*i].getOutPipe(), C_m[2*i].len_sary,
		 			 				C_m[2*i+1].getOutPipe(), C_m[2*i+1].len_sary);
		}
		for(int i = 0; i < 2; i++) {
			E_m[i] = new T_merge(D_m[2*i].getOutPipe(), D_m[2*i].len_sary,
		 			 			  	D_m[2*i+1].getOutPipe(), D_m[2*i+1].len_sary);
		}
		for(int i = 0; i < 1; i++) {
			F_m[i] = new T_merge(E_m[2*i].getOutPipe(), E_m[2*i].len_sary,
									E_m[2*i+1].getOutPipe(), E_m[2*i+1].len_sary);
		}
	}
	public void start_sort() {
		for(int i = 0; i < 32; i++) {
			A_m[i].start();
		}
		for(int i = 0; i < 16; i++) {
			B_m[i].start();
		}
		for(int i = 0; i < 8; i++) {
			C_m[i].start();
		}
		for(int i = 0; i < 4; i++) {
			D_m[i].start();
		}
		for(int i = 0; i < 2; i++) {
			E_m[i].start();
		}
		for(int i = 0; i < 1; i++) {
			F_m[i].start();
		}
	}
	public Boolean is_Alive() {
		return F_m[0].isAlive();
	}
}
public class sort {
	static int M = 5000;
	static int[] test_array = null;
	public static void showMenu(){
		System.out.print(
		 "+----------------------------------------------------------------------------+\n"
		+"| G/g: Ask a integer N >= 1,000,000, and generate a integer array of N       |\n" 
		 
		+"|      elements, and fill the array with N integers (int).                   |\n"
		
		+"+----------------------------------------------------------------------------+\n"
		        
		+"| C/c: Display the contents of array, 10 integers per line, and 20 lines     |\n"
		        
		+"|      screen. Allow quit during the display. If it quits in the middle,     |\n"
		       
		+"|      the last 3 lines must be display.                                     |\n"
		       
		+"+----------------------------------------------------------------------------+\n"
		        
		+"| S/s: Sort the array using multithreaded piped I/O algorithm described above|\n"
		        
		+"+----------------------------------------------------------------------------+\n"
		        
		+"| F/f: Shuffle the data.                                                     |\n"
		    
		+"+----------------------------------------------------------------------------+\n"
		        
		+"| H/h/?: Show this menu.                                                     |\n"
		        
		+"+----------------------------------------------------------------------------+\n"
		       
		+"| E/Q: Exit or quit the program.                                             |\n" 
		    
		+"+----------------------------------------------------------------------------+\n"
		+"input:");
		
		
		String input = "H";
		Scanner in = new Scanner(System.in);
		input = in.next();
		

		if(input.equals("H") || input.equals("h")) {
			showMenu();
		}

		
		if(input.equals("G") || input.equals("g")) {
			System.out.println("please enter an Integer:");
	        M = in.nextInt();
			System.out.println(M);
			test_array = new int[M];
			Random r = new Random();
			for(int i = 0; i < M; i++) {
				test_array[i] = r.nextInt(M);
				//test_array[i] = M - i;
			}
			showMenu();
		}
		if(input.equals("C") || input.equals("c")) {
			T_merge.Disp(test_array);
			showMenu();
		}
		if(input.equals("S") || input.equals("s")) {
			long t1 = System.currentTimeMillis();
			
			MuiltiThread_Sort ms = new MuiltiThread_Sort(test_array);
			ms.start_sort();
			
			while(ms.is_Alive());
			test_array = ms.F_m[0].both;
			long t2 = System.currentTimeMillis();
			System.out.print("time used:");
			System.out.print((t2-t1)/1000f);
			System.out.print(" s\n");
			
			showMenu();
		}
		
		if(input.equals("F") || input.equals("f")) {
			Random r = new Random();
			for(int i = 0; i < M; i++) {
				int j = r.nextInt(M);
				int temp = test_array[i];
				test_array[i] = test_array[j];
				test_array[j] = temp;
			}
			showMenu();
		}
		
		if(input.equals("Q") || input.equals("q")) {
			System.exit(0);
		}
		
		
		
	}
	public static void main(String[] args) {
		System.out.print("Author: 毛煜苏\n");
		System.out.print("Student ID: M201576111\n");
		showMenu();

	}

}
