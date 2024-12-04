import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class day3 {
    public static void main(String[] args) {
        int res = 0;
        // use ../input/day2.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day3.input"))) {
            scan.useDelimiter("\\A");
            List<String> fileContent = scan.hasNext() ? Arrays.asList(scan.next().split("")) : new ArrayList<>();

            List<Map<String, Integer>> hp = new ArrayList<>();
            Map<String, Integer> h = new HashMap<>();
            int x = 0;
            int y = 0;
            h.put("x", x);
            h.put("y", y);
            h.put("val", 1);

            for (String dir : fileContent) {
                if ("^".equals(dir)) {
                    y++;
                } else if ("v".equals(dir)) {
                    y--;
                } else if (">".equals(dir)) {
                    x++;
                } else {
                    x--;
                }
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
