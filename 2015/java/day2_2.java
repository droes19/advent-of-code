import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day2_2 {
    public static void main(String[] args) {
        int res = 0;
        // use ../input/day2.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day2.input"))) {
            scan.useDelimiter("\n");
            while (scan.hasNext()) {
                String[] a = scan.next().trim().split("x");
                int l = Integer.valueOf(a[0]);
                int w = Integer.valueOf(a[1]);
                int h = Integer.valueOf(a[2]);

                int smallest1 = Math.min(l, Math.min(w, h));
                int smallest2;
                if (smallest1 == l) {
                    smallest2 = Math.min(w, h);
                } else if (smallest1 == w) {
                    smallest2 = Math.min(l, h);
                } else {
                    smallest2 = Math.min(l, w);
                }
                int feet = 2 * (smallest1 + smallest2);

                res += (l * w * h) + feet;
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
