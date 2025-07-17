// TODO: not solved yet
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class day12_2 {
  public static void main(String[] args) {
    int res = 0;
    try (Scanner scan = new Scanner(new File("../input/day12.input"))) {
      String str = scan.nextLine().trim();
      List<Integer> listKey = new ArrayList<>();
      Stack<JsonToken> stack = new Stack<>();
      Map<Integer, Map<String, String>> map = new HashMap<>();
      for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        if (c == '{' || c == '[') {
          map.put(i, new HashMap<>());
          listKey.add(i);
          stack.push(new JsonToken(c == '{' ? 1 : 2, i));
          map.get(i).put("type", c == '{' ? "1" : "2");
        } else if (c == '}' || c == ']') {
          JsonToken idx = stack.pop();
          if (!map.get(idx.getKey()).containsKey("str")) {
            map.get(idx.getKey()).put("str", str.substring(idx.getKey(), i + 1));
          }
        }
      }
      do {
        res = 0;
        for (Integer key : listKey) {
          if (map.get(key).get("type").equals("1")) {

            String strOri = map.get(key).get("str");
            String strFil = strOri;
            boolean filtered = false;
            if (map.get(key).containsKey("filtered")) {
              strFil = map.get(key).get("filtered");
              filtered = true;
            }

            String tmp = strFil.substring(1, strFil.length() - 1);
            if (!tmp.contains("{") && !tmp.contains("[")) {
              int numStart = 0;
              if (tmp.contains("red")) {
                map.get(key).put("value", "0");
              } else {
                for (char c : tmp.toCharArray()) {
                  if (String.valueOf(c).matches("\\d+")) {
                    numStart += Integer.parseInt(String.valueOf(c));
                  }
                }
                map.get(key).put("value", String.valueOf(numStart));
              }
              for (Integer k : listKey) {
                if (!k.equals(key)) {
                  String strOri2 = map.get(k).get("str");
                  String strFil2 = strOri2;
                  if (map.get(k).containsKey("filtered")) {
                    strFil2 = map.get(k).get("filtered");
                  }
                  strFil2 = strFil2.replace(filtered ? strFil : strOri, map.get(key).get("value"));
                  map.get(k).put("filtered", strFil2);
                }
              }
            } else {
              continue;
            }
          } else {
            String strOri = map.get(key).get("str");
            String strFil = strOri;
            boolean filtered = false;
            if (map.get(key).containsKey("filtered")) {
              strFil = map.get(key).get("filtered");
              filtered = true;
            }

            String tmp = strFil.substring(1, strFil.length() - 1);
            if (!tmp.contains("{") && !tmp.contains("[")) {
              int numStart = 0;
              for (char c : tmp.toCharArray()) {
                if (String.valueOf(c).matches("\\d+")) {
                  numStart += Integer.parseInt(String.valueOf(c));
                }
              }
              map.get(key).put("value", String.valueOf(numStart));
              for (Integer k : listKey) {
                if (!k.equals(key)) {
                  String strOri2 = map.get(k).get("str");
                  String strFil2 = strOri2;
                  if (map.get(k).containsKey("filtered")) {
                    strFil2 = map.get(k).get("filtered");
                  }
                  strFil2 = strFil2.replace(filtered ? strFil : strOri, map.get(key).get("value"));
                  map.get(k).put("filtered", strFil2);
                }
              }
            } else {
              continue;
            }
          }
          if (map.get(key).containsKey("str")) {
            res++;
          } else {
            if (map.get(key).get("type").equals("1")) {
              if (map.containsKey(key + 1) && map.get(key + 1).get("type").equals("2")) {
                map.get(key).put("str", map.get(key + 1).get("str"));
              }
            } else if (map.get(key).get("type").equals("2")) {
              if (map.containsKey(key - 1) && map.get(key - 1).get("type").equals("1")) {
                map.get(key).put("str", map.get(key - 1).get("str"));
              }
            }
          }
        }
      } while (res > 0);
      for (Integer key : listKey) {
        if (map.get(key).get("type").equals("1")) {
          System.out.println("Found object: " + map.get(key).get("str"));
        } else {
          System.out.println("Found array: " + map.get(key).get("str"));
        }
      }
      System.out.println("Number of objects1: " + map.size());
      System.out.println("Key: " + 1 + ", Value: " + map.get(1).get("str"));
      for (int i = 0; i < listKey.size(); i++) {
        int key = listKey.get(i);
        String str1 = map.get(key).get("str");
        for (int j = i + 1; j < listKey.size(); j++) {
          int key2 = listKey.get(j);
          String str2 = map.get(key2).get("str");
          if (str1.contains(str2)) {
            map.remove(key2);
            listKey.remove(j);
          }
        }
      }
      System.out.println("Number of objects2: " + map.size());
      int idx = 0;
      // for (Integer key : listKey) {
      // if (idx == 5) {
      // break;
      // }
      // System.out.println("==============================a");
      // System.out.println("Key: " + key + ", Value: " + map.get(key).get("str"));
      // System.out.println("==============================b");
      // idx++;
      // }
      // System.out.println("Key: " + 2 + ", Value: " + map.get(2).get("str"));
      // for (int i = 0; i < map.size(); i++) {
      // System.out.println("==============================a");
      // System.out.println("Key: " + i + ", Value: " + map.get(i).get("str"));
      // System.out.println("==============================b");
      // }
      // System.out.println(res);
    } catch (FileNotFoundException e) {
      System.exit(1);
    }
  }
}

class JsonToken {
  int type; // 1 for object, 2 for array
  int key; // key in the map

  public JsonToken(int type, int key) {
    this.type = type;
    this.key = key;
  }

  public int getType() {
    return type;
  }

  public int getKey() {
    return key;
  }
}
