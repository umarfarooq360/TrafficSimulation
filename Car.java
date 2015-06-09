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
    private int direction;
    private int blocksBeforeTurn;
    private int turnDirection;
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
    public Car (int idNumber, boolean marked, int blocksBeforeTurn,
                int turnDirection) {
        this(idNumber, marked);
        this.blocksBeforeTurn = blocksBeforeTurn;
        this.turnDirection    = turnDirection;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Car (int idNumber, boolean marked, int blocksBeforeTurn,
                int turnDirection, int row, int col, int direction) {
        this(idNumber, marked, blocksBeforeTurn, turnDirection);
        this.row = row;
        this.col = col;
        this.direction = direction;
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
    public int getDirection() {
        return this.direction;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Update car after moving to new segment
    ///////////////////////////////////////////////////////////////////////
    public void moveTo(int row, int col, int direction) {
        if (!(this.row == row &&
              this.col == col &&
              this.direction == direction)) {
            
            this.row = row;
            this.col = col;
            this.direction = direction;
        
            if (this.blocksBeforeTurn >= 0)
                this.blocksBeforeTurn--;
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get direction the car will be heading in the next block
    ///////////////////////////////////////////////////////////////////////
    public int getNextDirection() {
        if (this.blocksBeforeTurn == 0)
           return Direction.getDirectionAfterTurn(this.direction,
                                                  this.turnDirection);
        return this.direction;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// String representation of car
    ///////////////////////////////////////////////////////////////////////
    public String toString() {
        return "car#" + this.idNumber;
    }

}
