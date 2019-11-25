public class Vertex {
	private int id;
	private int x;
	private int y;
	
	//Constructor
	public Vertex() {
		this.id = 0;
		this.x = 0;
		this.y = 0;
	}
	
	//Constructor
	public Vertex(int id, int x, int y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	//Get and Set Id
	public int getId() { return id;	}
	public void setId(int id) {	this.id = id; }
	//Get and Set X-coordinate
	public int getX() {	return x; }
	public void setX(int x) { this.x = x; }
	//Get and Set Y-coordinate
	public int getY() {	return y; }
	public void setY(int y) { this.y = y; }
	
	//return distance to another vertex from this vertex
	public double distanceTo(Vertex vertex) {
		//distance between the vertices in the x-plane
		int xDistance = this.getX() - vertex.getX();
		//distance between the vertices in the y-plane
		int yDistance = this.getY() - vertex.getY();
		//using x and y distances to calculate the actual distance between the points (Pythagorean Theorem)
		double distance = Math.sqrt((xDistance * xDistance) + (yDistance * yDistance));
		
		return distance;
	}
}
