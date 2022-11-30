import java.util.ArrayList;

public class Graph {

    private int nVertices;
    private int nEdges;
    private int totalDegree;
    ArrayList<Edge> adjacencyList[];

    public Graph(int nVertices) {
        //Initialize an empty graph with vertices and 0 edges.
        this.nVertices = nVertices;
        this.nEdges = 0;
        this.totalDegree = 0;
        adjacencyList = new ArrayList[nVertices];
        for (int i = 0; i < nVertices; i++) {
            adjacencyList[i] = new ArrayList<Edge>();
        }
    }

    public ArrayList<Edge>[] getAdjacencyList() {
        return adjacencyList;
    }

    public int getTotalVertices() {
        return nVertices;
    }

    public int getTotalDegree() {
        return totalDegree;
    }
    public int getTotalEdges() {
        return nEdges;
    }

    public void addEdge(int start, int end, int weight) {
        Edge edge = new Edge(start, end, weight);
        adjacencyList[start].add(edge);
        Edge reverseEdge = new Edge(end, start, weight);
        adjacencyList[end].add(reverseEdge);
        totalDegree += 2;
        nEdges++;
    }

    public boolean checkIfEdgeExists(int i, int j){
        for (Edge e: adjacencyList[i]) {
            if ( e.getAdjacentVertex(i) == j) {
                return true;
            }
        }
        return false;
    }

}

//        Whenever an edge is introduced in a graph; It will connect two nodes (vertices). So degree of both those nodes will increase by 1.
//
//        Thus Sum of degrees will increase by 2.
//
//        So we can say that every addition of edge increases sum of degrees by 2.
//
//        So if there are 𝐸 such edges: sum of degrees =2+2+2… (𝐸 times) = 2𝐸.