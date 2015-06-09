// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** TrafficModel
// *****************************************************************************
// *****************************************************************************


import java.util.*;

public class TrafficModel extends Object {

    private ArrayList<Car> cars;
    private int numOfCars;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficModel () {
        this.cars = new ArrayList<Car>();
        this.numOfCars = 0;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficModel (int numOfCars) {
        this();
        this.numOfCars = numOfCars;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Run simulation
    ///////////////////////////////////////////////////////////////////////
    public void doTest() {
        
        for (int i=0; i < numOfCars; i++) {
            this.cars.add(i, new Car(i+1, false, 1, 0, Direction.NORTHWARD));
        }
        
        for (int i=0; i < this.cars.size(); i++) {
            DebugOutput.print("" + this.cars.get(i));
        }
    }
}
