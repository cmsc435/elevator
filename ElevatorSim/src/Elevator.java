import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Elevator {
	
	
	public volatile TreeSet<Request> floorReq;
	public volatile int elevatorid;
	public volatile int currentLevel;
	public volatile Request currentRequest;
	public volatile int destinationLevel;
	public volatile Direction direction;
	public ArrayList<Integer> priorityFloors;
	public RequestComparator comp;
	
	
	
	public enum Sector {
		BOTTOM, TOP
	}
	
	public enum Direction {
		UP, DOWN, IDLE
	}
	
	// normal instantiation of elevator without prioritization
	public Elevator() {
		currentLevel = 0; //should be initialized if we figure out what the default level is
		comp = new RequestComparator();
		floorReq = new TreeSet<Request>(comp);
		/** need to implement comparator to sort requests in treeset based on request status
		 * (dropoff over pickup), direction (same direction over opposite direction), floor
		 * number (closer floors over farther floors)
		 */
		currentRequest = null;
		elevatorid = this.hashCode();
		System.out.println("elevator " + elevatorid + "initialized");
	}
	
	
	// instantiation of elevator with prioritization  of top 2 or bottom 2 floors based on arg
	public Elevator(Sector sect) {
		priorityFloors = new ArrayList<Integer>();
		
		if (sect == Sector.BOTTOM) {
			priorityFloors.add(1);
			priorityFloors.add(2);
		}
		else {
			priorityFloors.add(3);
			priorityFloors.add(4);
		}
	}
	
	public void addFloor(Request req) {
		floorReq.add(req);
	}

	
	public int getLevel() {
		return currentLevel;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getDestination() {
		return destinationLevel;
	}

	/** moves the elevator as long as there are requests to be satisfied,
	 * afterwards goes back to idling 
	 */
	public void move() {
		while (!floorReq.isEmpty()) {
			currentRequest = floorReq.pollFirst();
			direction = currentRequest.dir;
			destinationLevel = currentRequest.floor;
			if (currentLevel < destinationLevel) {
				// current request is above elevator's current level,
				// move elevator up
				goUp();
			}
			else if (currentLevel > destinationLevel) {
				// current request is below elevator's current level,
				// move elevator down
				goDown();
			}
			else {
				// elevator is at request level, request has been satisfied
				System.out.println("elevator " + elevatorid + ": at floor " + currentLevel + " to " 
				+ currentRequest.status.toString() + " request at floor " + destinationLevel);
				// print statement for debugging
				floorReq.remove(currentRequest);
				currentRequest = null;
				
			}
			System.out.println("elevator " + elevatorid + ": currently at " + currentLevel);
		}
		System.out.println("elevator " + elevatorid + ": out of requests. commencing idling");
		idle();
	}
	
	public void idle() {
		while (floorReq.isEmpty()) {
			destinationLevel = 0;
			if (currentLevel != 0) {
				System.out.println("elevator " + elevatorid + ": going down from " + currentLevel
						+ " to idle");
				// debugging print statement
				goDown();
			}
			else {
				//System.out.println("elevator " + elevatorid + ": idling at ground");
				// debugging print statement
				direction = Elevator.Direction.IDLE;
			}
		}
		System.out.println("elevator " + elevatorid + ": received request. stopping idling to service");
		// debugging print statement
		move();
		// floors have been added to the elevator's treeset queue so call move to take
		// care of them
	}
	
	
	private void goUp() {
		/** moves elevator up a floor*/
		System.out.println("elevator " + elevatorid + ":" + " moving up from " 
				+ currentLevel + " to " + destinationLevel);
		// print statement for debugging
		direction = Elevator.Direction.UP;
		currentLevel++;
	}
	
	private void goDown() {
		/** moves elevator down a floor */
		System.out.println("elevator " + elevatorid + ":" + " moving down from " 
				+ currentLevel + " to " + destinationLevel);
		// print statement for debugging
		direction = Elevator.Direction.DOWN;
		currentLevel--;
	}

	
	// review to ensure correctness and case-comprehensiveness
	private class RequestComparator implements Comparator<Request> {
		/** comparator for elevator's treeset of requests to allow for correct movements and
		 * prioritization
		 */
		
		
		/** treeset ordering is in ascending order so to be first, return -1 if arg0 is priority,
		 * 1 if arg1 is priority, 0 if tie
		 */
		@Override
		public int compare(Request arg0, Request arg1) {
			
			if (arg0.status == Request.Status.DROPOFF && arg1.status == Request.Status.PICKUP) {
				// only time when pickup request > dropoff request, pickup between/at curr floor
				// and dest. and in same direction
				if (arg1.dir == direction && ((arg1.floor > arg0.floor && arg1.floor <= currentLevel)  
						|| (arg1.floor < arg0.floor && arg1.floor >= currentLevel))) {
					return 1;
				}
				else {
					return -1;
				}
			}
			
			else if (arg1.status == Request.Status.DROPOFF && arg0.status == Request.Status.PICKUP) {
				// only time when pickup request > dropoff request, pickup between/at curr floor
				// and dest. and in same direction
				if (arg0.dir == direction && ((arg0.floor > arg1.floor && arg0.floor <= currentLevel)  
						|| (arg0.floor < arg1.floor && arg0.floor >= currentLevel))) {
					return 1;
				}
				else {
					return -1;
				}
			}
			
			else if ((arg1.status == Request.Status.DROPOFF && arg0.status == Request.Status.DROPOFF)
					|| (arg0.status == Request.Status.PICKUP && arg1.status == Request.Status.PICKUP)) {
				// when both requests have same status (dropoff or pickup), prioritize one with correct direction, then one
				// with closer floor
				if (arg1.dir == direction && arg0.dir != direction) {
					return 1;
				}
				else if (arg1.dir != direction && arg0.dir == direction) {
					return -1;
				}
				else {
					/** to potentially include : situation where both req are pickups and in the correct direction,
					 * but one req is behind the elevator on its trip but is still closer in floor difference
					 */
					if (direction == Direction.DOWN) {
						if ((currentLevel - arg0.floor) < (currentLevel - arg1.floor)) {
							// direction down and arg0 has closer floor
							return -1;
						}
						else {
							// direction down and arg1 has closer floor
							return 1;
						}
					}
					else {
						if ((currentLevel - arg0.floor) > (currentLevel - arg1.floor)) {
							// direction up and arg0 has closer floor
							return -1;
						}
						else {
							// direction up and arg1 has closer floor
							return 1;
						}
					}
				}
			}
			
			else {
				// should meet one of the criteria above, otherwise settle with tie
				return 0;
			}
		}
		
	}

}
