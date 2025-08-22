import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class day16_2 {
  public static void main(String[] args) {
    Map<String, Integer> clues = new HashMap<>();
    clues.put("children", 3);
    clues.put("cats", 7);
    clues.put("samoyeds", 2);
    clues.put("pomeranians", 3);
    clues.put("akitas", 0);
    clues.put("vizslas", 0);
    clues.put("goldfish", 5);
    clues.put("trees", 3);
    clues.put("cars", 2);
    clues.put("perfumes", 1);

    Map<String, Map<String, Integer>> sues = new HashMap<>();
    int res = 0;
    try (Scanner scan = new Scanner(new File("../input/day16.input"))) {
      scan.useDelimiter("\n");
      while (scan.hasNext()) {
        String str = scan.next().trim();
        String[] str2 = str.split(",");
        String name = str2[0].split(":")[0].trim();
        sues.put(name, new HashMap<>());
        sues.get(name)
            .put(str2[0].split(":")[1].trim(), Integer.parseInt(str2[0].split(":")[2].trim()));
        sues.get(name)
            .put(str2[1].split(":")[0].trim(), Integer.parseInt(str2[1].split(":")[1].trim()));
        sues.get(name)
            .put(str2[2].split(":")[0].trim(), Integer.parseInt(str2[2].split(":")[1].trim()));
      }

      for (String key : sues.keySet()) {
        Map<String, Integer> sue = sues.get(key);
        boolean match = true;
        for (String clue : clues.keySet()) {
          if (sue.containsKey(clue)) {
            if (clue.equals("cats") || clue.equals("trees")) {
              if (sue.get(clue) <= clues.get(clue)) {
                match = false;
                break;
              }
            } else if (clue.equals("pomeranians") || clue.equals("goldfish")) {
              if (sue.get(clue) >= clues.get(clue)) {
                match = false;
                break;
              }
            } else {
              if (sue.get(clue) != clues.get(clue)) {
                match = false;
                break;
              }
            }
          }
        }
        if (match) {
          res = Integer.parseInt(key.split(" ")[1]);
        }
      }

      System.out.println(res);
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
  }
}
