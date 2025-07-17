import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class day12_1 {
	public static void main(String[] args) {
		int res = 0;
		try (Scanner scan = new Scanner(new File("../input/day12.input"))) {
			String str = scan.nextLine().trim();
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
			// Map<Integer, Map<String, String>> map = new HashMap<>();
			// for (int i = 0; i < str.length(); i++) {
			// char c = str.charAt(i);
			// if (c == '{') {
			// map.put(i, new HashMap<>());
			// map.get(i).put("type", "1");
			// } else if (c == '[') {
			// map.put(i, new HashMap<>());
			// map.get(i).put("type", "2");
			// } else if (c == '}') {
			// for (int j = i - 1; j >= 0; j--) {
			// if (map.containsKey(j) && map.get(j).get("type").equals("1")) {
			// if (!map.get(j).containsKey("str")) {
			// map.get(j).put("str", str.substring(j, i + 1));
			// System.out.println("Found object: "
			// + map.get(j).get("str"));
			// break;
			// }
			// }
			// }
			// } else if (c == ']') {
			// for (int j = i - 1; j >= 0; j--) {
			// if (map.containsKey(j) && map.get(j).get("type").equals("2")) {
			// if (!map.get(j).containsKey("str")) {
			// map.get(j).put("str", str.substring(j, i + 1));
			// System.out.println("Found object: "
			// + map.get(j).get("str"));
			// break;
			// }
			// }
			// }
			// }
			// }
			// System.out.println(res);
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
	}
}
