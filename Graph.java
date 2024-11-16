import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    private int[][] am;
    private boolean directed;
    private static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    
    public Graph(boolean directed) { // initialise empty graph adjacency matrix
        this(new int[0][0], directed);
    }

    public Graph(int[][] am, boolean directed) {
        isValid(am);
        this.am = am;
        this.directed = directed;
    }

    public String depthFirstSearch(int startIndex) { // Depth first search is implemented by always going to the next smallest lettered node in the case of a choice (eg choose b or c, program will choose b first and go down that path)
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

            for (int i = am.length-1; i >= 0; i--) { // need to loop from largets to smallest to pop smallet to largest out of the stack
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

    public static char[] toAlpha(int[] nodes) {
        char[] out = new char[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            out[i] = alphabet[nodes[i]];
        }
        return out;
    }

    public int getNodeCount() {
        return am.length;
    }

    public int getDegree(int node) {
        int count = 0;
        for (int i = 0; i < am.length; i++) {
            if (am[node][i] != 0) {
                count++;
            }
        }
        return count;
    }

    public int getEdgeCount() {
        int count = 0;
        for (int i = 0; i < am.length; i++) {
            for (int j = 0; j < am.length; j++) {
                if (am[i][j] != 0) {
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

    public boolean edgeExists(int node1, int node2) {
        if (am[node1][node2] != 0) {
            return true;
        } else if (am[node2][node1] != 0 && !directed) {
            return true;
        } else {
            return false;
        }
    }

    public boolean nodeExists(int node) {
        if (node < am.length) {
            return true;
        } else {
            return false;
        }
    }

    public void addEdge(int node1, int node2) {
        am[node1][node2] = 1;
        if (!directed) {
            am[node2][node1] = 1;
        }
    }

    public void removeEdge(int node1, int node2) {
        am[node1][node2] = 0;
        if (!directed){
            am[node2][node1] = 0;
        }
    }

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

    public void removeNode(int node) {
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

    public void printGraph() {
        for(int i = 0; i < am.length; i++) {
            System.out.println(Arrays.toString(am[i]));
        }
    }

    public boolean isDirected() {
        return directed;
    }

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

    public int[][] getAdjacencyMatrix() {
        return am;
    }

    public void importEdgeList(int[][] el) {
        for (int i = 0; i < el.length; i++) {
            if (el[i].length != 2) {
                throw new IllegalArgumentException("Each row in edge list array must have exactly 2 elements!");
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

        this.am = new int[count][count]; // initialise adjancy matrix with all 0
        for (int i = 0; i < am.length; i++) {
            for (int j = 0; j < am.length; j++) {
                am[i][j] = 0;
            }
        }

        for (int i = 0; i < el.length; i++) {
            am[el[i][0]][el[i][1]] = 1;
            if (!directed) {
                am[el[i][1]][el[i][0]] = 1;
            }
        }
    }

    public void importAdjacencyMatrix(int [][] am) {
        isValid(am);
        this.am = am;
    }

    private void isValid(int[][] am) {
        int rows = am.length;
        for (int i = 0; i < rows; i++) {
            if (am[i].length != rows) {
                throw new IllegalArgumentException("Adjacency matrix array must be square!");
            }
        }
    }
}

// TODO: add weight removal on import of weighted adjacency matrix on a non weighted graph object
// TODO: add node naming