package hu.bme.mit.spaceship;

import java.security.NoSuchAlgorithmException;

/**
* A simple spaceship with two proton torpedo stores and four lasers
*/
public class GT4500 implements SpaceShip {

  private TorpedoStore primaryTorpedoStore;
  private TorpedoStore secondaryTorpedoStore;

  private boolean wasPrimaryFiredLast = false;

  public GT4500() {
    this.primaryTorpedoStore = new TorpedoStore(10);
    this.secondaryTorpedoStore = new TorpedoStore(10);
  }

  public boolean fireLaser(FiringMode firingMode) {
    // TODO not implemented yet
    return false;
  }

  /**
  * Tries to fire the torpedo stores of the ship.
  *
  * @param firingMode how many torpedo bays to fire
  * 	SINGLE: fires only one of the bays.
  * 			- For the first time the primary store is fired.
  * 			- To give some cooling time to the torpedo stores, torpedo stores are fired alternating.
  * 			- But if the store next in line is empty, the ship tries to fire the other store.
  * 			- If the fired store reports a failure, the ship does not try to fire the other one.
  * 	ALL:	tries to fire both of the torpedo stores.
  *
  * @return whether at least one torpedo was fired successfully
   * @throws NoSuchAlgorithmException
  */
  @Override
  public boolean fireTorpedo(FiringMode firingMode) {
    
    boolean firingSuccess = false;

    if (firingMode == FiringMode.SINGLE) {
      //case SINGLE:
        if (wasPrimaryFiredLast) {
          // try to fire the secondary first
          if (! secondaryTorpedoStore.isEmpty()) {
            try {
              firingSuccess = secondaryTorpedoStore.fire(1);
            } catch (NoSuchAlgorithmException e) {
              // TODO Auto-generated catch block
              //e.printStackTrace();
            }
            wasPrimaryFiredLast = false;
          }
          else {
            // although primary was fired last time, but the secondary is empty
            // thus try to fire primary again
            if (! primaryTorpedoStore.isEmpty()) {
              try {
                firingSuccess = primaryTorpedoStore.fire(1);
              } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
              }
              wasPrimaryFiredLast = true;
            }

            // if both of the stores are empty, nothing can be done, return failure
          }
        }
        else {
          // try to fire the primary first
          if (! primaryTorpedoStore.isEmpty()) {
            try {
              firingSuccess = primaryTorpedoStore.fire(1);
            } catch (NoSuchAlgorithmException e) {
              // TODO Auto-generated catch block
             // e.printStackTrace();
            }
            wasPrimaryFiredLast = true;
          }
          else {
            // although secondary was fired last time, but primary is empty
            // thus try to fire secondary again
            if (! secondaryTorpedoStore.isEmpty()) {
              try {
                firingSuccess = secondaryTorpedoStore.fire(1);
              } catch (NoSuchAlgorithmException e) {
                // TODO Auto-generated catch block
               // e.printStackTrace();
              }
              wasPrimaryFiredLast = false;
            }

            // if both of the stores are empty, nothing can be done, return failure
          }
        }
        //break;
      }
      else{
      //case ALL:
        //System.out.println("A+B conflict solved");
        // try to fire both of the torpedo stores
        //TODO implement feature
        if (! primaryTorpedoStore.isEmpty() && ! secondaryTorpedoStore.isEmpty())
        {
          try {
            firingSuccess = primaryTorpedoStore.fire(1);
            firingSuccess = secondaryTorpedoStore.fire(1);
          } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
          }
          
        }

        //break;
    }
  

    return firingSuccess;
  
  }

}
