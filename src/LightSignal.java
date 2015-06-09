// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** LightSignal
// *****************************************************************************
// *****************************************************************************


import java.util.*;

public class LightSignal extends Object {

    public static final int GREEN_NS = 0;
    public static final int ALL_RED_AFTER_GREEN_NS = 1;
    public static final int GREEN_EW = 2;
    public static final int ALL_RED_AFTER_GREEN_EW = 3;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public LightSignal () {
    }
  
    ///////////////////////////////////////////////////////////////////////
    /////// String representation of traffic light signal
    ///////////////////////////////////////////////////////////////////////
    public static String toString(int status) {
        if (status == GREEN_NS) {
            return "GREEN_NS";
        }
        if (status == ALL_RED_AFTER_GREEN_NS) {
            return "ALL_RED_AFTER_GREEN_NS";
        }
        if (status == GREEN_EW) {
            return "GREEN_EW";
        }
        if (status == ALL_RED_AFTER_GREEN_EW) {
            return "ALL_RED_AFTER_GREEN_EW";
        }
        return "";
    }
}
