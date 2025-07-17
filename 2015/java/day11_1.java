import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day11_1 {
	public static void main(String[] args) {
		String res = "";
		try (Scanner scan = new Scanner(new File("../input/day11.input"))) {
			String str = scan.nextLine().trim();
			System.out.println(str);
			char[] strChars = str.toCharArray();
			for (int i = 0; i < strChars.length; i++) {
				if (strChars[i] == 'i' || strChars[i] == 'o' || strChars[i] == 'l') {
					// Skip invalid characters
					strChars[i] = (char) (strChars[i] + 1);
					for (int j = i + 1; j < strChars.length; j++) {
						strChars[j] = 'a'; // Reset the following characters to 'a'
					}
				}
			}
			str = new String(strChars);
			do {
				// Increment the password
				char[] chars = str.toCharArray();
				for (int i = chars.length - 1; i >= 0; i--) {
					if (chars[i] == 'i' || chars[i] == 'o' || chars[i] == 'l') {
						// Skip invalid characters
						chars[i] = (char) (chars[i] + 1);

						for (int j = i + 1; j < chars.length; j++) {
							chars[j] = 'a'; // Reset the following characters to 'a'
						}
					}
					if (chars[i] == 'z') {
						chars[i] = 'a';
					} else {
						chars[i]++;
						break;
					}
				}
				str = new String(chars);
			} while (!isValidPassword(str));
			res = str;
			System.out.println(res);
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
	}

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	private static final String INVALID_CHAR = "iol";

	private static boolean isValidPassword(String password) {
		// Check for invalid characters
		for (char c : INVALID_CHAR.toCharArray()) {
			if (password.indexOf(c) >= 0) {
				return false;
			}
		}

		// Check for at least one increasing straight of three letters
		boolean hasStraight = false;
		for (int i = 0; i < password.length() - 2; i++) {
			if (ALPHABET.indexOf(password.charAt(i)) + 1 == ALPHABET.indexOf(password.charAt(i + 1))
					&& ALPHABET.indexOf(password.charAt(i + 1)) + 1 == ALPHABET
							.indexOf(password.charAt(i + 2))) {
				hasStraight = true;
				break;
			}
		}
		if (!hasStraight) {
			return false;
		}

		// Check for at least two different pairs of letters
		int pairCount = 0;
		for (int i = 0; i < password.length() - 1; i++) {
			if (password.charAt(i) == password.charAt(i + 1)) {
				pairCount++;
				i++; // Skip the next character to avoid counting the same pair again
			}
			if (pairCount >= 2) {
				return true;
			}
		}

		return false;
	}
}
