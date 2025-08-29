import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class day19_1 {
  public static void main(String[] args) {
    List<Obj> objs = new ArrayList<>();
    String originalString = null;
    int res = 0;
    try (Scanner scan = new Scanner(new File("../input/day19.input"))) {
      scan.useDelimiter("\n");
      boolean isObj = true;
      while (scan.hasNext()) {
        String str = scan.next().trim();
        if (str.isEmpty()) {
          isObj = false;
          continue;
        }
        if (isObj) {
          String[] o = str.split(" => ");
          Obj obj = new Obj(o[0].trim(), o[1].trim());
          objs.add(obj);
        } else {
          originalString = str;
        }
      }

      Set<String> strSet = new HashSet<>();
      for (Obj obj : objs) {
        if (originalString == null) {
          throw new Exception("String not found");
        }
        String tmp = new String(originalString);
        String key = obj.getKey();
        String replace = obj.getReplace();
        for (int i = 0; i < tmp.length(); i++) {
          int keyLength = key.length();
					if(i+keyLength > tmp.length()){
						continue;
					}
          if (key.equals(tmp.substring(i, i + keyLength))) {
            String str1 = tmp.substring(0, i);
            String str2 = tmp.substring(i + keyLength);
            String strRes = str1 + replace + str2;
            strSet.add(strRes);
          }
        }
      }
      System.out.println(strSet.size());
    } catch (FileNotFoundException e) {
      System.exit(1);
    } catch (Exception e) {
			e.printStackTrace();
      System.exit(1);
    }
  }
}

class Obj {
  private String key;
  private String replace;

  public Obj(String key, String replace) {
    this.key = key;
    this.replace = replace;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getReplace() {
    return replace;
  }

  public void setReplace(String replace) {
    this.replace = replace;
  }

  public String toString() {
    return "{key: " + key + ", replace: " + replace+"}";
  }
}
