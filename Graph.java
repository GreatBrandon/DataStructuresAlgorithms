import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * A graph object
 */
public class Graph {
    private int[][] am;
    private final boolean directed;
    private static final char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    /**
     * Constructs an empty graph
     * @param directed is the graph directed
     */
    public Graph(boolean directed) { // initialise empty graph adjacency matrix
        this(new int[0][0], directed);
    }

    /**
     * Attempts to construct a graph from a given adjacency matrix
     * @param am the adjacency matrix to use
     * @param directed is the graph directed
     * @throws IllegalArgumentException if argument is not a valid adjacency matrix
     */
    public Graph(int[][] am, boolean directed) {
        isValid(am);
        this.am = am;
        this.directed = directed;
    }

    /**
     * Performs depth first search on the graph
     * @param startIndex the start node to begin the search
     * @return Depth first search in comma delineated String format
     */
    public String depthFirstSearch(int startIndex) { // Depth first search is implemented by always going to the next smallest lettered node in the case of a choice (e.g. choose b or c, program will choose b first and go down that path)
        Stack<Integer> s = new Stack<>(); // stack of node indices, not node values
        int visitedCount = 0;
        String path = "";
        boolean[] visited = new boolean[am.length];
        for (int i = 0; i < am.length; i++) {
            visited[i] = false;
        }
        s.push(startIndex); // start element

        while (!s.isEmpty() && visitedCount < am.length) {
            int currentNode = s.pop();
            if (visited[currentNode]) {
                continue;
            } else {
                visited[currentNode] = true;
                visitedCount++;
                path += currentNode+", ";
            }

            for (int i = am.length-1; i >= 0; i--) { // need to loop from largest to smallest to pop smallest to the largest out of the stack
                if (am[currentNode][i] != 0 && !visited[i]) {
                    s.push(i);
                }
            }
        }
        if (visitedCount != am.length) {
            return "Graph not connected";
        } else if (am.length == 0) {
            return "Graph is empty";
        } else {
            return path;
        }
    }

    /**
     * Performs breadth first search on the graph
     * @param startIndex the start node to begin the search
     * @return Breadth first search in comma delineated String format
     */
    public String breadthFirstSearch(int startIndex) {
        Queue<Integer> q = new LinkedList<>(); // stack of node indices, not node values
        int visitedCount = 0;
        String path = "";
        boolean[] visited = new boolean[am.length];
        for (int i = 0; i < am.length; i++) {
            visited[i] = false;
        }
        q.add(startIndex); // start element

        while (!q.isEmpty() && visitedCount < am.length) { 
            int currentNode = q.remove();
            if (visited[currentNode]) {
                continue;
            } else {
                visited[currentNode] = true;
                visitedCount++;
                path += currentNode+", ";
            }

            for (int i = 0; i < am.length; i++) {
                if (am[currentNode][i] != 0 && !visited[i]) {
                    q.add(i);
                }
            }
        }
        if (visitedCount != am.length) {
            return "Graph not connected";
        } else if (am.length == 0) {
            return "Graph is empty";
        } else {
            return path;
        }
    }

    /**
     * Generates an alphabetic representation of the generated note path from DFS or BFS
     * @param path the path to be used
     * @return the path argument in alphabetic representation
     */
    public static String toAlpha(String path) {
        String alphaPath = "";
        for (char c: path.toCharArray()) {
            if (c == ',' || c == ' ') {
                alphaPath+=c;
            } else {
                for (int i = 0; i < alphabet.length; i++) {
                    if (c == i + '0') {
                        alphaPath+=alphabet[i];
                        break;
                    }
                }
            }
        }
        return alphaPath;
    }

    /**
     * Generates an alphabetic representation of the given array of nodes
     * @param nodes the array of nodes
     * @return the nodes argument in alphabetic representation
     */
    public static char[] toAlpha(int[] nodes) {
        char[] out = new char[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            out[i] = alphabet[nodes[i]];
        }
        return out;
    }

    /**
     * Gets amount of nodes in the graph
     * @return node count
     */
    public int getNodeCount() {
        return am.length;
    }

    /**
     * Gets amount of nodes a given node is connected to
     * @param node the node to check
     * @return the degree of the node
     */
    public int getDegree(int node) {
        int count = 0;
        for (int i = 0; i < am.length; i++) {
            if (am[node][i] != 0) {
                count++;
            }
        }
        return count;
    }

    /**
     * Gets count of all edges in the graph
     * @return edge count
     */
    public int getEdgeCount() {
        int count = 0;
        for (int[] row : am) {
            for (int j = 0; j < am.length; j++) {
                if (row[j] != 0) {
                    count++;
                }
            }
        }
        if (directed) {
            return count;
        } else {
            return count/2;
        }
    }

    /**
     * Check does an edge exist between 2 given nodes
     * @param node1 start node
     * @param node2 end node
     * @return true if edge exists, otherwise false
     */
    public boolean edgeExists(int node1, int node2) { // TODO: testing
        if (am[node1][node2] != 0) {
            return true;
        } else return am[node2][node1] != 0 && !directed;
    }

    /**
     * Check does a given node exist
     * @param node the node to check
     * @return true if exists, false otherwise
     */
    public boolean nodeExists(int node) {
        return node < am.length;
    }

