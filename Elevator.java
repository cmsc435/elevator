public class Elevator {
  public int currentLevel; 
  public int destinationLevel;
  public int status; //-1 going down, 0 not doing anything, 1 going up 

  public Elevator() {
    currentLevel = 0; //should be initialized if we figure out what the default level is
  }
  
  public void selectLevel(int destination) {
    if (destination == currentLevel) {
      //do nothing
    } else {
      
      //move elevator to the requested level
      
      //going down
      if ( (currentLevel > destination) && (status == 0) ) {
      
      
      //going up
      } else if ((currentLevel < destination) && (status == 0) {
        
        
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
  
}
