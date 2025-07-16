import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class day8_1 {
	public static void main(String[] args) {

		// use ../input/day8.test for using dummy input
		try (Scanner scan = new Scanner(new File("../input/day8.input"))) {
			scan.useDelimiter("\n");

			int str1Length = 0;
			int str2Length = 0;
			while (scan.hasNext()) {
				String a = scan.next().trim();
				str1Length += a.length();
				String b = a.substring(1, a.length() - 1);
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < b.length(); i++) {
					if (b.charAt(i) == '\\') {
						if (b.charAt(i + 1) == 'x') {
							String hex = b.substring(i + 2, i + 4);
							sb.append((char) Integer.parseInt(hex, 16));
							i += 3; // Skip the next two characters
						} else {
							sb.append(b.charAt(i + 1));
							i++; // Skip the next character
						}
					} else {
						sb.append(b.charAt(i));
					}
				}
				str2Length += sb.length();
			}
			System.out.println("Part 1: " + str1Length);
			System.out.println("Part 2: " + str2Length);
			System.out.println("Difference: " + (str1Length - str2Length));
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
	}
}
