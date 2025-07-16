import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day6_2 {
	public static void main(String[] args) {
		int[][] light = new int[1000][1000];
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
							light[x][y] = light[x][y] + 2;
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
							light[x][y] = turnOn ? light[x][y] + 1
									: Math.max(0, light[x][y] - 1);
						}
					}
				} else {
					System.exit(1);
				}
			}
			for (int y = 0; y < 1000; y++) {
				for (int x = 0; x < 1000; x++) {
					res += light[x][y];
				}
			}
			System.out.println(res);
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
	}
}
