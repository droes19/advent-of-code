import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day2_2 {
    public static void main(String[] args) {
        int res = 0;
        // use ../input/day2.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day2.input"))) {
            scan.useDelimiter("\n");
            while (scan.hasNext()) {
                String[] a = scan.next().trim().split("\\s+");
                boolean isIncrease = true;
                boolean isSafe = true;
                for (int i = 1; i < a.length; i++) {
                    int a1 = Integer.valueOf(a[i - 1]);
                    int a2 = Integer.valueOf(a[i]);
                    if (i == 1) {
                        isIncrease = a2 > a1;
                    }
                    if (isIncrease) {
                        if (a2 - a1 > 3 || a2 - a1 <= 0) {
                            isSafe = false;
                            break;
                        }
                    } else {
                        if (a2 - a1 < -3 || a2 - a1 >= 0) {
                            isSafe = false;
                            break;
                        }
                    }
                }
                if (isSafe) {
                    res++;
                } else {
                    int[] newArr = new int[a.length - 1];
                    int removeIdx = 0;
                    do {
                        isSafe = true;
                        int j = 0;
                        for (int i = 0; i < a.length; i++) {
                            if (i != removeIdx) {
                                newArr[j++] = Integer.valueOf(a[i]);
                            }
                        }
                        for (int i = 1; i < newArr.length; i++) {
                            int a1 = newArr[i - 1];
                            int a2 = newArr[i];
                            if (i == 1) {
                                isIncrease = a2 > a1;
                            }
                            if (isIncrease) {
                                if (a2 - a1 > 3 || a2 - a1 <= 0) {
                                    isSafe = false;
                                    break;
                                }
                            } else {
                                if (a2 - a1 < -3 || a2 - a1 >= 0) {
                                    isSafe = false;
                                    break;
                                }
                            }
                        }
                        if (!isSafe) {
                            removeIdx++;
                        } else {
                            res++;
                            isSafe = true;
                        }
                    } while (!isSafe && removeIdx < a.length);
                }
            }
            System.out.println(res);
        } catch (

        FileNotFoundException e) {
            System.exit(1);
        }
    }
}
