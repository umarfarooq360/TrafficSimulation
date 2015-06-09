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
        DebugOutput.print("The intersection at col "
                          + this.col
                          + " and row "
                          + this.row
                          + " reports");
        
        this.processIncomingSegment(this.inSouthSegment);
        this.processIncomingSegment(this.inEastSegment);
        this.processIncomingSegment(this.inNorthSegment);
        this.processIncomingSegment(this.inWestSegment);
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Process incoming segment
    ///////////////////////////////////////////////////////////////////////
    private void processIncomingSegment(Segment segment) {
        if (segment.isCarWaiting()) {
            DebugOutput.print("  incoming segment having direction "
                              + Direction.toString(segment.getDirection())
                              + " is nonempty and");
            
            Car car = segment.processFirstCar();
            int nextDirection = car.getNextDirection();
            DebugOutput.print("   "
                              + car
                              + " is removed and placed into outgoing segment"
                              + " having direction "
                              + Direction.toString(nextDirection));
            
            if (nextDirection == Direction.SOUTHWARD)
                this.outSouthSegment.addCar(car);
            if (nextDirection == Direction.EASTWARD)
                this.outEastSegment.addCar(car);
            if (nextDirection == Direction.NORTHWARD)
                this.outNorthSegment.addCar(car);
            if (nextDirection == Direction.WESTWARD)
                this.outWestSegment.addCar(car);
        }
        else {
            DebugOutput.print("  incoming segment having direction "
                              + Direction.toString(segment.getDirection())
                              + " is empty");
        }
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
