package advent17;

import java.util.ArrayList;
import java.util.List;

public class advent17 {
	static List<Integer> spin = new ArrayList<Integer>();
	// static int steps = 3;
	static int steps = 348;
	static int pos = 0;
	static int size = 1;
	static int value = 0;

	// 2: 0 (1)
	// 14: 0 (13) 1 11
	// 23: 0 (22) 13 1
	// 35: 0 (34) 22 30
	// 58: 0 (57) 34 46
	// 411: 0 (410) 57 386
	// 434: 0 (433) 410 57

	public static void main(String[] args) {
		spin.add(0);
		for (int i = 1; i < 2018; i++) {
			pos = (pos + steps) % spin.size();
			// System.out.println("pos" + pos);
			pos++;
			spin.add(pos, i);
			// display();
			reducedDisplay();
			// startDisplay();
		}
		// part2, ide ta bort array, räkna bara ut då pos är =1
		pos = 0;
		size=1;
		for (int i = 1; i < 50_000_000; i++) {
			pos = (pos + steps) % size;
			pos++;
			size++;
			if (pos == 1) {
				value = size - 1;
				System.out.println("val" + value);
			}
		}
	}

	static void startDisplay() {
		if (pos == 1) {
			String t = "" + spin.size() + ":";
			for (int i = 0; i < spin.size(); i++) {
				if (i < 4) {
					if (pos == i) {
						t += "(" + spin.get(i) + ")";
					} else {
						t += " " + spin.get(i) + " ";
					}
				}
			}
			System.out.println(t);
		}
	}

	static void reducedDisplay() {
		String t = "";
		for (int i = 0; i < spin.size(); i++) {
			if (Math.abs(pos - i) < 4) {
				if (pos == i) {
					t += "(" + spin.get(i) + ")";
				} else {
					t += " " + spin.get(i) + " ";
				}
			}
		}
		System.out.println(t);
	}

	static void display() {
		String t = "";
		for (int i = 0; i < spin.size(); i++) {
			if (pos == i) {
				t += "(" + spin.get(i) + ")";
			} else {
				t += " " + spin.get(i) + " ";
			}
		}
		System.out.println(t);
	}
}
