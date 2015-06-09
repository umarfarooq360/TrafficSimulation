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
        
        TrafficModel model
                = new TrafficModel(numberOfIntersectionsInOneDirection);
        model.doTest();
    }

}
