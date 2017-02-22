public class Building  {
	Elevator one, two;

	public Building() {
		one = new Elevator();
		two = new Elevator();
	}

	/* 
		direction = 1 (going up) or -1 (going down)
		userFloor = floor that the user presses up/down button
	*/
	public void request(int direction, int userFloor) {
		/************user requests to go up************/
		if (direction > 0) {
			/////////////figure out which elevator to use//////
			
			/* both elevators are not in use */
			if (one.getStatus() == 0 && two.getStaus() == 0) {
				/* randomly pick e1 or e2, and use that elevator */
				//TODO randomly select, and change next line of code
				call(one, userFloor);
				
			/* elevator 1 is going up and user's floor is before destination floor*/	
			} else if (one.getStatus() > 0 && one.getDestination() > userFloor) {
				/* use elevator 1, call it to the user's floor */
				call(one, userFloor);
				
			/* elevator 2 is going up and user's floor is before destination floor*/	
			} else if (two.getStatus() > 0 && two.getDestination() > userFloor) {
				/* use elevator 2, call it to the user's floor*/
				call(two, userFloor);
				
			/* elevator 1 not in use */	
			} else if (one.getStatus() == 0) {
				/* use elevator 1 */
				call(one, userFloor);
				
			/* elevator 2 not in use */	
			} else if (two.getStatus() == 0) {
				/* use elevator 2 */
				call(two, userFloor);
				
			} else {
				/* just use elevator 2 because idk what else to do */
				call(two, userFloor);
			}
				
		/************user requests to go down************/
		} else {
			/////////////figure out which elevator to use//////
			
			/* both elevators are not in use */
			if (one.getStatus() == 0 && two.getStaus() == 0) {
				/* randomly pick e1 or e2, and use that elevator */
			
			/* elevator 1 is going down and user's floor is before destination floor*/	
			} else if (one.getStatus() < 0 && one.getDestination() < userFloor) {
				/* use elevator 1 */
				call(one, userFloor);
				
			/* elevator 2 is going down and user's floor is before destination floor*/	
			} else if (two.getStatus() < 0 && two.getDestination() < userFloor) {
				/* use elevator 2 */
				call(two, userFloor);
				
			/* elevator 1 not in use */	
			} else if (one.getStatus() == 0) {
				/* use elevator 1 */
				call(one, userFloor);

			/* elevator 2 not in use */	
			} else if (two.getStatus() == 0) {
				/* use elevator 2 */
				call(two, userFloor);
				
			} else {
				/* just use elevator 2 because idk what else to do */
				call(two, userFloor);

			}		
		}

	}
		
}
