import java.util.LinkedList;

/*
 *  Implementation of the interface Graph with adjacency matrix.
*/

public class GraphAdjMatrix implements Graph {

    // ATTRIBUTES:
    int[][] matrix; // 2D Array
    boolean isDirected;// If the

    // TODO: CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no
    // edges
    public GraphAdjMatrix(int V, boolean directed) {
        this.isDirected = directed; // Sets if directed or not
        matrix = new int[V][V]; // Creates new Matrix
        for (int x = 0; x < V; x++) { // Loops through x-axis
            for (int y = 0; y < V; y++) { // Loops through y-axis
                matrix[x][y] = -1; // Creates a -1 in each position as the default. Other values are populated in
                                   // Main after
            }
        }
    }

    // TODO: 1. IMPLEMENTATION METHOD numVerts:
    public int numVerts() {
        return matrix.length; // No of nodes/vertices in the matrix.
    }

    // 2. IMPLEMENTATION METHOD numEdges: Returns the no. of edges in a graph
    // DO NOT COUNT BOTH EDGES IN UNDRIECTED GRAPH
    public int numEdges() {
        // TO-DO
        int edge = 0; // Connections with other vertices
        int selfEdge = 0; // Connections with self
        int v = matrix.length; // no of verts.
        int res = 0; // Return value

        // Loop through Matrix. Add edges
        for (int x = 0; x < v; x++) {
            for (int y = 0; y < v; y++) {
                // Loops through Matrix
                if (matrix[x][y] >= 0) { //TODO: Check this works correctly 
                    if (x == y) { // if it's a self connection
                        selfEdge++; // Increase Self edge
                    } else {
                        edge++; // Otherwise its a connection
                    }
                }
            }
        }
        // Avoid counting both edges in undirected graph
        if (!isDirected) {
            edge = edge / 2;
        }
        // We add the possibly changed edge with the unchanging self Edge for the
        // complete total.
        res = edge + selfEdge;

        // 3. Return res
        return res;
    }

    // 3. IMPLEMENTATION METHOD addEdge: Add Edge between vertices with weight
    // v1: Vertix 1, v2: Vertix 2, w: Weight of edge
    public void addEdge(int v1, int v2, int w) {
        try {
            matrix[v1][v2] = w; // Adds the weight of that edge to the vertices on the matrix
            if (!isDirected) {
                matrix[v2][v1] = w; // If it's undirected apply weight the other direction too.
            }
            // System.out.println("Vertex added successfully")

            // If it goes beyond the Index print error.
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Vertex out of bounds.");
        }
    }

    // 4. IMPLEMENTATION METHOD removeEdge: Removes an edge between v1 and v2
    // IF UNDIRECTED both direction must be removed.
    public void removeEdge(int v1, int v2) {
        try {
            matrix[v1][v2] = -1; // Sets back to -1 to show Edge has been removed
            if (!isDirected) {
                matrix[v2][v1] = -1; // If it's undirected set back to -1 the other direction too.
            }
            // If it tries to remove it from a point beyond the Array index it prints an
            // error
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Vertex out of bounds.");
        }
    }

   }

    // 5. IMPLEMENTATION METHOD hasEdge: Checks if Edge exists between v1 and v2
    public boolean hasEdge(int v1, int v2) {
        // If not -1 it has an edge.
        return (matrix[v1][v2] >= 0);
    }

    // 6. IMPLEMENTATION METHOD getWeightEdge:
    public int getWeightEdge(int v1, int v2) {
        // Return weight at given matrix
        return matrix[v1][v2];
    }

    // 7. IMPLEMENTATION METHOD getNeighbors:
    //TODO:
    public LinkedList getNeighbors(int v) {
        // Create a linked List as thats the return stated in the function. 
        LinkedList<Integer> res = new LinkedList<Integer>();
        //loops through the Yaxis of the Matrix and where it has an edge
        for(int i = 0; i < matrix.length; i++){
            if(matrix[v][i] >=0){
                res.add(i);
            }
        }
        return res;
    }

    // 8. IMPLEMENTATION METHOD getDegree:
    /* Degree defn: where the degree of a vertex counts 
    the number of times an edge terminates at that
    vertex. In an undirected graph, this means that each 
    loop increases the degree of a vertex by two.*/
    public int getDegree(int v) {
        // TODO: 
    }

    // 9. IMPLEMENTATION METHOD toString: TODO: Check formats right 
    public String toString() {
        String res = "";
        //Adds X-Axis to String
        for(int x = 0; x < matrix.length; x++){
            for(int y = 0; y < matrix[x].length; y++){
            res += String.format("|%d|", matrix[x][y]);
            }
            res += ("\n");
        }
        return res;
    }
}