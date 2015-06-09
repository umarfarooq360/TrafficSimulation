// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** Direction
// *****************************************************************************
// *****************************************************************************


import java.util.*;

public class Direction extends Object {

    public static final int SOUTHWARD  = 0;
    public static final int EASTWARD   = 1;
    public static final int NORTHWARD  = 2;
    public static final int WESTWARD   = 3;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Direction () {
        
    }
  
    ///////////////////////////////////////////////////////////////////////
    /////// String representation of directions
    ///////////////////////////////////////////////////////////////////////
    public static String toString(int direction) {
        if (direction == SOUTHWARD) {
            return "SOUTHWARD";
        }
        if (direction == EASTWARD) {
            return "EASTWARD";
        }
        if (direction == NORTHWARD) {
            return "NORTHWARD";
        }
        if (direction == WESTWARD) {
            return "WESTWARD";
        }
        return "";
    }
}

