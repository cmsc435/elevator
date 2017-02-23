import java.util.ArrayList;



// to do : create code for command line testing functionality of system or get gui working to be
// able to test against that


public class ElevatorController {
	ArrayList<Request> requests;
	Thread inputProcessor;
	Elevator left, right;
	// two elevators present in  system

	
	public ElevatorController() {
		// takes in all requests into treeset then delegates them to individual elevators
		super();
		requests = new ArrayList<Request>();
		inputProcessor = new Thread(new InputProcessor(), "Input Processor");
		left = new Elevator();
		right = new Elevator();
		inputProcessor.run();
	}


	public void addRequest(Request req) {
		// takes in up/down floor requests (outside elevator) from UI class 
		// and delegates them to individual elevators based on their position and curr direction
		requests.add(req);
		
	}
	
	public void addDropRequest(Request req) throws Exception {
		if (req.toDropOff != null) {
			req.toDropOff.addFloor(req);
		}
		else {
			throw new Exception("pickup request given to dropoff add method");
		}
	}
	
	
	// still needs to be finished
	public synchronized int allocRequest(Request req) {
		/** decides which elevator to allocate the request to; once allocated, that
		 * elevator must be the one to service the request; prioritized based on each elevator's
		 * current direction, if directions are the same, allocated based on closer floor, tiebreaker
		 * goes to elevator with smaller request treeset size; returns int for debugging purposes;
		 * 0 if successful allocation, 1 if unable to allocate to either elevator for some reason
		 */
		Elevator.Direction leftdir = left.direction;
		Elevator.Direction rightdir = right.direction;
		
		int leftLevel = left.currentLevel;
		int rightLevel = right.currentLevel;
		
		if (left.getDirection() == Elevator.Direction.IDLE 
				&& right.getDirection() == Elevator.Direction.IDLE) {
			// if both idle, doesnt matter, give to left
			left.addFloor(req);
		}
		
		else if (left.getDirection() == req.dir) {
			// if left elevator's direction matches the requests direction
			if (right.getDirection() != Elevator.Direction.UP) {
				if ((req.dir == Elevator.Direction.UP && leftLevel <= req.floor)
						|| req.dir == Elevator.Direction.DOWN && leftLevel >= req.floor) {
					// if request is in the left elevator's path, not behind it, 
					// and right elevator is going down, then add to left
					left.addFloor(req);
				}
				else {
					// if request is behind left elevator on the elevator's path upward, determine which
					// elevator to add to based on the closeness of each elevator's eventual destination floor
					if (left.currentRequest.floor == 0) {
						return 0;
					}
				}
			}
		}
		else {
			return 0;
		}
		return 0;
	}
	
	

public class InputProcessor implements Runnable {

	@Override
	public void run() {
		// infinitely checks if any requests have been made 
		// and allocates them to an elevator if so
		while(true) {
			while (!requests.isEmpty()) {
				int errChck = allocRequest(requests.remove(0));
				// take care of error checking potentially
			}
		}
	}

}
}
	


