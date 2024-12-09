import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class day6_2 {
    public static void main(String[] args) {
        // use ../input/day6.test for using dummy input
        List<List<String>> firstBoard;
        String firstStep = "";
        int firstY = 0;
        int firstX = 0;
        try (Scanner scan = new Scanner(new File("../input/day6.input"))) {
            scan.useDelimiter("\n");

            firstBoard = new ArrayList<>();
            while (scan.hasNext()) {
                List<String> col = Arrays.asList(scan.next().trim().split(""));
                firstBoard.add(col);
            }
            for (int i = 0; i < firstBoard.size(); i++) {
                for (int j = 0; j < firstBoard.get(i).size(); j++) {
                    if ("^".equals(firstBoard.get(i).get(j)) || ">".equals(firstBoard.get(i).get(j))
                            || "v".equals(firstBoard.get(i).get(j)) || "<".equals(firstBoard.get(i).get(j))) {
                        firstY = i;
                        firstX = j;
                        firstStep = firstBoard.get(i).get(j);
                        break;
                    }
                }
            }

            boolean run = true;
            List<List<String>> board = new ArrayList<>();
            List<String> successList = new ArrayList<>();
            List<String> failedList = new ArrayList<>();
            // int counter = 0;
            while (run) {
                board = new ArrayList<>();
                for (List<String> s : firstBoard) {
                    List<String> abc = new ArrayList<>();
                    for (String a : s) {
                        abc.add(a);
                    }
                    board.add(abc);
                }
                int y = firstY;
                int x = firstX;
                String step = firstStep;
                boolean setStop = true;
                String currYX = "";
                while (setStop) {
                    if ("^".equals(step)) {
                        if (y == board.size() - 1) {
                            break;
                        } else if ("#".equals(board.get(y - 1).get(x))) {
                            x += 1;
                            step = ">";
                        } else {
                            y -= 1;
                        }
                    } else if (">".equals(step)) {
                        if (x == board.get(0).size() - 1) {
                            break;
                        } else if ("#".equals(board.get(y).get(x + 1))) {
                            y += 1;
                            step = "v";
                        } else {
                            x += 1;
                        }
                    } else if ("v".equals(step)) {
                        if (y == board.get(0).size() - 1) {
                            break;
                        } else if ("#".equals(board.get(y + 1).get(x))) {
                            x -= 1;
                            step = "<";
                        } else {
                            y += 1;
                        }
                    } else if ("<".equals(step)) {
                        if (x == 0) {
                            break;
                        } else if ("#".equals(board.get(y).get(x - 1))) {
                            y -= 1;
                            step = "^";
                        } else {
                            x -= 1;
                        }
                    }
                    if (!(successList.contains(y + "_" + x) || failedList.contains(y + "_" + x))) {
                        board.get(y).set(x, "O");
                        setStop = false;
                        currYX = y + "_" + x;
                    }
                }

                if (setStop) {
                    break;
                }

                step = firstStep;
                y = firstY;
                x = firstX;

                boolean runGame = true;
                boolean success = false;
                List<String> turn = new ArrayList<>();
                while (runGame) {
                    if ("^".equals(step)) {
                        if (y == 0) {
                            runGame = false;
                            success = false;
                        } else if ("#".equals(board.get(y - 1).get(x)) || "O".equals(board.get(y - 1).get(x))) {
                            board.get(y).set(x, "+");
                            String o = (y - 1) + "_" + (x) + step;
                            if (turn.contains(o)) {
                                runGame = false;
                                success = true;
                            } else {
                                turn.add(o);
                            }
                            step = ">";
                        } else {
                            if ("-".equals(board.get(y).get(x)) || "#".equals(board.get(y).get(x - 1))
                                    || "O".equals(board.get(y).get(x - 1))) {
                                board.get(y).set(x, "+");
                            } else {
                                board.get(y).set(x, "|");
                            }

                            y -= 1;
                        }
                    } else if (">".equals(step)) {
                        if (x == board.get(0).size() - 1) {
                            runGame = false;
                            success = false;
                        } else if ("#".equals(board.get(y).get(x + 1)) || "O".equals(board.get(y).get(x + 1))) {
                            board.get(y).set(x, "+");
                            String o = (y) + "_" + (x + 1) + step;
                            if (turn.contains(o)) {
                                runGame = false;
                                success = true;
                            } else {
                                turn.add(o);
                            }
                            step = "v";
                        } else {
                            if ("|".equals(board.get(y).get(x)) || "#".equals(board.get(y - 1).get(x))
                                    || "O".equals(board.get(y - 1).get(x))) {
                                board.get(y).set(x, "+");
                            } else {
                                board.get(y).set(x, "-");
                            }

                            x += 1;
                        }
                    } else if ("v".equals(step)) {
                        if (y == board.size() - 1) {
                            runGame = false;
                            success = false;
                        } else if ("#".equals(board.get(y + 1).get(x)) || "O".equals(board.get(y + 1).get(x))) {
                            board.get(y).set(x, "+");
                            String o = (y + 1) + "_" + (x) + step;
                            if (turn.contains(o)) {
                                runGame = false;
                                success = true;
                            } else {
                                turn.add(o);
                            }
                            step = "<";
                        } else {
                            if ("-".equals(board.get(y).get(x)) || "#".equals(board.get(y).get(x + 1))
                                    || "O".equals(board.get(y).get(x + 1))) {
                                board.get(y).set(x, "+");
                            } else {
                                board.get(y).set(x, "|");
                            }

                            y += 1;
                        }
                    } else if ("<".equals(step)) {
                        if (x == 0) {
                            runGame = false;
                            success = false;
                        } else if ("#".equals(board.get(y).get(x - 1)) || "O".equals(board.get(y).get(x - 1))) {
                            board.get(y).set(x, "+");
                            String o = (y) + "_" + (x - 1) + step;
                            if (turn.contains(o)) {
                                runGame = false;
                                success = true;
                            } else {
                                turn.add(o);
                            }
                            step = "^";
                        } else {
                            if ("|".equals(board.get(y).get(x)) || "#".equals(board.get(y + 1).get(x))
                                    || "O".equals(board.get(y + 1).get(x))) {
                                board.get(y).set(x, "+");
                            } else {
                                board.get(y).set(x, "-");
                            }

                            x -= 1;
                        }
                    }
                }

                if (success) {
                    successList.add(currYX);
                } else {
                    failedList.add(currYX);
                }

                // System.out.println("successList = " + successList);
                // System.out.println("failedList = " + failedList);
                // System.out.println("counter = " + counter);
                // counter++;

            }

            System.out.println(successList.size());
        } catch (FileNotFoundException e) {
            System.exit(1);
        }
    }
}
