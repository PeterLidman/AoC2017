package advent15;

public class advent15 {
	// static long a = 65;
	// static long b = 8921;
	static long a = 289;
	static long b = 629;
	static int sum = 0;

	public static void main(String[] args) {
		for (int i = 0; i < 40_000_000; i++) {
			a = a * 16807 % 2147483647;
			b = b * 48271 % 2147483647;
//			System.out.println("a= " + a + " b= " + b);
			if ((a & 65535) - (b & 65535) == 0) {// match
				sum++;
			}
		}
		System.out.println("Part 1: Sum= " + sum);
		// part2
//		a = 65;
		// b = 8921;
		a = 289;
		b = 629;
		sum = 0;
		for (int i = 0; i < 5_000_000; i++) {
			do {
				a = a * 16807 % 2147483647;
			} while ((a % 4) != 0);
			do {
				b = b * 48271 % 2147483647;
			} while ((b % 8) != 0);
//			System.out.println("a= " + a + " b= " + b);
			if ((a & 65535) - (b & 65535) == 0) {// match
				sum++;
			}
		}
		System.out.println("Part 2: Sum= " + sum);
	}
}
