import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day6_1 {
	public static void main(String[] args) {
		boolean[][] light = new boolean[1000][1000];
		int res = 0;
		// use ../input/day6.test for using dummy input
		try (Scanner scan = new Scanner(new File("../input/day6.input"))) {
			scan.useDelimiter("\n");

			while (scan.hasNext()) {
				String a = scan.next().trim();
				String[] b = a.split(" ");
				if ("toggle".equalsIgnoreCase(b[0])) {
					int x1 = Integer.valueOf(b[1].split(",")[0]);
					int y1 = Integer.valueOf(b[1].split(",")[1]);

					int x2 = Integer.valueOf(b[3].split(",")[0]);
					int y2 = Integer.valueOf(b[3].split(",")[1]);

					for (int y = y1; y <= y2; y++) {
						for (int x = x1; x <= x2; x++) {
							light[x][y] = !light[x][y];
						}
					}
				} else if ("turn".equalsIgnoreCase(b[0])) {
					boolean turnOn = "on".equalsIgnoreCase(b[1]);

					int x1 = Integer.valueOf(b[2].split(",")[0]);
					int y1 = Integer.valueOf(b[2].split(",")[1]);

					int x2 = Integer.valueOf(b[4].split(",")[0]);
					int y2 = Integer.valueOf(b[4].split(",")[1]);

					for (int y = y1; y <= y2; y++) {
						for (int x = x1; x <= x2; x++) {
							light[x][y] = turnOn;
						}
					}
				} else {
					System.exit(1);
				}
			}
			for (int y = 0; y < 1000; y++) {
				for (int x = 0; x < 1000; x++) {
					if (light[x][y]) {
						res++;
					}
				}
			}
			System.out.println(res);
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
	}
}
