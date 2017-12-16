package advent16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class advent16 {
	static String[] row = new String[2];
	static int antal = 0;
	static String moves[] = new String[10000];
	static HashMap<String, Long> unika = new HashMap<String, Long>();
	static String param[] = new String[2];
	static int move = 0;
	static long walk = 1;

	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent16\\src\\advent16\\dance.txt")).forEach(e -> insert(e));
		String newOrder = "abcde";
		newOrder = spin(1, newOrder);
		System.out.println(newOrder);
		newOrder = exchange(3, 4, newOrder);
		System.out.println(newOrder);
		newOrder = partner("e".charAt(0), "b".charAt(0), newOrder);
		System.out.println(newOrder);
		newOrder = "abcdefghijklmnop";
		moves = row[0].split(",");
		do {
			move = 0;
			do {
				// System.out.println(moves[move]);
				switch (moves[move].charAt(0)) {
				case 's':
					newOrder = spin(Integer.valueOf(moves[move].substring(1)), newOrder);
					break;
				case 'x':
					param = moves[move].substring(1).split("/");
					newOrder = exchange(Integer.valueOf(param[0]), Integer.valueOf(param[1]), newOrder);
					break;
				case 'p':
					param = moves[move].substring(1).split("/");
					newOrder = partner(param[0].charAt(0), param[1].charAt(0), newOrder);
					break;
				default:
					System.out.println("nämenvadfannudå!");
				}
				move++;
			} while (moves.length > move);
			System.out.println("nr=" + walk + ":" + newOrder);
			if (null != unika.put(newOrder, walk)) {// rep found, fast forward walk
				System.out.println("repetition vid= " + walk + ":" + newOrder);
				unika.clear();// ignore the rest
				System.out.println("kvar att dansa" + (1_000_000_000 % walk));
				walk = (1_000_000_000 - (1_000_000_000 % (walk - 1))) + 1;// redan gjort den första dansen av resten
			}
		} while (walk++ < 1_000_000_000);
		System.out.println("efter 1^9 danser= " + newOrder);
	}

	static String spin(int d, String in) {
		return in.substring(in.length() - d) + in.substring(0, in.length() - d);
	}

	static String exchange(int a, int b, String in) {
		char ca[] = in.toCharArray();
		char tmp = ca[a];
		ca[a] = ca[b];
		ca[b] = tmp;
		return String.valueOf(ca);
	}

	static String partner(char ac, char bc, String in) {
		char ca[] = in.toCharArray();
		int a = 0, b = 0;
		for (int i = 0; i < in.length(); i++) {
			if (ca[i] == ac) {
				a = i;
			}
			if (ca[i] == bc) {
				b = i;
			}
		}
		return exchange(a, b, in);
	}

	static void insert(String in) {
		row[antal++] = in;
	}
}
