import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class day14_2 {
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
        reindeers.get(name).put("state", 0); // 0 means run, 1 means rest
        reindeers.get(name).put("step", reindeers.get(name).get("every"));
      }

      for (int second = 1; second <= 2503; second++) {
        for (String key : reindeers.keySet()) {
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
        List<String> leadReindeers = new ArrayList<>();
        for (String key : reindeers.keySet()) {
          if (leadReindeers.isEmpty()) {
            leadReindeers.add(key);
          } else {
            if (reindeers.get(key).getOrDefault("totalDistance", 0)
                >= reindeers.get(leadReindeers.get(0)).getOrDefault("totalDistance", 0)) {
              if (reindeers.get(key).getOrDefault("totalDistance", 0)
                  > reindeers.get(leadReindeers.get(0)).getOrDefault("totalDistance", 0)) {
                leadReindeers = new ArrayList<>();
              }
              leadReindeers.add(key);
            }
          }
        }

        for (String key : leadReindeers) {
          reindeers.get(key).put("points", reindeers.get(key).getOrDefault("points", 0) + 1);
        }
      }
      for (String key : reindeers.keySet()) {
        System.out.println("key: " + key);
        System.out.println(reindeers.get(key));
      }
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
  }
}
