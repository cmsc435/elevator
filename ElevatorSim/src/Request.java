
public class Request {
	public enum Direction {
		UP, DOWN
	}
	
	int floor;
	Direction dir;
	public Request(int floor, Direction dir) {
		super();
		this.floor = floor;
		this.dir = dir;
	}
	
	
}
