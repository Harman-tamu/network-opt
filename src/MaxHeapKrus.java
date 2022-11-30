import java.util.ArrayList;

public class MaxHeapKrus {

    private  Edge[] maxHeap;
    private  int heapSize;

    public MaxHeapKrus(int maxSize) {
        maxHeap = new Edge[maxSize];
        heapSize = 0;
    }

    public int parent(int pos) { return (pos - 1) / 2; }

    public void insert(Edge edge) {
        maxHeap[heapSize] = edge;
        // Traverse up and fix violated property
        int current = heapSize;
        while (maxHeap[current].getWeight() > maxHeap[parent(current)].getWeight()) {
            swap(current, parent(current));
            current = parent(current);
        }
        heapSize++;
    }

    public Edge maximum() {
        return maxHeap[0];
    }

    public void heapify(int index) {
        int l = 2 * index;
        int r = 2 * index + 1;
        int largest = index;
        if (l <= heapSize && maxHeap[l].getWeight() > maxHeap[index].getWeight()) {
            largest = l;
        }
        if (r <= heapSize && maxHeap[r].getWeight() > maxHeap[largest].getWeight()) {
            largest = r;
        }
        if (largest != index) {
            swap(index, largest);
            heapify(largest);
        }
    }

    public Edge extractMax()
    {
        Edge popped = maxHeap[0];
        maxHeap[0] = maxHeap[heapSize-1];
        heapSize--;
        heapify(0);
        return popped;
    }

    public void swap(int i, int j) {
        Edge tempHeap = maxHeap[i];
        maxHeap[i] = maxHeap[j];
        maxHeap[j] = tempHeap;
    }

    public static void main(String[] args) {
        //Testing Heap Methods
        Edge edge1 = new Edge(0, 1, 10);
        Edge edge2 = new Edge(1, 2, 100);
        Edge edge3 = new Edge(2, 3, 50);
        Edge edge4 = new Edge(3, 4, 61);
        Edge edge5 = new Edge(4, 0, 111);
        MaxHeapKrus newHeap = new MaxHeapKrus(10);
        Edge max;
        newHeap.insert(edge1);
        max = newHeap.maximum();
        System.out.println("HeapMax: " + max);
        newHeap.insert(edge2);
        max = newHeap.maximum();
        System.out.println("HeapMax " + max);
        newHeap.insert(edge3);
        max = newHeap.maximum();
        System.out.println("HeapMax " + max);
        System.out.println("HeapMax Extract " + newHeap.extractMax());
        max = newHeap.maximum();
        System.out.println("HeapMax " + max);
        newHeap.insert(edge4);
        max = newHeap.maximum();
        System.out.println("HeapMax " + max);
        newHeap.insert(edge5);
        max = newHeap.maximum();
        System.out.println("HeapMax " + max);

    }
}
