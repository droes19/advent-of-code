import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class day1 {
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
            Collections.sort(left);
            Collections.sort(right);
            for (int i = 0; i < left.size(); i++) {
                res += Math.abs(right.get(i) - left.get(i));
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
