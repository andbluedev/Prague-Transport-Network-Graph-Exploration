package graph;

public class Edge {
    private final int leftNode;
    private final int rightNode;
    private final double weight;


    public Edge(int v, int w, double weight) {
        this.leftNode = v;
        this.rightNode = w;
        this.weight = weight;
    }

    public int from() {
        return leftNode;
    }
    public int to() {
        return rightNode;
    }
    public double weight() {
        return weight;
    }

}
