import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day12_2 {
  public static void main(String[] args) {
    int res = 0;
    try (Scanner scan = new Scanner(new File("../input/day12.input"))) {
      String str = scan.nextLine().trim();
      for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        if (c == 'r') {
          if (str.charAt(i + 1) == 'e' && str.charAt(i + 2) == 'd') {
            if (str.charAt(i - 2) == ':') {
              int closeParenthesis = 0;
              int start = -1;
              for (int j = i; j > 0; j--) {
                if (str.charAt(j) == '{'
                    || str.charAt(j) == '['
                    || str.charAt(j) == '}'
                    || str.charAt(j) == ']') {
                  if (closeParenthesis == 0) {
                    if (str.charAt(j) == '}' || str.charAt(j) == ']') {
                      closeParenthesis++;
                    } else {
                      start = j;
                    }
                  } else {
                    if (str.charAt(j) == '}' || str.charAt(j) == ']') {
                      closeParenthesis++;
                    } else {
                      closeParenthesis--;
                    }
                  }
                }
                if (closeParenthesis == 0 && start != -1) {
                  break;
                }
              }
              int openParenthesis = 0;
              int end = -1;
              for (int j = i + 3; j < str.length(); j++) {
                if (str.charAt(j) == '{'
                    || str.charAt(j) == '['
                    || str.charAt(j) == '}'
                    || str.charAt(j) == ']') {
                  if (openParenthesis == 0) {
                    if (str.charAt(j) == '{' || str.charAt(j) == '[') {
                      openParenthesis++;
                    } else {
                      end = j;
                    }
                  } else {
                    if (str.charAt(j) == '{' || str.charAt(j) == '[') {
                      openParenthesis++;
                    } else {
                      openParenthesis--;
                    }
                  }
                }
                if (openParenthesis == 0 && end != -1) {
                  break;
                }
              }
              str = str.replace(str.substring(start, end + 1), "0");
              i = start - 1; // Adjust i to the start of the removed section
            }
          }
        }
      }
      for (int i = 0; i < str.length(); i++) {
        char c = str.charAt(i);
        if (String.valueOf(c).matches("\\d+")) {
          for (int j = i + 1; j < str.length(); j++) {
            if (!String.valueOf(str.charAt(j)).matches("\\d+")) {
              int numStart = i;
              if (str.charAt(i - 1) == '-') {
                numStart = i - 1;
              }
              i = j - 1;
              String num = str.substring(numStart, j);
              res += Integer.parseInt(num);
              break;
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
