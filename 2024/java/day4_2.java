import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day4_2 {
    public static void main(String[] args) {
        int res = 0;
        // use ../input/day3.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day4.input"))) {
            scan.useDelimiter("\n");
            List<String> all = new ArrayList<>();
            while (scan.hasNext()) {
                all.add(scan.next().trim());
            }

            for (int y = 1; y < all.size() - 1; y++) {
                List<String> str = Arrays.asList(all.get(y).split(""));
                for (int x = 1; x < str.size() - 1; x++) {
                    if ("A".equals(str.get(x))) {
                        List<String> str1 = Arrays.asList(all.get(y - 1).split(""));
                        List<String> str2 = Arrays.asList(all.get(y + 1).split(""));
                        if ((("M".equals(str1.get(x - 1)) && "S".equals(str2.get(x + 1))) ||
                                ("S".equals(str1.get(x - 1)) && "M".equals(str2.get(x + 1)))) &&
                                (("M".equals(str1.get(x + 1)) && "S".equals(str2.get(x - 1))) ||
                                        ("S".equals(str1.get(x + 1)) && "M".equals(str2.get(x - 1))))) {
                            res++;
                        }
                    }
                }

            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
