import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class day8 {
    static char[][] board = null;

    public static void readFile() {
        // use ../input/day8.test for using dummy input
        try (Scanner scan = new Scanner(new File("../input/day8.input"))) {
            List<char[]> all = new ArrayList<>();
            scan.useDelimiter("\n");
            while (scan.hasNext()) {
                char[] line = scan.next().trim().toCharArray();
                all.add(line);
            }
            board = new char[all.size()][];
            for (int i = 0; i < board.length; i++) {
                board[i] = all.get(i);
            }
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }

    static HashSet<Character> listAntennas = null;

    private static void getAntennas() {
        listAntennas = new HashSet<>();
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] != '.') {
                    listAntennas.add(board[y][x]);
                }
            }
        }
        System.out.println(listAntennas);
    }

    static Map<Character, List<Integer[]>> listAxis = null;

    private static void getListAxis() {
        listAxis = new HashMap<>();
        for (Character c : listAntennas) {
            List<Integer[]> coors = new ArrayList<>();
            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[y].length; x++) {
                    if (board[y][x] == c) {
                        coors.add(new Integer[] { y, x });
                    }
                }
            }
            listAxis.put(c, coors);
        }
    }
    static List<String> listAntiNodes = null;

    private static void process() {
        listAntiNodes = new ArrayList<>();
        for (Map.Entry<Character, List<Integer[]>> data : listAxis.entrySet()) {
            char[][] newBoard = createNewBoard(data.getKey());
            System.out.println("=============================" + data.getKey());
            for (char[] nb : newBoard) {
                System.out.println(Arrays.toString(nb));
            }
            System.out.println("=============================");

            List<Integer[]> axis = data.getValue();
            for (int i = 0; i < axis.size() - 1; i++) {
                int y1 = axis.get(i)[0];
                int x1 = axis.get(i)[1];
                for (int j = i + 1; j < axis.size(); j++) {
                    int y2 = axis.get(j)[0];
                    int x2 = axis.get(j)[1];
                    int y3, y4, x3, x4 = 0;
                    if (y1 < y2) {
                        y3 = y1 - (y2 - y1);
                        y4 = y2 + (y2 - y1);
                    } else {
                        y3 = y1 + (y1 - y2);
                        y4 = y2 - (y1 - y2);
                    }
                    if (x1 < x2) {
                        x3 = x1 - (x2 - x1);
                        x4 = x2 + (x2 - x1);
                    } else {
                        x3 = x1 + (x1 - x2);
                        x4 = x2 - (x1 - x2);
                    }
                    System.out.println(y1 + " " + x1);
                    System.out.println(y2 + " " + x2);
                    System.out.println(y3 + " " + x3);
                    System.out.println(y4 + " " + x4);
                    if (!(y3 < 0 || y3 >= newBoard.length || x3 < 0 || x3 >= newBoard.length)) {
                        newBoard[y3][x3] = '#';
                        String antinode = y3 +"_"+x3;
                        if(!listAntiNodes.contains(antinode)){
                            listAntiNodes.add(antinode);
                        }
                    }
                    if (!(y4 < 0 || y4 >= newBoard.length || x4 < 0 || x4 >= newBoard.length)) {
                        newBoard[y4][x4] = '#';
                        String antinode = y4 +"_"+x4;
                        if(!listAntiNodes.contains(antinode)){
                            listAntiNodes.add(antinode);
                        }
                    }

                }
            }
            System.out.println("2=============================" + data.getKey());
            for (char[] nb : newBoard) {
                System.out.println(Arrays.toString(nb));
            }
            System.out.println("=============================");

        }
    }

    private static char[][] createNewBoard(char ch) {
        char[][] newBoard = new char[board.length][board[0].length];
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] != ch) {
                    newBoard[y][x] = '.';
                } else {
                    newBoard[y][x] = ch;
                }
            }
        }

        return newBoard;
    }

    public static void main(String[] args) {

        readFile();
        getAntennas();
        getListAxis();
        process();

         System.out.println(listAntiNodes.size());
    }
}
