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
		Files.readAllLines(Paths.get("C:\\git\\AoC2017\\Advent21\\src\\advent21\\book.txt")).forEach(e -> readLine(e));
//		kontrolleraImporten();
		antal = 108;
		// fyll i boken
		for (int i = 0; i < antal; i++) {
			String s = bok[i][0].replaceAll("/", "");
			String r = bok[i][1].replaceAll("/", "");
			fullBok.put(s, r);
			fullBok.put(flippaHV(s), r);
			fullBok.put(rotera(s), r);
			fullBok.put(flippaHV(rotera(s)), r);
			fullBok.put(rotera(rotera(s)), r);
			fullBok.put(flippaHV(rotera(rotera(s))), r);
			fullBok.put(rotera(rotera(rotera(s))), r);
			fullBok.put(flippaHV(rotera(rotera(rotera(s)))), r);
		}
		// System.out.println("bok=" + antal + " fullbok= " + fullBok.size());
		// for (String a : fullBok.keySet()) {
		// System.out.println(a + " : " + fullBok.get(a));
		// }
		
		// Part 1
		// System.out.println(countRecursive(5, start));
		
		// Part 2
		System.out.println(countRecursive(18, start));
	}

	static int countRecursive(int iterations, String image) {
		int retur = 0;
		String tmpc[] = image.split("");
		String tmp = "";
		System.out.println("iter=" + iterations);
		// ritaUt(image);

		if (iterations < 1) {
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
				return countRecursive(iterations - 1, transform3to2(tmp));
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
			return countRecursive(iterations - 1, transform4to2(tmp));
		}
		System.out.println("nä men va fan nu då!");
		return -1;
	}

	static String transform4to2(String in) {
		int side = (int) Math.sqrt(in.length());
		String tmp = "";
		String tmp1 = "";
		String tmp2 = "";
		String tmp3 = "";
		String tmp4 = "";
		for (int i = 0; i < side / 4; i++) {
			tmp1 = "";
			tmp2 = "";
			tmp3 = "";
			tmp4 = "";
			for (int j = 0; j < side / 4; j++) {
				tmp1 += in.substring(i * side * 4 + j * 16, i * side * 4 + j * 16 + 4);
				tmp2 += in.substring(i * side * 4 + j * 16 + 4, i * side * 4 + j * 16 + 4 + 4);
				tmp3 += in.substring(i * side * 4 + j * 16 + 8, i * side * 4 + j * 16 + 8 + 4);
				tmp4 += in.substring(i * side * 4 + j * 16 + 12, i * side * 4 + j * 16 + 12 + 4);
			}
			tmp += tmp1 + tmp2 + tmp3 + tmp4;
		}
		return tmp;
	}

	static String transform3to2(String in) {
		int side = (int) Math.sqrt(in.length());
		String tmp = "";
		String tmp1 = "";
		String tmp2 = "";
		String tmp3 = "";
		for (int i = 0; i < side / 3; i++) {
			tmp1 = "";
			tmp2 = "";
			tmp3 = "";
			for (int j = 0; j < side / 3; j++) {
				tmp1 += in.substring(i * side * 3 + j * 9, i * side * 3 + j * 9 + 3);
				tmp2 += in.substring(i * side * 3 + j * 9 + 3, i * side * 3 + j * 9 + 3 + 3);
				tmp3 += in.substring(i * side * 3 + j * 9 + 6, i * side * 3 + j * 9 + 6 + 3);
			}
			tmp += tmp1 + tmp2 + tmp3;
		}
		return tmp;
	}

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