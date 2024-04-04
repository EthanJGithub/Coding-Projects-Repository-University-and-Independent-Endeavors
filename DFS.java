import java.util.*;

public class DFS {

    private Map<Character, List<Character>> adjacencyList;
    private Map<Character, Integer> colors;
    private Map<Character, Character> parent;
    private boolean isTwoColorable;

    public DFS(Map<Character, List<Character>> adjacencyList) {
        this.adjacencyList = adjacencyList;
        this.colors = new HashMap<>();
        this.parent = new HashMap<>();
        this.isTwoColorable = true;
    }

    public void depthFirstSearch(char node, int color) {
        colors.put(node, color);
        for (char neighbor : adjacencyList.getOrDefault(node, new ArrayList<>())) {
            if (!colors.containsKey(neighbor)) {
                parent.put(neighbor, node);
                depthFirstSearch(neighbor, 1 - color);
            } else if (colors.get(neighbor).equals(colors.get(node))) {
                isTwoColorable = false;
                break;
            }
        }
    }

    public boolean isGraphTwoColorable() {
        for (char node : adjacencyList.keySet()) {
            if (!colors.containsKey(node)) {
                depthFirstSearch(node, 0); // Start DFS from unvisited node with color 0
                if (!isTwoColorable) break;
            }
        }
        return isTwoColorable;
    }

    public void printResults() {
        System.out.println("Number of vertices: " + adjacencyList.size());
        int edges = adjacencyList.values().stream().mapToInt(List::size).sum() / 2;
        System.out.println("Number of edges: " + edges);

        System.out.println("π values:");
        for (Map.Entry<Character, Character> entry : parent.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        if (isTwoColorable) {
            System.out.println("The input graph is 2-colorable.");
            System.out.println("Coloring:");
            for (Map.Entry<Character, Integer> entry : colors.entrySet()) {
                System.out.println(entry.getKey() + ": " + (entry.getValue() == 0 ? "red" : "blue"));
            }
        } else {
            System.out.println("The input graph is not 2-colorable.");
            System.out.println("Vertices with assigned colors:");
            for (Map.Entry<Character, Integer> entry : colors.entrySet()) {
                System.out.println(entry.getKey() + ": " + (entry.getValue() == 0 ? "red" : "blue"));
            }
            System.out.println("Detected edge with same-color endpoints:");
            for (Map.Entry<Character, List<Character>> entry : adjacencyList.entrySet()) {
                char u = entry.getKey();
                for (char v : entry.getValue()) {
                    if (colors.get(u).equals(colors.get(v))) {
                        System.out.println("Edge: (" + u + ", " + v + ")");
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Define the adjacency list of the graph
    	  Map<Character, List<Character>> graph = new HashMap<>();
          graph.put('a', Arrays.asList('c', 'f', 'g', 'j'));
          graph.put('b', Arrays.asList('d', 'e', 'h', 'i'));
          graph.put('c', Arrays.asList('a', 'd', 'h', 'i'));
          graph.put('d', Arrays.asList('b', 'c', 'g', 'j'));
          graph.put('e', Arrays.asList('b', 'f', 'g', 'j'));
          graph.put('f', Arrays.asList('a', 'e', 'h', 'i'));
          graph.put('g', Arrays.asList('a', 'd', 'e', 'h'));
          graph.put('h', Arrays.asList('b', 'c', 'f', 'g'));
          graph.put('i', Arrays.asList('b', 'c', 'f', 'j'));
          graph.put('j', Arrays.asList('a', 'd', 'e', 'i'));
          
        Map<Character, List<Character>> graph2 = new HashMap<>();
        graph2.put('a', Arrays.asList('h', 'i'));
        graph2.put('b', Arrays.asList('f', 'h'));
        graph2.put('c', Arrays.asList('f', 'g'));
        graph2.put('d', Arrays.asList('g', 'l'));
        graph2.put('e', Arrays.asList('k', 'l'));
        graph2.put('f', Arrays.asList('b', 'c', 'i', 'j'));
        graph2.put('g', Arrays.asList('c', 'd', 'j', 'k'));
        graph2.put('h', Arrays.asList('a', 'b'));
        graph2.put('i', Arrays.asList('a', 'f'));
        graph2.put('j', Arrays.asList('f', 'g'));
        graph2.put('k', Arrays.asList('e', 'g'));
        graph2.put('l', Arrays.asList('d', 'e'));

        // Create the graph coloring object
        DFS coloring = new DFS(graph2);

        // Perform 2-coloring algorithm using DFS
        coloring.isGraphTwoColorable();

        // Print results
        coloring.printResults();
    }
}
