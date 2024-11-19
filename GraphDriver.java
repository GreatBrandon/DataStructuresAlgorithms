import java.util.Arrays;

public class GraphDriver {
    public static void main(String[] args) {
        int [][] am = {
            {0,1,1,0,0}, 
            {0,0,1,1,0}, 
            {0,0,0,0,1}, 
            {0,0,0,0,0}, 
            {0,0,0,0,0}}; // directed unweighted graph
        int [][] am2 = {
            {0,1,1,0,0}, 
            {1,0,1,1,0}, 
            {1,1,0,1,1}, 
            {0,1,1,0,0}, 
            {0,0,1,0,0}}; // undirected unweighted graph
        int [][] am3 = {
            {0,1,1,0,0}, 
            {0,0,1,1,0}, 
            {0,0,0,1,0}, 
            {0,0,0,0,0}, 
            {0,0,0,0,0}}; // directed unweighted graph, node 4 NOT CONNECTED

        int [][] am4 = {
            {0,1,0,0,0,1,0,0},
            {0,0,1,1,1,0,0,0},
            {0,0,0,0,1,0,0,1},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,1},
            {0,0,0,1,1,0,1,0},
            {0,0,0,0,1,0,0,0},
            {0,0,0,0,0,0,0,0}
        }; // graph from lecture picture example DIRECTED version

        int [][] am5 = {
            {0,22,0,0,0,12,0,0},
            {0,0,6,8,18,0,0,0},
            {0,0,0,0,14,0,0,19},
            {0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,4},
            {0,0,0,11,12,0,7,0},
            {0,0,0,0,23,0,0,0},
            {0,0,0,0,0,0,0,0}
        }; // graph from lecture picture example DIRECTED WEIGHTED version

        // Graph g2 = new Graph(true);
        // System.out.println(g2.breadthFirstSearch(0));
        // g2.printGraph();
        // g2.addNode();
        // g2.printGraph();
        // g2.addNode();
        // g2.printGraph();

        // int [][] el = {
        //     {0,2},
        //     {0,3},
        //     {1,2},
        //     {3,4}
        // };

        // g2.importEdgeList(el);
        // System.out.println(g2.getAdjacencyMatrix().length);
        // g2.printGraph();
        // System.out.println(g2.nodeExists(0));
        // System.out.println(g2.nodeExists(10));
        // System.out.println(Arrays.deepToString(g2.getEdgeList()));
        

        // Graph g1 = new Graph(am4, true);
        // System.out.println("Path using depth first search: " + g1.depthFirstSearch(0));
        // System.out.println("Path using breadth first search: " + g1.breadthFirstSearch(0));
        // System.out.println("Amount of nodes: " + g1.getNodeCount());
        // System.out.println("Degree of node 1 (b): " + g1.getDegree(1));
        // System.out.println("Amount of edges: "+g1.getEdgeCount());
        // System.out.println("Edge between F and D: "+g1.edgeExists(5, 3));
        // System.out.println("Edge between D and F: "+g1.edgeExists(3, 5));
        // System.out.println("Nodes adjacent to node 1 (B): "+Arrays.toString(g1.listAdjacentNodes(1)));
        // System.out.println("Nodes adjacent to node 1 (B): "+Arrays.toString(Graph.toAlpha(g1.listAdjacentNodes(1))));
        // System.out.println("Edge list of graph: "+Arrays.deepToString(g1.getEdgeList()));
        // g1.addNode();
        // System.out.println("Amount of nodes: "+g1.getNodeCount());
        // g1.printGraph();

        // g1.removeNode(3);
        // System.out.println("Amount of nodes: "+g1.getNodeCount());
        // g1.printGraph();

        // String path = g1.depthFirstSearch(0);
        // System.out.println("Depth first search path in alphabetic format: "+Graph.toAlpha(path));
        // g1.breadthFirstSearch(0);

        // WeightedGraph wg = new WeightedGraph(am5, true);
        // wg.addEdge(0, 7, 100);
        // wg.printGraph();
        // System.out.println(Arrays.deepToString(wg.getEdgeList()));   
        
        int [][] el2 = {
            {0,2, 40},
            {0,3, 50},
            {1,2, 25},
            {3,4, 35},
            {1,3, 25},
            {0,4, 50}
        };
        int [][] el3 = {
            {0,1,6},
            {0,2,1},
            {0,3,5},
            {1,2,5},
            {1,4,3},
            {2,3,5},
            {2,4,6},
            {2,5,4},
            {3,5,2},
            {4,5,6}
        }; // sample graph from lectures

        WeightedGraph wg2 = new WeightedGraph(false);
        wg2.importEdgeList(el3);
        // wg2.printGraph();
        // wg2.addEdge(0, 1); // should throw error
        // System.out.println(Arrays.deepToString(wg2.getEdgeList()));

        // WeightedGraph kruskal = wg2.kruskalMST();
        // System.out.println(kruskal.getEdgeCount());
        // System.out.println(Arrays.deepToString(kruskal.getEdgeList()));

        // WeightedGraph prim = wg2.primMST();
        // System.out.println(prim.getEdgeCount());
        // System.out.println(Arrays.deepToString(prim.getEdgeList()));

        System.out.println(wg2.dijkstra(0,4));
    }
}
