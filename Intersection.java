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
    private int minTimeToChangeSegment;
    private Segment inSouthSegment, inEastSegment,
                    inNorthSegment, inWestSegment;
    private Segment outSouthSegment, outEastSegment,
                    outNorthSegment, outWestSegment;
    private ArrayList<Car> cars;
    private ArrayList<Segment> outSegmentsForCars;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Intersection () {
        this.minTimeToChangeSegment = 0;
        this.cars = new ArrayList<Car>();
        this.outSegmentsForCars = new ArrayList<Segment>();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Intersection (int row, int col, int minTimeToChangeSegment) {
        this();
        this.row = row;
        this.col = col;
        this.minTimeToChangeSegment = minTimeToChangeSegment;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public Intersection (int row, int col, int minTimeToChangeSegment,
                         Segment[] segments) {
        this(row, col, minTimeToChangeSegment);
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
        this.removeCarsFromIntersection();
        
        if (!this.isCarInIntersection()) {
            this.processIncomingSegment(this.inSouthSegment);
            this.processIncomingSegment(this.inEastSegment);
            this.processIncomingSegment(this.inNorthSegment);
            this.processIncomingSegment(this.inWestSegment);
        
            this.processOutgoingSegment(this.outSouthSegment);
            this.processOutgoingSegment(this.outEastSegment);
            this.processOutgoingSegment(this.outNorthSegment);
            this.processOutgoingSegment(this.outWestSegment);
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Process outgoing segment
    ///////////////////////////////////////////////////////////////////////
    private void processOutgoingSegment(Segment segment) {
        if (segment.isCarWaiting()) {
            DebugOutput.print("  outgoing segment having direction "
                              + Direction.toString(segment.getDirection())
                              + " is nonempty");
        }
        else {
            DebugOutput.print("  outgoing segment having direction "
                              + Direction.toString(segment.getDirection())
                              + " is empty");
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Process incoming segment
    ///////////////////////////////////////////////////////////////////////
    private void processIncomingSegment(Segment segment) {
        if (!this.isCarInIntersection()) {
            if (!segment.isEmpty()) {
                DebugOutput.print("  incoming segment having direction "
                                  + Direction.toString(segment.getDirection())
                                  + " is nonempty and");
 
                Car car = segment.getFirstCar();
                DebugOutput.print("   "
                                  + car
                                  + " is closest to the intersection"
                                  + " ("
                                  + car.getTimer()
                                  + " time unit(s) to go)");
                
                if (segment.isCarWaiting()) {
                    int nextDirection = car.getNextDirection();
            
                    if (nextDirection == Direction.SOUTHWARD)
                        this.putCarInIntersection(segment,
                                                  this.outSouthSegment,
                                                  car);
                    if (nextDirection == Direction.EASTWARD)
                        this.putCarInIntersection(segment,
                                                  this.outEastSegment,
                                                  car);
                    if (nextDirection == Direction.NORTHWARD)
                        this.putCarInIntersection(segment,
                                                  this.outNorthSegment,
                                                  car);
                    if (nextDirection == Direction.WESTWARD)
                        this.putCarInIntersection(segment,
                                                  this.outWestSegment,
                                                  car);
                }
            }
            else {
                DebugOutput.print("  incoming segment having direction "
                                  + Direction.toString(segment.getDirection())
                                  + " is empty");
            }
        }
    }
    
    
    ///////////////////////////////////////////////////////////////////////
    /////// Put car into intersection
    ///////////////////////////////////////////////////////////////////////
    private void putCarInIntersection (Segment fromSegment, Segment toSegment,
                                      Car car) {
        if (toSegment.hasSpace()) {
            fromSegment.removeFirstCar();
            this.cars.add(car);
            this.outSegmentsForCars.add(toSegment);
            car.setTimeToTraverse(this.minTimeToChangeSegment);
            
            DebugOutput.print("   "
                              + car
                              + " is removed and placed into intersection");
        }
        else {
            DebugOutput.print("   "
                              + car
                              + " cannot be removed and placed"
                              + " into outgoing segment having direction "
                              + Direction.toString(toSegment.getDirection()));
            DebugOutput.print("   segment has no room for car ");
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Move car from intersection to outgoing segment
    ///////////////////////////////////////////////////////////////////////
    private void removeCar (Segment toSegment, Car car) {
        if (toSegment.addCar(car)) {
            DebugOutput.print("  "
                              + car
                              + " is removed and placed into outgoing segment"
                              + " having direction "
                              + Direction.toString(toSegment.getDirection()));
            if (toSegment.isExit()) {
                DebugOutput.print("  "
                                  + car
                                  + " leaves the grid");
            }
        }
        else {
            DebugOutput.print("  "
                              + car
                              + " cannot be removed and placed"
                              + " into outgoing segment having direction "
                              + Direction.toString(toSegment.getDirection()));
            DebugOutput.print("  segment has no room for car ");
        }
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Remove cars from intersection
    ///////////////////////////////////////////////////////////////////////
    private void removeCarsFromIntersection() {
        for (int i=0; i<this.cars.size(); i++) {
            Car car = this.cars.get(i);
            Segment outSegment = this.outSegmentsForCars.get(i);
            
            DebugOutput.print("  "
                              + car
                              + " is in intersection heading/turning "
                              + Direction.toString(outSegment.getDirection()));
            
            if (car.traversed()) {
                this.removeCar(outSegment, car);
                this.cars.remove(i);
                this.outSegmentsForCars.remove(i);
            }
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Is car in intersection?
    ///////////////////////////////////////////////////////////////////////
    private boolean isCarInIntersection() {
        return !(this.cars.isEmpty());
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
