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
    private int timer;

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
        this.timer = 0;
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
    /////// Get time left before traversed segment/intersection
    ///////////////////////////////////////////////////////////////////////
    public int getTimer() {
        return this.timer;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Set time to traverse segment/intersection
    ///////////////////////////////////////////////////////////////////////
    public void setTimeToTraverse(int timeToTraverse) {
        this.timer = timeToTraverse;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Did car traverse segment/intersection
    ///////////////////////////////////////////////////////////////////////
    public boolean traversed() {
        return this.timer == 0;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Decrease timer by 1
    ///////////////////////////////////////////////////////////////////////
    public void updateTimer() {
        if (this.timer > 0) {
            this.timer--;
        }
        DebugOutput.print("" + this
                          + " travels in segment/intersection"
                          + " in direction "
                          + Direction.toString(this.direction));
        DebugOutput.print("  for at least "
                          + this.timer
                          + " more time unit(s).");
                          
    }

    ///////////////////////////////////////////////////////////////////////
    /////// String representation of car
    ///////////////////////////////////////////////////////////////////////
    public String toString() {
        return "car#" + this.idNumber;
    }

}
