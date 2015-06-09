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
    private LinkedList<Car> queue;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Segment () {
        this.queue = new LinkedList<Car>();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Segment (int row, int col, int direction) {
        this();
        this.row = row;
        this.col = col;
        this.direction = direction;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Add car to segment queue
    ///////////////////////////////////////////////////////////////////////
    public void addCar (Car car) {
        car.setCol(this.col);
        car.setRow(this.row);
        car.setDirectionCarIsHeaded(this.direction);
        this.queue.add(car);
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get front most car in segment queue
    ///////////////////////////////////////////////////////////////////////
    public Car getFirstCar () {
        return (Car)this.queue.peek();
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
    public boolean equals(int col, int row, int direction) {
        return this.col == col
            && this.row == row
            && this.direction == direction;
    }
}
