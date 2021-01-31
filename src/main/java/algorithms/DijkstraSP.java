package algorithms;

import graph.Edge;
import graph.Graph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeSet;

public class DijkstraSP {
    public boolean[] marked;
    public int[] previous;
    public double[] distance;

    public void verifyNonNegative(Graph graph) {
        for (Edge edge : graph.getEdges()) {
            if (edge.weight() < 0) throw new IllegalArgumentException("One of the graph weights is negative");
        }
    }

    public void dijkstra(Graph g, int s) {
        this.verifyNonNegative(g);
        // initialize class
        int numberVertices = g.getAllNodes().size()+10; //because
        this.marked = new boolean[numberVertices];
        this.previous = new int[numberVertices];
        this.distance = new double[numberVertices+50];

        // setting the distance to infinite weight
        for (int i = 0; i < numberVertices; i++) {
            this.distance[i] = Double.POSITIVE_INFINITY;
        }

        TreeSet<Integer> queue = g.getAllNodes();
        //relative to start Vertice
        distance[s] = 0; // distance of 0 between start vertice and start vertice

        while (!queue.isEmpty()) {
            int vertex = this.findAndRemoveSmallestWeight(queue);

            for (int neighbor : g.getNeighbors(vertex)) {
                double alt = distance[vertex] + g.getEdgeWeight(vertex, neighbor);

                if (alt < distance[neighbor]) {
                    previous[neighbor] = vertex;
                    distance[neighbor] = alt;
                    marked[neighbor] = true;
                }
            }

        }
    }

    private int findAndRemoveSmallestWeight(TreeSet<Integer> queue) {
        int smallest = queue.first();

        for (int node : queue) {
            if( smallest<distance.length && node<distance.length){
                if (distance[smallest] > distance[node]) {
                    smallest = node;
                }
            }

        }
        queue.remove(smallest);
        return smallest;
    }


    public boolean hasPathTo(int v) {

        if (v>=this.marked.length){
            return true;
        }
        return this.marked[v];
    }


    public double distTo(int v) {
        return this.distance[v];
    }

    public Deque<Integer> printSP(int v) {
        Deque<Integer> shortestPath = new LinkedList<>();
        boolean finished = false;
        int currentNode = v;

        if (!hasPathTo(v)) {
            return shortestPath;
        }

        while (!finished ) {
            shortestPath.addFirst(currentNode);
            if (this.distance[currentNode] == 0) {
                finished = true;
            } else {
                currentNode = this.previous[currentNode];
            }

        }
        return shortestPath;
    }

}
