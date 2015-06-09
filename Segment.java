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

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Segment () {
        
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
    /////// Equals method
    ///////////////////////////////////////////////////////////////////////
    public boolean equals(int col, int row, int direction) {
        return this.col == col
            && this.row == row
            && this.direction == direction;
    }
}
