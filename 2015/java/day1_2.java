import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day1_2 {
    public static void main(String[] args) {
        int[] res = { 0, 0, 0 };
        boolean[] bol = { true };
        // use ../input/day1.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day1.input"))) {
            scan.useDelimiter("\\A");
            List<String> fileContent = scan.hasNext() ? Arrays.asList(scan.next().split("")) : new ArrayList<>();

            fileContent.forEach(x -> {
                res[1]++;
                if ("(".equals(x)) {
                    res[0]++;
                } else if (")".equals(x)) {
                    res[0]--;
                }
                if (res[0] == -1) {
                    if (bol[0]) {
                        res[2] = res[1];
                        bol[0] = false;
                    }
                }
            });
            System.out.println(res[2]);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
