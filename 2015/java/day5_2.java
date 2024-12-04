import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day5_2 {
    public static void main(String[] args) {
        int res = 0;
        // use ../input/day5.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day5.input"))) {
            scan.useDelimiter("\n");

            while (scan.hasNext()) {
                // boolean nice = true;
                String a = scan.next().trim();
                String[] b = a.split("");

                boolean repeat = false;
                boolean pair = false;
                for (int i = 0; i < b.length; i++) {
                    if (i < b.length - 2) {
                        if (b[i].equals(b[i + 2]))
                            repeat = true;
                    }
                    if (i < b.length - 3) {
                        String t = b[i] + "" + b[i + 1];
                        for (int j = i + 2; j < b.length - 1; j++) {
                            String t2 = b[j] + "" + b[j + 1];
                            if (t.equals(t2))
                                pair = true;

                        }
                    }
                }
                if (repeat && pair) {
                    res++;
                }

            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
