package advent23;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class advent23 {
	static String[] tree = new String[41];
	static int antal = 0;
	static int plats = 0;
	static int maximum = 0;
	static long sound = 0;
	static int pc = 0;
	static HashMap<String, Long> vars = new HashMap<String, Long>();
	static String[] a = new String[10];
	private static int howManyTimesMul=0;

	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent23\\src\\advent23\\code.txt")).forEach(e -> insert(e));
		//print();
		//System.exit(0);
		vars.put("a", 0L);
		vars.put("b", 0L);
		vars.put("c", 0L);
		vars.put("d", 0L);
		vars.put("e", 0L);
		vars.put("f", 0L);
		vars.put("g", 0L);
		vars.put("h", 0L);
		do {
			status();
			a = tree[pc].split(" ");
			switch (a[0]) {
			case "set":
				vars.put(a[1], getV(a[2]));
				break;
			case "sub":
				vars.put(a[1], (vars.get(a[1]) != null) ? getV(a[1]) - getV(a[2]) : (-1 * getV(a[2])));
				break;
			case "mul":
				howManyTimesMul++;
				vars.put(a[1], (vars.get(a[1]) != null) ? getV(a[1]) * getV(a[2]) : 0);
				break;
			case "jnz":
				if (getV(a[1]) != 0) {
					pc += getV(a[2]) - 1;
				}
				break;
			default:
				System.out.println("åh fan= " + a[0]);
			}
			pc++;
		} while (pc < antal && pc > 0);
		System.out.println("how many times mul is executed= " + howManyTimesMul);
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
