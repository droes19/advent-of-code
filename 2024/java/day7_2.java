import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day7_2 {
    static List<String[]> all = new ArrayList<>();
    static List<String[]> failedList = new ArrayList<>();

    public static void readFile() {
        // use ../input/day7.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day7.input"))) {
            scan.useDelimiter("\n");
            while (scan.hasNext()) {
                String[] line = scan.next().trim().split(":");
                all.add(line);
            }
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }

    private static void generateCombinations(List<String[]> results, char[] current, char[] operators, int index) {
        if (index == current.length) {
            results.add(new String(current).split(""));
            return;
        }
        for (char operator : operators) {
            current[index] = operator;
            generateCombinations(results, current, operators, index + 1);
        }
    }

    public static List<String[]> generatePossibilities(int length) {
        char[] operators = { '+', 'x', '|' };
        List<String[]> results = new ArrayList<>();
        generateCombinations(results, new char[length], operators, 0);
        return results;
    }

    public static BigInteger process() {
        BigInteger adding = new BigInteger("0");
        for (String[] line : all) {

            BigInteger result = new BigInteger(line[0]);
            String[] temp = line[1].trim().split(" ");

            BigInteger[] ln = new BigInteger[temp.length];
            for (int i = 0; i < temp.length; i++) {
                ln[i] = new BigInteger(temp[i]);
            }

            List<String[]> possibilities = generatePossibilities(ln.length - 1);
            for (String[] poss : possibilities) {
                BigInteger res = ln[0];
                for (int i = 0; i < ln.length - 1; i++) {
                    switch (poss[i]) {
                        case "+":
                            res = res.add(ln[i + 1]);
                            break;
                        case "x":
                            res = res.multiply(ln[i + 1]);
                            break;
                        case "|":
                            String tmp = res.toString() + "" + ln[i + 1].toString();
                            res = new BigInteger(tmp);
                            break;
                        default:
                            break;
                    }
                }
                if (res.equals(result)) {
                    adding = adding.add(res);
                    break;
                }
            }
        }
        return adding;
    }

    public static void main(String[] args) {

        readFile();
        BigInteger p = process();

        System.out.println(p);
    }
}
