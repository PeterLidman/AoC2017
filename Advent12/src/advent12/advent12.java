package advent12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class advent12 {
	static String[] tree = new String[3000];
	static int antal = 0;
	static int groups = 1;
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent12\\src\\advent12\\plum.txt")).forEach(e -> advent12.insert(e));
//		print();
		fillMap(0);
		System.out.println("Part 1: " + map.size());

		// Part 2
		for (int i = 0; i < 2000; i++) {
			if (!map.containsKey(i)) {// new group
				if (!map.containsValue(i)) {
					groups++;
					fillMap(i);
				}
			}
		}
		System.out.println("Part 2: antal grupper=" + groups);

//		System.out.println("groups=" + antal);

		// for ( Integer a : map.keySet()) {
		// System.out.println(":" + a + " @" + map.get(a));
		// }
	}

	static void fillMap(int pos) {
		String de;
		int pl = 0;
		int opl = 0;

		if (map.get(pos) != null) {
			// System.out.println("redan kollat " + pos);
			return;
		}
		de = tree[pos].substring(tree[pos].indexOf(">") + 2);
//		System.out.println("rest=" + de);
		do {
			pl = de.indexOf(",", pl + 1);
			if (pl < 0) {// sista
				map.put(pos, Integer.valueOf(de.substring(opl)));
				fillMap(Integer.valueOf(de.substring(opl)));
				// System.out.println(":" + de.substring(opl) + ":");
			} else {
				map.put(pos, Integer.valueOf(de.substring(opl, pl)));
				fillMap(Integer.valueOf(de.substring(opl, pl)));
				// System.out.println("::" + de.substring(opl, pl) + "::");
			}
			opl = pl + 2;
		} while (pl > 0);
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
