// ========================================================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
//
// Coding Quiz 9
//
// =========================== Requirements =============================== 
// Implement the BFS traversal to find the distance/shortest path length
// from a source vertex s to every vertex in the graph.
// 
// The BFS class is coded as a subclass of the MyGraph class provided in
// the "MyGraph.java" file. It is also using the LinkedQueue class which
// is provided in the "LinkedQueue.java" file.
//
// Your output may look as follows:
// ------------------------------------------------------------------------
// Shortest path lengths from E to every vertex in G1:
//
// Vertex     Distance  
// -------------------
//    A           2
//    B           1
//    C           2
//    D           2
//    E           0
//    F           1
//
// Shortest path lengths from 3 to every vertex in G2:
//
// Vertex     Distance  
// -------------------
//    1           1
//    2           2
//    3           0
//    4           1
//    5           3
//    6           1
//    7           3
//    8           2
//    9           2
//   10           2
//
// ============================== Note ====================================
//
// 1. DO NOT MODIFY OR DELETE ANY GIVEN CODE OR COMMENTS!!!
// 2. You ONLY need to write code under each comment "YOUR CODE GOES HERE".
// 3. Modify the file name to "BFS.java" to compile and run.
// 4. Make sure that you download the "MyGraph.java" and "LinkedQueue.java"  
//    files posted under this coding quiz. Place them in the same folder
//    as your current file to compile and run the code.
//
// ========================================================================

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class BFS<V> extends MyGraph<V> {

    // Breadth-First Search to find shortest path lengths from source vertex s to every vertex
    public Map<V, Integer> breadthFirstSearch(V s) {
        
        LinkedQueue<V> queue = new LinkedQueue<>(); // Queue data structure for implementing BFS
        Map<V, Integer> distances = new HashMap<>(); // To store distance value for each vertex
        // You will also need to create an ArrayList 
        // to store colors of the vertices or to mark if a vertex has been visited or not
        // YOUR CODE GOES HERE --Part 1/1--
        ArrayList<V> colors = new ArrayList<>(); // To store colors of the vertices or to mark if a vertex has been visited or not

        // Set the initial distance for all vertices to infinity except for source vertex, which is 0
        for (V vertex : vertices) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(s, 0);

        // Enqueue the source vertex
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            V current = queue.dequeue();

            // Next, iterate over all the neighbors of the vertex
            LinkedList<V> adjacencyList = adjacencyLists.get(vertices.indexOf(current));
            for (V neighbor : adjacencyList) {
                // Verify if neighbor has been visited
                if (!colors.contains(neighbor)) {
                    // Update the distance then enqueue the neighbor
                    distances.put(neighbor, distances.get(current) + 1);
                    queue.enqueue(neighbor);
                    colors.add(neighbor);
                }
            }
        }

        return distances;
    }




        


    public static void main(String[] args) {
        // Create two graphs to test BFS
    	BFS<Character> graph1 = new BFS<>();
        BFS<Integer> graph2 = new BFS<>();

        // Add vertices
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F'};
        for (char vertex : vertices)
            graph1.addVertex(vertex);
        for (int i = 1; i <= 10; i++)
            graph2.addVertex(i);

        // Define the edges as 2D array
        char[][] edges1 = {
                {'A', 'B'}, {'A', 'C'}, {'B', 'D'}, {'B', 'E'},
                {'C', 'D'}, {'C', 'F'}, {'D', 'F'}, {'E', 'F'}
        };
        int[][] edges2 = {
                {1, 2}, {1, 3}, {2, 4}, {2, 5},
                {3, 4}, {3, 6}, {4, 10}, {5, 7},
                {5, 8}, {6, 8}, {6, 9}, {7, 10},
                {8, 9}, {8, 10}, {9, 10}, {9, 1}
        };

        // Add edges
        for (char[] edge : edges1) {
            char u = edge[0];
            char v = edge[1];
            graph1.addEdge(u, v);
        }

        for (int[] edge : edges2) {
            int u = edge[0];
            int v = edge[1];
            graph2.addEdge(u, v);
        }

        // Perform BFS from a starting vertex
        char s1 = 'E';
        int s2 = 3;
        Map<Character, Integer> distances1 = graph1.breadthFirstSearch(s1);
        Map<Integer, Integer> distances2 = graph2.breadthFirstSearch(s2);

        // Print the distance/shortest path lengths for G1
        System.out.println("Shortest path lengths from " + s1 + " to every vertex in G1:\n");
        System.out.printf("%-10s %-10s%n", "Vertex", "Distance");
        System.out.println("-------------------");
        for (Map.Entry<Character, Integer> entry : distances1.entrySet()) {
            char vertex = entry.getKey();
            int distance = entry.getValue();
            System.out.printf("%4s %11s%n", vertex, distance);
        }
        System.out.println();
        
        // Print the distance/shortest path lengths for G2
        System.out.println("Shortest path lengths from " + s2 + " to every vertex in G2:\n");
        System.out.printf("%-10s %-10s%n", "Vertex", "Distance");
        System.out.println("-------------------");
        for (Map.Entry<Integer, Integer> entry : distances2.entrySet()) {
            int vertex = entry.getKey();
            int distance = entry.getValue();
            System.out.printf("%4s %11s%n", vertex, distance);
        }
        System.out.println();
    }

}
