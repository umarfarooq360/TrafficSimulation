// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** Car
// *****************************************************************************
// *****************************************************************************

import java.util.*;

public class Car extends Object {
    
    private boolean marked;
    private int idNumber;
    private int row;
    private int col;
    private int directionCarIsHeaded;
    private int entranceTime;
    private int exitTime;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Car () {
        
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Car (int idNumber, boolean marked,
                int row, int col, int directionCarIsHeaded) {
        this.idNumber = idNumber;
        this.marked = marked;
        this.row = row;
        this.col = col;
        this.directionCarIsHeaded = directionCarIsHeaded;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Get current row position of car
    ///////////////////////////////////////////////////////////////////////
    public int getRow() {
        return this.row;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Get current column position of car
    ///////////////////////////////////////////////////////////////////////
    public int getCol() {
        return this.col;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Get current direction the car is heading
    ///////////////////////////////////////////////////////////////////////
    public int getDirectionCarIsHeaded() {
        return this.directionCarIsHeaded;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// String representation of car
    ///////////////////////////////////////////////////////////////////////
    public String toString() {
        return "Car " + this.idNumber;
    }

}
