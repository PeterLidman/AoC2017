package advent24;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class advent24b {
	static int antal = 0;
	static List<String> comp = new ArrayList<String>();
	static int maxcnt = 0;
	static int maxcntstr = 0;

	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent24\\src\\advent24\\components.txt")).forEach(e -> insert(e));
		// print();
		// System.exit(0);
		System.out.println("Max str= " + calcMaxStrenght(comp, "", 0, true, 0));
		System.out.println("Longest=" + maxcnt + " with str=" + maxcntstr);
	}

	static int calcMaxStrenght(List<String> inA, String bridge, int strenght, boolean frontPortUsed, int cnt) {
		int maxStyrka = strenght;
		//System.out.println("bro=" + bridge + " str=" + strenght);
		if (bridge.equals("")) {
			for (int i = 0; i < inA.size(); i++) {
				if (inA.get(i).charAt(0) == '0') {
					List<String> v = new ArrayList<String>();
					v.addAll(inA);
					String del = v.remove(i);
					int val = compValue(del);
					int maxv = calcMaxStrenght(v, bridge + "--" + del, val+strenght, true, cnt + 1);
					maxStyrka = Math.max(maxStyrka, maxv);
				}
			}
		} else {
			for (int i = 0; i < inA.size(); i++) {
				String ports[] = inA.get(i).split("/");
				int anslut = bridgeEnd(bridge, frontPortUsed);
				if (anslut == Integer.valueOf(ports[0])) {
					List<String> v = new ArrayList<String>();
					v.addAll(inA);
					String del = v.remove(i);
					int val = compValue(del);
					int maxv = calcMaxStrenght(v, bridge + "--" + del, val+strenght, true, cnt + 1);
					maxStyrka = Math.max(maxStyrka, maxv);
				}
				if (anslut == Integer.valueOf(ports[1])) {
					List<String> v = new ArrayList<String>();
					v.addAll(inA);
					String del = v.remove(i);
					int val = compValue(del);
					int maxv = calcMaxStrenght(v, bridge + "--" + del, val+strenght, false, cnt + 1);
					maxStyrka = Math.max(maxStyrka, maxv);
				}
			}
		}
		if (maxcnt <= cnt) {
			maxcnt = cnt;
			if (maxStyrka > maxcntstr) {
				maxcntstr = maxStyrka ;
			}
		}
		return maxStyrka;
	}

	static int bridgeEnd(String in, boolean front) {
		String b[] = in.split("--");
		String c[] = b[b.length - 1].split("/");
		return front ? Integer.valueOf(c[1]) : Integer.valueOf(c[0]);
	}

	static int compValue(String in) {
		String a[] = in.split("/");
		return Integer.valueOf(a[0]) + Integer.valueOf(a[1]);
	}

	static void print() {
		for (int i = 0; i < antal; i++) {
			System.out.println(i + 1 + "/" + antal + ":" + comp.get(i));
		}
	}

	static void insert(String in) {
		comp.add(in);
		antal++;
	}
}
