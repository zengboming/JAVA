import java.util.Scanner;

public class HashTableTest{
	HashTable<Member> ht =new HashTable<Member>(); 
	Scanner scn=new Scanner(System.in);
	
	public static void main(String args[]){
		HashTableTest app=new HashTableTest();
		app.fill();
		app.show();
		app.testRemoveAndPut();
	}
	
	public void show() {
		// TODO Auto-generated method stub
		ht.show();
	}

	public void fill(){
		for(int i=0;i<8;i++)
			ht.put(new Member());
	}
	
	public void testRemoveAndPut(){
		String line;
		char ch;
		while(true){
			System.out.printf("\t\tEnter A for add new member, R to remove a member");
			line=scn.nextLine();
			ch=line.trim().toUpperCase().charAt(0);
			switch(ch){
				case 'A': addMember();break;
				case 'R': removeMember();break;
				default	: System.out.printf("\n\t\tIllegal selection!\n");
			}
			ht.show();
		}
	}

	public void removeMember() {
		int id=readId();
		ht.remove(new Member(id));
		
	}

	public void addMember() {
		int id=readId();
		ht.put(new Member(id));
	}
	
	public int readId(){
		System.out.printf("\n\t\tEnter a id:");
		int id = Integer.parseInt(scn.nextLine());
		return id;
	}
}