import java.util.Arrays;

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
        for (int i = 0; i < el.length; i++) {
            if (el[i].length != 3) {
                throw new IllegalArgumentException("Each row in weighted edge list array must have exactly 3 elements!");
            }
        }
        int[] nodes = new int[el.length*2];
        int count = 0;
        boolean unique;

        for (int col = 0; col < 2; col++) { // loops through both columns of edge list
            for (int row = 0; row < el.length; row++) { // loops through every entry
                unique = true;
                for (int k = 0; k < count; k++) { // loops through already found nodes to check is it unique
                    if (nodes[k] == el[row][col]) {
                        unique = false;
                    }
                }
                if (unique) {
                    nodes[count] = el[row][col];
                    count++;
                }
            }
        }

        int [][] am = new int[count][count]; // initialise adjancy matrix with all 0
        for (int i = 0; i < am.length; i++) {
            for (int j = 0; j < am.length; j++) {
                am[i][j] = 0;
            }
        }

        for (int i = 0; i < el.length; i++) {
            am[el[i][0]][el[i][1]] = el[i][2];
            if (!isDirected()) {
                am[el[i][1]][el[i][0]] = el[i][2];
            }
        }
        importAdjacencyMatrix(am);
    }

    private WeightedGraph kruskalMST() {  // work in progress, algorithm fails when connecting 2 edges from 2 separate trees during the process
        WeightedGraph kruskal = new WeightedGraph(isDirected());

        int[][] el = getEdgeList(); // gets edge list
        Arrays.sort(el, (a,b) -> a[2]-b[2]); // sort edge list by weight in ascending order
        int currentEdge = 0; // position of current egde in edge list

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
}
