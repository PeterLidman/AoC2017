package advent25;

public class advent25 {
	public static void main(String[] args) {
		char state = 'A';
		int pos = 9_000;
		long steps = 0;
		boolean cv = false;
		boolean tape[] = new boolean[20_000];
		int ones = 0;

		do {
			switch (state) {
			case 'A':
				if (!cv) {
					tape[pos] = true;
					pos++;
					state = 'B';
				} else {
					tape[pos] = false;
					pos--;
					state = 'D';
				}
				break;
			case 'B':
				if (!cv) {
					tape[pos] = true; //part 1
					pos++;
					state = 'C';
				} else {
					tape[pos] = false;
					pos++;
					state = 'F';
				}
				break;
			case 'C':
				if (!cv) {
					tape[pos] = true;
					pos--;
					state = 'C';
				} else {
					tape[pos] = true;
					pos--;
					state = 'A';
				}
				break;
			case 'D':
				if (!cv) {
					tape[pos] = false;//part 1
					pos--;
					state = 'E';
				} else {
					tape[pos] = true;
					pos++;
					state = 'A';
				}
				break;
			case 'E':
				if (!cv) {
					tape[pos] = true;//part 1
					pos--;
					state = 'A';
				} else {
					tape[pos] = false;
					pos++;
					state = 'B';
				}
				break;
			case 'F':
				System.out.println("step=" + steps + " pos=" + pos);
				if (!cv) {
					tape[pos] = false;
					pos++;
					state = 'C';
				} else {
					tape[pos] = false;
					pos++;
					state = 'E';
				}
				break;
			default:
				System.out.println("omfg!" + state);
			}
			cv = tape[pos];
			steps++;
		} while (steps < 12317297 );
		// count 1
		for (int i = 0; i < 20_000; i++) {
			if (tape[i]) {
				ones++;
				if(ones==1) 
					System.out.println("första på " +i);
				if(ones==5593)
					System.out.println("sista på " + i);
			}
		}
		System.out.println("räknade till " + ones + " ettor");
	}
}
