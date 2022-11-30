import java.util.ArrayList;
import java.util.Stack;

public class MaxBandwidthDijks {
    public static int status[];
    public static int parent[];
    public static int bandwidth[];


    public static int maxBandwidthPath(Graph graph, int source, int target) {
        status = new int[graph.getTotalVertices()];
        parent = new int[graph.getTotalVertices()];
        bandwidth = new int[graph.getTotalVertices()];

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
        }

        //Dijsktra algorithm to find destination
        while (status[target] != Constants.INTREE) {
            //pick a fringe v with maximum bandwidth
            int maxBandwidth = Integer.MIN_VALUE;
            int v = -1;
            for (int i = 0; i < graph.getTotalVertices(); i++) {
                if (status[i] == Constants.FRINGE && bandwidth[i] > maxBandwidth) {
                        maxBandwidth = bandwidth[i];
                        v = i;
                }
            }
            status[v] = Constants.INTREE;

            ArrayList<Edge> vAdjacentToMax = graph.getAdjacencyList()[v];
            for (Edge edge : vAdjacentToMax) {
                int w = edge.getAdjacentVertex(v);
                if (status[w] == Constants.UNSEEN) {
                    parent[w] = v;
                    status[w] = Constants.FRINGE;
                    bandwidth[w] = Math.min(bandwidth[v], edge.getWeight());
                } else if(status[w] == Constants.FRINGE && bandwidth[w] < Math.min(bandwidth[v], edge.getWeight())) {
                    parent[w] = v;
                    bandwidth[w] = Math.min(bandwidth[v], edge.getWeight());
                }
            }
        }
        // Can use this if need to trace Path
        // tracePath(parent, target);
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
        System.out.println("Path Lenght: " + pathLength);
    }
}
