package model;

import java.util.Observable;

public class Graph extends Observable {

	// static final variable(s)
	public static final int size = 10;	// # of MAX vertices 
	// variable(s)
	private Vertex root;			// root vertex of the graph
	private Vertex[] vertices;	// vertices in the graph
	private Edge[] edges;		// edges in the graph
	
	// constructor 
	public Graph() {
		root = new Vertex();
		vertices = new Vertex[0];
		edges = new Edge[0];
	}
	
	// getters 
	public int getSize() {	return size;		}
	public Vertex getRoot() {	return root;		}
	public int getNumVertices() {	return vertices.length;		}
	public Vertex[] getVertices() {	return vertices;	}
	public Edge[] getEdges() {	return edges;	}
	// setters
	public void setRoot(Vertex r) {		root = r;	}
	
	// array functions
	public int find(int n) {
		for (int i = 0; i < vertices.length; i++) {
			if (vertices[i].getVertexNumber() == n)
				return i;
		}
		return -1; // did not find this vertex
	}
	public boolean add(Vertex v) {
		if (find(v.getVertexNumber()) != -1)
			return false; // vertex exists
		else { // vertex does not exist
			if (getNumVertices() < 10) {
				Vertex[] tempV = vertices;
				vertices = new Vertex[tempV.length + 1];
				for (int i = 0; i < tempV.length; i++) {
					vertices[i] = tempV[i];
				}
				vertices[tempV.length] = v;
				setChanged();
				notifyObservers();
				return true;
			}
			return false;
		}
	}
	
	public int findEdge(Vertex start, Vertex end) {
		for (int i = 0; i < edges.length; i++) {
			if (edges[i].getStart().getVertexNumber() == start.getVertexNumber() &&
					edges[i].getEnd().getVertexNumber() == end.getVertexNumber())
				return i;
		}
		return -1; // did not find edge
	}
	public boolean addEdge(Vertex start, Vertex end) {
//		if (getNumVertices() < 10) 
			int n = find(start.getVertexNumber());
			vertices[n].addAdjacentVertex(end, 1);
			Edge e = new Edge(start, end, 1);
			Edge[] tempE = edges;
			edges = new Edge[tempE.length + 1];
			for (int i = 0; i < tempE.length; i++) {
				edges[i] = tempE[i];
			}
			edges[tempE.length] = e;
			setChanged();
			notifyObservers();
			return true;
//		}
//		return false;
	}
	public boolean removeEdge(Vertex start, Vertex end) {
		Edge[] tempE = edges;
		int index = findEdge(start,end);
		int count = 0;
		if (index != -1) {
			vertices[find(start.getVertexNumber())].removeAdjacentVertex(end);
			edges = new Edge[tempE.length - 1];
			for (int i = 0; i < tempE.length; i++) {
				if (i != index) {
					edges[count] = tempE[i];
					count++;
				} else 
					continue;
			}
			setChanged();
			notifyObservers();
			return true;
		} else 
			return false; // edge not found
	}
	
	public static Graph graph1() {
		Graph g = new Graph();
		// init vertices 
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);
		Vertex v5 = new Vertex(5);
		Vertex v6 = new Vertex(6);
		Vertex v7 = new Vertex(7);
		Vertex v8 = new Vertex(8);
		Vertex v9 = new Vertex(9);
		Vertex v10 = new Vertex(10);
		// init edges 
//		Edge e1 = new Edge(v1, v2, 1);
//		v1.addEdge(e1);
		
		g.add(v1);
		g.add(v2);
		g.add(v3);
		g.add(v4);
		g.add(v5);
		g.add(v6);
		g.add(v7);
		g.add(v8);
		
		// vertex 1 adjacent
//		v1.addAdjacentVertex(v2, 1);
		g.addEdge(v1,v2);
		// vertex 2 adjacent
//		v2.addAdjacentVertex(v3, 1);
		g.addEdge(v2,v3);
		// vertex 3 adjacent
//		v3.addAdjacentVertex(v4, 1);
		g.addEdge(v3,v4);
		// vertex 4 adjacent
//		v4.addAdjacentVertex(v6, 1);
		g.addEdge(v4,v6);
		// vertex 5 adjacent
//		v5.addAdjacentVertex(v8, 1);
		g.addEdge(v5,v8);
		// vertex 6 adjacent
//		v6.addAdjacentVertex(v5, 1);
		g.addEdge(v6,v5);
		// vertex 7 adjacent
//		v7.addAdjacentVertex(v6, 1);
		g.addEdge(v7,v6);
		// vertex 8 adjacent
//		v8.addAdjacentVertex(v7, 1);
		g.addEdge(v8,v7);
		
		// vertex 9 adjacent
//		v9.addAdjacentVertex(v8, 1);
//		v9.addAdjacentVertex(v10, 3);
		// vertex 10 adjacent
//		v10.addAdjacentVertex(v8, 2);
//		v10.addAdjacentVertex(v9, 3);
		
		// add vertices to graph
//		g.add(v1);
//		g.add(v2);
//		g.add(v3);
//		g.add(v4);
//		g.add(v5);
//		g.add(v6);
//		g.add(v7);
//		g.add(v8);
//		g.add(v9);
//		g.add(v10);
		// set root of graph
		g.setRoot(v1);
		// add vertices to graph
		
		
		return g;
		
	}
}
