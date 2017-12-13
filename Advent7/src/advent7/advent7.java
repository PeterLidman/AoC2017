package advent7;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class advent7 {
	static String[] tree = new String[1300];
	static int antal = 0;
	static int plats = 0;

	public static void main(String[] args) throws IOException {
		advent7 t = new advent7();
		String[] a;
		List<String> c = new ArrayList<String>();

		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent7\\src\\advent7\\tree.txt")).forEach(e -> t.insert(e));

		print();

		System.out.println("chi :" + getChildren(tree[0]));
		a = getChildrenX(tree[0]);
		System.out.println("chi :" + a.length);
		System.out.println("chi :" + a[0]);
		System.out.println("chi :" + a[1]);
		System.out.println("val :" + getValue(tree[0]));
		System.out.println("word:" + getFirstWord(tree[0]));

		for (int i = 0; i < antal; i++) {
			for (String aa : getChildrenX(tree[i])) {
				c.add(aa);
			}
		}
		for (int i = 0; i < antal; i++) {
			if (getChildren(tree[i]).length() > 0) {
				if (!c.contains(getFirstWord(tree[i]))) {
					System.out.println("hittade:" + tree[i] + "på plats" + i);
					plats = i;
				}
			}
		}
		System.out.println("klart");

		System.out.println(printSum(plats));
		System.out.println(printSum(getPosOfWord("gjxqx")));
		System.out.println(printSum(getPosOfWord("yruivis")));
		System.out.println(printSum(getPosOfWord("sphbbz")));
		System.out.println("felet:" + getPosOfWord("sphbbz"));
		System.out.println("2671 - 7*217 =" + (2671 - 7*217));
	}
	
	static String printSum(int pl) {
		int place;
		String strsumma = "- " + getFirstWord(tree[pl]) + " " + summera(pl);
				
		if (getChildren(tree[pl]).length() > 0) {
			strsumma += " (";
			for (String aa : getChildrenX(tree[pl])) {
				place = getPosOfWord(aa);
				strsumma += getFirstWord(tree[place]) + " " + summera(place) + " + ";
			}
			strsumma += ")";
		}
		return strsumma;
	}

	static int summera(int pl) {
		int summa = getValue(tree[pl]);
		if (getChildren(tree[pl]).length() > 0) {
			for (String aa : getChildrenX(tree[pl])) {
				summa += summera(getPosOfWord(aa));
			}
		}
		return summa;
	}

	static int getPosOfWord(String in) {
		for (int i = 0; i < antal; i++) {
			if (in.equals(getFirstWord(tree[i]))) {
				return i;
			}
		}
		return -1;
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
