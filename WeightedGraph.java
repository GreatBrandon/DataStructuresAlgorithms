import java.util.Arrays;
import java.util.Comparator;

public class WeightedGraph extends Graph{

    public WeightedGraph(boolean directed) {
        super(directed);
    }

    public WeightedGraph(int[][] am, boolean directed) {
        super(am, directed);
    }

    public void addEdge(int node1, int node2, int weight) {
        if (weight == 0) {
            throw new IllegalArgumentException("Weight cannot be 0! Use removeEdge instead to remove an edge");
        }
        getAdjacencyMatrix()[node1][node2] = weight;
        if (!isDirected()) {
            getAdjacencyMatrix()[node2][node1] = weight;
        }
    }

    @Override
    public void addEdge(int node1, int node2) {
        throw new IllegalArgumentException("Cannot invoke non weighted function on weighted graph!");
    }
    
    @Override
    public int[][] getEdgeList() {
        int[][] am = getAdjacencyMatrix();
        int[][] el = new int[this.getEdgeCount()][3];
        int count = 0;
        int start;

        for (int i = 0; i < am.length; i++){
            if (isDirected()) {
                start = 0;
            } else {
                start = i;
            }
            for (int j = start; j < am.length; j++){
                if (am[i][j] != 0) {
                    el[count][0] = i;
                    el[count][1] = j;
                    el[count][2] = am[i][j];
                    count++;
                }
            }
        }
        return el;
    }

    @Override
    public void importEdgeList(int[][] el) {
        for (int[] row : el) {
            if (row.length != 3) {
                throw new IllegalArgumentException("Each row in weighted edge list array must have exactly 3 elements!");
            }
        }
        int count = super.getNodeCount(el);

        int [][] am = new int[count][count]; // initialise adjacency matrix with all 0
        for (int i = 0; i < am.length; i++) {
            for (int j = 0; j < am.length; j++) {
                am[i][j] = 0;
            }
        }

        for (int[] row : el) {
            am[row[0]][row[1]] = row[2];
            if (!isDirected()) {
                am[row[1]][row[0]] = row[2];
            }
        }
        importAdjacencyMatrix(am);
    }

    public WeightedGraph kruskalMST() {  // work in progress, algorithm fails when connecting 2 edges from 2 separate trees during the process
        WeightedGraph kruskal = new WeightedGraph(isDirected());

        int[][] el = getEdgeList(); // gets edge list
        Arrays.sort(el, Comparator.comparingInt(a -> a[2])); // sort edge list by weight in ascending order

        int currentEdge = 0; // position of current edge in edge list

        int[][] edges = new int[getNodeCount()-1][3]; // edges to be in MST graph
        int edgeCount = 0; // amount of edges contained in above array

        int[] visitedNodes = new int[getNodeCount()]; // nodes already visited, used to check for loops
        int nodeCount = 0;

        while (edgeCount < getNodeCount()-1 && currentEdge < el.length) {
            boolean node1Unique = true;
            boolean node2Unique = true;
            // determine loops
            for (int i = 0; i < nodeCount; i++) {
                if (visitedNodes[i] == el[currentEdge][0]) {
                    node1Unique = false;
                    break;
                }
            }
            for (int i = 0; i < nodeCount; i++) {
                if (visitedNodes[i] == el[currentEdge][1]) {
                    node2Unique = false;
                    break;
                }
            }

            if (node1Unique || node2Unique) {
                edges[edgeCount][0] = el[currentEdge][0]; // add edge to MST
                edges[edgeCount][1] = el[currentEdge][1];
                edges[edgeCount][2] = el[currentEdge][2];
                edgeCount++;
                if (node1Unique) { // add unique node to list of already visited nodes
                    visitedNodes[nodeCount] = el[currentEdge][0];
                    nodeCount++;
                }
                if (node2Unique) {
                    visitedNodes[nodeCount] = el[currentEdge][1];
                    nodeCount++;
                }
            }
            currentEdge++; // move to next edge in edge list
        }
        if (edgeCount < edges.length) {
            System.out.println("Graph not connected!");
        }

        kruskal.importEdgeList(edges);
        return kruskal;
    }

    public WeightedGraph primMST() {
        return primMST(0);
    }

    public WeightedGraph primMST(int startNode) {
        WeightedGraph prim = new WeightedGraph(isDirected());

        int[][] edges = new int[getNodeCount()-1][3]; // edges to be in MST graph
        int edgeCount = 0; // amount of edges contained in above array

        int[] visitedNodes = new int[getNodeCount()]; // nodes already visited, used to check for loops
        visitedNodes[0] = startNode;
        int nodeCount = 1;

        int[][] am = getAdjacencyMatrix();

        while(edgeCount < getNodeCount()-1) {
            int smallestEdge = 0;
            int node1 = -1;
            int node2 = -1;

            for(int i = 0; i < nodeCount; i++) { // for each node already visited
                for (int j = 0; j < am.length; j++) {
                    if (am[visitedNodes[i]][j] != 0) { // if edge exists
                        boolean visited = false;
                        for (int k = 0; k < nodeCount; k++) { // check for loops
                            if (visitedNodes[k] == j) {
                                visited = true;
                                break;
                            }
                        }
                        if (!visited && (smallestEdge == 0 || am[visitedNodes[i]][j] < smallestEdge)) { // check is it the smallest edge that hasn't been visited yet
                            smallestEdge = am[visitedNodes[i]][j];
                            node1 = visitedNodes[i];
                            node2 = j;
                        }
                    }
                }
            }

            edges[edgeCount][0] = node1;
            edges[edgeCount][1] = node2;
            edges[edgeCount][2] = smallestEdge;
            edgeCount++;
            visitedNodes[nodeCount] = node2;
            nodeCount++;
        }
        prim.importEdgeList(edges);
        return prim;
    }
}
