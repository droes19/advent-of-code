import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class day13_1 {
  public static void main(String[] args) {
    Map<String, Map<String, Integer>> people = new java.util.HashMap<>();
    List<String> listPeople = new ArrayList<>();
    int res = 0;
    try (Scanner scan = new Scanner(new File("../input/day13.input"))) {
      scan.useDelimiter("\n");
      while (scan.hasNext()) {
        String a = scan.next().trim();
        String[] b = a.split(" ");
        int happiness =
            Integer.valueOf((b[2].trim().equalsIgnoreCase("lose") ? "-" : "") + b[3].trim());
        String p1 = b[0].trim();
        String p2 = b[b.length - 1].replace(".", "").trim();
        Map<String, Integer> map1 = people.putIfAbsent(p1, new java.util.HashMap<>());
        if (map1 == null) {
          listPeople.add(p1);
        }
        people.get(p1).put(p2, happiness);
      }
      String fixedPerson = listPeople.get(0);
      List<String> others = new ArrayList<>(listPeople.subList(1, listPeople.size()));
      List<List<String>> arrangements = new ArrayList<>();
      generatePermutations(others, 0, arrangements);

      int optimalHappiness = Integer.MIN_VALUE;
      for (List<String> perm : arrangements) {
        List<String> seating = new ArrayList<>();
        seating.add(fixedPerson);
        seating.addAll(perm);
        int happiness = 0;
        for (int i = 0; i < seating.size(); i++) {
          String left = seating.get(i);
          String right = seating.get((i + 1) % seating.size());
          int leftHappiness = people.get(left).getOrDefault(right, 0);
          int rightHappiness = people.get(right).getOrDefault(left, 0);
          happiness += leftHappiness + rightHappiness;
        }
        if (happiness > optimalHappiness) {
          optimalHappiness = happiness;
        }
      }

      System.out.println("Optimal happiness: " + optimalHappiness);

    } catch (FileNotFoundException e) {
      System.exit(1);
    }
  }

  private static void generatePermutations(
      List<String> list, int index, List<List<String>> result) {
    if (index == list.size() - 1) {
      result.add(new ArrayList<>(list));
      return;
    }

    for (int i = index; i < list.size(); i++) {
      Collections.swap(list, i, index);
      generatePermutations(list, index + 1, result);
      Collections.swap(list, i, index); // backtrack
    }
  }
}
