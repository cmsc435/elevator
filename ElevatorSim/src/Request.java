
public class Request {
	/** each request has a floor number from where request was made, direction to where
	 * request is meant to go, and status of whether user is being picked up or dropped off
	 *
	 */
	
	/** indicates whether request is to go down or up
	 */
	
	
	public enum Status {
		PICKUP, DROPOFF
	}
	/** indicates whether request is from outside elevator, start floor where user
	 * is picked up, or whether it is from inside elevator, end floor where user
	 * is dropped off; will be used to prioritize queue of floors for elevator to go to
	 * since getting user to their final destination should be priority before servicing others
	 */
	
	int floor;
	Elevator.Direction dir;
	Status status;
	Elevator toDropOff = null;
	
	/** constructor for pickups, not specific to an elevator, elevator that services will
	 * be determined by ElevatorController */
	public Request(int floor, Elevator.Direction dir, Status stat) {
		this.floor = floor;
		this.dir = dir;
		this.status = stat;
		}
	
	/** constructor for dropoffs, specific to an elevator, elevator that services is predetermined
	 * from input and ElevatorController will simply pass it on to elevator in arg
	 */
	public Request(int floor, Status stat, Elevator.Direction dir, Elevator elevator) {
		this.floor = floor;
		this.status = stat;
		this.dir = dir;
		this.toDropOff = elevator;
	}
	
	
}
