import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MaxBandwidthKrus {

    private static MaxHeapKrus heap;
    private static int[] rank;
    private static int[] parent;
    private static Graph newGraph;

    private static int[] color;
    private static int[] parentBFS;
    private static int[] bandwidth;


    private static void sortEdges(Graph graph) {
        heap = new MaxHeapKrus(graph.getTotalDegree() + 1);
        for (int v = 0; v < graph.getTotalVertices(); v++) {
            ArrayList<Edge> edgeList = graph.getAdjacencyList()[v];
            for (Edge edge : edgeList) {
                heap.insert(edge);
            }
        }
    }

    public static int find(int vertex) {
        int v = vertex;
        while (parent[v] != v) {
            v = parent[v];
        }
        return v;
    }

    public static void union(int r1, int r2) {
        if (rank[r1] > rank[r2]) {
            parent[r2] = r1;
        } else if (rank[r1] < rank[r2]) {
            parent[r1] = r2;
        } else {
            parent[r1] = r2;
            rank[r2]++;
        }
    }

    public static int maxBandwidthPath(Graph graph, int source, int destination) {
        // Kruskal Algorithm
        // Sort all edges in non-increasing order first

        sortEdges(graph);
        parent = new int[graph.getTotalVertices()];
        rank = new int[graph.getTotalVertices()];
        for (int i = 0; i < graph.getTotalVertices(); i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        newGraph = new Graph(graph.getTotalVertices());
        for (int e = 0; e < graph.getTotalEdges(); e++) {
            //for (int i = 0; i < heap.getHeapNumber(); i++) {
            Edge edge = heap.extractMax();
            int U = edge.getStart();
            int V = edge.getEnd();
            int R1 = find(U);
            int R2 = find(V);
            if (R1 != R2) {
                newGraph.addEdge(edge.getStart(), edge.getEnd(), edge.getWeight());
                union(R1, R2);
            }
        }

        // Path search using BFS
        color = new int[newGraph.getTotalVertices()];
        parentBFS = new int[newGraph.getTotalVertices()];
        bandwidth = new int[newGraph.getTotalVertices()];
        for (int v = 0; v < newGraph.getTotalVertices(); v++) {
            color[v] = Constants.WHITE;
            parentBFS[v] = -1;
            bandwidth[v] = Integer.MAX_VALUE;
        }
        color[source] = Constants.GREY;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (Edge edge : newGraph.getAdjacencyList()[u]) {
                int v = edge.getAdjacentVertex(u);
                if (color[v] == Constants.WHITE) {
                    color[v] = Constants.GREY;
                    bandwidth[v] = Math.min(bandwidth[u], edge.getWeight());
                    parentBFS[v] = u;
                    queue.offer(v);
                    if (v == destination) {
                        queue.clear();
                        break;
                    }
                } else if (color[v] == Constants.GREY && bandwidth[v] < Math.min(bandwidth[u], edge.getWeight())) {
                    parentBFS[v] = u;
                    bandwidth[v] = Math.min(bandwidth[u], edge.getWeight());
                }
            }
            color[u] = Constants.BLACK;
        }
        // Can be used to trace Path
        // tracePath(parentBFS, destination);
        return bandwidth[destination];
    }

    public static void tracePath(int[] parent, int target) {
        int i = target;
        int pathLength = 0;
        Stack<Integer> path = new Stack<>();
        while(i != -1) {
            path.add(i);
            i = parent[i];
        }
        while(!path.isEmpty()) {
            System.out.print(path.pop() + " -> ");
            pathLength++;
        }
        System.out.println("Path Lenght: " + pathLength);
    }
}
