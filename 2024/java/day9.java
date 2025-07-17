import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day9 {
    static String[] line = null;

    public static void readFile() {
        // use ../input/day9.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day9.input"))) {
            scan.useDelimiter("\\A");
            line = scan.next().trim().split("");

        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }

    private static void setMemory() {
        List<String> l = new ArrayList<>();
        Integer num = 0;
        boolean isNum = false;

        Integer a = 0;
        for (int i = 0; i < line.length; i++) {
            a += Integer.valueOf(line[i]);
            isNum = !isNum;
            if ("0".equals(line[i])) {
                continue;
            }
            int count = 1;
            while (count <= Integer.valueOf(line[i])) {
                if (isNum) {
                    l.add(num.toString());
                } else {
                    l.add(".");
                }
                count++;
            }
            if (isNum) {
                if (num == 9) {
                    num = 0;
                } else {
                    num++;
                }
            }
        }
        System.out.println(a);
        System.out.println(l.size());
        line = l.toArray(new String[l.size()]);
        System.out.println(line.length);

    }

    private static void fillFreeSpace() {
        for (int i = 0; i < line.length; i++) {
            if (".".equals(line[i]) && i != line.length - 1) {
                for (int j = line.length - 1; j >= 0; j--) {
                    // System.out.println(i +" "+ j);
                    if (i == j) {
                        break;
                    }
                    if (!".".equals(line[j])) {
                        line[i] = line[j];
                        line[j] = ".";
                        break;
                    }
                }
            }
        }
    }

    private static void run() {
        int res = 0;
        for (int i = 0; i < line.length; i++) {
            if (".".equals(line[i])) {
                System.out.println("i = " + (i - 1) + ", line[" + (i - 1) + "] = " + line[i - 1]);
                System.out.println("i = " + i + ", line[" + i + "] = " + line[i]);
                break;
            }
            int mul = i * Integer.valueOf(line[i]);
            res += mul;
            System.out.println("i = " + i + ", line[" + i + "] = " + line[i]);
            System.out.println("mul = " + mul + ", res = " + res);
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        readFile();
        // System.out.println(Arrays.deepToString(line));

        setMemory();
        // System.out.println(Arrays.deepToString(line));

        fillFreeSpace();
        // System.out.println(Arrays.deepToString(line));

        run();
    }
}
