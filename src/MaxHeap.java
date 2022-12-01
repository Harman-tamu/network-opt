public class MaxHeap {

    private int[] H; //array H[5000], where each element H[i] gives the name of a vertex in the graph
    private int[] D; //The vertex value are given in another array D[5000].
    private int[] P; //Stores the position of v in H
    private int heapSize;
    private int maxsize;

    public MaxHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.heapSize = 0;
        this.H = new int[maxsize];
        this.D = new int[maxsize];
        this.P = new int[maxsize];
    }

    private int parent(int pos) { return (pos - 1) / 2; }

    private boolean isLeaf(int pos)
    {
        if (pos > (heapSize / 2) && pos <= heapSize) {
            return true;
        }
        return false;
    }

    private void swap(int i, int j) {
        int temp = H[i];
        H[i] = H[j];
        H[j] = temp;
        temp = D[i];
        D[i] = D[j];
        D[j] = temp;
        P[H[i]] = i;
        P[H[j]] = j;
    }

    private void maxHeapify(int k)
    {
        if (isLeaf(k))
            return;
        int l = 2 * k;
        int r = 2 * k + 1;
        int largest = k;
        if (l <= heapSize && D[l] > D[k]) {
            largest = l;
        }
        if (r <= heapSize && D[r] > D[largest]) {
            largest = r;
        }
        if (largest != k) {
            swap(largest, k);
            maxHeapify(largest);
        }
    }

    public void insert(int vertex, int bandwidth)
    {
        H[heapSize] = vertex;
        D[heapSize] = bandwidth;
        P[vertex] = heapSize;
        // Traverse up and fix violated property
        int current = heapSize;
        while (D[current] > D[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
        heapSize++;
    }

    public void delete(int vertex) {
        int index = P[vertex];
//        while (index < heapSize && H[index] != vertex) {
//            index++;
//        }

        H[index] = H[heapSize-1];
        D[index] = D[heapSize-1];
        P[H[index]] = index; // In some case this degrades the performance
        heapSize--;
        maxHeapify(index);
    }

    public int maxFringe() {
        return H[0];
    }

    public int extractMax()
    {
        int popped = H[0];
        H[0] = H[heapSize-1];
        D[0] = D[heapSize-1];
        P[H[0]] = 0;
        heapSize--;
        maxHeapify(0);
        return popped;
    }


    public static void main(String[] arg)
    {
        //Testing Heap Methods
        MaxHeap newHeap = new MaxHeap(10);
        int max;
        newHeap.insert(2,23);
        max = newHeap.maxFringe();
        System.out.println("Max Vertex " + max);
        newHeap.insert(3,12);
        max = newHeap.maxFringe();
        System.out.println("Max Vertex " + max);
        newHeap.insert(6,121);
        max = newHeap.maxFringe();
        System.out.println("Max Vertex " + max);
        newHeap.delete(6);
        max = newHeap.maxFringe();
        System.out.println("Max Vertex " + max);
        newHeap.insert(4,29);
        max = newHeap.maxFringe();
        System.out.println("Max Vertex " + max);
        newHeap.insert(10,559);
        max = newHeap.maxFringe();
        System.out.println("Max Vertex " + max);
//        Max Vertex 2
//        Max Vertex 2
//        Max Vertex 6
//        Max Vertex 2
//        Max Vertex 4
//        Max Vertex 10
    }
}
