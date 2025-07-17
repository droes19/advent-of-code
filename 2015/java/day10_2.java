import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day10_2 {
	public static void main(String[] args) {
		String res = "";
		try (Scanner scan = new Scanner(new File("../input/day10.input"))) {
			String str = scan.nextLine().trim();
			for (int loop = 0; loop < 50; loop++) {
				String s = "";
				for (int i = 0; i < str.length(); i++) {
					char c = str.charAt(i);
					int count = 1;
					for (int j = i + 1; j < str.length(); j++) {
						if (str.charAt(j) == c) {
							count++;
						} else {
							break;
						}
						i = j;
					}
					s += count + "" + c;
				}
				str = s;
				System.out.println("Loop " + (loop + 1) + ": " + str.length());
			}
			System.out.println(res);
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
	}
}
