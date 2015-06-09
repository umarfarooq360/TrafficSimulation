// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** Turn
// *****************************************************************************
// *****************************************************************************


import java.util.*;

public class Turn extends Object {

    public static final int NEVER_TURN     = 0;
    public static final int TURN_RIGHTWARD = 1;
    public static final int TURN_LEFTWARD  = -1;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Turn () {
    }
  
    ///////////////////////////////////////////////////////////////////////
    /////// String representation of turn
    ///////////////////////////////////////////////////////////////////////
    public static String toString(int turn) {
        if (turn == NEVER_TURN) {
            return "never turn";
        }
        if (turn == TURN_RIGHTWARD) {
            return "turn rightward";
        }
        if (turn == TURN_LEFTWARD) {
            return "turn leftward";
        }
        return "";
    }
}

