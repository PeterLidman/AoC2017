package advent8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class advent8 {
	static String[] tree = new String[1005];
	static int antal = 0;
	static int plats = 0;
	private static int maximum;

	public static void main(String[] args) throws IOException {
		advent8 t = new advent8();
		HashMap<String, Integer> vars = new HashMap<String, Integer>();
		String[] a;
		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent8\\src\\advent8\\code.txt")).forEach(e -> t.insert(e));
		// print();
		for (int i = 0; i < antal; i++) {
			vars.put(getFirstWord(tree[i]), 0);
		}
		maximum = 0;
		for (int i = 0; i < antal; i++) {
			int tmp = 0;
			a = tree[i].split(" ");
			// System.out.println(" 0:" + a[0] + " 1:" + a[1] + " 2:" + a[2] + " 3:" + a[3]
			// + " 4:" + a[4] + " 5:" + a[5] + " 6:" + a[6]);

			switch (a[5]) {
			case "==":
				if (vars.get(a[4]) - Integer.valueOf(a[6]) == 0) {
					tmp = Integer.valueOf(a[2]) * (a[1].equals("inc") ? 1 : -1) + vars.get(a[0]);
					vars.put(a[0], tmp);
				}
				break;
			case "!=":
				if (vars.get(a[4]) - Integer.valueOf(a[6]) != 0) {
					tmp = Integer.valueOf(a[2]) * (a[1].equals("inc") ? 1 : -1) + vars.get(a[0]);
					vars.put(a[0], tmp);
				}
				break;
			case ">":
				if (vars.get(a[4]) > Integer.valueOf(a[6])) {
					tmp = Integer.valueOf(a[2]) * (a[1].equals("inc") ? 1 : -1) + vars.get(a[0]);
					vars.put(a[0], tmp);
				}
				break;
			case "<":
				if (vars.get(a[4]) < Integer.valueOf(a[6])) {
					tmp = Integer.valueOf(a[2]) * (a[1].equals("inc") ? 1 : -1) + vars.get(a[0]);
					vars.put(a[0], tmp);
				}
				break;
			case ">=":
				if (vars.get(a[4]) >= Integer.valueOf(a[6])) {
					tmp = Integer.valueOf(a[2]) * (a[1].equals("inc") ? 1 : -1) + vars.get(a[0]);
					vars.put(a[0], tmp);
				}
				break;
			case "<=":
				if (vars.get(a[4]) <= Integer.valueOf(a[6])) {
					tmp = Integer.valueOf(a[2]) * (a[1].equals("inc") ? 1 : -1) + vars.get(a[0]);
					vars.put(a[0], tmp);
				}
				break;
			default:
				System.out.println("åh fan= " + a[5]);
			}
			if (tmp > maximum) {
				maximum = tmp;
			}
		}
		System.out.println("Part 2: maximum= " + maximum);

		int max = 0;
		for (String aa : vars.keySet()) {
			if (vars.get(aa) > max) {
				max = vars.get(aa);
			}
		}
		System.out.println("Part 1: max= " + max);
	}

	static String[] getChildrenX(String in) {
		return getChildren(in).split(", ");
	}

	static String getChildren(String in) {
		int a = in.indexOf("->");
		if (a > 0) {
			return in.substring(a + 3);
		}
		return "";
	}

	static int getValue(String in) {
		return Integer.valueOf(in.substring(in.indexOf("(") + 1, in.indexOf(")")));
	}

	static String getFirstWord(String in) {
		return in.split(" ")[0];
	}

	static void print() {
		for (int i = 0; i < antal; i++) {
			System.out.println(i + 1 + "/" + antal + ":" + tree[i]);
		}
	}

	void insert(String in) {
		tree[antal++] = in;
	}
}
