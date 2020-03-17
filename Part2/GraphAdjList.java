
//Note: I used https://algorithms.tutorialhorizon.com/graph-implementation-adjacency-list-better-set-2/ as reference
import java.util.LinkedList;
import java.util.Iterator;

/**
 * Graph implementation that uses Adjacency Lists to store edges. It contains
 * one linked-list for every vertex i of the graph. The list for i contains one
 * instance of VertexAdjList for every vertex that is adjacent to i. For
 * directed graphs, if there is an edge from vertex i to vertex j then there is
 * a corresponding element in the adjacency list of node i (only). For
 * undirected graphs, if there is an edge between vertex i and vertex j, then
 * there is a corresponding element in the adjacency lists of *both* vertex i
 * and vertex j. The edges are not sorted; they contain the adjacent nodes in
 * *order* of edge insertion. In other words, for a graph, the node at the head
 * of the list of some vertex i corresponds to the edge involving i that was
 * added to the graph least recently (and has not been removed, yet).
 */

public class GraphAdjList implements Graph {

	// ATTRIBUTES:
	boolean isDirected; // if the graph is directed
	LinkedList[] myList; //This stores the vertexes

	/*
	 * CONSTRUCTOR: Creates a directed/undirected graph with V vertices and no
	 * edges. It initializes the array of adjacency edges so that each list is
	 * empty.
	 */
	public GraphAdjList(int v, boolean directed) {
		this.isDirected = directed;
		myList = new LinkedList[v];
		for (int i = 0; i < v; i++) {
			myList[i] = new LinkedList<Edge>();
		}
	}

	// 1. IMPLEMENTATION METHOD numVerts:
	// Returns length of the list, which is the no of Verts in our case
	public int numVerts() {
		return myList.length;
	}

	// 2. IMPLEMENTATION METHOD numEdges:
	//Returns the number of edges. Not on the marking scheme. 
	public int numEdges() {
		// TODO:
		int edge = 0; // Edge counter
		//Loops for each vert
		for(int i = 0; i< myList.length; i++){
			//edgeList stores all the edges connected to the first value passed in. 
			LinkedList<Edge> edgeList = myList[i];
			//Loops through each Edge
			for (int j = 0; j < edgeList.size(); j++) {
				//If edge has a weight of not -1 it counts it
				if(edgeList.get(j).getWeight() >=0){
					edge++;
				}
			}
		}
		// Return the number of edges.
		return edge;
	}

	/**3. IMPLEMENTATION METHOD addEdge: Adds edge connection between verts 
	 * v1: Value of the Vert that v2 is connected to. 	v2: Value of the Vert connected to v1	w: weight of the connection from v1 -> v2
	 * Note: These definitions apply in all instances and won't be explained again to help make comments readable
	 * 
	 * If Undirected apply the same in reverse, has an issue where it counts the same edge if added again
	 */
	public void addEdge(int v1, int v2, int w) {
		// Try is to ensure that Out of Bounds exception is caught if there
		try {
			// Adds an edge and weight to vertice's Edge list
			myList[v1].add(new Edge(v2, w));
			// If undirected repeat the other direction
			if (!isDirected) {
				myList[v2].add(new Edge(v1, w));
			}
			// Catch for a value out of bounds
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index out of Bounds, Vertice at this location does not exist.");
		}

	}

	// 4. IMPLEMENTATION METHOD removeEdge:
	// We traverse through the vector list for v1 and remove the element v2.
	public void removeEdge(int v1, int v2) {
		try {
			//edgeList stores all the edges connected to the first value passed in. 
			LinkedList<Edge> edgeList = myList[v1];
			//Loops through the Edges
			for (int i = 0; i < edgeList.size(); i++) {
				//Temp Edge stores the Edge stored at index i
				Edge tempEdge = edgeList.get(i);
				//If thats the same as the 2nd value passed in, it remove the value
				if (tempEdge.getVertex() == v2) {
					myList[v1].remove(i);
				}
			}
			// If Undirected we do the same in the other direction.
			if (!isDirected) {
				edgeList = myList[v2];
				for (int i = 0; i < edgeList.size(); i++) {
					Edge tempEdge = edgeList.get(i);
					if (tempEdge.getVertex() == v1) {
						myList[v2].remove(i);
						break;
					}
				}
			}
			// Catch for a value out of bounds
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index out of Bounds, Vertice at this location does not exist.");
		}
	}

	// 5. IMPLEMENTATION METHOD hasEdge: Returns true/false depending on in v1 has v2 as an edge. 
	public boolean hasEdge(int v1, int v2) {
		// TO-DO
		try {
			//edgeList stores all the edges connected to the first value passed in. 
			LinkedList<Edge> edgeList = myList[v1];
			//Loops through edgeList to see if any vertex is the same as v2. If so returns true. 
			for (int i = 0; i < edgeList.size(); i++) {
				Edge tempEdge = edgeList.get(i);
				if (tempEdge.getVertex() == v2) {
					return true;
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index out of Bounds, Vertice at this location does not exist.");
		}
		return false;
	}

	// 6. IMPLEMENTATION METHOD getWeightEdge: Checks the weight of the connection between v1 and v2
	public int getWeightEdge(int v1, int v2) {
		try {
			//List holding the edges for v1
			LinkedList<Edge> edgeList = myList[v1];
			//Loops through each edge
			for (int i = 0; i < edgeList.size(); i++) {
				//tempEdge stores the current edge we check
				Edge tempEdge = edgeList.get(i);
				//If it matches v2 we return the weight. 
				if (tempEdge.getVertex() == v2) {
					return tempEdge.getWeight();
				}
			}
		//Catches out of bound errors 
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index out of Bounds, Vertice at this location does not exist.");
		}
		return -1;
	}

	// 7. IMPLEMENTATION METHOD getNeighbors:
	public LinkedList getNeighbors(int v) {
		LinkedList<Edge> res = new LinkedList();
		try {
			LinkedList<Edge> edgeList = myList[v];
			for (int i = 0; i < edgeList.size(); i++) {
				res.add(edgeList.get(i));
			}

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Index out of Bounds, Vertice at this location does not exist.");
		}
		return res;
	}

	// 8. IMPLEMENTATION METHOD getDegree: Counts the number of edges that terminate at v. I'm not sure If I did this one backwards
	public int getDegree(int v) {
		// TO-DO
		int res = 0; //No of degres. 

		//checks the no of edges v has.
		res += myList[v].size();
		//If undirected there is twice as many assuming edge touching itself in both directions counts as 2. If not you'd include a minus 1 to res. 
		if(!isDirected){
			res*=2;
		}
		//return the number of degrees
		return res;
	}

	// 9. IMPLEMENTATION METHOD toString: Prints graph showing representation of the connections between verts out.
	public String toString() {
		// TO-DO
		String res = "====================\n"; //Line to begin the String, makes it more readable.
		for (int i = 0; i < myList.length; i++) {
			res += i + ":\n"; //Displays number of the Vert
			
			//Loops through the verts, and prints any connections to other verts + weight
			for (int j = 0; j < myList[i].size(); j++) {
				Edge temp = (Edge) myList[i].get(j);
				res += "Vertex: " + temp.getVertex();
				res += "\tWeight: " + temp.getWeight() + "\n";
			}
			res += "\n"; //New line for readability
		}
		res += "====================\n";//Line to show end of toString
		return res;
	}

}