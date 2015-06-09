// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** Test_TrafficModel
// *****************************************************************************
// *****************************************************************************

import java.io.*;
import java.util.*;

public class Test_TrafficModel extends Object {
    
    ///////////////////////////////////////////////////////////////////////
    /////// Main
    ///////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        
        for (int i=0; i < 17; i++) {
            input.nextLine();
        }
        
        int numberOfIntersectionsInOneDirection = input.nextInt();
        DebugOutput.print("Number of intersections in one direction: "
                          + numberOfIntersectionsInOneDirection);

        input.nextLine();
        input.nextLine();
        int minTimeToTravelSegment = input.nextInt();
        DebugOutput.print("Minimum time to travel segment: "
                          + minTimeToTravelSegment);
 
        input.nextLine();
        input.nextLine();
        int minTimeToChangeSegment = input.nextInt();
        DebugOutput.print("Minimum time to change segment: "
                          + minTimeToChangeSegment);
        
        input.nextLine();
        input.nextLine();
        int numberOfCars = input.nextInt();
        DebugOutput.print("Number of cars created for the test: "
                          + numberOfCars);
        
        ArrayList<Car> cars = new ArrayList<Car>();
        for (int i=0; i < numberOfCars; i++) {
            input.nextLine();
            input.nextLine();
            int carNumber = input.nextInt();
            DebugOutput.print("Car number: " + carNumber);
            
            input.nextLine();
            input.nextLine();
            int initialCol = input.nextInt();
            DebugOutput.print("born in the segment that is positioned at col: "
                              + initialCol);
            
            input.nextLine();
            input.nextLine();
            int initialRow = input.nextInt();
            DebugOutput.print("born in the segment that is positioned at row: "
                              + initialRow);
            
            input.nextLine();
            input.nextLine();
            int initialDirection = input.nextInt();
            DebugOutput.print("born in the segment that is oriented in direction: "
                              + initialDirection);

            input.nextLine();
            input.nextLine();
            int blocksBeforeTurn = input.nextInt();
            DebugOutput.print("additional blocks to travel prior to turning "
                              + blocksBeforeTurn);
            
            input.nextLine();
            input.nextLine();
            int turnDirection = input.nextInt();
            DebugOutput.print("direction the car will turn, when the car turns: "
                              + turnDirection);
            
            cars.add(new Car(carNumber, true, blocksBeforeTurn, turnDirection,
                             initialRow, initialCol, initialDirection));
        }
        
        TrafficModel model
                = new TrafficModel(numberOfIntersectionsInOneDirection,
                                   minTimeToTravelSegment,
                                   minTimeToChangeSegment, cars);
        String result = model.doTest();
        System.out.println(result);
    }

}
