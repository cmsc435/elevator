import java.util.ArrayList;



// to do : create code for command line testing functionality of system or get gui working to be
// able to test against that


public class ElevatorController {
	ArrayList<Request> requests;
	Thread inputProcessor, leftThread, rightThread;
	Elevator left, right;
	// two elevators present in  system

	public static void main(String[] args) {
		ElevatorController control = new ElevatorController();
		for (int i = 0; i < 5; i++) {
			Request req;
			if (i < 4) {
				req = new Request(i, Elevator.Direction.UP, Request.Status.PICKUP);
			}
			else {
				req = new Request(i, Elevator.Direction.DOWN, Request.Status.PICKUP);
			}
			control.addRequest(req);
		}
		Request req = new Request(2, Elevator.Direction.UP, Request.Status.DROPOFF);
		try {
			control.addDropRequest(req);
		}
		catch (Exception e) {
			
		}
	}
	
	
	public ElevatorController() {
		// takes in all requests into treeset then delegates them to individual elevators
		super();
		System.out.println("ElevatorController initialized");
		requests = new ArrayList<Request>();
		inputProcessor = new Thread(new InputProcessor(), "Input Processor");
		left = new Elevator();
		right = new Elevator();
		leftThread = new Thread(new Runnable() {

			@Override
			public void run() {
				left.idle();
			}
			
		});
		rightThread = new Thread(new Runnable() {

			@Override
			public void run() {
				right.idle();
			}
			
		});
		System.out.println("Input processing thread initialized");
		leftThread.start();
		rightThread.start();
		inputProcessor.start();
	}

	/** takes in up/down floor requests (outside elevator) from UI class 
	 * and later delegates them to individual elevators based on their 
	 * position and curr direction in allocRequest
	 */
	public synchronized void addRequest(Request req) {
		requests.add(req);
		
	}
	
	/** takes in pickup requests (inside elevator) from UI class
	 * and delegates them to elevator given in request object param,
	 * not allocated based on allocRequest algorithm
	 */
	public synchronized void addDropRequest(Request req) throws Exception {
		if (req.toDropOff != null) {
			req.toDropOff.addFloor(req);
		}
		else {
			throw new Exception("pickup request given to dropoff add method");
		}
	}
	
	
	// still needs to be finished
	public synchronized int allocRequest(Request req) {
		System.out.println("next message should be request allocation");
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
		
		if (leftdir == Elevator.Direction.IDLE 
				&& rightdir == Elevator.Direction.IDLE) {
			// if both idle, doesnt matter, give to left
			left.addFloor(req);
			System.out.println("request given to idle elevator " + left.elevatorid);
		}
		
		else if (leftdir == req.dir) {
			// if left elevator's direction matches the requests direction
			if (rightdir != req.dir) {
				if ((req.dir == Elevator.Direction.UP && leftLevel <= req.floor)
						|| req.dir == Elevator.Direction.DOWN && leftLevel >= req.floor) {
					// if request is in the left elevator's path, not behind it, 
					// and right elevator is going opposite way, then add to left
					left.addFloor(req);
					System.out.println("request give to elevator " + left.elevatorid + "since in left up path");
				}
				else {
					// if request is behind left elevator on the elevator's path upward, determine which
					// elevator to add to based on the closeness of each elevator's eventual destination floor
					if (left.currentRequest.floor == 0) {
						return 0;
					}
				}
			}
			else {
				
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
		System.out.println("ElevatorController started inputprocessor thread");
		while(true) {
			while (!requests.isEmpty()) {
				System.out.println("calling allocation of next request");
				int errChck = allocRequest(requests.remove(0));
				// take care of error checking potentially
			}
		}
	}

}
}
	


