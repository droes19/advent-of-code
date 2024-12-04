import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class day3_2 {
    public static void main(String[] args) {
        int res = 0;
        // use ../input/day2.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day3.input"))) {
            scan.useDelimiter("\\A");
            List<String> fileContent = scan.hasNext() ? Arrays.asList(scan.next().split("")) : new ArrayList<>();

            List<Map<String, Integer>> hp = new ArrayList<>();
            Map<String, Integer> h = new HashMap<>();
            int x1 = 0;
            int x2 = 0;
            int y1 = 0;
            int y2 = 0;
            h.put("x", 0);
            h.put("y", 0);
            h.put("val", 2);

            boolean t = true;

            for (String dir : fileContent) {
                if ("^".equals(dir)) {
                    if (t) {
                        y1++;
                    } else {
                        y2++;
                    }
                } else if ("v".equals(dir)) {
                    if (t) {
                        y1--;
                    } else {
                        y2--;
                    }
                } else if (">".equals(dir)) {
                    if (t) {
                        x1++;
                    } else {
                        x2++;
                    }
                } else {
                    if (t) {
                        x1--;
                    } else {
                        x2--;
                    }
                }
                int x = t ? x1 : x2;
                int y = t ? y1 : y2;
                t = !t;
                boolean found = false;
                for (Map<String, Integer> data : hp) {
                    if (data.get("x") == x && data.get("y") == y) {
                        data.put("val", data.get("val") + 1);
                        found = true;
                    }
                }
                ;
                if (!found) {
                    h = new HashMap<>();
                    h.put("x", x);
                    h.put("y", y);
                    h.put("val", 1);
                    hp.add(h);
                }
            }

            System.out.println(hp.size());
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
