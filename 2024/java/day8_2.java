import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class day8_2 {
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
    static List<char[][]> listBoard = null;

    private static void process() {
        listAntiNodes = new ArrayList<>();
        listBoard = new ArrayList<>();
        for (Map.Entry<Character, List<Integer[]>> data : listAxis.entrySet()) {
            char[][] newBoard = createNewBoard(data.getKey());
            System.out.println("=============================" + data.getKey());
            for (char[] nb : newBoard) {
                System.out.println(Arrays.toString(nb));
            }
            System.out.println("=============================");

            List<Integer[]> axis = data.getValue();
            for (int i = 0; i < axis.size() - 1; i++) {
                // for (int i = 0; i < 2; i++) {
                for (int j = i + 1; j < axis.size(); j++) {
                    int y1 = axis.get(i)[0];
                    int x1 = axis.get(i)[1];
                    int y2 = axis.get(j)[0];
                    int x2 = axis.get(j)[1];
                    boolean run = true;
                    do {
                        int y3, x3 = 0;
                        y3 = y1 + (y1 - y2);
                        x3 = x1 + (x1 - x2);

                        if (!(y3 < 0 || y3 >= newBoard.length || x3 < 0 || x3 >= newBoard.length)) {
                            newBoard[y3][x3] = '#';
                            y2 = y1;
                            x2 = x1;
                            y1 = y3;
                            x1 = x3;
                        } else {
                            run = false;
                        }
                    } while (run);

                    y1 = axis.get(i)[0];
                    x1 = axis.get(i)[1];
                    y2 = axis.get(j)[0];
                    x2 = axis.get(j)[1];
                    run = true;
                    do {
                        int y3, x3 = 0;
                        y3 = y2 + (y2 - y1);
                        x3 = x2 + (x2 - x1);
                        if (!(y3 < 0 || y3 >= newBoard.length || x3 < 0 || x3 >= newBoard.length)) {
                            newBoard[y3][x3] = '#';
                            y1 = y2;
                            x1 = x2;
                            y2 = y3;
                            x2 = x3;
                        } else {
                            run = false;
                        }

                    } while (run);
                }
                listBoard.add(newBoard);
            }
            System.out.println("2=============================" + data.getKey());
            for (char[] nb : newBoard) {
                System.out.println(Arrays.toString(nb));
            }
            System.out.println("=============================");
            // break;

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

    private static void joinBoard() {
        char[][] newBoard = listBoard.get(0);
        int counter = 0;
        for (char[][] board : listBoard) {
            if (counter == 0) {
                counter++;
                continue;
            }

            for (int y = 0; y < board.length; y++) {
                for (int x = 0; x < board[y].length; x++) {
                    if (newBoard[y][x] != '.' && newBoard[y][x] != '#') {
                        continue;
                    } else if (board[y][x] != '.' && board[y][x] != '#') {
                        newBoard[y][x] = board[y][x];
                    } else if ((newBoard[y][x] == '#' && board[y][x] == '.')
                            || (newBoard[y][x] == '.' && board[y][x] == '#')) {
                        newBoard[y][x] = '#';
                    }
                }
            }
        }

        int res = 0;
        System.out.println("3=============================");
        for (char[] nb : newBoard) {
            System.out.println(Arrays.toString(nb));
            for (char b : nb) {
                if (b != '.') {
                    res++;
                }
            }
        }
        System.out.println("============================= " + res);
    }

    public static void main(String[] args) {

        readFile();
        getAntennas();
        getListAxis();
        process();
        joinBoard();

        System.out.println(listAntiNodes.size());
    }
}
