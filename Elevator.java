public class Elevator {
  public int currentLevel; 
  public int destinationLevel;
  public int status; //-1 going down, 0 not doing anything, 1 going up 

  public Elevator() {
    currentLevel = 0; //should be initialized if we figure out what the default level is
    status = 0;
  }
  
  public void selectLevel(int selected) {
    if (selected == currentLevel) {
      //do nothing
    } else {
      /* selected level is before final level */
      if (destinationLevel > selected) {
        status = 1;
        goTo(selected);
        goTo(destinationLevel);
        
      /* selected level is after current destination */  
      } else if (selected > destinationLevel) {
        goTo(destinationLevel);
        goTo(selected);
     
      } else if (selected == destinationLevel) {
        goTo(selected);
      }
      
      destinationLevel = selected;
      currentLevel = selected;
    }
  }
  
  public void goTo(int destination) {
    /* elevator not doing anything */
    if (status == 0) {
      
    /* elevator was going up */  
    } else if (status > 0) {
     
    /* elevator was going down */  
    } else {
      
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
