package advent2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Advent2 {
	public static void main(String[] args) {
		String str;
		String arr[];
		int sum = 0;
		int max;
		int min;
		int value;
		int value2;

		try {
			FileInputStream in = new FileInputStream("C:\\git\\AoC2017\\Advent2\\src\\advent2\\input.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			while ((str = br.readLine()) != null) {
//				System.out.println(str);
				arr = str.split("\t");
//				System.out.println("l=" + arr.length);
				value = Integer.valueOf(arr[0]);
				max = value;
				min = value;
				// Part 2
				for (int i = 0; i < arr.length - 1; i++) {
					for (int j = i + 1; j < arr.length; j++) {
						value = Integer.valueOf(arr[i]);
						value2 = Integer.valueOf(arr[j]);
						if (value % value2 == 0) {
							max = value;
							min = value2;
							break;
						}
						if (value2 % value == 0) {
							min = value;
							max = value2;
							break;
						}
					}
				}
				// Part 1
//				for (int i = 0; i < arr.length; i++) {
//					value = Integer.valueOf(arr[i]);
//
//					if (value > max) {
//						max = value;
//					}
//					if (value < min) {
//						min = value;
//					}
//				}
//				System.out.println("max=" + max + "min=" + min + "div=" + max / min);
//				sum += (max - min);// Part 1

				sum += (max / min);// Part 2
			}
			System.out.println("Advent2: sum=" + sum);
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
