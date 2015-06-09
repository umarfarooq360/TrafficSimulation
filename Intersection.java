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
    /////// Do unit of work
    ///////////////////////////////////////////////////////////////////////
    public void doUnitOfWork () {
        DebugOutput.print("" + this);
        DebugOutput.print("Incoming Southward: "  + this.inSouthSegment);
        DebugOutput.print("Incoming Eastward: "   + this.inEastSegment);
        DebugOutput.print("Incoming Northward: "  + this.inNorthSegment);
        DebugOutput.print("Incoming Westward: "   + this.inWestSegment);
        DebugOutput.print("Outgoing Southward: "  + this.outSouthSegment);
        DebugOutput.print("Outgoing Eastward: "   + this.outEastSegment);
        DebugOutput.print("Outgoing Northward: "  + this.outNorthSegment);
        DebugOutput.print("Outgoing Westward: "   + this.outWestSegment);
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
