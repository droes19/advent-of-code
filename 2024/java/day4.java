import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day4 {
    public static void main(String[] args) {
        int res = 0;
        // use ../input/day3.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day4.input"))) {
            scan.useDelimiter("\n");
            List<String> all = new ArrayList<>();
            while (scan.hasNext()) {
                all.add(scan.next().trim());
            }

            for (int y = 0; y < all.size(); y++) {
                List<String> str = Arrays.asList(all.get(y).split(""));
                for (int x = 0; x < str.size(); x++) {
                    if ("X".equals(str.get(x))) {
                        if (x >= 3) {
                            if ("M".equals(str.get(x - 1)) &&
                                    "A".equals(str.get(x - 2)) &&
                                    "S".equals(str.get(x - 3))) {
                                res++;
                            }
                        }
                        if (x <= str.size() - 1 - 3) {
                            if ("M".equals(str.get(x + 1)) &&
                                    "A".equals(str.get(x + 2)) &&
                                    "S".equals(str.get(x + 3))) {
                                res++;
                            }
                        }
                        if (y >= 3) {
                            List<String> str1 = Arrays.asList(all.get(y - 1).split(""));
                            List<String> str2 = Arrays.asList(all.get(y - 2).split(""));
                            List<String> str3 = Arrays.asList(all.get(y - 3).split(""));
                            if ("M".equals(str1.get(x)) &&
                                    "A".equals(str2.get(x)) &&
                                    "S".equals(str3.get(x))) {
                                res++;
                            }
                            if (x >= 3) {
                                if ("M".equals(str1.get(x - 1)) &&
                                        "A".equals(str2.get(x - 2)) &&
                                        "S".equals(str3.get(x - 3))) {
                                    res++;
                                }
                            }
                            if (x <= str.size() - 1 - 3) {
                                if ("M".equals(str1.get(x + 1)) &&
                                        "A".equals(str2.get(x + 2)) &&
                                        "S".equals(str3.get(x + 3))) {
                                    res++;
                                }
                            }
                        }
                        if (y <= all.size() - 1 - 3) {
                            List<String> str1 = Arrays.asList(all.get(y + 1).split(""));
                            List<String> str2 = Arrays.asList(all.get(y + 2).split(""));
                            List<String> str3 = Arrays.asList(all.get(y + 3).split(""));
                            if ("M".equals(str1.get(x)) &&
                                    "A".equals(str2.get(x)) &&
                                    "S".equals(str3.get(x))) {
                                res++;
                            }
                            if (x >= 3) {
                                if ("M".equals(str1.get(x - 1)) &&
                                        "A".equals(str2.get(x - 2)) &&
                                        "S".equals(str3.get(x - 3))) {
                                    res++;
                                }
                            }
                            if (x <= str.size() - 1 - 3) {
                                if ("M".equals(str1.get(x + 1)) &&
                                        "A".equals(str2.get(x + 2)) &&
                                        "S".equals(str3.get(x + 3))) {
                                    res++;
                                }
                            }
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
