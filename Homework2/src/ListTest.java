import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class ListTest {

	Stack<Integer> stk = new Stack<Integer>();
	Queue<Integer> que = new Queue<Integer>();
	SQueue<Integer> squ = new SQueue<Integer>();
	Random rnd = new Random();
	static Scanner scn = new Scanner(System.in);
	static int num;

	public static void main(String args[]) {

		String order;
		ListTest app = new ListTest();
		System.out.println("Please enter the order");
		while (true) {
			System.out.print("order:");
			order = scn.nextLine();
			Scanner sn = new Scanner(System.in);
			switch (order) {
			case "G":
			case "g":
				System.out.print("Please enter the number of object:");
				num = sn.nextInt();
				app.fill();
				break;
			case "S":
			case "s":
				app.display("s");
				break;
			case "Q":
			case "q":
				app.display("q");
				break;
			case "O":
			case "o":
				app.display("o");
				break;
			case "D":
			case "d":
				app.remove();
				break;
			case "I":
			case "i":
				System.out.print("Please enter the member is added:");
				int i = sn.nextInt();
				app.add(i);
				break;
			case "H":
			case "h":
			case "?":
				System.out
						.println("+===================================================+");
				System.out
						.println("| G: Ask for a N, and generate N members of mixed   |");
				System.out
						.println("| kinds and put them into three list structures.    |");
				System.out
						.println("| Make sure you destroy the lists before create     |");
				System.out
						.println("| new ones if the lists are not empty.              |");
				System.out
						.println("+---------------------------------------------------+");
				System.out
						.println("| S/s: List members in stack, 1 member per line,    |");
				System.out
						.println("| 20 members per screen with header line, allow     |");
				System.out
						.println("| Q/q to quit listing after each 20 members.        |");
				System.out
						.println("+---------------------------------------------------+");
				System.out
						.println("| Q/q: List members in queue (same requirements).   |");
				System.out
						.println("+---------------------------------------------------+");
				System.out
						.println("| O/o: List members in ordered queue sorted by SSN  |");
				System.out
						.println("| (same requirements).                              |");
				System.out
						.println("+---------------------------------------------------+");
				System.out
						.println("| D/d: Remove first element from queue, pop member  |");
				System.out
						.println("| from stack, and delete the same member from       |");
				System.out
						.println("| sorted queue as the one removed from stack.       |");
				System.out
						.println("+---------------------------------------------------+");
				System.out
						.println("| I/i: Randomly generate new member, and put        |");
				System.out
						.println("| the object into the three structures. Print       |");
				System.out
						.println("| out the new member added in.                      |");
				System.out
						.println("+---------------------------------------------------+");
				System.out
						.println("| H/?/h: Display this menu.                         |");
				System.out
						.println("| E/e : Exit                                        |");
				System.out
						.println("+===================================================+");
				break;
			case "E":
			case "e":
				return;
			default:
				break;
			}
		}
	}

	void fill() {
		int v;
		for (int i = 0; i < num; i++) {
			v = rnd.nextInt(100);
			stk.push(v);
			que.enque(v);
			squ.enque(v);
		}

	}

	void display(String s) {
		switch (s) {
		case "s":
			display(stk, "Stack");
			break;
		case "q":
			display(que, "Queue");
			break;
		case "o":
			display(squ, "Sorted Queue");
			break;
		}

	}

	void display(List<Integer> l, String name) {

		Iterator<Integer> itr = l.iterator(true);
		int k = 0;
		int i = 0;
		System.out
				.printf("\n================= %s ====================\n", name);
		while (itr.hasNext()) {
			System.out.printf("%4d", itr.next());
			i++;
			if (i == 20) {
				i = 0;
				System.out.printf(
						"\n============== End %s listing ============\n", name);
				System.out
						.printf("Do you exit listing,if you want to exit,enter Q/q");
				Scanner s = new Scanner(System.in);
				String flog = s.nextLine();
				if (flog.equals("q") || flog.equals("Q")) {
					break;
				} else {
					System.out.printf(
							"\n================= %s ====================\n",
							name);
				}
			}
		}
		if (i != 20) {
			System.out.printf("\n============== End %s listing ============\n",
					name);
		}
	}

	void remove() {
		Integer vs = null, vq = null, vsq = null, vsq2 = null;
		if (!stk.empty())
			vs = stk.pop();
		if (!que.empty())
			vq = que.deque();
		if (!squ.empty())
			vsq = squ.deque();
		if (!squ.empty())
			vsq2 = squ.deque(vs);

		System.out
				.printf("\n================= Test of rmove ()  ====================\n");
		System.out.printf("\n\t\t%s is popped out from stack.\n", vs);
		System.out.printf("\n\t\t%s is dequed from queue .\n", vq);
		System.out.printf("\n\t\t%s is smalled value in sorted queue.\n", vsq);
		System.out
				.printf("\n\t\t%s is removed from sorted queue, euqals to top of statc.\n",
						vsq2);
		System.out.printf("\n============== End of Remove ============\n");

	}

	void add(int i) {
		stk.push(i);
		que.enque(i);
		squ.enque(i);
		System.out
				.printf("\n================= Test of Add ()  ====================\n");
		System.out.printf("\n\t\t%d is pushed in stack.\n", i);
		System.out.printf("\n\t\t%d is enqued in queue .\n", i);
		System.out.printf("\n\t\t%d is enqued in sorted queue.\n", i);
		System.out.printf("\n============== End of Add ============\n");
	}
}
