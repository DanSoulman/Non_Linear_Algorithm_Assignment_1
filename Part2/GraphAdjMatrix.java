import java.util.LinkedList;

/*
 *  Implementation of the interface Graph with adjacency matrix.
*/

public class GraphAdjMatrix implements Graph {

    // ATTRIBUTES:
    int[][] matrix; // 2D Array
    boolean isDirected;// If the Graph is directed or not

    // CONSTRUCTOR: Creates a directed/undirected graph with V vertices
    public GraphAdjMatrix(int V, boolean directed) {
        this.isDirected = directed; // Sets if directed or not
        matrix = new int[V][V]; // Creates new Matrix
        for (int x = 0; x < V; x++) { // Loops through x-axis
            for (int y = 0; y < V; y++) { // Loops through y-axis
                matrix[x][y] = -1; // Creates a -1 in each position as the default. Other values are populated in
                                   // Rest 2
            }
        }
    }

    // 1. IMPLEMENTATION METHOD numVerts:
    public int numVerts() {
        return matrix.length; // No of nodes/vertices in the matrix.
    }

    // 2. IMPLEMENTATION METHOD numEdges: Returns the no. of edges in a graph
    public int numEdges() {
        // TO-DO
        int edge = 0; // Connections with other vertices or self

        // 1. Loop through Matrix.
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                // 2. When there is a weight to the matrix add it
                if (matrix[x][y] >= 0) {
                    edge++;
                }
            }
        }
        // 3. Return num of edges
        return edge;
    }

    // 3. IMPLEMENTATION METHOD addEdge: Add Edge between vertices with weight
    // v1: Vertix 1, v2: Vertix 2, w: Weight of edge
    public void addEdge(int v1, int v2, int w) {
        // We use a try so we can catch the Out of Bounds
        try {
            matrix[v1][v2] = w; // 1. Adds the weight of that edge to the vertices on the matrix
            if (!isDirected) {
                matrix[v2][v1] = w; // 2. If it's undirected apply weight the other direction too.
            }

            // If it goes beyond the Index, print error.
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Vertex out of bounds.");
        }
    }

    // 4. IMPLEMENTATION METHOD removeEdge: Removes an edge between v1 and v2
    // IF UNDIRECTED both direction must be removed.
    public void removeEdge(int v1, int v2) {
        try {
            matrix[v1][v2] = -1; // 1. Sets back to -1 to show Edge has been removed
            if (!isDirected) {
                matrix[v2][v1] = -1; // 2. If it's undirected set back to -1 the other direction too.
            }
            // If it tries to remove it from a point beyond the Array index it prints an
            // error
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Vertex out of bounds.");
        }
    }

    // 5. IMPLEMENTATION METHOD hasEdge: Checks if Edge exists between v1 and v2
    public boolean hasEdge(int v1, int v2) {
        // 1. If not -1 it has an edge. Returns true else false
        return (matrix[v1][v2] >= 0);
    }

    // 6. IMPLEMENTATION METHOD getWeightEdge:
    public int getWeightEdge(int v1, int v2) {
        // 1. Return weight at given matrix
        return matrix[v1][v2];
    }

    // 7. IMPLEMENTATION METHOD getNeighbors:
    // value v being passed in is a point on the x axis of the matrix. We will then
    // look at point x in each column to find which have weights
    public LinkedList getNeighbors(int v) {
        // 1. Create a linked List as thats the return stated in the function.
        LinkedList<Integer> res = new LinkedList<Integer>();
        // 2. loops through the Yaxis of the Matrix
        for (int i = 0; i < matrix.length; i++) {
            // 3.Where not -1 there is an edge at that point on the y axis, we add that to
            // the list.
            if (matrix[v][i] >= 0) {
                res.add(i);
            }
        }
        // 4. return res
        return res;
    }

    // 8. IMPLEMENTATION METHOD getDegree:
    /*
     * Degree defn: where the degree of a vertex counts the number of times an edge
     * terminates at that vertex. In an undirected graph, this means that each loop
     * increases the degree of a vertex by two.
     */
    public int getDegree(int v) {
        int res = 0; // Return value, in this case degrees

        try {
            //1. Loop through Matrix
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[v][i] >= 0) { //2. Where value at given point isn't default
                    res++; //3. there is a degree
                }
            }
            //4. If Undirected we count the other direction also 
            if (!isDirected) {
                for (int i = 0; i < matrix.length; i++) {
                    if (matrix[i][v] >= 0) {
                        res++;
                    }
                }
            }
        //Make sure doesn't go Out of bounds 
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Vertex out of bounds.");
        }
        //5.return final value
        return res;

    }

    // 9. IMPLEMENTATION METHOD toString:
    // Loops through the matrix and prints out the value of each loaction.
    public String toString() {
        String res = "";
        // 1. Loops X-Axis
        for (int x = 0; x < matrix.length; x++) {
            // 2. Loops y axis,
            for (int y = 0; y < matrix.length; y++) {
                // 3. Adds each location to the output string
                res += String.format("|%d|", matrix[x][y]);
            }
            // 4. New line at the end of each row
            res += ("\n");
        }
        // 5. return result
        return res;
    }
}