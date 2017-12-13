package advent10;
public class advent10 {
	static String puzzle = "106,118,236,1,130,0,235,254,59,205,2,87,129,25,255,118";
	// static String puzzle = "";
	// static String puzzle = "AoC 2017";
	// static String puzzle = "1,2,3";
	// static String puzzle = "1,2,4";
	static int puzlen = 0;
	static int[] suffix = { 17, 31, 73, 47, 23 };
	static int[] intpuz = new int[59];
	static int data[] = new int[256];
	static int[] seqinlenght = { 106, 118, 236, 1, 130, 0, 235, 254, 59, 205, 2, 87, 129, 25, 255, 118 };
	static int tin[] = { 0, 1, 2, 3, 4 };
	static int cp = 0;
	static int skipsize = 0;
	static int[] dense = new int[16];
	static String denseStr = "";
	static String a = "";

	public static void main(String[] args) {
		for (int i = 0; i < 256; i++) {
			data[i] = i;
		}
		cp = 0;
		skipsize = 0;
		print();
		for (int i = 0; i < seqinlenght.length; i++) {
			reverse(cp, seqinlenght[i]);
			cp = (cp + seqinlenght[i] + skipsize++) % 256;
			System.out.println("cp" + cp + "skips" + skipsize + "length= " + seqinlenght[i]);
			print();
		}
		System.out.println("summa= " + data[0] * data[1]);
		///////////
		// part2 //
		///////////
		for (int i = 0; i < puzzle.length(); i++) {
			puzlen++;
			intpuz[i] = puzzle.charAt(i);
			// System.out.println(i + " " + intpuz[i]);
		}
		intpuz[puzlen++] = suffix[0];
		intpuz[puzlen++] = suffix[1];
		intpuz[puzlen++] = suffix[2];
		intpuz[puzlen++] = suffix[3];
		intpuz[puzlen++] = suffix[4];
		for (int i = 0; i < puzlen; i++) {
			a += i + ":" + intpuz[i] + " ";
		}
		System.out.println(a);

		for (int i = 0; i < 256; i++) {
			data[i] = i;
		}
		cp = 0;
		skipsize = 0;
		for (int j = 0; j < 64; j++) {
			for (int i = 0; i < puzlen; i++) {
				reverse(cp, intpuz[i]);
				cp = (cp + intpuz[i] + skipsize++) % 256;
				// System.out.println("cp" + cp + "skips" + skipsize + "length=
				// " + intpuz[i]);
				// print();
			}
		}
		for (int i = 0; i < 16; i++) {
			dense[i] = data[i * 16];
			for (int j = 1; j < 16; j++) {
				dense[i] = dense[i] ^ data[i * 16 + j];
			}
			System.out.println(" i=" + i + " dense=" + dense[i]);
			denseStr += (char) dense[i];
		}
		System.out.println("densstring:" + denseStr);
		System.out.println("hexdensstring:" + toHex(denseStr));
	}

	public static String toHex(String arg) {
		String ut = "";
		for (int i = 0; i < arg.length(); i++) {
			ut += String.format("%02x", dense[i]);
		}
		return ut;
	}

	static void reverse(int start, int len) {
		int temp;
		if (len < 2) {
			return;
		}
		for (int i = 0; i < len / 2; i++) {
			// System.out.println("i" + i);
			temp = data[(i + start) % 256];
			data[(i + start) % 256] = data[(len - i + start - 1) % 256];
			data[(len - i + start - 1) % 256] = temp;
		}
	}

	static void print() {
		String a = "";
		for (int i = 0; i < 256; i++) {
			a += " " + i + ":" + data[i];
		}
		System.out.println("->" + a);
	}
}
