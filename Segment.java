// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** Segment
// *****************************************************************************
// *****************************************************************************

import java.util.*;

public class Segment extends Object {
    
    private int row;
    private int col;
    private int direction;
    private int maxCapacity;
    private int minTimeToTravelSegment;
    private boolean isEntrance;
    private boolean isExit;
    private LinkedList<Car> queue;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Segment () {
        this.maxCapacity = 1;
        this.minTimeToTravelSegment = 0;
        this.queue = new LinkedList<Car>();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Segment (int row, int col, int direction, boolean isEntrance,
                    boolean isExit, int maxCapacity,
                    int minTimeToTravelSegment) {
        this();
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.isEntrance = isEntrance;
        this.isExit = isExit;
        this.maxCapacity = maxCapacity;
        this.minTimeToTravelSegment = minTimeToTravelSegment;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Let car enter segment
    ///////////////////////////////////////////////////////////////////////
    public boolean addCar(Car car) {
        if (this.hasSpace()) {
            car.moveTo(this.row, this.col, this.direction);
            car.setTimeToTraverse(this.minTimeToTravelSegment);
            this.queue.add(car);
            return true;
        }
        DebugOutput.print("   "
                          + car
                          + " can not enter "
                          + this
                          + " (no space)");
        return false;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get front most car in segment queue
    ///////////////////////////////////////////////////////////////////////
    public Car getFirstCar() {
        return (Car)this.queue.peek();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get front most car in segment queue and remove it from queue
    ///////////////////////////////////////////////////////////////////////
    public Car removeFirstCar() {
        return (Car)this.queue.removeFirst();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Is queue empty?
    ///////////////////////////////////////////////////////////////////////
    public boolean isEmpty() {
        return (this.getFirstCar() == null);
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Is car waiting?
    ///////////////////////////////////////////////////////////////////////
    public boolean isCarWaiting() {
        Car car = this.getFirstCar();
        return (car != null && car.traversed()) ;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Is car waiting and requiring green to enter intersection ?
    ///////////////////////////////////////////////////////////////////////
    public boolean isCarWaitingForGreen() {
        Car car = this.getFirstCar();
        return (car != null && car.traversed() && !car.turnsRightNext()) ;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Has segment space for additonal car?
    ///////////////////////////////////////////////////////////////////////
    public boolean hasSpace() {
        if (this.isEntrance || this.isExit)
          return true;
        return this.queue.size() < this.maxCapacity;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get direction of segment
    ///////////////////////////////////////////////////////////////////////
    public int getDirection() {
        return this.direction;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Is segment an exit segment?
    ///////////////////////////////////////////////////////////////////////
    public boolean isExit() {
        return this.isExit;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// String representation of segment
    ///////////////////////////////////////////////////////////////////////
    public String toString() {
        return "Segment "
                + "col: "
                + this.col
                + "/"
                + "row: "
                + this.row
                + "/"
                + Direction.toString(this.direction);
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// String representation of segment status
    ///////////////////////////////////////////////////////////////////////
    public String toStringStatus() {
        Car firstCar = this.getFirstCar();
        if (firstCar == null) {
            return "Segment is empty.";
        } else {
            return "Holds " + firstCar.toString();
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Equals method
    ///////////////////////////////////////////////////////////////////////
    public boolean equals(int row, int col, int direction) {
        return this.col == col
            && this.row == row
            && this.direction == direction;
    }
}
