package advent13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class advent13 {
	static String[] row = new String[100];
	static int antal = 0;
	static int sev = 0;
	static int delay = 0;
	static int scan[] = new int[100];

	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("C:\\git\\AoC2017\\Advent13\\src\\advent13\\scanner.txt")).forEach(e -> insert(e));
		// print();
		for (int i = 0; i < 43; i++) {
			scan[Integer.valueOf(row[i].substring(0, row[i].indexOf(":")))] = Integer
					.valueOf(row[i].substring(row[i].indexOf(":") + 2));
		}
		for (int i = 0; i < 95; i++) {
			sev += scanner(scan[i], i) ? i * scan[i] : 0;
		}
		System.out.println("severity= " + sev);

		do {
			sev = 0;
			if ( scanner(scan[0],  delay)) { //
				sev = 1337;
			}
			for (int i = 0; i < 95; i++) {
				sev += scanner(scan[i], i + delay) ? i * scan[i] : 0;
			}
			delay++;
		} while (sev > 0);
		System.out.println("delay= " + --delay + " severity= " + sev);

	}

	static boolean scanner(int l, int pico) {
		if (l < 1) {
			return false;
		}
		return (pico % (l * 2 - 2)) == 0;
	}

	static void print() {
		for (int i = 0; i < antal; i++) {
			System.out.println(i + 1 + "/" + antal + ":" + row[i]);
		}
	}

	static void insert(String in) {
		row[antal++] = in;
	}
}
