public class Edge {
    private int start;
    private int end;
    private int weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getWeight() {
        return weight;
    }

    public int getAdjacentVertex(int x) {
        if (x == start) {
            return end;
        } else if (x == end) {
            return start;
        } else throw new IllegalArgumentException("Illegal endpoint");
    }

    @Override
    public String toString() {
        return start + "-" + end + " " + weight;
    }

}

