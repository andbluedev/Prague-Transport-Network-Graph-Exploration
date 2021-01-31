package dto;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SPEntry {
    private int startNode;
    private int goalNode;
    private Deque<Integer> bfsShortestPath;
    private Deque<Integer> dijkstraShortestPath;

    public SPEntry(int startNode, int goalNode, Deque<Integer> bfsShortestPath, Deque<Integer> dijkstraShortestPath) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.bfsShortestPath = bfsShortestPath;
        this.dijkstraShortestPath = dijkstraShortestPath;
    }

    public SPEntry() {
    }


    public int getStartNode() {
        return startNode;
    }

    public int getGoalNode() {
        return goalNode;
    }

    public Deque<Integer> getBfsShortestPath() {
        return bfsShortestPath;
    }

    public Deque<Integer> getDijkstraShortestPath() {
        return dijkstraShortestPath;
    }


    public List<Integer> getDijkstraShortestPathAsList() {
        List ouput = new ArrayList();
        ouput.addAll(this.dijkstraShortestPath);
        return ouput;
    }
}
