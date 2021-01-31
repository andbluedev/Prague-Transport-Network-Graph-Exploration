import algorithms.*;
import graph.Edge;
import graph.Graph;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Graph graph = new Graph();


            graph.loadGraphFromFile("network_subway.csv");

            BFSShortestPath bfsShortestPath = new BFSShortestPath();

            System.out.println("--- bfs sp 1 to 10 --");
            bfsShortestPath.bfs(graph, 1);
            bfsShortestPath.printSP(10);


            DijkstraSP dijkstraSP = new DijkstraSP();

            System.out.println("--- dijkstra 1 to 10 --");
            dijkstraSP.dijkstra(graph, 1);
            dijkstraSP.printSP(10);


            System.out.println("=> CALCULATION OF SHORTEST PATHS");
            String fileName = "savedCalculations.json";

            ShortestPathCalculation spCalculation = new ShortestPathCalculation();
            // Calculating Shortest paths from Graph Object
            spCalculation.initFromGraph(graph);

            // Saving results to file to avoid re-computation
//             spCalculation.saveTofile(fileName);
            // loading results from json file
//            spCalculation.initFromFile(fileName);

            // show Shortest Path Between Nodes
            spCalculation.printForGivenNodes(1, 10);

            EdgeBetweeness edgeClustering = new EdgeBetweeness(graph);

            Edge edgeWithMaxBetweeness = edgeClustering.getEdgeWithMaxBetweeness();
            System.out.println("Edge with max betweeness : " + edgeWithMaxBetweeness.from() + " => "+ edgeWithMaxBetweeness.to());
            ClustersCalculation clustersCalculation = new ClustersCalculation(graph);
            clustersCalculation.findClusters(5);


        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
