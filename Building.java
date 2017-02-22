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
		if(one.getStatus() < 0 && direction > 0) {
			//use elevator 2
		} else if (two.getStatus()
	}
		
}
