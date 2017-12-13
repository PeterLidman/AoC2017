package advent5;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class advent5 {
	public static void main(String[] args) {
		String str;
		List<Integer> jmp = new ArrayList<>();
		int sum = 0;
		int pc = 0;
		int hopp;

		try {
			FileInputStream in = new FileInputStream("C:\\git\\AoC2017\\Advent5\\src\\advent5\\jumps.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while ((str = br.readLine()) != null) {
				jmp.add(Integer.valueOf(str));
				// sum++;
			}

			while (pc >= 0 && pc < jmp.size()) {
				// process

				hopp = jmp.get(pc);

				if (hopp > 2) {
					jmp.set(pc, hopp - 1);
				} else {
					jmp.set(pc, hopp + 1);
				}
				pc += hopp;
				//System.out.println("pc=" + pc + "inst:" + sum + "hopp" + hopp);

				sum++;
			}

			System.out.println("Advent5: " + sum);
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Hittade inte filjäveln");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("io-fel");
			e.printStackTrace();
		}
	}
}
