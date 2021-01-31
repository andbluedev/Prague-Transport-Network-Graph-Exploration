package algorithms;

import graph.Edge;
import graph.Graph;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ClustersCalculation {

    private Graph graph;

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public ClustersCalculation(Graph graph) {
        this.graph = graph;
    }

    public void findClusters(int maxClusters) {
        int clusterCount = 1;
        CopyOnWriteArrayList<ArrayList<Integer>> listClusters = new CopyOnWriteArrayList<>();
        ArrayList<Integer> firstCluster = new ArrayList<>();
        listClusters.add(firstCluster);
        Set<Integer> addedNodes = new TreeSet<>();


        while (clusterCount < maxClusters) {
            EdgeBetweeness edgeClustering = new EdgeBetweeness(getGraph());
            Map<Edge, Integer> edgesBetweeness = edgeClustering.getEdgesBetweenesss();
            Edge maxBetEdge = edgeClustering.getEdgeWithMaxBetweeness();
            System.out.println("Edge with max betweeness : " + maxBetEdge.from() + " => " + maxBetEdge.to());
            Graph newGraph = this.getGraph();
            newGraph.getEdges().remove(maxBetEdge);
            ArrayList<Integer> currentCluster = firstCluster;


            Set<Integer> nodeSet = newGraph.getAllNodes();

            for (int startNode : nodeSet
            ) {
                if (!addedNodes.contains(startNode))
                    for (int goalNode : nodeSet
                    ) {
                        if (!addedNodes.contains(goalNode)) {
                            if (startNode != goalNode) {
                                BFSShortestPath bfsShortestPath = new BFSShortestPath();

                                bfsShortestPath.bfs(newGraph, startNode);

                                if (!bfsShortestPath.hasPathTo(goalNode)) {
                                    for (ArrayList<Integer> cluster : listClusters
                                    ) {
                                        if (currentCluster.contains(startNode) && !currentCluster.contains(goalNode)
                                        ) {
                                            if (clusterCount < maxClusters) {
                                                ArrayList<Integer> newCluster = new ArrayList<>();
                                                newCluster.add(goalNode);
                                                addedNodes.add(goalNode);
                                                listClusters.add(newCluster);
                                                System.out.println("new cluster");
                                                clusterCount += 1;
                                                currentCluster = newCluster;
                                            } else {
                                                for (Integer node : nodeSet
                                                ) {
                                                    if (!addedNodes.contains(node)) {
                                                        currentCluster.add(node);
                                                    }

                                                }
                                            }


                                        }
                                    }


                                } else {

                                    if (!currentCluster.contains(startNode) && !addedNodes.contains(startNode)) {
                                        currentCluster.add(startNode);
                                        addedNodes.add(startNode);


                                    }
                                    if (!currentCluster.contains(goalNode) && !addedNodes.contains(goalNode)) {
                                        currentCluster.add(goalNode);
                                        addedNodes.add(goalNode);


                                    }


                                }

                            }
                        }

                    }
                this.setGraph(newGraph);
            }
            System.out.println("Number of clusters : "+ listClusters.size());
            for (ArrayList<Integer> cluster: listClusters
                 ) {
                int clusterNb = listClusters.indexOf(cluster)+1;
                System.out.println("Cluster n°"+ clusterNb+" : "+cluster);
            }


        }


    }
}
