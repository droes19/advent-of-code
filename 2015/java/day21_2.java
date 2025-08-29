import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class day21_2 {
  private static Integer playerHP = 100;

  public static void main(String[] args) {
    List<Map<String, String>> weapon = getWeapons();
    List<Map<String, String>> armor = getArmors();
    List<Map<String, String>> ring = getRings();
    try {
      List<String> lines = Files.readAllLines(Path.of("../input/day21.input"));
      Map<String, Integer> boss =
          Map.of(
              "hp", Integer.valueOf(lines.get(0).split(":")[1].trim()),
              "damage", Integer.valueOf(lines.get(1).split(":")[1].trim()),
              "armor", Integer.valueOf(lines.get(2).split(":")[1].trim()));

      int worstCost = Integer.MIN_VALUE;
      List<Map<String, String>> worstEq = null;

      for (Map<String, String> wp : weapon) {
        int baseDmg = Integer.parseInt(wp.get("damage"));
        int baseArm = 0;

        // try with each armor (including NONE)
        for (Map<String, String> ar : armor) {
          int dmg = baseDmg;
          int arm = baseArm + Integer.parseInt(ar.get("armor"));

          List<Map<String, String>> eq = new ArrayList<>();
          eq.add(wp);
          if (!ar.get("name").equals("NONE")) eq.add(ar);

          boolean isPlayerWin = fight(boss, playerHP, dmg, arm);
          if (!isPlayerWin) {
            int cost = costOf(eq);
            if (cost > worstCost) {
              worstCost = cost;
              worstEq = List.copyOf(eq);
            }
          }

          // try use one ring
          for (int i = 0; i < ring.size(); i++) {
            Map<String, String> r1 = ring.get(i);
            int dmg1 = dmg + Integer.parseInt(r1.get("damage"));
            int arm1 = arm + Integer.parseInt(r1.get("armor"));

            eq.add(r1);
            isPlayerWin = fight(boss, playerHP, dmg1, arm1);
            if (!isPlayerWin) {
              int cost = costOf(eq);
              if (cost > worstCost) {
                worstCost = cost;
                worstEq = List.copyOf(eq);
              }
            }

            // try use two ring
            for (int j = i + 1; j < ring.size(); j++) {
              Map<String, String> r2 = ring.get(j);
              int dmg2 = dmg1 + Integer.parseInt(r2.get("damage"));
              int arm2 = arm1 + Integer.parseInt(r2.get("armor"));

              eq.add(r2);
              isPlayerWin = fight(boss, playerHP, dmg2, arm2);
              if (!isPlayerWin) {
                int cost = costOf(eq);
                if (cost > worstCost) {
                  worstCost = cost;
                  worstEq = List.copyOf(eq);
                }
              }
              eq.remove(r2);
            }
            eq.remove(r1);
          }
        }
      }

      if (worstEq != null) {
        printEq(worstEq);
      } else {
        System.out.println("No losing equipment found.");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  static List<Map<String, String>> getWeapons() {
    List<Map<String, String>> weapon = new ArrayList<>();
    weapon.add(
        Map.of("type", "weapon", "name", "Dagger", "cost", "8", "damage", "4", "armor", "0"));
    weapon.add(
        Map.of("type", "weapon", "name", "Shortsword", "cost", "10", "damage", "5", "armor", "0"));
    weapon.add(
        Map.of("type", "weapon", "name", "Warhammer", "cost", "25", "damage", "6", "armor", "0"));
    weapon.add(
        Map.of("type", "weapon", "name", "Longsword", "cost", "40", "damage", "7", "armor", "0"));
    weapon.add(
        Map.of("type", "weapon", "name", "Greataxe", "cost", "74", "damage", "8", "armor", "0"));
    weapon.sort(Comparator.comparingInt(s -> Integer.parseInt(s.get("cost"))));
    return weapon;
  }

  static List<Map<String, String>> getArmors() {
    List<Map<String, String>> armor = new ArrayList<>();
    armor.add(Map.of("type", "armor", "name", "NONE", "cost", "0", "damage", "0", "armor", "0"));
    armor.add(
        Map.of("type", "armor", "name", "Leather", "cost", "13", "damage", "0", "armor", "1"));
    armor.add(
        Map.of("type", "armor", "name", "Chainmail", "cost", "31", "damage", "0", "armor", "2"));
    armor.add(
        Map.of("type", "armor", "name", "Splintmail", "cost", "53", "damage", "0", "armor", "3"));
    armor.add(
        Map.of("type", "armor", "name", "Bandedmail", "cost", "75", "damage", "0", "armor", "4"));
    armor.add(
        Map.of("type", "armor", "name", "Platemail", "cost", "102", "damage", "0", "armor", "5"));
    armor.sort(Comparator.comparingInt(s -> Integer.parseInt(s.get("cost"))));
    return armor;
  }

  static List<Map<String, String>> getRings() {
    List<Map<String, String>> ring = new ArrayList<>();
    ring.add(
        Map.of("type", "ring", "name", "Damage +1", "cost", "25", "damage", "1", "armor", "0"));
    ring.add(
        Map.of("type", "ring", "name", "Damage +2", "cost", "50", "damage", "2", "armor", "0"));
    ring.add(
        Map.of("type", "ring", "name", "Damage +3", "cost", "100", "damage", "3", "armor", "0"));
    ring.add(
        Map.of("type", "ring", "name", "Defense +1", "cost", "20", "damage", "0", "armor", "1"));
    ring.add(
        Map.of("type", "ring", "name", "Defense +2", "cost", "40", "damage", "0", "armor", "2"));
    ring.add(
        Map.of("type", "ring", "name", "Defense +3", "cost", "80", "damage", "0", "armor", "3"));
    ring.sort(Comparator.comparingInt(s -> Integer.parseInt(s.get("cost"))));
    return ring;
  }

  private static boolean fight(
      Map<String, Integer> boss, int playerHp, int playerDmg, int playerArmor) {
    int bossHp = boss.get("hp");
    int bossDmg = boss.get("damage");
    int bossArmor = boss.get("armor");

    int playerHit = Math.max(1, playerDmg - bossArmor);
    int bossHit = Math.max(1, bossDmg - playerArmor);

    while (true) {
      bossHp -= playerHit;
      if (bossHp <= 0) return true;

      playerHp -= bossHit;
      if (playerHp <= 0) return false;
    }
  }

  static void printEq(List<Map<String, String>> eq) {
    int cost = 0;
    int damage = 0;
    int armor = 0;
    for (Map<String, String> item : eq) {
      cost += Integer.valueOf(item.get("cost"));
      damage += Integer.valueOf(item.get("damage"));
      armor += Integer.valueOf(item.get("armor"));
      System.out.print(item.get("name") + ", ");
    }
    System.out.println(" | cost: " + cost + ", damage: " + damage + ", armor: " + armor);
  }

  static int costOf(List<Map<String, String>> eq) {
    int c = 0;
    for (Map<String, String> m : eq) c += Integer.parseInt(m.get("cost"));
    return c;
  }
}
