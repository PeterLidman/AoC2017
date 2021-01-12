package advent18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class advent18Part2 {
	static String[] tree = new String[41];
	static int antal = 0;
	static long sound = 0;
	static int pc = 0;
	static int pc2 = 0;
	static HashMap<String, Long> vars = new HashMap<String, Long>();
	static HashMap<String, Long> vars2 = new HashMap<String, Long>();
	static Queue<Long> q = new LinkedList<Long>();
	static Queue<Long> q2 = new LinkedList<Long>();
	static String[] a = new String[10];
	static Long qV = null;
	static int sendingValue = 0;
	static boolean waiting = false;
	static boolean waiting2 = false;

	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent18\\src\\advent18\\code.txt")).forEach(e -> insert(e));
		// print();
		vars2.put("p", 1L);
		do {
			// program 0
//			status();
			a = tree[pc].split(" ");
			switch (a[0]) {
			case "snd":
				q2.add(getV(a[1]));
				break;
			case "set":
				vars.put(a[1], getV(a[2]));
				break;
			case "add":
				vars.put(a[1], (vars.get(a[1]) != null) ? getV(a[1]) + getV(a[2]) : getV(a[2]));
				break;
			case "mul":
				vars.put(a[1], (vars.get(a[1]) != null) ? getV(a[1]) * getV(a[2]) : 0);
				break;
			case "mod":
				vars.put(a[1], (vars.get(a[1]) != null) ? getV(a[1]) % getV(a[2]) : 0);
				break;
			case "rcv":
				if ((qV = q.poll()) != null) {
					vars.put(a[1], qV);
					waiting=false;
				} else {
					pc--; // stå still, dvs gör om detta tills andra programmet jobbat ifatt
					waiting=true;
				}
				break;
			case "jgz":
				if (getV(a[1]) > 0) {
					pc += getV(a[2]) - 1;
				}
				break;
			default:
				System.out.println("prg0 åh fan= " + a[0]);
			}
			pc++;
			// program 1
//			status2();
			a = tree[pc2].split(" ");
			switch (a[0]) {
			case "snd":
//				System.out.println("sänding=" + sendingValue);
				sendingValue++;
				q.add(getV2(a[1]));
				break;
			case "set":
				vars2.put(a[1], getV2(a[2]));
				break;
			case "add":
				vars2.put(a[1], (vars2.get(a[1]) != null) ? getV2(a[1]) + getV2(a[2]) : getV2(a[2]));
				break;
			case "mul":
				vars2.put(a[1], (vars2.get(a[1]) != null) ? getV2(a[1]) * getV2(a[2]) : 0);
				break;
			case "mod":
				vars2.put(a[1], (vars2.get(a[1]) != null) ? getV2(a[1]) % getV2(a[2]) : 0);
				break;
			case "rcv":
				if ((qV = q2.poll()) != null) {
					vars2.put(a[1], qV);
					waiting2=false;
				} else {
					pc2--; // stå still, dvs gör om detta tills andra programmet jobbat ifatt
					waiting2=true;
				}
				break;
			case "jgz":
				if (getV2(a[1]) > 0) {
					pc2 += getV2(a[2]) - 1;
				}
				break;
			default:
				System.out.println("prg1 åh fan= " + a[0]);
			}
			pc2++;

			if (q2.isEmpty() && q.isEmpty() && waiting && waiting2) { // nått deadlock
				System.out.println("Part 2: Deadlock, Prg1 sent= " + sendingValue + " times");
				System.exit(0);
			}
		} while (true);
	}

	static void status() {
		String ut = "pc=" + pc + " code=" + tree[pc] + " Vars=";
		for (String a : vars.keySet()) {
			ut += " " + a + "=" + vars.get(a);
		}
		System.out.println(ut);
	}

	static void status2() {
		String ut = "pc2=" + pc2 + " code=" + tree[pc2] + " Vars=";
		for (String a : vars2.keySet()) {
			ut += " " + a + "=" + vars2.get(a);
		}
		System.out.println(ut);
	}

	static long getV(String in) {
		if (in.chars().allMatch(Character::isLetter)) {
			return vars.get(in);
		} else {
			return Long.valueOf(in);
		}
	}

	static long getV2(String in) {
		if (in.chars().allMatch(Character::isLetter)) {
			return vars2.get(in);
		} else {
			return Long.valueOf(in);
		}
	}

	static void print() {
		for (int i = 0; i < antal; i++) {
			System.out.println(i + 1 + "/" + antal + ":" + tree[i]);
		}
	}

	static void insert(String in) {
		tree[antal++] = in;
	}
}
