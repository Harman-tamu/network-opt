import java.util.ArrayList;
import java.util.Stack;

public class MaxBandwidthDijksHeap {
    public static int status[];
    public static int parent[];
    public static int bandwidth[];
    public static MaxHeap maxHeap;


    public static int maxBandwidthPath(Graph graph, int source, int target) {
        status = new int[graph.getTotalVertices()];
        parent = new int[graph.getTotalVertices()];
        bandwidth = new int[graph.getTotalVertices()];
        maxHeap = new MaxHeap(graph.getTotalVertices());

        for (int i = 0; i < graph.getTotalVertices(); i++) {
            status[i] = Constants.UNSEEN;
        }

        //Starting with source
        status[source] = Constants.INTREE;
        parent[source] = -1;

        //Update vertices adjacent to source
        ArrayList<Edge> adjacentToSource = graph.getAdjacencyList()[source];
        for (Edge edge : adjacentToSource) {
            int w = edge.getAdjacentVertex(source);
            status[w] = Constants.FRINGE;
            parent[w] = source;
            bandwidth[w] = edge.getWeight();
            maxHeap.insert(w, bandwidth[w]);
        }

        //Dijsktra algorithm using Heap
        while (status[target] != Constants.INTREE) {
            //pick a fringe v with max bandwidth from the heap
            int v = maxHeap.extractMax();
            status[v] = Constants.INTREE;

            ArrayList<Edge> vAdjacentToMax = graph.getAdjacencyList()[v];
            for (Edge edge : vAdjacentToMax) {
                int w = edge.getAdjacentVertex(v);
                if (status[w] == Constants.UNSEEN) {
                    parent[w] = v;
                    status[w] = Constants.FRINGE;
                    bandwidth[w] = Math.min(bandwidth[v], edge.getWeight());
                    maxHeap.insert(w, bandwidth[w]);
                } else if(status[w] == Constants.FRINGE && bandwidth[w] < Math.min(bandwidth[v], edge.getWeight())) {
                    maxHeap.delete(w);
                    parent[w] = v;
                    bandwidth[w] = Math.min(bandwidth[v], edge.getWeight());
                    maxHeap.insert(w, bandwidth[w]);
                }
            }
        }
        // Can use this if need to trace Path
       //  tracePath(parent, target);
        return bandwidth[target];
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
        System.out.println();
        System.out.println("Path Length: " + pathLength);
    }
}
