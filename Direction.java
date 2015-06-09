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
    /////// Compute direction after turn
    ///////////////////////////////////////////////////////////////////////
    public static int getDirectionAfterTurn(int direction, int turn) {
        if (turn == Turn.NEVER_TURN)
            return direction;
        
        if (turn == Turn.TURN_LEFTWARD) {
            if (direction == SOUTHWARD)
                return EASTWARD;
            if (direction == EASTWARD)
                return NORTHWARD;
            if (direction == NORTHWARD)
                return WESTWARD;
            if (direction == WESTWARD)
                return SOUTHWARD;
        }
        
        if (turn == Turn.TURN_RIGHTWARD) {
            if (direction == SOUTHWARD)
                return WESTWARD;
            if (direction == WESTWARD)
                return NORTHWARD;
            if (direction == NORTHWARD)
                return EASTWARD;
            if (direction == EASTWARD)
                return SOUTHWARD;
        }
        return direction;
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

