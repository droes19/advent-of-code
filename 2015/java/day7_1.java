import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class day7_1 {
	public static void main(String[] args) {
		// int x = 123;
		// System.out.println("x = " + x);
		// int y = 456;
		// System.out.println("y = " + y);
		// int d = x & y; // bitwise AND
		// System.out.println("d = " + d);
		// int e = x | y; // bitwise OR
		// System.out.println("e = " + e);
		// int f = x << 2; // bitwise left shift
		// System.out.println("f = " + f);
		// int g = y >> 2; // bitwise right shift
		// System.out.println("g = " + g);
		// int h = ~x; // bitwise NOT
		// System.out.println("h = " + h);
		// int i = (~y) & 0xFFFF; // bitwise NOT
		// System.out.println("i = " + i);
		// int j = x ^ y; // bitwise XOR
		// System.out.println(j);

		// int[][] light = new int[1000][1000];
		// int res = 0;
		Map<String, Map<String, String>> wires = new java.util.HashMap<>();

		// use ../input/day6.test for using dummy input
		try (Scanner scan = new Scanner(new File("../input/day7.input"))) {
			scan.useDelimiter("\n");

			while (scan.hasNext()) {
				String a = scan.next().trim();
				String[] b = a.split("->");
				wires.put(b[1].trim(), new java.util.HashMap<>());
				String[] parts = b[0].trim().split(" ");
				if (parts.length == 1) {
					if (parts[0].matches("\\d+")) {
						wires.get(b[1].trim()).put("result", parts[0]);
					} else {
						wires.get(b[1].trim()).put("value", parts[0]);
					}
				} else if (parts.length == 2) {
					wires.get(b[1].trim()).put("op", "NOT");
					wires.get(b[1].trim()).put("value", parts[1]);
				} else if (parts.length == 3) {
					wires.get(b[1].trim()).put("op", parts[1]);
					wires.get(b[1].trim()).put("value", parts[0]);
					wires.get(b[1].trim()).put("value2", parts[2]);
				} else {
					System.exit(1);
				}
			}
			int resolved = 0;
			do {
				resolved = 0;
				for (String key : wires.keySet()) {
					if (wires.get(key).containsKey("result")) {
						System.out.println("Key: " + key + " already resolved");
						resolved++;
					} else {
						if (!wires.get(key).containsKey("op")) {
							if (wires.get(key).containsKey("value")) {
								String value = wires.get(key).get("value");
								if (wires.containsKey(value) && wires.get(value)
										.containsKey("result")) {
									wires.get(key).put("result",
											wires.get(value).get("result"));
								}
							} else {
								System.out.println("Error: " + key + " has no value");
								System.exit(1);
							}
						} else if (!wires.get(key).containsKey("value2")) {
							if (wires.get(key).containsKey("value")) {
								String value = wires.get(key).get("value");
								if (value.matches("\\d+")) {
									int result = Integer.valueOf(value);
									wires.get(key).put("result",
											((~result) & 0xFFFF) + "");
								} else if (wires.containsKey(value) && wires.get(value)
										.containsKey("result")) {
									int result = Integer.valueOf(
											wires.get(value).get("result"));
									wires.get(key).put("result",
											((~result) & 0xFFFF) + "");
								}
							} else {
								System.out.println("Error: " + key + " has no value");
								System.exit(1);
							}
						} else {
							String op = wires.get(key).get("op");
							String value1 = wires.get(key).get("value");
							String value2 = wires.get(key).get("value2");
							Integer result1 = null;
							Integer result2 = null;
							if (value1.matches("\\d+")) {
								result1 = Integer.valueOf(value1);
							} else if (wires.containsKey(value1)
									&& wires.get(value1)
											.containsKey("result")) {
								result1 = Integer.valueOf(
										wires.get(value1).get(
												"result"));
							}
							if (value2.matches("\\d+")) {
								result2 = Integer.valueOf(value2);
							} else if (wires.containsKey(value2)
									&& wires.get(value2)
											.containsKey("result")) {
								result2 = Integer.valueOf(
										wires.get(value2).get(
												"result"));
							}
							if (result1 != null && result2 != null) {
								int result = 0;
								switch (op) {
									case "AND":
										result = result1 & result2;
										break;
									case "OR":
										result = result1 | result2;
										break;
									case "RSHIFT":
										result = result1 >> result2;
										break;
									case "LSHIFT":
										result = result1 << result2;
										break;
									default:
										System.out.println("Error: " + op
												+ " is not bitwise operator");
								}
								wires.get(key).put("result", String.valueOf(result));
							}
						}
					}
				}
				System.out.println("Resolved: " + resolved);
			} while (resolved < wires.size());
			System.out.println(wires.get("a").get("result"));
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
	}
}
