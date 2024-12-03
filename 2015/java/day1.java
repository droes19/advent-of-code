import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day1 {
    public static void main(String[] args) {
        int[] res = {0};
        // use ../input/day1.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day1.input"))) {
            scan.useDelimiter("\\A");
            List<String> fileContent = scan.hasNext() ? Arrays.asList(scan.next().split("")) : new ArrayList<>();

            fileContent.forEach(x -> {
                if ("(".equals(x)) {
                    res[0]++;
                } else if (")".equals(x)) {
                    res[0]--;
                }
            });
            System.out.println(res[0]);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
