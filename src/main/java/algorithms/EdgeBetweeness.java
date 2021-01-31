package algorithms;

import dto.SPEntry;
import graph.Edge;
import graph.Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EdgeBetweeness {
    private Graph graph;

    public EdgeBetweeness(Graph graph) {
        this.graph = graph;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Map<Edge, Integer> getEdgesBetweenesss() {
        ShortestPathCalculation spCalculation = new ShortestPathCalculation();
        spCalculation.initFromGraph(this.graph);

        Map<Edge, Integer> edgeBetweeness = new HashMap<>();

        for (Edge edge : this.graph.getEdges()) {
            edgeBetweeness.put(edge, 0);
        }

        // gets iterates through each shortest path between nodes and increments the edge betweenes
        for (SPEntry entry : spCalculation.getShortestPathList()) {
            List<Integer> shortestPath = entry.getDijkstraShortestPathAsList();

            if (shortestPath.size() > 0) {
                for (int i = 0; i < shortestPath.size() - 1; i++) {
                    Edge edge = this.graph.getEdge(shortestPath.get(i), shortestPath.get(i + 1));
                    int betweenness = edgeBetweeness.get(edge);
                    edgeBetweeness.put(edge, betweenness + 1);
                }
            }
        }
        return edgeBetweeness;
    }

    public Edge getEdgeWithMaxBetweeness() {
        Edge maxBetweenessEdge = this.graph.getEdges().get(0);

        Map<Edge, Integer> edgesBetweenesss = getEdgesBetweenesss();

        for (Edge edge : this.graph.getEdges()) {
            if (edgesBetweenesss.get(edge) > edgesBetweenesss.get(maxBetweenessEdge)) {
                maxBetweenessEdge = edge;
            }
        }
        return maxBetweenessEdge;
    }


}
