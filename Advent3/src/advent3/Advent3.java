package advent3;

public class Advent3 {
	int size = 600;
	int y = size / 2;
	int x = size / 2;
	int offset = 1;
	int c=2;
	int[][] m = new int[size][size];
	int goal = 277678;
//	int goal = 12;
//	int goal = 23;
//	int goal = 1024;
	int find = 0;
	String print = "";

	void reset() {
		m[x][y] = 1;
	}

	public static void main(String[] args) {
		Advent3 test = new Advent3();
		test.reset();
		while (test.find == 0) {
			test.calc();
		}
		System.out.println("result:" + test.find);

//		for (int i = 0; i < test.size; i++) {
//			for (int j = 0; j < test.size; j++) {
//				test.print += test.m[j][i] + "\t";
//			}
//			System.out.println(test.print);
//			test.print = "";
//		}

	}

	private void calc() {
		for (int i = 0; i < offset; i++) {
			x++;
			sum();
		}
		for (int i = 0; i < offset; i++) {
			y--;
			sum();
		}
		offset++;
		for (int i = 0; i < offset; i++) {
			x--;
			sum();
		}
		for (int i = 0; i < offset; i++) {
			y++;
			sum();
		}
		offset++;
	}

	void sum() {
//		System.out.println("y=" + y + "x=" + x + "c=" + c);

		//Part 1
//		m[x][y] += c++;
//		if(find ==0 && c > goal) {
//			find = Math.abs(x - size /2) + Math.abs(y - size /2) ;
//		}
		
// part 2
		m[x][y] += m[x + 1][y];
		m[x][y] += m[x + 1][y + 1];
		m[x][y] += m[x + 1][y - 1];
		m[x][y] += m[x - 1][y];
		m[x][y] += m[x - 1][y + 1];
		m[x][y] += m[x - 1][y - 1];
		// m[x][y] += m[x][y];
		m[x][y] += m[x][y + 1];
		m[x][y] += m[x][y - 1];
		if (find == 0 && m[x][y] > goal) {
			find = m[x][y];
		}
	}
}