    /**
     * Adds an edge between 2 nodes
     * @param node1 start node
     * @param node2 end node
     * @throws ArrayIndexOutOfBoundsException if any argument less than 0 or node doesn't exist
     */
    public void addEdge(int node1, int node2) {
        am[node1][node2] = 1;
        if (!directed) {
            am[node2][node1] = 1;
        }
    }

    /**
     * Removes an edge between 2 nodes
     * @param node1 start node
     * @param node2 end node
     * @throws ArrayIndexOutOfBoundsException if any argument less than 0 or node doesn't exist
     */
    public void removeEdge(int node1, int node2) {
        am[node1][node2] = 0;
        if (!directed){
            am[node2][node1] = 0;
        }
    }

    /**
     * Adds a new node with no edges
     */
    public void addNode() {
        int [][] temp = new int[am.length+1][am.length+1];

        for (int i = 0; i <= am.length; i++) {
            for (int j = 0; j < am.length; j++) {
                if (i == am.length) {
                    temp[i][j] = 0;
                } else {
                    temp[i][j] = am[i][j];
                }
            }
            temp[i][am.length] = 0;
        }
        this.am = temp;
    }

    /**
     * Removes a given node
     * @param node the node to be removed
     *
     */
    public void removeNode(int node) { // TODO: test removing node index 0, could crash 
        int [][] temp = new int[am.length-1][am.length-1];

        for (int i = 0; i < am.length; i++){
            if (i < node) { // copy rows before node to be removed
                for (int j = 0; j < am.length; j++) { 
                    if (j < node) {
                        temp[i][j] = am[i][j];
                    } else if (j > node) {
                        temp[i][j-1] = am[i][j];
                    }
                }
            } else if (i > node) { // copy rows after node to be removed
                for (int j = 0; j < am.length; j++) { 
                    if (j < node) {
                        temp[i-1][j] = am[i][j];
                    } else if (j > node) {
                        temp[i-1][j-1] = am[i][j];
                    }
                }
            }
        }
        this.am = temp;
    }

    /**
     * Lists all adjacent nodes to the given node
     * @param node the given node
     * @return an array of all adjacent nodes (if any exist)
     */
    public int[] listAdjacentNodes(int node) {
        int[] nodes = new int[this.getDegree(node)];
        int count = 0;
        for (int i = 0; i < am.length; i++) {
            if (am[node][i] != 0) {
                nodes[count] = i;
                count++;
            }
        }
        return nodes;
    }

    /**
     * Prints the graph to the console
     */
    public void printGraph() {
        for (int[] row : am) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Checks is the graph directed
     * @return true if directed, false otherwise
     */
    public boolean isDirected() {
        return directed;
    }

    /**
     * Gets the edge list representation of the graph
     * @return an edge list represented as a 2-dimensional array where the rows are edges, column 0 is the start node of a given edge and column 1 is the end node
     */
    public int[][] getEdgeList() {
        int[][] el = new int[this.getEdgeCount()][2];
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
                    count++;
                }
            }
        }
        return el;
    }

    /**
     * Gets the adjacency matrix representation of the graph
     * @return an adjacency matrix represented as a 2-dimensional array where the rows are the starting nodes and the columns are the end nodes
     */
    public int[][] getAdjacencyMatrix() {
        return am;
    }

    /**
     * Import an edge list to be represented as a graph
     * @param el the edge list to be imported
     * @throws IllegalArgumentException if all rows don't have exactly 2 columns
     */
    public void importEdgeList(int[][] el) {
        for (int[] row : el) {
            if (row.length != 2) {
                throw new IllegalArgumentException("Each row in edge list array must have exactly 2 elements!");
            }
        }
        int count = getNodeCount(el);

        this.am = new int[count][count]; // initialise adjacency matrix with all 0
        for (int i = 0; i < am.length; i++) {
            for (int j = 0; j < am.length; j++) {
                am[i][j] = 0;
            }
        }

        for (int[] row : el) {
            am[row[0]][row[1]] = 1;
            if (!directed) {
                am[row[1]][row[0]] = 1;
            }
        }
    }

    /**
     * Gets the count of unique nodes in a given edge list
     * @param el the edge list to be checked
     * @return the amount of unique nodes
     * @throws ArrayIndexOutOfBoundsException if any row has less than 2 columns
     */
    public int getNodeCount(int[][] el) {
        int[] nodes = new int[el.length*2];
        int count = 0;
        boolean unique;

        for (int col = 0; col < 2; col++) { // loops through both columns of edge list
            for (int[] row : el) { // loops through every entry
                unique = true;
                for (int k = 0; k < count; k++) { // loops through already found nodes to check is it unique
                    if (nodes[k] == row[col]) {
                        unique = false;
                        break;
                    }
                }
                if (unique) {
                    nodes[count] = row[col];
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * Attempts to import an adjacency matrix to be used as a graph
     * @param am the adjacency matrix to use
     * @throws IllegalArgumentException if argument is not a valid adjacency matrix
     */
    public void importAdjacencyMatrix(int [][] am) {
        isValid(am);
        this.am = am;
    }

    /**
     * Checks the validity of a given adjacency matrix array
     * @param am the array to be checked
     * @throws IllegalArgumentException if argument is not a valid adjacency matrix
     */
    private void isValid(int[][] am) {
        int rows = am.length;
        for (int[] row : am) {
            if (row.length != rows) {
                throw new IllegalArgumentException("Adjacency matrix array must be square!");
            }
        }
    }
}

// TODO: add weight removal on import of weighted adjacency matrix on a non weighted graph object
// TODO: add node naming