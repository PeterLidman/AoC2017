package advent22;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class advent22b {
	static int antal = 0;
	static int size = 1000;
	static String fil[] = new String[300];
	static String grid[] = new String[size];
	static int dir = 0; // 0N 1E 2S 3W
	static int posX = 0;
	static int posY = 0;
	static int causedInfection = 0;

	// clean -> weakened -> infected -> flagged = . -> @ -> # -> ¤

	public static void main(String[] args) throws Exception {
		Files.readAllLines(Paths.get("C:\\git\\AoC2017\\Advent22\\src\\advent22\\virusmap.txt"))
				.forEach(e -> readLine(e));
		//printa();
		for (int i = 0; i < size; i++) {// init empty
			char[] fill = new char[size];
			Arrays.fill(fill, '.');
			grid[i] = new String(fill);
		}
		for (int i = 0; i < antal; i++) {// insert virus
			for (int j = 0; j < antal; j++) {
				grid[i + (size - antal) / 2] = insertCharAt(j + (size - antal) / 2, grid[i + (size - antal) / 2],
						fil[i].charAt(j));
			}
		}
		posX = size / 2 - 1;
		posY = size / 2 - 1;

		for (int i = 0; i < 10_000_000; i++) {
			// one burst
			switch (grid[posY].charAt(posX)) {
			case '.':
				dir += 3;// vänstersväng ;-)
				grid[posY] = insertCharAt(posX, grid[posY], 'W');
				break;
			case 'W':
				// rakt fram
				grid[posY] = insertCharAt(posX, grid[posY], '#');
				causedInfection++;
				break;
			case '#':
				dir++;// högersväng
				grid[posY] = insertCharAt(posX, grid[posY], 'F');
				break;
			case 'F':
				dir += 2; // backa
				grid[posY] = insertCharAt(posX, grid[posY], '.');
				break;
			default:
				System.out.println("crap ");
			}
			dir %= 4;

			switch (dir) {
			case 0:
				posY--;
				break;
			case 1:
				posX++;
				break;
			case 2:
				posY++;
				break;
			case 3:
				posX--;
				break;
			default:
				System.out.println("oh no!" + dir);
			}
		}
		grid[posY] = insertCharAt(posX, grid[posY], 'X');
		printGrid();
		System.out.println("total caused infection= " + causedInfection);

	}

	static String insertCharAt(int pos, String in, char inc) {
		return in.substring(0, pos) + inc + in.substring(pos + 1);
	}

	static void printGrid() {
		for (int i = 0; i < size; i++) {
			System.out.println(grid[i]);
		}
	}

	static void printa() {
		for (int i = 0; i < antal; i++) {
			System.out.println(fil[i]);
		}
	}

	static void readLine(String in) {
		fil[antal++] = in;
	}
}
