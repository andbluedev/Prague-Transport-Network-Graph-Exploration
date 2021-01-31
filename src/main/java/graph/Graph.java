package graph;

import dto.StationDescription;
import utils.EdgeWeightComputation;
import utils.StationDao;
import utils.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Graph {
    private ArrayList<Edge> edges;

    public Graph() {
        this.edges = new ArrayList<>();
    }

    public void loadGraphFromFile(String path) throws IOException {
        StationDao stationDao = new StationDao();

        String parsedFile = StringUtils.parsefileToString(path);
        String[] lines = StringUtils.splitByLine(parsedFile);

        for (String line : lines) {

            String[] lineNumbers = line.split(";");
            int leftStation = Integer.parseInt(lineNumbers[0]);
            int rightStation = Integer.parseInt(lineNumbers[1]);

            StationDescription leftStationDescription = stationDao.getStationDescription(leftStation);
            StationDescription rightStationDescription = stationDao.getStationDescription(rightStation);

            double computedWeight = EdgeWeightComputation.compute(
                    leftStationDescription.getLatitude(),
                    leftStationDescription.getLongitude(),
                    rightStationDescription.getLatitude(),
                    rightStationDescription.getLongitude()
            );

            this.edges.add(new Edge(leftStation, rightStation,
                    computedWeight
            ));
        }
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public  Edge getEdge(int startNode, int goalNode) {
        Edge selectedEdge = null;

        for (Edge edge:  this.edges) {
            if (edge.from() == startNode && edge.to() == goalNode) {
                selectedEdge = edge;
            }
        }
        return selectedEdge;

    }

    public void addDirectedEdge(Edge directedEdge) {
        this.edges.add(directedEdge);
    }

    public TreeSet<Integer> getNeighbors(int v) {
        TreeSet<Integer> nodeNeighbors = new TreeSet<>();

        for (Edge edge : this.edges) {
            if (edge.from() == v) {
                nodeNeighbors.add(edge.to());
            }
        }
        return nodeNeighbors;
    }

    public TreeSet<Integer> getAllNodes() {
        TreeSet<Integer> nodeTree = new TreeSet<>();
        for (Edge edge : this.edges) {
            nodeTree.add(edge.from());
        }
        return nodeTree;
    }

    public double getEdgeWeight(int from, int to) {
        boolean isFound = false;
        int cursor = 0;
        double result = Double.POSITIVE_INFINITY;

        while (!isFound) {

            Edge edge = this.getEdges().get(cursor);
            if (edge.from() == from && edge.to() == to) {
                result = edge.weight();
                isFound = true;
            }
            cursor++;
        }
        return result;
    }
}
