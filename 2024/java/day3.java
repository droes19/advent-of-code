import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day3 {
    public static void main(String[] args) {
        int res = 0;
        // use ../input/day3.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day3.input"))) {
            scan.useDelimiter("\\A");
            String fileContent = scan.hasNext() ? scan.next() : "";
            String[] arr = fileContent.split("mul\\(");
            for (int i = 0; i < arr.length; i++) {
                String str = arr[i].split("\\)")[0];
                String[] n = str.split(",");
                if (n.length == 2) {
                    if (!day3.isNumeric(n[0]) || !day3.isNumeric(n[1])) {
                        continue;
                    }
                    res += Integer.valueOf(n[0]) * Integer.valueOf(n[1]);
                }
            }
            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }
}
