public class Elevator {
  public int currentLevel; 
  public int destinationLevel;

  public Elevator() {
    currentLevel = 0; //should be initialized if we figure out what the default level is
  }
  
  public void selectLevel(int destination) {
    //move elevator to the requested level
    
    //set the currentLevel
    currentLevel = destination;
  }
  
  public int getLevel() {
    return currentLevel;
  }

}
