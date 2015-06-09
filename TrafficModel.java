// Team member's names: Hunter Lambert, Omar Farooq,
//                      Tyler Barnett, Hans-Peter Hoellwirth

// *****************************************************************************
// *****************************************************************************
// **** TrafficModel
// *****************************************************************************
// *****************************************************************************


import java.util.*;

public class TrafficModel extends Object {

    private Intersection intersections[][];
    private int numIntersectionsInOneDirection;

    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficModel () {
        this.numIntersectionsInOneDirection = 0;
    }
    
    ///////////////////////////////////////////////////////////////////////
    /////// Constructor
    ///////////////////////////////////////////////////////////////////////
    public TrafficModel (int numIntersectionsInOneDirection) {
        this.numIntersectionsInOneDirection = numIntersectionsInOneDirection;
        this.intersections = new Intersection[numIntersectionsInOneDirection]
                                            [numIntersectionsInOneDirection];
    }

    ///////////////////////////////////////////////////////////////////////
    /////// Do test
    ///////////////////////////////////////////////////////////////////////
    public void doTest() {
        
        for (int row=0; row < numIntersectionsInOneDirection; row++) {
            for (int col=0; col < numIntersectionsInOneDirection; col++) {
                this.intersections[row][col]
                    = new Intersection(row + 1, col + 1);
            }
            
        }
        
        for (int row=0; row < numIntersectionsInOneDirection; row++) {
            for (int col=0; col < numIntersectionsInOneDirection; col++) {
                this.intersections[row][col].doUnitOfWork();
            }
        }
    }
}
