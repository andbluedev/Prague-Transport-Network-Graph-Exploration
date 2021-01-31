import graph.Edge;
import graph.Graph;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestWDgraph {

    @Test
    public void test_loadGraphMatrixFromFile() {
        //Arrange
        Graph graph = new Graph();

        try {
            // Act
            graph.loadGraphFromFile("graph-WDG.txt");
            Edge firstEdge = graph.getEdges().get(0);
            Edge lastEdge = graph.getEdges().get(14);

            assertEquals(4, firstEdge.from());
            assertEquals(5,firstEdge.to());
            assertEquals(0.35,firstEdge.weight(), 0.01);


            assertEquals(6, lastEdge.from());
            assertEquals(4,lastEdge.to());
            assertEquals(0.93,lastEdge.weight(), 0.01);

            // assert
        } catch (IOException e) {
            System.out.println("File not found.");
        }
    }



}
