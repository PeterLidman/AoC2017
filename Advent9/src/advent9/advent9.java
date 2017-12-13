package advent9;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class advent9 {
	static String[] tree = { "", "", "", "", "" };
	static String burp = "";
	static int antal = 0;
	static boolean cleanMode = false;
	static String tkn;
	static int ccl = 0;
	static int totsum = 0;
	static int garbage = 0;

	public static void main(String[] args) throws IOException {
		advent9 t = new advent9();
		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent9\\src\\advent9\\stream.txt")).forEach(e -> t.insert(e));
		filter(0);
		filter2();
//		print();
		System.out.println("{= " + count("{", 2) + " }= " + count("}", 2));
		System.out.println("Svar= " + calc(tree[2]));
		System.out.println("garbage= " + garbage);
	}

	static int count(String in, int col) {
		int counter = 0;

		for (int i = 0; i < tree[col].length(); i++) {
			tkn = tree[col].substring(i, i + 1);
			if (in.equals(tkn)) {
				counter++;
			}
		}
		return counter;
	}

	static int calc(String in) {
		for (int i = 0; i < in.length(); i++) {
			tkn = tree[2].substring(i, i + 1);
			if ("{".equals(tkn)) {
				ccl++;
				totsum += ccl;
			}
			if ("}".equals(tkn)) {
				ccl--;
			}
		}
		return totsum;
	}

	static void filter2() {
		for (int i = 0; i < tree[1].length(); i++) {
			tkn = tree[1].substring(i, i + 1);
			if (tkn.equals("<")) {
				if (!cleanMode) {// räkna inte första <
					garbage--;
				}
				cleanMode = true;
			}
			if (tkn.equals(">")) {
				cleanMode = false;
			} else {
				if (!cleanMode) {
					tree[2] += tkn;
				} else { // rensas bort
					garbage++;
				}
			}
		}
		antal++;
	}

	static void filter(int plats) {
		for (int i = 0; i < tree[plats].length(); i++) {
			tkn = tree[0].substring(i, i + 1);
			if ("!".equals(tkn)) {
				i++;
			} else {
				tree[1] += tkn;
			}
		}
		antal++;
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
