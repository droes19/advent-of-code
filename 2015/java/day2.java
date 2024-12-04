import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day2 {
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
                int d1 = l*w;
                int d2 = w*h;
                int d3 = h*l;
                int smallest = d1 < d2 ? (d1 < d3 ? d1 : d3) : (d2 < d3 ? d2 : d3);
                
                res += (2*d1) + (2*d2) + (2*d3) + smallest;
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
