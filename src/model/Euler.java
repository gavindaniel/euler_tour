package model;

public class Euler {

	private Graph _graph;
	private String _result;
	private boolean[] _visited;
	private Edge[] _queue;
	
	// constructor 
	public Euler(Graph g) {
		_graph = g;
		_result = "";
		_visited = new boolean[g.getEdges().length]; //size
		_queue = new Edge[0];
	}
	
	public String start(int starting_vertex) {
		_result = search(_graph.getVertices()[starting_vertex - 1], _queue, _visited, _result);
		_result += "End";
		boolean success = true;
		for (int i = 0; i < _visited.length; i++) {
			if (_visited[i] == false) {
				success = false;
				break;
			}
		}
		if (success) {
			String temp = _result;
			_result = "Success! Euler path found!\nEuler Path Start -> " + temp;
		} else 
			_result = "Error! No path found";
		return _result;
	}
	
	public void tour(Vertex currentV, Edge[] edges) {
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
	
	public String search(Vertex currentV, Edge[] queue, boolean[] visited, String result) {
		String out = "";
		Vertex[] adjV = currentV.getAdjacentVertices();
		Edge[] tempQ = queue;
		int index = -1; //n
		
		for (int i = 0; i < adjV.length; i++) {
			boolean flag = false;
			index = _graph.findEdge(currentV, adjV[i]); // n =
			if (visited[index] == false) {
				for (int j = 0; j < tempQ.length; j++) {
					// check if edge is already in the queue
					if (index != -1) // n !=
						if (tempQ[j].getStart().getVertexNumber() == _graph.getEdges()[index].getStart().getVertexNumber() && 
								tempQ[j].getEnd().getVertexNumber() == _graph.getEdges()[index].getEnd().getVertexNumber()) { //getEdges()[n]
							flag = true; // edge is already in the queue
							break;
						}
				}
				// if edge was not found, add it to the queue
				if (!flag) { 
					tempQ = queue;
					queue = new Edge[queue.length + 1];
					for (int k = 0; k < tempQ.length; k++) {
						queue[k] = tempQ[k];
					}
					queue[tempQ.length] = _graph.getEdges()[index];
				}
			}
		}
		
		boolean done = true;
		// check if all edges have been visited
		for (int i = 0; i < visited.length; i++) { 
			if (visited[i] == false) {
				done = false;
				break;
			}
		}
		// check if we are done visiting edges and if any edges are in the queue
		if (!done && queue.length > 0) 
			for (int i = 0; i < queue.length; i++) {
				index = _graph.findEdge(queue[i].getStart(), queue[i].getEnd()); //int index
				if (!visited[index]) {
					if (currentV.getVertexNumber() == queue[i].getStart().getVertexNumber()) {
						visited[index] = true;
						Vertex endV = queue[i].getEnd();
						result += currentV.getVertexNumber() + " -> ";
						queue = removeEdgeFromQueue(queue, i);
						out = search(endV, queue, visited, result);
					}
				}
			}
		else if (done) {
			result += currentV.getVertexNumber() + " -> ";
			out = result;
		}
		
		return out;
	}
	
	
	public Edge[] removeEdgeFromQueue(Edge[] queue, int index) {
		Edge[] temp = queue;
		int count = 0;
		queue = new Edge[temp.length - 1];
		for (int i = 0; i < temp.length; i++) {
			if (i != index) {
				queue[count] = temp[i];
				count++;
			} else 
				continue;
		}
		return queue;
	}
}
