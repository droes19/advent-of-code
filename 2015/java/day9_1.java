import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class day9_1 {
	public static void main(String[] args) {
		Map<String, Map<String, Integer>> cities = new java.util.HashMap<>();
		int res = 0;
		// use ../input/day9.test for using dummy input
		try (Scanner scan = new Scanner(new File("../input/day9.input"))) {
			scan.useDelimiter("\n");

			while (scan.hasNext()) {
				String a = scan.next().trim();
				String[] b = a.split("=");
				int distance = Integer.valueOf(b[1].trim());
				String city1 = b[0].split("to")[0].trim();
				String city2 = b[0].split("to")[1].trim();
				cities.putIfAbsent(city1, new java.util.HashMap<>());
				cities.putIfAbsent(city2, new java.util.HashMap<>());
				cities.get(city1).put(city2, distance);
				cities.get(city2).put(city1, distance);
			}
			Integer shortest = null;
			System.out.println("Cities: " + cities.keySet());
			System.out.println("total cities: " + cities.size());
			HashSet<String> visited = new HashSet<>();

			for (String startCity : cities.keySet()) {
				visited.clear();
				visited.add(startCity);
				int totalDistance = 0;
				String currentCity = startCity;
				while (visited.size() < cities.size()) {
					Map<String, Integer> neighbors = cities.get(currentCity);
					String nextCity = null;
					int minDistance = Integer.MAX_VALUE;
					for (String neighbor : neighbors.keySet()) {
						if (!visited.contains(neighbor)
								&& neighbors.get(neighbor) < minDistance) {
							minDistance = neighbors.get(neighbor);
							nextCity = neighbor;
						}
					}
					if (nextCity == null) {
						break;
					}
					totalDistance += minDistance;
					visited.add(nextCity);
					currentCity = nextCity;
				}
				if (visited.size() == cities.size()) {
					if (shortest == null || totalDistance < shortest) {
						shortest = totalDistance;
					}
				}
			}

			res = shortest != null ? shortest : 0;
			System.out.println(res);
		} catch (FileNotFoundException e) {
			System.exit(1);
		}
	}
}
