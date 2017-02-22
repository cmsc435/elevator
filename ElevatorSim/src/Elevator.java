import java.util.ArrayList;
import java.util.TreeSet;

public class Elevator {
	public TreeSet requests;
	public int currentLevel; 
	public int destinationLevel;
	public int status; //-1 going down, 0 not doing anything, 1 going up
	public ArrayList<Integer> priorityFloors = new ArrayList<Integer>();
	
	public enum Sector {
		BOTTOM, TOP
	}
	
	
	// normal instantiation of elevator without prioritization
	public Elevator() {
		currentLevel = 0; //should be initialized if we figure out what the default level is
		status = 0;
	}
	
	
	// instantiation of elevator with prioritization  of top 2 or bottom 2 floors based on arg
	public Elevator(Sector sect) {
		if (sect == Sector.BOTTOM) {
			priorityFloors.add(1);
			priorityFloors.add(2);
		}
		else {
			priorityFloors.add(3);
			priorityFloors.add(4);
		}
	}

	public void selectLevel(int destination) {
		if (destination == currentLevel) {
			//do nothing
		} else {
			destinationLevel = destination;
			//move elevator to the requested level

			//going down
			if ( (currentLevel > destination) && (status == 0) ) {


				//going up
			} else if ((currentLevel < destination) && (status == 0)) {


			}

			//TODO: check status not == 0

			//set the currentLevel
			currentLevel = destination;
		}
	}

	public int getLevel() {
		return currentLevel;
	}

	public int getStatus() {
		return status;
	}

	public int getDestination() {
		return destinationLevel;
	}                

}
