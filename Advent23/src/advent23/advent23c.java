package advent23;

public class advent23c {
	public static void main(String[] args) {
		int n = 0;
		for (int i = 108100; i <= 125100; i += 17)
			if (!isPrime(i))
				n++;
		System.out.println("n=" + n);
	}

	private static boolean isPrime(long n) {
		if (n < 2)
			return false;
		for (long i = 2; i * i <= n; i++)
			if (n % i == 0)
				return false;

		return true;
	}
}
