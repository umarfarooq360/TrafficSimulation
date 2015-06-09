// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** TrafficModel
// *****************************************************************************
// *****************************************************************************


import java.util.*;

public class TrafficModel extends Object {

    private int idNumber;
    private int lengthOfSimulation;
    private int beginMarking;
    private int endMarking;
    
    private int numIntersectionsInOneDirection;
    private int minTimeToTravelSegment;
    private int minTimeToChangeSegment;
    private int minTimeLightGreen;
    private int minTimeToChangeLight;
    private int maxSegmentCapacity;
    
    private int choiceOfThreeOptions;
    private double probabilityOfCreatedCarAlwaysMovingStraight;
    private double probabilityOfArrivalPerIteration;

    private Intersection intersections[][];
    private ArrayList<Segment> segments;
    private ArrayList<Car> cars;
    private Random rnd;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficModel () {
        this.idNumber = 0;
        this.lengthOfSimulation = 20;
        this.beginMarking = 0;
        this.endMarking = 0;
        
        this.numIntersectionsInOneDirection = 0;
        this.minTimeToTravelSegment = 0;
        this.minTimeToChangeSegment = 0;
        this.minTimeLightGreen = 0;
        this.minTimeToChangeLight = 0;
        this.maxSegmentCapacity = 0;
        
        this.choiceOfThreeOptions = 0;
        this.probabilityOfCreatedCarAlwaysMovingStraight = 0.0;
        this.probabilityOfArrivalPerIteration = 0.0;
        
        this.cars = new ArrayList<Car>();
        this.rnd = new Random();
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
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficModel(int idNumber,
                        int limitOfSimulationLength,
                        int limitOfNumberOfIntersectionsInOneDirection,
                        int limitOfSegmentCapacity,
                        int lengthOfSimulation,
                        int numIntersectionsInOneDirection,
                        int beginMarking,
                        int endMarking,
                        int maxSegmentCapacity,
                        int minTimeLightGreen,
                        int minTimeToChangeLight,
                        int minTimeToTravelSegment,
                        int minTimeToChangeSegment,
                        int choiceOfThreeOptions,
                        double probabilityOfCreatedCarAlwaysMovingStraight,
                        double probabilityOfArrivalPerIteration) {
        this();
        this.idNumber = idNumber;
        this.lengthOfSimulation = lengthOfSimulation;
        this.beginMarking = beginMarking;
        this.endMarking = endMarking;
        
        this.numIntersectionsInOneDirection = numIntersectionsInOneDirection;
        this.minTimeToTravelSegment = minTimeToTravelSegment;
        this.minTimeToChangeSegment = minTimeToChangeSegment;
        this.minTimeLightGreen = minTimeLightGreen;
        this.minTimeToChangeLight = minTimeToChangeLight;
        this.maxSegmentCapacity = maxSegmentCapacity;
        
        this.choiceOfThreeOptions = choiceOfThreeOptions;
        this.probabilityOfCreatedCarAlwaysMovingStraight =
            probabilityOfCreatedCarAlwaysMovingStraight;
        this.probabilityOfArrivalPerIteration =
            probabilityOfArrivalPerIteration;
        
        this.intersections = new Intersection[numIntersectionsInOneDirection]
        [numIntersectionsInOneDirection];
        this.segments = new ArrayList<Segment>();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get simulation output
    ///////////////////////////////////////////////////////////////////////
    public String getSimulationOutput() {
        return "During the simulation, there were "
               + this.getNumberOfMarkedCars()
               + " marked cars within the grid"
               + "\n"
               + "and the median time a marked car was in the grid was "
               + this.getMedianTimeInGrid();
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Do test (no randomization)
    ///////////////////////////////////////////////////////////////////////
    public String doTest() {
        this.createSegments();
        this.createIntersections();
        this.createCars();
        
        for (int time = 1; time <= this.lengthOfSimulation; time++)
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
        
        return getSimulationOutput();
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Do simulation
    ///////////////////////////////////////////////////////////////////////
    public String doSimulation() {
        this.echoPrintInputs();
        this.rnd.setSeed(this.idNumber);
        
        this.createSegments();
        this.createIntersections();
        
        for (int time = 1; time <= this.lengthOfSimulation; time++)
        {
            DebugOutput.print("TIME: " + time);
            
            this.createCar(time);
            
            for (int i=0; i < this.cars.size(); i++) {
                this.cars.get(i).updateTimer();
            }
            
            for (int row=0; row < numIntersectionsInOneDirection; row++) {
                for (int col=0; col < numIntersectionsInOneDirection; col++) {
                    this.intersections[row][col].doUnitOfWork();
                }
            }
        }
        
        return getSimulationOutput();
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
        
        int minTimeToTravelSegment = this.minTimeToTravelSegment;
        if (isEntrance || isExit) {
            minTimeToTravelSegment = 0;
        }
        
        Segment segment = new Segment(row, col, direction, isEntrance, isExit,
                                      this.maxSegmentCapacity,
                                      minTimeToTravelSegment);
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
                = new Intersection(r, c, this.minTimeToChangeSegment,
                                   this.minTimeLightGreen,
                                   this.minTimeToChangeLight,
                                   segments);
            }
            
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get entrance row
    ///////////////////////////////////////////////////////////////////////
    private int getEntranceRow(int direction, int position) {
        if (direction == Direction.SOUTHWARD)
            return this.numIntersectionsInOneDirection;
        if (direction == Direction.NORTHWARD)
            return 1;
        return position;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get entrance column
    ///////////////////////////////////////////////////////////////////////
    private int getEntranceCol(int direction, int position) {
        if (direction == Direction.WESTWARD)
            return this.numIntersectionsInOneDirection;
        if (direction == Direction.EASTWARD)
            return 1;
        return position;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get turn direction for new car
    ///////////////////////////////////////////////////////////////////////
    private int getTurnDirection() {
        if (this.choiceOfThreeOptions == 1) {
            return 0;
        } else if (this.choiceOfThreeOptions == 2) {
            if (this.rnd.nextDouble() <=
                this.probabilityOfCreatedCarAlwaysMovingStraight) {
                return 0;
            } else {
                return 1;
            }
        } else {
            if (this.rnd.nextDouble() <=
                this.probabilityOfCreatedCarAlwaysMovingStraight) {
                return 0;
            } else if (this.rnd.nextDouble() <= 0.5) {
                return 1;
            } else {
                return -1;
            }
        }
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get blocks before turn of new car
    ///////////////////////////////////////////////////////////////////////
    private int getBlocksBeforeTurn(int turnDirection) {
        if (turnDirection == 0)
            return -1;
        return this.rnd.nextInt(this.numIntersectionsInOneDirection);
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Put new car into grid
    ///////////////////////////////////////////////////////////////////////
    private void createCar(int timer) {
        if (this.rnd.nextDouble() <= this.probabilityOfArrivalPerIteration) {
            
            int carNumber = cars.size() + 1;
            boolean isMarked = this.beginMarking <= timer
                               && timer < this.endMarking;

            // set initial position of car
            int initialDirection = this.rnd.nextInt(4);
            int initialPosition = this.rnd.nextInt
                                    (this.numIntersectionsInOneDirection) + 1;
            int initialRow = this.getEntranceRow(initialDirection,
                                                initialPosition);
            int initialCol = this.getEntranceCol(initialDirection,
                                                 initialPosition);
            
            // set path of car
            int turnDirection = this.getTurnDirection();
            int blocksBeforeTurn = this.getBlocksBeforeTurn(turnDirection);
            
            // create car and put it in grid
            Car car = new Car(carNumber, isMarked, blocksBeforeTurn,
                              turnDirection, initialRow, initialCol,
                              initialDirection);
            this.cars.add(car);
            this.findSegment(initialRow, initialCol, initialDirection).
                addCar(car);
            
            DebugOutput.print("" + car
                              + " created (marked = "
                              + isMarked
                              + ") in col "
                              + initialCol
                              + " and row "
                              + initialRow);
            
            if (turnDirection == 0) {
                DebugOutput.print("" + car + " will never turn");
            } else {
                DebugOutput.print("" + car
                                  + " will "
                                  + Turn.toString(turnDirection)
                                  + " in "
                                  + blocksBeforeTurn
                                  + " block(s)");
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
    
    ///////////////////////////////////////////////////////////////////////
    /////// Get number of marked cars
    ///////////////////////////////////////////////////////////////////////
    private int getNumberOfMarkedCars() {
        int numberOfMarkedCars = 0;
        
        for (int i=0; i < this.cars.size(); i++) {
            if (cars.get(i).isMarked()) {
                numberOfMarkedCars++;
            }
        }
        return numberOfMarkedCars;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Calculate median time of marked cars in grid
    ///////////////////////////////////////////////////////////////////////
    private double getMedianTimeInGrid() {
        ArrayList<Integer> markedCarsTimeInGrid = new ArrayList<Integer>();
    
        // only select marked cars
        for (int i=0; i < this.cars.size(); i++) {
            if (cars.get(i).isMarked()) {
                markedCarsTimeInGrid.add(cars.get(i).getTimeInGrid());
            }
        }
        
        if (markedCarsTimeInGrid.size() > 0) {
            // sort times in grid
            Collections.sort(markedCarsTimeInGrid);
        
            // find median
            int middle = markedCarsTimeInGrid.size()/2;
            if (markedCarsTimeInGrid.size()%2 == 1) {
                return markedCarsTimeInGrid.get(middle);
            } else {
                return (markedCarsTimeInGrid.get(middle-1)
                        + markedCarsTimeInGrid.get(middle)) / 2.0;
            }
        }
        return 0;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Echo print all input values
    ///////////////////////////////////////////////////////////////////////
    private void echoPrintInputs() {
        System.out.print("\n\n***** BEGIN Summary of input values ************");
        System.out.println("********************************");
        System.out.println("The chosen ID number is: " + this.idNumber);
        System.out.print("The desired length of simulation is: ");
        System.out.println(this.lengthOfSimulation);
        System.out.print("The desired number of intersections in one ");
        System.out.print("direction is: ");
        System.out.println(this.numIntersectionsInOneDirection);
        System.out.print("The desired time to begin creating marked cars is: ");
        System.out.println(this.beginMarking);
        System.out.print("The desired time to stop creating marked cars is: ");
        System.out.println(this.endMarking);
        System.out.print("Desired maximum number of cars going in the same ");
        System.out.print("direction inside one block\nof a street is: ");
        System.out.println(this.maxSegmentCapacity);
        System.out.print("Desired minimum time a light is green is: ");
        System.out.println(this.minTimeLightGreen);
        System.out.print("Desired minimum time to change a red light to ");
        System.out.println("green is: " + this.minTimeToChangeLight);
        System.out.print("Desired minimum time for a car to travel one block ");
        System.out.println("is: " + this.minTimeToTravelSegment);
        System.out.print("Desired minimum time for a car to travel from " +
                         "the end of one block to the\n");
        System.out.println("beginning of another block is: " +
                           this.minTimeToChangeSegment);
        System.out.println("Chosen option is: " + this.choiceOfThreeOptions);
        System.out.print("The desired probability of a created car always ");
        System.out.println("moving straight is: " +
                           this.probabilityOfCreatedCarAlwaysMovingStraight);
        System.out.print("Desired probability of arrival of a car per tenth ");
        System.out.println("of second is: " +
                           this.probabilityOfArrivalPerIteration);
        System.out.print("***** END   Summary of input values **************");
        System.out.println("******************************\n\n");
    }
}
