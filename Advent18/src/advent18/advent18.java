package advent18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class advent18 {
	static String[] tree = new String[41];
	static int antal = 0;
	static int plats = 0;
	static int maximum = 0;
	static long sound = 0;
	static int pc = 0;
	static HashMap<String, Long> vars = new HashMap<String, Long>();
	static String[] a = new String[10];

	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent18\\src\\advent18\\code.txt")).forEach(e -> insert(e));
		// print();
		do {
			status();
			a = tree[pc].split(" ");
			switch (a[0]) {
			case "snd":
				sound = getV(a[1]);
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
				if (getV(a[1]) != 0) {
					System.out.println("Sound is=" + sound);
					System.exit(0);
				}
				break;
			case "jgz":
				if (getV(a[1]) > 0) {
					pc += getV(a[2]) - 1;
				}
				break;
			default:
				System.out.println("åh fan= " + a[0]);
			}
			pc++;
		} while (pc < 41 && pc > 0);
	}

	static void status() {
		String ut = "pc=" + pc + " code=" + tree[pc] + " Vars=";
		for (String a : vars.keySet()) {
			ut += " " + a + "=" + vars.get(a);
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

	static void print() {
		for (int i = 0; i < antal; i++) {
			System.out.println(i + 1 + "/" + antal + ":" + tree[i]);
		}
	}

	static void insert(String in) {
		tree[antal++] = in;
	}
}
