package advent23;

public class advent23b {
	public static void main(String[] args) {
		long h = 0;
		long f = 0;
		long d = 0;
		long e = 0;
		long b = 0;
		long c = 0;

		b = 81;
		b *= 100;
		b += 100_000;
		c = b;
		c += 17000;
		System.out.println("b=" + b + "c=" + c);

		do {
			f = 1;
			d = 2;
			do {
				e = (b / d) - 1; // optimera startvärde
				do {
					if (d * e == b) { // två faktorer blir b
						f = 0;
					}
					e++;
				} while (d * e <= b); // avbryt när vi passerat
				// System.out.println("d=" + d + "e=" + e +"h=" + h);
				d++;
			} while (d < b && f == 1 && d < (b / 2)); // avbryt om vi funnit, gå
														// bara halva sträckan
														// då produkten måste
														// hittats om den finns

			if (f == 0) { // det fanns två faktorer
				h++;
			}

			if ((b - c) >= 0) {
				System.out.println("h=" + h);
				System.exit(0);
			}
			b += 17;
		} while (true);
	}
}
