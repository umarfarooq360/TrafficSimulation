// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** TrafficLight
// *****************************************************************************
// *****************************************************************************


import java.util.*;

public class TrafficLight extends Object {
    
    private int minTimeLightGreen;
    private int minTimeToChangeLight;
    private int status;
    private int timer;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficLight () {
        this.minTimeLightGreen = 0;
        this.minTimeToChangeLight = 0;
        this.status = LightSignal.GREEN_NS;
        this.timer = 0;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficLight (int minTimeLightGreen, int minTimeToChangeLight) {
        this();
        this.minTimeLightGreen = minTimeLightGreen;
        this.minTimeToChangeLight = minTimeToChangeLight;
        this.timer = minTimeLightGreen;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Request switch to next status
    ///////////////////////////////////////////////////////////////////////
    public void requestSwitchStatus() {
        if (this.timer <= 0) {
            if (this.status == LightSignal.GREEN_NS) {
                this.status = LightSignal.ALL_RED_AFTER_GREEN_NS;
                this.timer = this.minTimeToChangeLight;
            }
            else if (this.status == LightSignal.ALL_RED_AFTER_GREEN_NS) {
                this.status = LightSignal.GREEN_EW;
                this.timer = this.minTimeLightGreen;
            }
            else if (this.status == LightSignal.GREEN_EW) {
                this.status = LightSignal.ALL_RED_AFTER_GREEN_EW;
                this.timer = this.minTimeToChangeLight;
            }
            else if (this.status == LightSignal.ALL_RED_AFTER_GREEN_EW) {
                this.status = LightSignal.GREEN_NS;
                this.timer = this.minTimeLightGreen;
            }
            DebugOutput.print("  traffic light switched to status "
                              + LightSignal.toString(this.status));
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Is traffic light green in given direction?
    ///////////////////////////////////////////////////////////////////////
    public boolean isGreenInDirection(int direction) {
        if (direction == Direction.SOUTHWARD &&
            this.status == LightSignal.GREEN_NS) {
            return true;
        }
        else if (direction == Direction.EASTWARD &&
                 this.status == LightSignal.GREEN_EW) {
            return true;
        }
        else if (direction == Direction.NORTHWARD &&
                 this.status == LightSignal.GREEN_NS) {
            return true;
        }
        else if (direction == Direction.WESTWARD &&
                 this.status == LightSignal.GREEN_EW) {
            return true;
        }
        return false;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Is traffic light green in any direction?
    ///////////////////////////////////////////////////////////////////////
    public boolean isGreenInAnyDirection() {
        return (this.status == LightSignal.GREEN_NS ||
                this.status == LightSignal.GREEN_EW);
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Decrease timer by 1
    ///////////////////////////////////////////////////////////////////////
    public void updateTimer() {
        if (this.timer > 0) {
            this.timer--;
        }
        
        if (this.status == LightSignal.ALL_RED_AFTER_GREEN_NS ||
            this.status == LightSignal.ALL_RED_AFTER_GREEN_EW) {
            this.requestSwitchStatus();
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get traffic light status
    ///////////////////////////////////////////////////////////////////////
    public int getStatus() {
        return this.status;
    }
  
    ///////////////////////////////////////////////////////////////////////
    /////// String representation of traffic light
    ///////////////////////////////////////////////////////////////////////
    public String toString() {
        return "traffic light in status "
               + LightSignal.toString(this.status)
               + " for at least "
               + this.timer
               + " more time unit(s)";
    }
}

