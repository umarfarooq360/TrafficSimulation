// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** TrafficModel
//
// Subset 8 comments:
// - car tracks the time to go before reaching the intersection rather than the
//   number of time units already spent in the segment/intersection
// - allows already for turns in intersection
// - allows for multiple cars in intersection
//
// *****************************************************************************
// *****************************************************************************


import java.util.*;

public class TrafficModel extends Object {

    private Intersection intersections[][];
    private ArrayList<Segment> segments;
    private ArrayList<Car> cars;
    private int numIntersectionsInOneDirection;
    private int minTimeToTravelSegment;
    private int minTimeToChangeSegment;
    private int simulationLength;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficModel () {
        this.numIntersectionsInOneDirection = 0;
        this.minTimeToTravelSegment = 0;
        this.minTimeToChangeSegment = 0;
        this.simulationLength = 20;
        this.cars = new ArrayList<Car>();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficModel (int numIntersectionsInOneDirection,
                         int minTimeToTravelSegment,
                         int minTimeToChangeSegment) {
        this();
        this.numIntersectionsInOneDirection = numIntersectionsInOneDirection;
        this.minTimeToTravelSegment = minTimeToTravelSegment;
        this.minTimeToChangeSegment = minTimeToChangeSegment;
        
        this.intersections = new Intersection[numIntersectionsInOneDirection]
                                            [numIntersectionsInOneDirection];
        this.segments = new ArrayList<Segment>();
        this.cars = new ArrayList<Car>();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficModel (int numIntersectionsInOneDirection,
                         int minTimeToTravelSegment,
                         int minTimeToChangeSegment,
                         ArrayList<Car> cars) {
        this(numIntersectionsInOneDirection,
             minTimeToTravelSegment,
             minTimeToChangeSegment);
        this.cars = cars;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Do test
    ///////////////////////////////////////////////////////////////////////
    public void doTest() {
        this.createSegments();
        this.createIntersections();
        this.createCars();
        
        for (int time=1; time <= this.simulationLength; time++)
        {
            DebugOutput.print("TIME: " + time);
            
            for (int i=0; i < this.cars.size(); i++) {
                this.cars.get(i).updateTimer();
            }

            for (int row=0; row < numIntersectionsInOneDirection; row++) {
                for (int col=0; col < numIntersectionsInOneDirection; col++) {
                    this.intersections[row][col].doUnitOfWork();
                }
            }
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Create and print new segment
    ///////////////////////////////////////////////////////////////////////
    private void createSegment(int row, int col, int direction) {
        boolean isEntrance = (row == 1 && direction == Direction.NORTHWARD)
                          || (col == 1 && direction == Direction.EASTWARD)
                          || (row == numIntersectionsInOneDirection &&
                              direction == Direction.SOUTHWARD)
                          || (col == numIntersectionsInOneDirection &&
                              direction == Direction.WESTWARD);
        boolean isExit = row == 0
                        || col == 0
                        || row == (numIntersectionsInOneDirection + 1)
                        || col == (numIntersectionsInOneDirection + 1);
        
        Segment segment = new Segment(row, col, direction, isEntrance, isExit,
                                      this.minTimeToTravelSegment);
        segments.add(segment);
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Find segment
    ///////////////////////////////////////////////////////////////////////
    private Segment findSegment(int row, int col, int direction) {
        for (int i=0; i < segments.size(); i++) {
            if (segments.get(i).equals(row, col, direction)) {
                return segments.get(i);
            }
        }
        DebugOutput.print("Could not find segment: "
                          + "col: "
                          + col
                          + "/"
                          + "row: "
                          + row
                          + "/"
                          + Direction.toString(direction));
        return null;
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Initialize all segments
    ///////////////////////////////////////////////////////////////////////
    private void createSegments() {
        for (int row=0; row <= numIntersectionsInOneDirection + 1; row++) {
            for (int col=0; col <= numIntersectionsInOneDirection + 1;
                 col++) {
                if (col > 0 && col < numIntersectionsInOneDirection + 1) {
                    if (row > 0)
                        createSegment(row, col, Direction.NORTHWARD);
                    if (row < numIntersectionsInOneDirection + 1)
                        createSegment(row, col, Direction.SOUTHWARD);
                }
                if (row > 0 && row < numIntersectionsInOneDirection +1 ) {
                    if (col > 0)
                        createSegment(row, col, Direction.EASTWARD);
                    if (col < numIntersectionsInOneDirection + 1)
                        createSegment(row, col, Direction.WESTWARD);
                }
            }
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Initialize all intersections
    ///////////////////////////////////////////////////////////////////////
    private void createIntersections() {
        for (int row=0; row < numIntersectionsInOneDirection; row++) {
            for (int col=0; col < numIntersectionsInOneDirection; col++) {
                int r = row + 1;
                int c = col + 1;
                
                Segment[] segments = new Segment[8];
                segments[0] = this.findSegment(r, c, Direction.SOUTHWARD);
                segments[1] = this.findSegment(r, c, Direction.EASTWARD);
                segments[2] = this.findSegment(r, c, Direction.NORTHWARD);
                segments[3] = this.findSegment(r, c, Direction.WESTWARD);
                segments[4] = this.findSegment(r - 1, c, Direction.SOUTHWARD);
                segments[5] = this.findSegment(r, c + 1, Direction.EASTWARD);
                segments[6] = this.findSegment(r + 1, c, Direction.NORTHWARD);
                segments[7] = this.findSegment(r, c - 1, Direction.WESTWARD);
                
                this.intersections[row][col]
                = new Intersection(r, c, this.minTimeToChangeSegment, segments);
            }
            
        }
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Put cars into street grid
    ///////////////////////////////////////////////////////////////////////
    private void createCars() {
        for (int i=0; i < this.cars.size(); i++) {
            Car car = this.cars.get(i);
            this.findSegment(car.getRow(), car.getCol(), car.getDirection()).
                addCar(car);

        }
    }
}
