import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day5 {
    public static void main(String[] args) {
        int res = 0;
        // use ../input/day5.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day5.input"))) {
            scan.useDelimiter("\n");

            while (scan.hasNext()) {
                // boolean nice = true;
                String a = scan.next().trim();
                String[] b = a.split("");

                if (!a.contains("ab") && !a.contains("cd") && !a.contains("pq") && !a.contains("xy")) {
                    int vowels = 0;
                    int twice = 0;
                    for (int i = 0; i < b.length; i++) {
                        if ("a".equals(b[i]) || "i".equals(b[i]) || "u".equals(b[i]) || "e".equals(b[i])
                                || "o".equals(b[i])) {
                            vowels++;
                        }
                        if (i < b.length - 1) {
                            if (b[i].equals(b[i + 1]))
                                twice++;
                        }
                    }
                    if (vowels >= 3 && twice >= 1) {
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
