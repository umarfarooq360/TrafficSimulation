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
    public Car (int idNumber, boolean marked) {
        this.idNumber = idNumber;
        this.marked = marked;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Car (int idNumber, boolean marked,
                int row, int col, int directionCarIsHeaded) {
        this(idNumber, marked);
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
    /////// Set current row position of car
    ///////////////////////////////////////////////////////////////////////
    public void setRow(int row) {
        this.row = row;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Get current column position of car
    ///////////////////////////////////////////////////////////////////////
    public int getCol() {
        return this.col;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Set current column position of car
    ///////////////////////////////////////////////////////////////////////
    public void setCol(int col) {
        this.col = col;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Get current direction the car is heading
    ///////////////////////////////////////////////////////////////////////
    public int getDirectionCarIsHeaded() {
        return this.directionCarIsHeaded;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Set current direction the car is heading
    ///////////////////////////////////////////////////////////////////////
    public void setDirectionCarIsHeaded(int direction) {
        this.directionCarIsHeaded = direction;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// String representation of car
    ///////////////////////////////////////////////////////////////////////
    public String toString() {
        return "Car " + this.idNumber;
    }

}
