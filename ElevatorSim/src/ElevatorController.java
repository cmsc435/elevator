import java.util.TreeSet;

public class ElevatorController {
	TreeSet<Request> requests;
	Thread inputProcessor;
	Elevator left, right;
	// two elevators present in  system

	public static void main(String args) {
		
	}
	
	
	public ElevatorController() {
		// takes in all requests into treeset then delegates them to individual elevators
		super();
		requests = new TreeSet<Request>();
		inputProcessor = new Thread(new InputProcessor(), "Input Processor");
		left = new Elevator();
		right = new Elevator();
	}


	public void addRequest(Request req) throws Exception {
		// takes in up/down floor requests (outside elevator) from UI class 
		// and delegates them to individual elevators based on their position and curr direction
		if (requests.add(req) == false) {
			throw new Exception("request already present. should not be allowed to call again");
		}
		
	}
}
	


