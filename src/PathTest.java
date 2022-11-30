import java.util.Random;

public class PathTest {
    private static int nTotalVertices = 5000;
    private static int nTestGraphs = 5;
    private static int nSrcToDest = 5;

    public static void main(String[] args) {
        // five pairs of graphs
        for (int i = 0; i < nTestGraphs; i++) {

            System.out.println("Graph: " + (i + 1));
            long startTime = System.nanoTime();
            Graph sparseGraph = GraphGenerator.sparseGraphGenerator(nTotalVertices);
            long endTime = System.nanoTime();
            System.out.println();
            System.out.println("Sparse undirected graph generation time: " + String.format("%.5f", (double) (endTime - startTime)/ 1_000_000_000) + "s");
            for (int j = 0; j < nSrcToDest; j++) {
                // five pairs of src and dest
                System.out.println("Testing Source and Destination : " + (j + 1));
                Random random = new Random();
                int src = random.nextInt(nTotalVertices);
                int dest = random.nextInt(nTotalVertices);
                while ( src == dest) {
                    dest = random.nextInt(nTotalVertices);
                }
                System.out.println("Source: " + src + " Destination: " + dest);

                //Max-Bandwidth-Path Dijkstra
                startTime = System.nanoTime();
                int maxBandwidth = MaxBandwidthDijks.maxBandwidthPath(sparseGraph, src, dest);
                endTime = System.nanoTime();
                System.out.println("Max Bandwidth: " + maxBandwidth);
                System.out.println("Max-Bandwidth-Path Dijkstra execution time: " + String.format("%.5f", (double) (endTime - startTime)/ 1_000_000_000) + "s");

                //Max-Bandwidth-Path Dijkstra Heap
                startTime = System.nanoTime();
                int maxBandwidthPathHeap = MaxBandwidthDijksHeap.maxBandwidthPath(sparseGraph, src, dest);
                endTime = System.nanoTime();
                System.out.println("Max Bandwidth: " + maxBandwidthPathHeap);
                System.out.println("Max-Bandwidth-Path Dijkstra Heap execution time: " + String.format("%.5f", (double) (endTime - startTime)/ 1_000_000_000) + "s");

                //Max-Bandwidth-Path Kruskal
                startTime = System.nanoTime();
                int maxBandwidthPathKrus = MaxBandwidthKrus.maxBandwidthPath(sparseGraph, src, dest);
                endTime = System.nanoTime();
                System.out.println("Max Bandwidth: " + maxBandwidthPathKrus);
                System.out.println("Max-Bandwidth-Path Kruskal execution time: " + String.format("%.5f", (double) (endTime - startTime)/ 1_000_000_000) + "s");

            }
            System.out.println("");
            startTime = System.nanoTime();
            Graph denseGraph = GraphGenerator.denseGraphGenerator(nTotalVertices);
            endTime = System.nanoTime();
            System.out.println();
            System.out.println("Dense undirected graph generation time: " + String.format("%.5f", (double) (endTime - startTime)/ 1_000_000_000) + "s");
            for (int j = 0; j < nSrcToDest; j++) {
                // five pairs of src and dest
                System.out.println("Testing Source and Destination : " + (j + 1));
                Random random = new Random();
                int src = random.nextInt(nTotalVertices);
                int dest = random.nextInt(nTotalVertices);
                while ( src == dest) {
                    dest = random.nextInt(nTotalVertices);
                }
                System.out.println("Source: " + src + " Destination: " + dest);
                //Max-Bandwidth-Path Dijkstra
                startTime = System.nanoTime();
                int maxBandwidth = MaxBandwidthDijks.maxBandwidthPath(denseGraph, src, dest);
                endTime = System.nanoTime();
                System.out.println("Max Bandwidth: " + maxBandwidth);
                System.out.println("Max-Bandwidth-Path Dijkstra execution time: " + String.format("%.5f", (double) (endTime - startTime)/ 1_000_000_000) + "s");

                //Max-Bandwidth-Path Dijkstra Heap
                startTime = System.nanoTime();
                int maxBandwidthPathHeap = MaxBandwidthDijksHeap.maxBandwidthPath(denseGraph, src, dest);
                endTime = System.nanoTime();
                System.out.println("Max Bandwidth: " + maxBandwidthPathHeap);
                System.out.println("Max-Bandwidth-Path Dijkstra Heap execution time: " + String.format("%.5f", (double) (endTime - startTime)/ 1_000_000_000) + "s");

                //Max-Bandwidth-Path Kruskal
                startTime = System.nanoTime();
                int maxBandwidthPathKrus = MaxBandwidthKrus.maxBandwidthPath(denseGraph, src, dest);
                endTime = System.nanoTime();
                System.out.println("Max Bandwidth: " + maxBandwidthPathKrus);
                System.out.println("Max-Bandwidth-Path Kruskal execution time: " + String.format("%.5f", (double) (endTime - startTime)/ 1_000_000_000) + "s");

            }
            System.out.println();
            System.out.println(" ------------------------------------------------------ ");
            System.out.println();
        }
    }
}
