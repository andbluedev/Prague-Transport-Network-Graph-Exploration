package algorithms;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dto.SPEntry;
import graph.Graph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Deque;
import java.util.LinkedList;

public class ShortestPathCalculation {
    private Deque<SPEntry> shortestPathList;

    public ShortestPathCalculation() {
        this.shortestPathList  = new LinkedList<>();
    }

    public void initFromFile(String fileName) {
        this.loadFromFile(fileName);
    }

    public void initFromGraph(Graph graph) {
        long startTime = System.currentTimeMillis();

        for (int startNode : graph.getAllNodes()) {
            for (int goalNode : graph.getAllNodes()) {
                if (startNode != goalNode) {
                    BFSShortestPath bfsShortestPath = new BFSShortestPath();
                    DijkstraSP dijkstraSP = new DijkstraSP();

                    bfsShortestPath.bfs(graph, startNode);
                    dijkstraSP.dijkstra(graph, startNode);

                    SPEntry shortestPathEntry = new SPEntry(startNode, goalNode, bfsShortestPath.getSP(goalNode), dijkstraSP.printSP(goalNode));

                    this.shortestPathList.addLast(shortestPathEntry);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Shortest Paths between nodes computed in " + (endTime - startTime) + " milliseconds");
    }

    public void printForGivenNodes(int startNode, int goalNode) {
        SPEntry givenNodeSPEntry = this.getEntryForGivenNodes(startNode, goalNode);
        this.printShortestPathEntry(givenNodeSPEntry);
    }


    public SPEntry getEntryForGivenNodes(int startNode, int goalNode) {
        SPEntry spEntry = new SPEntry();

        for (SPEntry entry : this.shortestPathList) {
            if (entry.getStartNode() == startNode && entry.getGoalNode() == goalNode) {
                spEntry = entry;
            }
        }
        return spEntry;
    }

    public void printAllShortestPaths() {
        System.out.println("----------- Shortest paths bewteen every node ------------");
        int count = 1;
        for (SPEntry entry : this.shortestPathList) {
            System.out.println("------- Iteration " + count + "-----------");
            this.printShortestPathEntry(entry);
            count++;
        }
    }

    public void printShortestPathEntry(SPEntry entry) {
        System.out.println("Start Node : " + entry.getStartNode());
        System.out.println("Goal Node : " + entry.getGoalNode());

        String bfsDisplay = entry.getBfsShortestPath().size() == 0 ? "No Path found" : entry.getBfsShortestPath().toString();
        System.out.println("BFS Shortest Path : " + bfsDisplay);

        String dijkstraSpDisplay = entry.getDijkstraShortestPath().size() == 0 ? "No Path found" : entry.getDijkstraShortestPath().toString();
        System.out.println("Dijkstra Shortest Path : " + dijkstraSpDisplay);

        System.out.println("\n");
    }

    public Deque<SPEntry> getShortestPathList() {
        return shortestPathList;
    }

    /**
     * Persistence  methods
     */

    public void saveTofile(String fileName) {
        final Gson gson = new GsonBuilder().create();

        try (FileWriter file = new FileWriter(fileName)) {
            file.write(gson.toJson(shortestPathList));
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile(String fileName) {
        final Gson gson = new GsonBuilder().create();
        final Type SPEntryDequeueType = new TypeToken<Deque<SPEntry>>() {
        }.getType();

        try (FileReader reader = new FileReader(fileName)) {
            this.shortestPathList = gson.fromJson(reader, SPEntryDequeueType);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
