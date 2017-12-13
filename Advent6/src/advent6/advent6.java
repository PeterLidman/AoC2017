package advent6;

public class advent6 {

	int depth = 16;
	int size = 20000;
	int offset = 1;
	int[][] m = new int[size][depth];

	public static void main(String[] args) {
		advent6 t = new advent6();
		String a = "2 8 8 5 4 2 3 1 5 5 1 2 15 13 5 14";
		String[] b;
		int pc = 1;
		String x;

		b = a.split(" ");
		for (int i = 0; i < t.depth; i++) {
			t.m[0][i] = Integer.valueOf(b[i]);
			// test.m[0][i] = Integer.valueOf(b[i]);
			// System.out.println(":-" + t.m[0][i]);
		}

		t.copy(0);
		t.proc(1);
		while (!t.find(pc)) {
			t.copy(pc);
			pc++;
			t.proc(pc);

			//System.out.println("pc" + pc);

		}
		System.out.println("pc=" + pc);

//		for (int j = 0; j <= pc; j++) {
//			x = "";
//			for (int i = 0; i < t.depth; i++) {
//				x += ":" + t.m[j][i];
//			}
//			System.out.println("pc=" + j + " " + x);
//		}
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
				System.out.println("träff" + i);
				return f;
			}
		}
		return false;
	}
}
