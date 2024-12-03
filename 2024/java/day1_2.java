import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class day1_2 {
    public static void main(String[] args) {
        int res = 0;
        // use ../input/day1.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day1.input"))) {
            List<Integer> left = new ArrayList<>();
            List<Integer> right = new ArrayList<>();
            scan.useDelimiter("\n");
            while (scan.hasNext()) {
                String[] a = scan.next().trim().split("\\s+");
                left.add(Integer.valueOf(a[0]));
                right.add(Integer.valueOf(a[1]));
            }

            for (Integer x : left) {
                int count = Collections.frequency(right, x);
                res += Math.abs(x * count);
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
