package advent6;

public class advent6 {
	int depth = 16;
	int size = 20000;
	int offset = 1;
	static int sizeOfLoop;
	int[][] m = new int[size][depth];

	public static void main(String[] args) {
		advent6 t = new advent6();
//		String a = "2 8 8 5 4 2 3 1 5 5 1 2 15 13 5 14";
		String a = "4 10 4 1 8 4 9 14 5 1 14 15 0 15 3 5";
		String[] b;
		int pc = 1;

		b = a.split(" ");
		for (int i = 0; i < t.depth; i++) {
			t.m[0][i] = Integer.valueOf(b[i]);
		}
		t.copy(0);
		t.proc(1);
		while (!t.find(pc)) {
			t.copy(pc);
			pc++;
			t.proc(pc);
		}
		System.out.println("Part 1= " + pc);
		System.out.println("Part 2= " + (pc - sizeOfLoop));
	}

	void copy(int row) {
		for (int i = 0; i < depth; i++) {
			m[row + 1][i] = m[row][i];
		}
	}

	void proc(int row) {
		int distr;
		int max = 0;
		for (int i = 1; i < depth; i++) {
			if (m[row][max] < m[row][i]) {
				max = i;
			}
		}
		distr = m[row][max];
		m[row][max] = 0;
		while (distr > 0) {
			max++;
			m[row][max % depth]++;
			distr--;
		}
	}

	boolean find(int row) {
		boolean f = true;
		for (int i = row - 1; i > -1; i--) {
			f = true;
			for (int j = 0; j < depth; j++) {
				if (m[row][j] != m[i][j]) {
					f = false;
					break;
				}
			}
			if (f) {
//				System.out.println("tr�ff" + i);
				sizeOfLoop = i;
				return f;
			}
		}
		return false;
	}
}
