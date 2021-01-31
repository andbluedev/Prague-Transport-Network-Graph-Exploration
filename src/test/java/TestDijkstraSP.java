import algorithms.DijkstraSP;
import graph.Edge;
import graph.Graph;
import org.junit.Test;

import java.io.IOException;
import java.util.Deque;

import static org.junit.Assert.*;


public class TestDijkstraSP {

    @Test(expected = IllegalArgumentException.class)
    public void test_verifyNegative_with_negative_edge() {

        Graph graph = new Graph();
        DijkstraSP algorithm = new DijkstraSP();

        graph.addDirectedEdge(new Edge(1, 2, -0.2d));


        algorithm.verifyNonNegative(graph);
    }


    @Test
    public void test_loadGraphMatrixFromFile() {
        Graph graph = new Graph();
        DijkstraSP algorithm = new DijkstraSP();
        try {
            graph.loadGraphFromFile("graph-WDG.txt");
            Edge firstEdge = graph.getEdges().get(0);
            Edge lastEdge = graph.getEdges().get(14);

            algorithm.verifyNonNegative(graph);

            // assert
            assertEquals(4, firstEdge.from());
            assertEquals(5, firstEdge.to());
            assertEquals(0.35, firstEdge.weight(), 0.01);

            assertEquals(6, lastEdge.from());
            assertEquals(4, lastEdge.to());
            assertEquals(0.93, lastEdge.weight(), 0.01);

        } catch (IOException e) {
            System.out.println("File now found.");
        }
    }

    @Test
    public void test_DijskraSP() {
        Graph graph = new Graph();
        DijkstraSP algorithm = new DijkstraSP();
        try {
            graph.loadGraphFromFile("graph-WDG.txt");

            algorithm.dijkstra(graph, 0);

            assertEquals(0.0d, algorithm.distance[0], 0.1);
            assertEquals(0.26d, algorithm.distance[2], 0.1);
        } catch (IOException e) {
            System.out.println("File now found.");
        }
    }

    @Test
    public void test_DijskraSP_dist_0_to_6() {
        Graph graph = new Graph();
        DijkstraSP algorithm = new DijkstraSP();
        try {
            graph.loadGraphFromFile("graph-WDG.txt");

            algorithm.dijkstra(graph, 0);

            assertEquals(0.26, algorithm.distTo(2), 0.1);
            assertEquals(0.60, algorithm.distTo(7), 0.1);
            assertEquals(0.99, algorithm.distTo(3), 0.1);
            assertEquals(1.51, algorithm.distTo(6), 0.1);
        } catch (IOException e) {
            System.out.println("File now found.");
        }
    }

    @Test
    public void test_DijskraSP_0_has_path_to_6() {
        Graph graph = new Graph();
        DijkstraSP algorithm = new DijkstraSP();
        try {
            graph.loadGraphFromFile("graph-WDG.txt");

            algorithm.dijkstra(graph, 0);

            assertTrue(algorithm.hasPathTo(6));
        } catch (IOException e) {
            System.out.println("File now found.");
        }
    }

    @Test
    public void test_DijskraSP_shortest_path_0_to_6() {
        Graph graph = new Graph();
        DijkstraSP algorithm = new DijkstraSP();
        try {
            graph.loadGraphFromFile("graph-WDG.txt");

            algorithm.dijkstra(graph, 0);

            Deque<Integer> result = algorithm.printSP(6);
            Object[] resultArr = result.toArray();

            assertEquals(0,(int)resultArr[0]);
            assertEquals(2,(int)resultArr[1]);
            assertEquals(7,(int)resultArr[2]);
            assertEquals(3,(int)resultArr[3]);
            assertEquals(6,(int)resultArr[4]);
        } catch (IOException e) {
            System.out.println("File now found.");
        }
    }



}
