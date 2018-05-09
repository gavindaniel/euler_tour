package model;

public class Euler {

	private Graph _graph;
	private String _result;
	private boolean[] _visited;
	
	// constructor 
	public Euler(Graph g) {
		_graph = g;
		_result = "";
		_visited = new boolean[10]; //size
	}
	
	public String start(int starting_vertex) {
		System.out.print("Start (" + starting_vertex + ") -> ");
		tour(_graph.getVertices()[starting_vertex - 1], _graph.getEdges());
//		_result = search(_graph.getVertices()[starting_vertex - 1], _visited, _result);
		System.out.println("End");
		return _result;
	}
	
	public void tour(Vertex currentV, Edge[] edges) {
//		Vertex adjacentV = new Vertex();
		if (edges.length >= 0) {
			System.out.print(currentV.getVertexNumber());
			if (currentV.getAdjacentVertices().length > 0) { 
				System.out.print(" -> ");
				Vertex preV = currentV;
				currentV = currentV.getAdjacentVertices()[0];
				int index = _graph.findEdge(preV,currentV);
				if (index != -1) { // edge found
					_graph.removeEdge(preV,currentV);
					tour(currentV, _graph.getEdges());
				}
			}
			else {
				if (_graph.getEdges().length == 0)
					System.out.print(" -> End of path! -> ");
				else
					System.out.println("Error! No path found!");
			}
			
		}
		return;
	}
	
//	public String search(Vertex currentV, boolean[] visited, String result) {
//		String out = "";
//		visited[currentV.getVertexNumber()-1] = true;
//		System.out.print(currentV.getVertexNumber() + " ");
//		result += currentV.getVertexNumber() + " ";
//		
//		Vertex[] adjV = currentV.getAdjacentVertices();
//
//		for (int i = 0; i < adjV.length; i++) {
//			boolean flag = false;
//			for (int j = 0; j < temp.length; j++) {
//				if (adjV[i].getVertexNumber() == temp[j].getVertexNumber())
//					flag = true;
//			}
//			if (!flag) {
//				temp = stack;
//				stack = new Vertex[stack.length + 1];
//				for (int k = 0; k < temp.length; k++) {
//					stack[k] = temp[k];
//				}
//				stack[temp.length] = adjV[i];
//			}
//		}
//		boolean done = true;
//		for (int i = 0; i < visited.length; i++) {
//			if (visited[i] == false) {
//				done = false;
//			}
//		}
//		
//		if (!done && stack.length > 0)
//			for (int i = 0; i < stack.length; i++) {
//				if (!visited[stack[i].getVertexNumber()-1])
//					out = search(stack[i], stack, visited, result);
//			}
//		else if (done)
//			out = result;
//		
//		return out;
//	}
}
