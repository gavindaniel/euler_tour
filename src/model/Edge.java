package model;

public class Edge {

	private Vertex _start;		// origin vertex of the edge
	private Vertex _end;			// destination vertex of the edge
	private int _weight;			// weight of the edge 
	
	// empty constructor
	public Edge() {
		_start = new Vertex();			// empty vertex
		_end = new Vertex();				// empty vertex
		_weight = -9999;					// obvious value to represent an initialized edge
	}
	// pre-filled constructor
		public Edge(Vertex start, Vertex end) {
			_start = start;
			_end = end;
			_weight = -9999;				// obvious null value
		}
	// pre-filled constructor
	public Edge(Vertex start, Vertex end, int weight) {
		_start = start;
		_end = end;
		_weight = weight;
	}
	
	// getters
	public Vertex getStart() {		return _start;	}
	public Vertex getEnd() {		return _end;	}
	public int getWeight() {		return _weight;	}
	// setters
	public void setOrigin(Vertex start) {	this._start = start;		}
	public void setDestination(Vertex end) {	this._end = end;	}
	public void setWeight(int weight) {	this._weight = weight;		}
}
