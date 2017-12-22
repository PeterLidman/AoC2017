package advent21;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class advent21 {
	private static final String PATTERN = "(.*) => (.*)";
	static int antal = 0;
	static String bok[][] = new String[300][2];
	static HashMap<String, String> fullBok = new HashMap<String, String>();
	static String start = ".#...####";

	public static void main(String[] args) throws Exception {
		Files.readAllLines(Paths.get("C:\\git\\AoC2017\\Advent21\\src\\advent21\\book1.txt")).forEach(e -> readLine(e));
		// kontrolleraImporten();
		// fyll i boken
		for (int i = 0; i < antal; i++) {
			String s = bok[i][0].replaceAll("/", "");
			String r = bok[i][1].replaceAll("/", "");
			fullBok.put(s, r);
			// fullBok.put(flippaUN(s), r);
			fullBok.put(flippaHV(s), r);

			fullBok.put(rotera(s), r);
			// fullBok.put(flippaUN(rotera(s)), r);
			fullBok.put(flippaHV(rotera(s)), r);

			fullBok.put(rotera(rotera(s)), r);
			// fullBok.put(flippaUN(rotera(rotera(s))), r);
			fullBok.put(flippaHV(rotera(rotera(s))), r);

			fullBok.put(rotera(rotera(rotera(s))), r);
			// fullBok.put(flippaUN(rotera(rotera(rotera(s)))), r);
			fullBok.put(flippaHV(rotera(rotera(rotera(s)))), r);
		}
		System.out.println("bok=" + antal + " fullbok= " + fullBok.size());
		for (String a : fullBok.keySet()) {
			System.out.println(a + " : " + fullBok.get(a));
		}

		// System.out.println(rotera("1234"));
		// System.out.println(flippaUN("1234"));
		// System.out.println(flippaHV("1234"));
		// System.out.println(rotera("123456789"));
		// System.out.println(flippaUN("123456789"));
		// System.out.println(flippaHV("123456789"));
		// System.out.println(rotera(rotera("123456789")));
		// System.out.println(rotera(rotera(rotera("123456789"))));
		// System.out.println(rotera(rotera(rotera(rotera("123456789")))));
		// System.out.println(fullBok.get(start));
		System.out.println(countRecursive(2, start));
	}

	static int countRecursive(int iterations, String image) {
		int retur = 0;
		String tmpc[] = image.split("");
		String tmp = "";
		System.out.println("iter=" + iterations);
		ritaUt(image);

		if (iterations < 1) { // count # i ett löv
			for (int i = 0; i < tmpc.length; i++) {
				if (tmpc[i].equals("#")) {
					retur++;
				}
			}
			System.out.println("räknade #=" + retur);
			return retur;
		}
		if (image.length() == 4) { // 2*2
			return countRecursive(iterations - 1, fullBok.get(image));
		}
		if (image.length() == 9) { // 3*3
			return countRecursive(iterations - 1, fullBok.get(image));
		}
		if (image.length() > 9) {
			int side = (int) Math.sqrt(image.length());
			tmp = "";
			if (image.length() % 2 == 0) {
				System.out.println("%2 side");
				for (int i = 0; i < side; i += 2) {
					for (int j = 0; j < side; j += 2) {
						tmp += fullBok.get(image.substring(j + i * side, j + i * side + 2)
								+ image.substring(j + (i + 1) * side, j + (i + 1) * side + 2));
					}
				}
				//System.out.println(tmp);
				return countRecursive(iterations - 1, tmp);
			}
			// 3
			System.out.println("%3 side");
			for (int i = 0; i < side; i += 3) {
				for (int j = 0; j < side; j += 3) {
					tmp += fullBok.get(image.substring(j + i * side, j + i * side + 3)
							+ image.substring(j + (i + 1) * side, j + (i + 1) * side + 3)
							+ image.substring(j + (i + 2) * side, j + (i + 2) * side + 3));
				}
			}
			//System.out.println(tmp);
			return countRecursive(iterations - 1, tmp);
		}
		System.out.println("nä men va fan nu då!");
		return -1;
	}

	// static String kvadrant4to2(int k, String in) {
	// String s[] = in.split("");
	// String ut = "";
	// if (in.length() == 16) {
	// switch (k) {
	// case 1:
	// ut = s[2] + s[3] + s[6] + s[7];
	// break;
	// case 2:
	// ut = s[0] + s[1] + s[4] + s[5];
	// break;
	// case 3:
	// ut = s[8] + s[9] + s[12] + s[13];
	// break;
	// case 4:
	// ut = s[10] + s[11] + s[14] + s[15];
	// break;
	// default:
	// System.out.println("åh näj");
	// }
	// }
	// System.out.println("kvad=" + k + " = " + ut);
	// return ut;
	// }
	static void ritaUt(String in) {
		int side = (int) Math.sqrt(in.length());
		for (int j = 0; j < side; j++) {
			System.out.println(in.substring(j * side, j * side + side));
		}
	}

	static String flippaHV(String in) {
		String s[] = in.split("");
		String ut = "";
		if (in.length() == 4) {
			ut = s[1] + s[0] + s[3] + s[2];
		}
		if (in.length() == 9) {
			ut = s[2] + s[1] + s[0] + s[5] + s[4] + s[3] + s[8] + s[7] + s[6];
		}
		return ut;
	}

	// static String flippaUN(String in) {
	// String s[] = in.split("");
	// String ut = "";
	// if (in.length() == 4) {
	// ut = s[2] + s[3] + s[0] + s[1];
	// }
	// if (in.length() == 9) {
	// ut = s[6] + s[7] + s[8] + s[3] + s[4] + s[5] + s[0] + s[1] + s[2];
	// }
	// return ut;
	// }

	static String rotera(String in) {
		String s[] = in.split("");
		String ut = "";
		if (in.length() == 4) {
			ut = s[2] + s[0] + s[3] + s[1];
		}
		if (in.length() == 9) {
			ut = s[6] + s[3] + s[0] + s[7] + s[4] + s[1] + s[8] + s[5] + s[2];
		}
		return ut;
	}

	static void kontrolleraImporten() {
		for (int i = 0; i < antal; i++) {
			System.out.println("Row= " + i + "from= " + bok[i][0] + " to= " + bok[i][1]);
		}
	}

	static void readLine(String in) {
		Pattern pattern = Pattern.compile(PATTERN);
		Matcher matcher = pattern.matcher(in);
		matcher.find();
		for (int i = 0; i < 2; i++) {
			bok[antal][i] = matcher.group(i + 1);
		}
		antal++;
	}
}
