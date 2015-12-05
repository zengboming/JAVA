import java.util.Scanner;
//java:how to launch default browser
//java vector
//arrays/collection.sort:to sort array
//Vector<Member> ve=new Vector<Member>;
public class MemberTest{
	static int num;
	Member ma[]=null;
	
	public static void main(String args[]){
		
		//Member m=new Member();
		MemberTest app=new MemberTest();
		Scanner sca=new Scanner(System.in);
		System.out.println("Enter H/h/? for help, or command :");
		String s=sca.nextLine();
		char c='h';
		if(s.toUpperCase().charAt(0)=='H'||s.charAt(0)=='?'){
			c=app.menu();
		}
		while(true){
		switch (c) {
		case 'G':
			//clear
			app.fill();
			app.menu();
			break;
		case 'S':
			
			break; 
		case 'V':
			app.show();
			app.menu();
			break; 
		case 'O':
			
			break; 
		case 'F':
			
			break; 
		case 'L':
			
			break; 
		case 'H':
			app.menu();
			break; 
		case 'E':
			System.exit(0);
			break; 
		default:
			System.out.printf("Wrong Input!Please enter the correct key");
			app.menu();
			break;
		}
		}
		//app.fill();
		//app.show();
	}
	
	public void fill(){
		System.out.printf("please input number:");
		Scanner sc=new Scanner(System.in);
		num=sc.nextInt();
		ma=new Member[num];
		for(int i=0;i<ma.length;i++)
			ma[i]=getMember();
		System.out.printf("generate success!\n");
	}
	
	Member getMember(){
		switch(Member.rnd.nextInt(5)){
		case 0: return new Member();
		case 1:	return new Student();
		case 2: return new Employee();
		case 3: return new Faculty();
		case 4: return new Staff();
		default:return null;
		}
	}
	
	public void show(){
		String s;
		Scanner sc=new Scanner(System.in);
		for(int i=0;i<=ma.length;i++){
			System.out.printf("%4d:%40s\n",i+1,ma[i].toString(true));
			if((i+1)%40==0){
				System.out.printf("\n\t\t Enter 'Q' to quit, return to continue...");
				s=sc.nextLine();
				if(s.toUpperCase().charAt(0)=='Q') continue;
			}
		}
	}
	
	public char menu(){
		System.out.printf("\n\t\t================ CS 394 Assignment 1===============\n" +
				"\t\t G/g: Ask for a N, and generate N members of\n\t\t mixed Member " +
				"class's objects, and store in a Vector\n\t\t and a array Objects.\n\n" +
				"\t\t S/s/ : Sort the members in the vector and array\n\t\t inascending " +
				"order.\n\n\t\t V/v/ : Show the members in the vector and array .\n\n" +
				"\t\t O/o/ : Save objects inside vector into a HTML file\n\t\t with objects" +
				" saved in the format of HTML Table.\n\n" +
				"\t\t F/f : Show HTML file contents on screen.\n\n" +
				"\t\t L/l : Launch the default internet browser to\n\t\t display the " +
				"generated HTML file.\n" +
				"\t\t --------------------------------------------------\n" +
				"\t\t H/h/?: Display this menu.\n" +
				"\t\t E/e : Exit\n" +
				"\t\t =================================================\n");
		Scanner sca=new Scanner(System.in);
		String s1=sca.nextLine();
		char c=s1.toUpperCase().charAt(0);
		return c;
	}
}