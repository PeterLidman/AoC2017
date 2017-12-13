package advent11;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class advent11 {
	static int antal = 0;
	static String[] tree = new String[10];
	static int c[] = new int[6];
	static int pc = 0;
	static String a = "";
	static String b = "";
	static int max = 0;
	static int distance = 0;

	public static void main(String[] args) throws IOException {
		advent11 t = new advent11();
		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent11\\src\\advent11\\hex.txt")).forEach(e -> t.insert(e));
		// print();
		for (int i = 0; i > 6; i++) {
			c[i] = 0;
		}
		b = tree[0];

		do {
			pc = b.indexOf(",");
			if (pc == -1) {
				a = b;
			} else {
				a = b.substring(0, pc);
				b = b.substring(pc + 1, b.length());
			}
			switch (a) {
			case "n":
				c[0]++;
				break;
			case "s":
				c[1]++;
				break;
			case "nw":
				c[2]++;
				break;
			case "ne":
				c[3]++;
				break;
			case "sw":
				c[4]++;
				break;
			case "se":
				c[5]++;
				break;
			default:
				System.out.println("äsch");
			}
			distance = dist();
			if (max < distance) {
				max = distance;
				// System.out.println("max=" + max);
			} else {
				// System.out.println("closer");
			}
		} while (pc > 0);

		for (int i = 0; i < 6; i++) {
			System.out.println(i + ":" + c[i]);
		}
		System.out.println("max=" + max + "final dist=" + dist());

	}

	static int dist() {
		boolean n = c[0] > c[1];
		boolean ne = c[3] > c[4];
		boolean se = c[5] > c[2];
		int nv = Math.abs(c[0] - c[1]);
		int nev = Math.abs(c[3] - c[4]);
		int sev = Math.abs(c[5] - c[2]);
		int minv = 0;

		if (n && ne && se) {
			return nev + absmin(sev,nv);
		}
		if (n && ne && !se) {
			return nv + absmin(nev, sev);
		}
		if (n && !ne && se) {
			minv = min3(nv, nev, sev);
			return nv + nev + sev - 3 * minv;
		}
		if (n && !ne && !se) {
			return sev + absmin(nv,nev);
		}
		if (!n && ne && se) {
			return sev + absmin(nev,nv);
		}
		if (!n && ne && !se) {
			minv = min3(nv, nev, sev);
			return nv + nev + sev - 3 * minv;
		}
		if (!n && !ne && se) {
			return nv + absmin(sev,nev);
		}
		// if (!n && !ne && !se) { }
		return nev + absmin(sev,nv);
	}

	static int absmin(int a, int b) {
		return Math.abs(a - b) + Math.min(a, b);
	}
	
	static int min3(int a, int b, int c) {
		return Math.min(a, Math.min(b, c));
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
