// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** Intersection
// *****************************************************************************
// *****************************************************************************

import java.util.*;

public class Intersection extends Object {
    
    private int row;
    private int col;
    private Segment inSouthSegment, inEastSegment,
                    inNorthSegment, inWestSegment;
    private Segment outSouthSegment, outEastSegment,
                    outNorthSegment, outWestSegment;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Intersection () {
        
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Intersection (int row, int col) {
        this();
        this.row = row;
        this.col = col;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Intersection (int row, int col, Segment[] segments) {
        this(row, col);
        if (segments.length == 8) {
            this.inSouthSegment = segments[0];
            this.inEastSegment = segments[1];
            this.inNorthSegment = segments[2];
            this.inWestSegment = segments[3];
            this.outSouthSegment = segments[4];
            this.outEastSegment = segments[5];
            this.outNorthSegment = segments[6];
            this.outWestSegment = segments[7];
        }
    }
 
    ///////////////////////////////////////////////////////////////////////
    /////// Get incoming southward segment
    ///////////////////////////////////////////////////////////////////////
    public Segment getInSouthSegment() {
        return this.inSouthSegment;
    }
 
    ///////////////////////////////////////////////////////////////////////
    /////// Get incoming eastward segment
    ///////////////////////////////////////////////////////////////////////
    public Segment getInEastSegment() {
        return this.inEastSegment;
    }
   
    ///////////////////////////////////////////////////////////////////////
    /////// Get incoming northward segment
    ///////////////////////////////////////////////////////////////////////
    public Segment getInNorthSegment() {
        return this.inNorthSegment;
    }
   
    ///////////////////////////////////////////////////////////////////////
    /////// Get incoming westward segment
    ///////////////////////////////////////////////////////////////////////
    public Segment getInWestSegment() {
        return this.inWestSegment;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get outgoing southward segment
    ///////////////////////////////////////////////////////////////////////
    public Segment getOutSouthSegment() {
        return this.outSouthSegment;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get outgoing eastward segment
    ///////////////////////////////////////////////////////////////////////
    public Segment getOutEastSegment() {
        return this.outEastSegment;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get outgoing northward segment
    ///////////////////////////////////////////////////////////////////////
    public Segment getOutNorthSegment() {
        return this.outNorthSegment;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get outgoing westward segment
    ///////////////////////////////////////////////////////////////////////
    public Segment getOutWestSegment() {
        return this.outWestSegment;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Do unit of work
    ///////////////////////////////////////////////////////////////////////
    public void doUnitOfWork () {
        DebugOutput.print("" + this);
        DebugOutput.print("Incoming Southward: "
                          + this.inSouthSegment.toStringStatus());
        DebugOutput.print("Incoming Eastward: "
                          + this.inEastSegment.toStringStatus());
        DebugOutput.print("Incoming Northward: "
                          + this.inNorthSegment.toStringStatus());
        DebugOutput.print("Incoming Westward: "
                          + this.inWestSegment.toStringStatus());
        DebugOutput.print("Outgoing Southward: "
                          + this.outSouthSegment.toStringStatus());
        DebugOutput.print("Outgoing Eastward: "
                          + this.outEastSegment.toStringStatus());
        DebugOutput.print("Outgoing Northward: "
                          + this.outNorthSegment.toStringStatus());
        DebugOutput.print("Outgoing Westward: "
                          + this.outWestSegment.toStringStatus());
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// String representation of intersection
    ///////////////////////////////////////////////////////////////////////
    public String toString() {
        return "Intersection "
        + "col: "
        + this.col
        + "/"
        + "row: "
        + this.row;
    }
}
