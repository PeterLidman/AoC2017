package advent4;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class advent4 {
	public static void main(String[] args) {
		String str;
		String arr[];
		int sum = 0;
		boolean found;

		try {
			FileInputStream in = new FileInputStream("C:\\git\\AoC2017\\Advent4\\src\\advent4\\phrase.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			while ((str = br.readLine()) != null) {
//				System.out.println(str);
				arr = str.split(" ");
//				System.out.println("l=" + arr.length);
				found = false;
				for (int i = 0; i < arr.length - 1; i++) {
					for (int j = i + 1; j < arr.length; j++) {
						if (arr[i].equals(arr[j])) {
							found = true;
							break;
						}
						//part2
						if (isAnagram(arr[i], arr[j])) {
//							System.out.println(arr[i] + " :: " + arr[j]);
							found = true;
							break;
						}
					}
				}
				if (!found) {
					sum++;
				}
				System.out.println("Advent4: sum=" + sum);
			}
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println("Hittade inte filjäveln");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("io-fel");
			e.printStackTrace();
		}
	}

	private static boolean isAnagram(String word1, String word2) {
		word1 = word1.replaceAll("\\s", "").toLowerCase();
		word2 = word2.replaceAll("\\s", "").toLowerCase();
		char[] word1Arr = word1.toCharArray();
		char[] word2Arr = word2.toCharArray();
		Arrays.sort(word1Arr);
		Arrays.sort(word2Arr);
		return (Arrays.equals(word1Arr, word2Arr));
	}
}
