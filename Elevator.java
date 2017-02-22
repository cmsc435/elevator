public class Elevator {
  public int currentLevel; 
  public int destinationLevel;
  public int status; //-1 going down, 0 not doing anything, 1 going up 

  public Elevator() {
    currentLevel = 0; //should be initialized if we figure out what the default level is
    status = 0;
  }
  
  public void selectLevel(int selected) {

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
