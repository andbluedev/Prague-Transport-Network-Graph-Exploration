package algorithms;

import graph.Graph;

import java.util.Deque;
import java.util.LinkedList;

public class BFSShortestPath {
    public boolean[] marked;
    public int[] previous;
    public int[] distance;


    public void bfs(Graph g, int startVertice) {
        LinkedList<Integer> queue = new LinkedList<>();

        //initialize class attributes (kind of weird to do here but ok.)
        int nbNodes = g.getAllNodes().size()+30 ; //because
        this.marked = new boolean[nbNodes];
        this.previous = new int[nbNodes];
        this.distance = new int[nbNodes];

        //relative start Vertice
        distance[startVertice] = 0; // distance of 0 between start vertice and start vertice
        marked[startVertice] = true;

        queue.add(startVertice);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            for (int vertice : g.getNeighbors(vertex)) {
                if (!marked[vertice]) {
                    previous[vertice] = vertex;
                    distance[vertice] = distance[vertex] + 1;
                    marked[vertice] = true;
                    queue.add(vertice);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return this.marked[v];
    }


    public int distTo(int v) {
        return this.distance[v];
    }

    public void printSP(int v) {
        System.out.println(this.getSP(v));
    }

    public Deque<Integer> getSP(int v) {
        Deque<Integer> shortestPath = new LinkedList<>();
        boolean finished = false;
        int currentNode = v;

        if (!hasPathTo(v)) {
            return shortestPath;
        }

        while (!finished) {
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
