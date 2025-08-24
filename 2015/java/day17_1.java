import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day17_1 {
  public static void main(String[] args) {
    List<Integer> containers = new ArrayList<>();
    try (Scanner scan = new Scanner(new File("../input/day17.input"))) {
      scan.useDelimiter("\n");
      while (scan.hasNext()) {
        String str = scan.next().trim();
        containers.add(Integer.valueOf(str));
      }
      int target = 150;
      List<List<Integer>> lists = new ArrayList<>();
      for (int i = 0; i < (1 << containers.size()); i++) {
        int sum = 0;
        List<Integer> list = new ArrayList<>();
        for (int j = 0; j < containers.size(); j++) {
          if ((i & (1 << j)) != 0) {
            list.add(containers.get(j));
            sum += containers.get(j);
          }
        }

        if (sum == target) {
          lists.add(list);
        }
      }

      System.out.println(lists.size());
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
  }
}
