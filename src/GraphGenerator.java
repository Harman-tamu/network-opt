import java.util.Random;

public class GraphGenerator {

    public static Graph denseGraphGenerator (int nVertices) {
        Graph graph = new Graph(nVertices);
        Random random = new Random();
        int[] degree = new int[nVertices];
        //Generate Edges in Undirected Dense Graph
        int nEdges = 0;
        for (int i = 0; i < nVertices; i++) {
            for (int j = i + 1; j < nVertices; j++) {
                int randomProbability = random.nextInt(100) + 1;
                int randomWeight = random.nextInt(100) + 1;
                if (randomProbability <= Constants.PERCENTAGE) {
                    graph.addEdge(i, j, randomWeight);
                    nEdges++;
                    degree[i]++;
                    degree[j]++;
                }
            }
        }

        System.out.println("****** Generated Undirected Dense Graph ******");
        System.out.println("Total edges: " + nEdges);
        return graph;
    }

    public static Graph sparseGraphGenerator(int nVertices) {
        Graph graph = new Graph(nVertices);
        Random random = new Random();
        int[] degree = new int[nVertices];
        int nEdge = 0;
        for (int i = 0; i < nVertices - 1; i++) {
            int weight = random.nextInt(100) + 1;
            graph.addEdge(i, i+1, weight);
            degree[i] = 1;
            nEdge++;
        }
        //Generate Edges in Undirected Sparse Graph using Handshake Theorem 2E = Sum of deg of vertices

        while (nEdge < (Constants.MAX_VERTICES * Constants.AVG_DEGREE) / 2) {
            int i = random.nextInt(nVertices);
            int j = random.nextInt(nVertices);
            int weight = random.nextInt(100) + 1;
            if (degree[i] < Constants.AVG_DEGREE && degree[j] < Constants.AVG_DEGREE && i != j) {
                if (!graph.checkIfEdgeExists(i, j)) {
                    graph.addEdge(i, j, weight);
                    degree[i]++;
                    degree[j]++;
                    nEdge++;
                }
            }
        }
        System.out.println("****** Generated Undirected Sparse Graph ******");
        System.out.println("Total edges: " + nEdge);
        return graph;
    }
}
