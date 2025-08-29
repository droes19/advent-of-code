import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class day19_2 {
  public static void main(String[] args) {
    try {
      List<String> lines = Files.readAllLines(Path.of("../input/day19.input"));

      List<Rule> rules = new ArrayList<>();
      String target = null;

      for (String raw : lines) {
        String line = raw.trim();
        if (line.isEmpty()) continue;
        if (line.contains("=>")) {
          String[] p = line.split("\\s*=>\\s*");
          rules.add(new Rule(p[0], p[1]));
        } else {
          target = line;
        }
      }

      if (target == null) {
        System.err.println("No target molecule found.");
        return;
      }

      List<Rule> rev = new ArrayList<>();
      for (Rule r : rules) rev.add(new Rule(r.rhs(), r.lhs()));
      rev.sort(Comparator.comparingInt((Rule r) -> r.lhs().length()).reversed());

      int best = Integer.MAX_VALUE;
      Random rnd = new Random();

      for (int attempt = 1; attempt <= 10000; attempt++) {
        // Optional: shuffle to escape dead ends
        Collections.shuffle(rev, rnd);

        String cur = target;
        int steps = 0;
        boolean progressed = true;

        while (!cur.equals("e") && progressed) {
          progressed = false;
          for (Rule r : rev) {
            String lhs = r.lhs();
            String rhs = r.rhs();
            int idx = cur.indexOf(lhs);
            if (idx >= 0) {
              cur = cur.substring(0, idx) + rhs + cur.substring(idx + lhs.length());
              steps++;
              progressed = true;
              break;
            }
          }
        }

        if (cur.equals("e")) {
          best = Math.min(best, steps);
          System.out.println("Found in " + steps + " steps (attempt " + attempt + ")");
          break;
        }
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

record Rule(String lhs, String rhs) {}
