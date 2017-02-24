import java.util.ArrayList;


// to do : create code for command line testing functionality of system or get gui working to be
// able to test against that


public class ElevatorController {
	public volatile ArrayList<Request> requests;
	Thread inputProcessor, leftThread, rightThread;
	Elevator left, right;
	long leftpid, rightpid;
	
	
	// two elevators present in  system
/**
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
	*/
	
	public ElevatorController() {
		// takes in all requests into treeset then delegates them to individual elevators
		super();
		System.out.println("ElevatorController initialized");
		requests = new ArrayList<Request>();
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
		inputProcessor = new Thread(new InputProcessor());
		System.out.println("Input processing thread initialized");
		leftThread.start();
		rightThread.start();
		leftpid = leftThread.getId();
		rightpid = rightThread.getId();
		// processids to discern between thread processes
		inputProcessor.start();
	}

	/** takes in up/down floor requests (outside elevator) from UI class 
	 * and later delegates them to individual elevators based on their 
	 * position and curr direction in allocRequest
	 */
	public synchronized void addRequest(Request req) {
		System.out.println("request being added to elevatorcontroller arraylist");
		requests.add(req);
		
	}
	
	/** takes in pickup requests (inside elevator) from UI class
	 * and delegates them to elevator given in request object param,
	 * not allocated based on allocRequest algorithm
	 */
	public synchronized void addDropRequest(Request req) {
		if (req.toDropOff != null) {
			req.toDropOff.addFloor(req);
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
			int leftDifference = Math.max(req.floor, leftLevel) - Math.min(req.floor, leftLevel);
			int rightDifference = Math.max(req.floor, rightLevel) - Math.min(req.floor, rightLevel);
			if (leftDifference < rightDifference){
				// finds difference between arg request's floor and elevator's eventual destination floor, gives
				// to the one that has smaller difference (closer)
				left.addFloor(req);
				System.out.println("request give to elevator " + left.elevatorid + "since idle and closer");
			}
			else if (leftDifference > rightDifference){
				right.addFloor(req);
				System.out.println("request give to elevator " + right.elevatorid + "since idle and closer");
			}
			else {
				if (leftLevel == 1) {
					right.addFloor(req);
				}
				else if (rightLevel == 1) {
					left.addFloor(req);
				}
				else {
					if (left.floorReq.size() > right.floorReq.size()) {
						right.addFloor(req);
					}
					else {
						left.addFloor(req);
					}
				}
			}
		}
		
		else if (leftdir == req.dir) {
			// if left elevator's direction matches the requests direction
			if (rightdir != req.dir) {
				if ((req.dir == Elevator.Direction.UP && leftLevel <= req.floor)
						|| (req.dir == Elevator.Direction.DOWN && leftLevel >= req.floor)) {
					// if request is in the left elevator's path, not behind it, 
					// and right elevator is going opposite way, then add to left
					left.addFloor(req);
					System.out.println("request give to elevator " + left.elevatorid + "since in left path");
				}
				else {
					// if request is behind left elevator on the elevator's path, determine which
					// elevator to add to based on the closeness of each elevator's eventual destination floor
					if ((Math.max(req.floor, left.destinationLevel) - Math.min(req.floor, left.destinationLevel)) <
							(Math.max(req.floor, right.destinationLevel) - Math.min(req.floor, right.destinationLevel))){
						// finds difference between arg request's floor and elevator's eventual destination floor, gives
						// to the one that has smaller difference (closer)
						left.addFloor(req);
						System.out.println("request give to elevator " + left.elevatorid + "since closer destination"
								+ " despite being already past request floor");
					}
					else {
						right.addFloor(req);
						System.out.println("request give to elevator " + right.elevatorid + "since closer destination"
								+ " despite being in opposite direction");
					}
				}
			}
			else {
				if ((req.dir == Elevator.Direction.UP && leftLevel <= req.floor && rightLevel <= leftLevel)
						|| (req.dir == Elevator.Direction.DOWN && leftLevel >= req.floor 
						&& rightLevel >= leftLevel)) {
					left.addFloor(req);
					System.out.println("request given to idle elevator " + left.elevatorid +" because closer");
				}
				else if ((req.dir == Elevator.Direction.UP && rightLevel <= req.floor && leftLevel <= rightLevel)
						|| (req.dir == Elevator.Direction.DOWN && rightLevel >= req.floor 
						&& leftLevel >= rightLevel)) {
					right.addFloor(req);
					System.out.println("request given to idle elevator " + right.elevatorid +" because closer");
				}
				else {
					if ((Math.max(req.floor, left.destinationLevel) - Math.min(req.floor, left.destinationLevel)) <
							(Math.max(req.floor, right.destinationLevel) - Math.min(req.floor, right.destinationLevel))){
						// finds difference between arg request's floor and elevator's eventual destination floor, gives
						// to the one that has smaller difference (closer)
						left.addFloor(req);
						System.out.println("request give to elevator " + left.elevatorid + "since closer destination"
								+ " despite both past request floor");
					}
					else {
						right.addFloor(req);
						System.out.println("request give to elevator " + right.elevatorid + "since closer destination"
								+ " despite both past request floor");
					}
				}
			}
		}
		else if (rightdir == req.dir) {
			// right elevator's direction matches the request's direction
			if ((req.dir == Elevator.Direction.UP && rightLevel <= req.floor)
					|| req.dir == Elevator.Direction.DOWN && rightLevel >= req.floor) {
				// if request is in the right elevator's path, not behind it, 
				// and left elevator is going opposite way, then add to right
				right.addFloor(req);
				System.out.println("request give to elevator " + right.elevatorid + "since in right path");
			}
			else {
				// if request is behind right elevator on the elevator's path, determine which
				// elevator to add to based on the closeness of each elevator's eventual destination floor
				if ((Math.max(req.floor, left.destinationLevel) - Math.min(req.floor, left.destinationLevel)) <
						(Math.max(req.floor, right.destinationLevel) - Math.min(req.floor, right.destinationLevel))){
					// finds difference between arg request's floor and elevator's eventual destination floor, gives
					// to the one that has smaller difference (closer)
					left.addFloor(req);
					System.out.println("request give to elevator " + left.elevatorid + "since closer destination"
							+ " despite being in opposite direction");
				}
				else {
					right.addFloor(req);
					System.out.println("request give to elevator " + right.elevatorid + "since closer destination"
							+ " despite being already past request floor");
				}
			}
		}
		else if (rightdir == Elevator.Direction.IDLE) {
			// since request is not in path of either elevator, if one is idle, allocate to that elev
			right.addFloor(req);
		}
		else if (leftdir == Elevator.Direction.IDLE) {
			// since request is not in path of either elevator, if one is idle, allocate to that elev
			left.addFloor(req);
		}
		else if (leftdir != req.dir && rightdir != req.dir) {
			// if neither are going in the direction of the request, use elevator with closest destination
			// floor of current request
			if ((Math.max(req.floor, left.destinationLevel) - Math.min(req.floor, left.destinationLevel)) <
					(Math.max(req.floor, right.destinationLevel) - Math.min(req.floor, right.destinationLevel))){
				// finds difference between arg request's floor and elevator's eventual destination floor, gives
				// to the one that has smaller difference (closer)
				left.addFloor(req);
				System.out.println("both wrong direct. but request give to elevator " 
				+ left.elevatorid + "since closer destination");
			}
			else {
				right.addFloor(req);
				System.out.println("both wrong direct. but request give to elevator " 
				+ right.elevatorid + "since closer destination");
			}
		}
		else {
			// if somehow didn't meet any of the cases, return 0 to indicate error
			return 0;
		}
		// successful allocation
		return 1;
	}
	
	
	public class InputProcessor implements Runnable {

		@Override
		public void run() {
			// infinitely checks if any requests have been made 
			// and allocates them to an elevator if so
			System.out.println("ElevatorController started inputprocessor thread");
			while(true) {
				if (!(requests.isEmpty())) {
					System.out.println("calling allocation of next request");
					int errChck = UI.control.allocRequest(requests.remove(0));
					// take care of error checking potentially
					
					if (errChck == 0) {
						try {
							throwError();
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		private void throwError() throws Exception {
			throw new Exception("Failed to allocate request");
		}

	}
}
	


