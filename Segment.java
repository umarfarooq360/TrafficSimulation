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
    private boolean isExit;
    private LinkedList<Car> queue;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Segment () {
        this.maxCapacity = 3;
        this.queue = new LinkedList<Car>();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Segment (int row, int col, int direction, boolean isExit) {
        this();
        this.row = row;
        this.col = col;
        this.direction = direction;
        this.isExit = isExit;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Let car enter segment
    ///////////////////////////////////////////////////////////////////////
    public boolean addCar(Car car) {
        if (this.queue.size() < this.maxCapacity) {
            car.moveTo(this.row, this.col, this.direction);
            this.queue.add(car);
            return true;
        }
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
        return (Car)this.queue.getFirst();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Is queue empty?
    ///////////////////////////////////////////////////////////////////////
    public boolean isCarWaiting() {
        return (this.getFirstCar() != null);
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
