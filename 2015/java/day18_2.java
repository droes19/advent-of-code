import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class day18_2 {
  public static void main(String[] args) {
    List<List<Character>> grid = new ArrayList<>();
    int res = 0;
    try (Scanner scan = new Scanner(new File("../input/day18.input"))) {
      scan.useDelimiter("\n");
      while (scan.hasNext()) {
        String str = scan.next().trim();
        List<Character> line = str.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        grid.add(line);
      }
      for (int y = 0; y < grid.size(); y++) {
        for (int x = 0; x < grid.get(0).size(); x++) {
          if (isCorner(grid, y, x)) {
            grid.get(y).set(x, '#');
            continue;
          }
        }
      }
      int maxStep = 100;
      int step = 1;
      List<List<Character>> currentGrid = deepCopyGrid(grid);

      do {
        List<List<Character>> newGrid = deepCopyGrid(grid);
        for (int y = 0; y < grid.size(); y++) {
          for (int x = 0; x < grid.get(0).size(); x++) {
            if (isCorner(currentGrid, y, x)) {
              newGrid.get(y).set(x, '#');
              continue;
            }

            boolean isNeighbor1On = isNeighbor1On(currentGrid, y, x);
            boolean isNeighbor2On = isNeighbor2On(currentGrid, y, x);
            boolean isNeighbor3On = isNeighbor3On(currentGrid, y, x);
            boolean isNeighbor4On = isNeighbor4On(currentGrid, y, x);
            boolean isNeighbor5On = isNeighbor5On(currentGrid, y, x);
            boolean isNeighbor6On = isNeighbor6On(currentGrid, y, x);
            boolean isNeighbor7On = isNeighbor7On(currentGrid, y, x);
            boolean isNeighbor8On = isNeighbor8On(currentGrid, y, x);
            int totalNeighborOn =
                0
                    + (isNeighbor1On ? 1 : 0)
                    + (isNeighbor2On ? 1 : 0)
                    + (isNeighbor3On ? 1 : 0)
                    + (isNeighbor4On ? 1 : 0)
                    + (isNeighbor5On ? 1 : 0)
                    + (isNeighbor6On ? 1 : 0)
                    + (isNeighbor7On ? 1 : 0)
                    + (isNeighbor8On ? 1 : 0);

            if (currentGrid.get(y).get(x) == '#') {
              if (totalNeighborOn != 2 & totalNeighborOn != 3) {
                newGrid.get(y).set(x, '.');
              } else {
                newGrid.get(y).set(x, '#');
              }
            } else {
              if (totalNeighborOn == 3) {
                newGrid.get(y).set(x, '#');
              } else {
                newGrid.get(y).set(x, '.');
              }
            }
          }
        }
        currentGrid = deepCopyGrid(newGrid);
        step++;
      } while (step <= maxStep);
      for (List<Character> row : currentGrid) {
        for (Character c : row) {
          if (c == '#') {
            res++;
          }
        }
      }
      System.out.println(res);
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
  }

  private static boolean isNeighbor1On(List<List<Character>> grid, int gy, int gx) {
    int y = gy - 1;
    int x = gx - 1;
    boolean on = false;
    if (y >= 0 && x >= 0) {
      if (grid.get(y).get(x) == '#') {
        on = true;
      }
    }
    return on;
  }

  private static boolean isNeighbor2On(List<List<Character>> grid, int gy, int gx) {
    int y = gy - 1;
    int x = gx;
    boolean on = false;
    if (y >= 0) {
      if (grid.get(y).get(x) == '#') {
        on = true;
      }
    }
    return on;
  }

  private static boolean isNeighbor3On(List<List<Character>> grid, int gy, int gx) {
    int y = gy - 1;
    int x = gx + 1;
    boolean on = false;
    if (y >= 0 && x < grid.size()) {
      if (grid.get(y).get(x) == '#') {
        on = true;
      }
    }
    return on;
  }

  private static boolean isNeighbor4On(List<List<Character>> grid, int gy, int gx) {
    int y = gy;
    int x = gx - 1;
    boolean on = false;
    if (x >= 0) {
      if (grid.get(y).get(x) == '#') {
        on = true;
      }
    }
    return on;
  }

  private static boolean isNeighbor5On(List<List<Character>> grid, int gy, int gx) {
    int y = gy;
    int x = gx + 1;
    boolean on = false;
    if (x < grid.size()) {
      if (grid.get(y).get(x) == '#') {
        on = true;
      }
    }
    return on;
  }

  private static boolean isNeighbor6On(List<List<Character>> grid, int gy, int gx) {
    int y = gy + 1;
    int x = gx - 1;
    boolean on = false;
    if (y < grid.size() && x >= 0) {
      if (grid.get(y).get(x) == '#') {
        on = true;
      }
    }
    return on;
  }

  private static boolean isNeighbor7On(List<List<Character>> grid, int gy, int gx) {
    int y = gy + 1;
    int x = gx;
    boolean on = false;
    if (y < grid.size()) {
      if (grid.get(y).get(x) == '#') {
        on = true;
      }
    }
    return on;
  }

  private static boolean isNeighbor8On(List<List<Character>> grid, int gy, int gx) {
    int y = gy + 1;
    int x = gx + 1;
    boolean on = false;
    if (y < grid.size() && x < grid.size()) {
      if (grid.get(y).get(x) == '#') {
        on = true;
      }
    }
    return on;
  }

  private static List<List<Character>> deepCopyGrid(List<List<Character>> original) {
    List<List<Character>> copy = new ArrayList<>();
    for (List<Character> row : original) {
      copy.add(new ArrayList<>(row));
    }
    return copy;
  }

  private static boolean isCorner(List<List<Character>> grid, int gy, int gx) {
    if ((gy == 0 && gx == 0)
        || (gy == 0 && gx == grid.size() - 1)
        || (gy == grid.size() - 1 && gx == 0)
        || (gy == grid.size() - 1 && gx == grid.size() - 1)) {
      return true;
    }
    return false;
  }
}
