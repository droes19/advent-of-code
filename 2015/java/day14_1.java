import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class day14_1 {
  public static void main(String[] args) {
    Map<String, Map<String, Integer>> reindeers = new java.util.HashMap<>();
    int res = 0;
    try (Scanner scan = new Scanner(new File("../input/day14.input"))) {
      scan.useDelimiter("\n");
      while (scan.hasNext()) {
        String a = scan.next().trim();
        String[] b = a.split(" ");
        String name = b[0].trim();
        int distance = Integer.valueOf(b[3].trim());
        int every = Integer.valueOf(b[6].trim());
        int rest = Integer.valueOf(b[b.length - 2].trim());
        reindeers.put(name, new HashMap<>());
        reindeers.get(name).put("distance", distance);
        reindeers.get(name).put("every", every);
        reindeers.get(name).put("rest", rest);
      }

      for (int second = 0; second <= 2503; second++) {
        for (String key : reindeers.keySet()) {
          if (second == 0) {
            reindeers.get(key).put("state", 0); // 0 means run, 1 means rest
            reindeers.get(key).put("step", reindeers.get(key).get("every"));
          } else {
            Map<String, Integer> reindeer = reindeers.get(key);
            reindeer.put("step", reindeer.get("step") - 1);
            if (reindeer.get("state") == 0) {
              reindeer.put(
                  "totalDistance",
                  reindeer.getOrDefault("totalDistance", 0) + reindeer.get("distance"));
              if (reindeer.get("step") == 0) {
                reindeer.put("state", 1);
                reindeer.put("step", reindeer.get("rest"));
              }
            } else {
              if (reindeer.get("step") == 0) {
                reindeer.put("state", 0);
                reindeer.put("step", reindeer.get("every"));
              }
            }
          }
        }
      }
      for (String key : reindeers.keySet()) {
        System.out.println(reindeers.get(key));
      }
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
  }
}
