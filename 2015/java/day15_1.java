import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class day15_1 {
  public static void main(String[] args) {
    Map<String, Map<String, Integer>> ingredients = new HashMap<>();
    List<String> keys = new ArrayList<>();
    int res = 0;
    /* try (Scanner scan = new Scanner(new File("../input/day15.test"))) { */
    try (Scanner scan = new Scanner(new File("../input/day15.input"))) {
      scan.useDelimiter("\n");
      while (scan.hasNext()) {
        String str = scan.next().trim();
        String[] str2 = str.split(":");
        keys.add(str2[0].trim());
        String[] str3 = str2[1].trim().split(",");
        ingredients.put(str2[0].trim(), new HashMap<>());
        System.out.println("str2[0].trim(): " + str2[0].trim());
        for (String s : str3) {
          String[] b = s.trim().split(" ");
          String name = b[0].trim();
          int value = Integer.valueOf(b[1].trim());
          ingredients.get(str2[0].trim()).put(name, value);
          System.out.println("name: " + name + ", value: " + value);
        }
      }
      for (int teaspoon1 = 0; teaspoon1 <= 100; teaspoon1++) {
        int valueCapacity1 = ingredients.get(keys.get(0)).get("capacity") * teaspoon1;
        int valueDurability1 = ingredients.get(keys.get(0)).get("durability") * teaspoon1;
        int valueFlavor1 = ingredients.get(keys.get(0)).get("flavor") * teaspoon1;
        int valueTexture1 = ingredients.get(keys.get(0)).get("texture") * teaspoon1;

        if (keys.size() == 2) {
          int teaspoon2 = 100 - teaspoon1;
          int valueCapacity2 = ingredients.get(keys.get(1)).get("capacity") * teaspoon2;
          int valueDurability2 = ingredients.get(keys.get(1)).get("durability") * teaspoon2;
          int valueFlavor2 = ingredients.get(keys.get(1)).get("flavor") * teaspoon2;
          int valueTexture2 = ingredients.get(keys.get(1)).get("texture") * teaspoon2;

          int totalValueCapacity = valueCapacity1 + valueCapacity2;
          totalValueCapacity = Math.max(totalValueCapacity, 0);
          int totalValueDurability = valueDurability1 + valueDurability2;
          totalValueDurability = Math.max(totalValueDurability, 0);
          int totalValueFlavor = valueFlavor1 + valueFlavor2;
          totalValueFlavor = Math.max(totalValueFlavor, 0);
          int totalValueTexture = valueTexture1 + valueTexture2;
          totalValueTexture = Math.max(totalValueTexture, 0);
          int total =
              totalValueCapacity * totalValueDurability * totalValueFlavor * totalValueTexture;

          if (total > res) {
            res = total;
          }
        } else {
          for (int teaspoon2 = 0; teaspoon2 <= 100 - teaspoon1; teaspoon2++) {
            int valueCapacity2 = ingredients.get(keys.get(1)).get("capacity") * teaspoon2;
            int valueDurability2 = ingredients.get(keys.get(1)).get("durability") * teaspoon2;
            int valueFlavor2 = ingredients.get(keys.get(1)).get("flavor") * teaspoon2;
            int valueTexture2 = ingredients.get(keys.get(1)).get("texture") * teaspoon2;

            if (keys.size() == 3) {
              int teaspoon3 = 100 - teaspoon1 - teaspoon2;
              int valueCapacity3 = ingredients.get(keys.get(2)).get("capacity") * teaspoon3;
              int valueDurability3 = ingredients.get(keys.get(2)).get("durability") * teaspoon3;
              int valueFlavor3 = ingredients.get(keys.get(2)).get("flavor") * teaspoon3;
              int valueTexture3 = ingredients.get(keys.get(2)).get("texture") * teaspoon3;

              int totalValueCapacity = valueCapacity1 + valueCapacity2 + valueCapacity3;
              totalValueCapacity = Math.max(totalValueCapacity, 0);
              int totalValueDurability = valueDurability1 + valueDurability2 + valueDurability3;
              totalValueDurability = Math.max(totalValueDurability, 0);
              int totalValueFlavor = valueFlavor1 + valueFlavor2 + valueFlavor3;
              totalValueFlavor = Math.max(totalValueFlavor, 0);
              int totalValueTexture = valueTexture1 + valueTexture2 + valueTexture3;
              totalValueTexture = Math.max(totalValueTexture, 0);
              int total =
                  totalValueCapacity * totalValueDurability * totalValueFlavor * totalValueTexture;
              if (total > res) {
                res = total;
              }
            } else {
              for (int teaspoon3 = 0; teaspoon3 <= 100 - teaspoon1 - teaspoon2; teaspoon3++) {
                int valueCapacity3 = ingredients.get(keys.get(2)).get("capacity") * teaspoon3;
                int valueDurability3 = ingredients.get(keys.get(2)).get("durability") * teaspoon3;
                int valueFlavor3 = ingredients.get(keys.get(2)).get("flavor") * teaspoon3;
                int valueTexture3 = ingredients.get(keys.get(2)).get("texture") * teaspoon3;

                if (keys.size() == 4) {
                  int teaspoon4 = 100 - teaspoon1 - teaspoon2 - teaspoon3;
                  int valueCapacity4 = ingredients.get(keys.get(3)).get("capacity") * teaspoon4;
                  int valueDurability4 = ingredients.get(keys.get(3)).get("durability") * teaspoon4;
                  int valueFlavor4 = ingredients.get(keys.get(3)).get("flavor") * teaspoon4;
                  int valueTexture4 = ingredients.get(keys.get(3)).get("texture") * teaspoon4;

                  int totalValueCapacity =
                      valueCapacity1 + valueCapacity2 + valueCapacity3 + valueCapacity4;
                  totalValueCapacity = Math.max(totalValueCapacity, 0);
                  int totalValueDurability =
                      valueDurability1 + valueDurability2 + valueDurability3 + valueDurability4;
                  totalValueDurability = Math.max(totalValueDurability, 0);
                  int totalValueFlavor = valueFlavor1 + valueFlavor2 + valueFlavor3 + valueFlavor4;
                  totalValueFlavor = Math.max(totalValueFlavor, 0);
                  int totalValueTexture =
                      valueTexture1 + valueTexture2 + valueTexture3 + valueTexture4;
                  totalValueTexture = Math.max(totalValueTexture, 0);
                  int total =
                      totalValueCapacity
                          * totalValueDurability
                          * totalValueFlavor
                          * totalValueTexture;
                  if (total > res) {
                    res = total;
                  }
                } else {
                  // you can add more ingredients here or use a loop to iterate through all
                  // ingredients
                }
              }
            }
          }
        }
      }
      System.out.println(res);
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
  }
}
