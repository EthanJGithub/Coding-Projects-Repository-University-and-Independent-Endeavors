// ===============================================
// CSCI 3230 Data Structures
// Instructor: Yao Xu, Ph.D.
// 
// M8 - MyGraph 
//      Implementation of an undirected graph 
//      using adjacency lists representation
// 
// ===============================================

import java.util.ArrayList;
import java.util.LinkedList;

public class MyGraph<V> { // undirected graph

    protected ArrayList<V> vertices;
    protected ArrayList<LinkedList<V>> adjacencyLists;

    // Constructor
    public MyGraph() {
        vertices = new ArrayList<>();
        adjacencyLists = new ArrayList<>();
    }

    // Returns the number of vertices in the graph
    public int numVertices() {
        return vertices.size();
    }

    // Returns the number of edges in the graph
    public int numEdges() {
        int count = 0;
        for (LinkedList<V> adjacencyList : adjacencyLists) {
            count += adjacencyList.size();
        }
        return count / 2; // divide by 2 for undirected edges
    }

    // Checks if the graph is empty (has no vertices)
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    // Checks if there is an edge from vertex u to vertex v in the graph
    public boolean hasEdge(V u, V v) {
        int indexU = vertices.indexOf(u);
        int indexV = vertices.indexOf(v);
        if (indexU != -1 && indexV != -1) {
            LinkedList<V> adjacencyList = adjacencyLists.get(indexU);
            return adjacencyList.contains(v);
        }
        return false;
    }

    // Adds a vertex v to the graph
    public void addVertex(V v) {
        if (vertices.contains(v)) {
            throw new IllegalArgumentException("Vertex already exists: " + v);
        }
        vertices.add(v);
        adjacencyLists.add(new LinkedList<>());
    }

    // Adds an edge between vertices u and v in the graph
    // For undirected graphs, the edge is added in both directions
    public void addEdge(V u, V v) {
        int indexU = vertices.indexOf(u);
        int indexV = vertices.indexOf(v);
        if (indexU == -1) {
            throw new IllegalArgumentException("Vertex not found: " + u);
        }
        if (indexV == -1) {
            throw new IllegalArgumentException("Vertex not found: " + v);
        }
        LinkedList<V> adjacencyListU = adjacencyLists.get(indexU);
        LinkedList<V> adjacencyListV = adjacencyLists.get(indexV);
        adjacencyListU.addLast(v);
        adjacencyListV.addLast(u);
    }

    // Removes a vertex v from the graph
    // Also removes any edges associated with the vertex
    public void removeVertex(V v) {
        int indexV = vertices.indexOf(v);
        if (indexV == -1) {
            throw new IllegalArgumentException("Vertex not found: " + v);
        }
        vertices.remove(indexV);
        adjacencyLists.remove(indexV);
        for (LinkedList<V> list : adjacencyLists) {
            list.remove(v);
        }
    }

    // Removes an edge between vertices u and v in the graph
    // For undirected graphs, the edge is removed in both directions
    public void removeEdge(V u, V v) {
        int indexU = vertices.indexOf(u);
        int indexV = vertices.indexOf(v);
        if (indexU == -1) {
            throw new IllegalArgumentException("Vertex not found: " + u);
        }
        if (indexV == -1) {
            throw new IllegalArgumentException("Vertex not found: " + v);
        }
        LinkedList<V> adjacencyListU = adjacencyLists.get(indexU);
        LinkedList<V> adjacencyListV = adjacencyLists.get(indexV);
        adjacencyListU.remove(v);
        adjacencyListV.remove(u);
    }
    
    
    // ------------------------- Driver -------------------------
    public static void main(String[] args) {
        MyGraph<Character> graph = new MyGraph<>();

        // Adding vertices
        graph.addVertex('a');
        graph.addVertex('b');
        graph.addVertex('c');
        graph.addVertex('d');
        graph.addVertex('e');

        // Adding edges
        graph.addEdge('a', 'b'); // removed
        graph.addEdge('a', 'c');
        graph.addEdge('b', 'd'); // removed
        graph.addEdge('c', 'd'); // removed
        graph.addEdge('d', 'e');

        // Testing methods
        System.out.println("Number of vertices: " + graph.numVertices());
        System.out.println("Number of edges: " + graph.numEdges());
        System.out.println();
        
        System.out.println("Is the graph empty? " + graph.isEmpty());
        System.out.println("Does the graph have an edge (a, b)? " + graph.hasEdge('a', 'b'));
        System.out.println("Does the graph have an edge (b, c)? " + graph.hasEdge('b', 'c'));
        System.out.println();

        // Removing an edge
        System.out.println("Removing edge (c, d) ...");
        graph.removeEdge('c', 'd');
        System.out.println("Number of edges left: " + graph.numEdges());
        System.out.println();
        
        // Removing a vertex
        System.out.println("Removing vertex 'b' ...");
        graph.removeVertex('b');
        System.out.println("Number of vertices left: " + graph.numVertices());
        System.out.println("Number of edges left: " + graph.numEdges());
        
    }
}
