import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class day20_2 {
  public static void main(String[] args) throws Exception {
    // int target = Integer.parseInt(Files.readString(Path.of("../input/day20.test")).trim());
    int target = Integer.parseInt(Files.readString(Path.of("../input/day20.input")).trim());
    System.out.println(target);

    int max = Math.max(1, target / 10);

    outerloop:
    while (true) {
      int[] presents = new int[max + 1];
      Arrays.fill(presents, 0);

      // For each elf from 1 to max
      for (int elf = 1; elf <= max; elf++) {
        int maxVisits = 50;
        // For each house that this elf visits (multiples of elf), add presents
        for (int house = elf; house <= max; house += elf) {
          // Each elf visits only 50 houses
          if (maxVisits-- <= 0) {
            break;
          }
          presents[house] += 11 * elf; // Each elf delivers 11 * elf presents
        }
      }

      for (int house = 1; house <= max; house++) {
        if (presents[house] >= target) {
          System.out.println(house);
          break outerloop;
        }
      }

      max *= 2;
    }
  }
}
