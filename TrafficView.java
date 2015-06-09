// Programmer: Arthur Charlesworth  (c) Copyright 2015

// *****************************************************************************
// *****************************************************************************
// **** TrafficView 
// *****************************************************************************
// *****************************************************************************


import java.util.*;

public class TrafficView {
  
   public static void main(String[] args) {
     int limitOfSimulationLength = 60000;
     int limitOfNumberOfIntersectionsInOneDirection = 10;
     int limitOfSegmentCapacity = 100;
     Random rnd = new Random();
     Scanner console = new Scanner(System.in);

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain idNumber (of simulation)
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input the desired ID number of simulation (or -1, if ");
     System.out.println("you want the computer");
     System.out.println("to choose the ID number randomly):");
     int idNumber = console.nextInt();   
     if (idNumber == -1) {
          idNumber = Math.abs(rnd.nextInt());
     } else if (idNumber <= 0) {
          System.out.println("ID number must be -1 or else a positive integer");
          return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain lengthOfSimulation
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input the desired length of simulation (all times ");
     System.out.println("are in *simulated* time units): ");
     int lengthOfSimulation = console.nextInt();
     if (lengthOfSimulation < 1 || 
         lengthOfSimulation > limitOfSimulationLength) {
        System.out.print("Desired length of simulation must be an integer ");
        System.out.print("greater than 0 and less \nthan or equal to ");
        System.out.print(limitOfSimulationLength);
        return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain numIntersectionsInOneDirection
     ///////////////////////////////////////////////////////////////////////
     System.out.print("The number of intersections in north-south direction ");
     System.out.print("is the same as\nthe number in east-west");
     System.out.println(" direction.");
     System.out.print("Input the desired number of intersections in one ");
     System.out.println("direction: ");
     int numIntersectionsInOneDirection = console.nextInt();
     if (numIntersectionsInOneDirection < 1 || 
         numIntersectionsInOneDirection > 
         limitOfNumberOfIntersectionsInOneDirection) {
        System.out.print("Desired number of intersections in one direction ");
        System.out.print("must be ");
        System.out.println("an integer greater than 0");
        System.out.println("and less than or equal to " +
                           limitOfNumberOfIntersectionsInOneDirection);
        return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain beginMarking
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input the desired time to begin creating marked cars ");
     System.out.println("whose statistical\nproperties will be summarized: ");
     int beginMarking = console.nextInt();
     if (beginMarking < 1 || beginMarking > lengthOfSimulation) {
        System.out.print("Desired time to begin creating marked cars must be ");
        System.out.println("an integer greater than 0");
        System.out.print("and less than or equal to your desired length ");
        System.out.println("of simulation " + lengthOfSimulation);
        return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain endMarking
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input the desired time to stop creating marked cars ");
     System.out.println("whose statistical\nproperties will be summarized: ");
     int endMarking = console.nextInt();
     if (endMarking < beginMarking || endMarking > lengthOfSimulation) {
        System.out.print("Desired time to stop creating marked cars must be ");
        System.out.println("an integer greater than");
        System.out.print("or equal to the desired time to begin creating ");
        System.out.print("marked cars and ");
        System.out.print("and less than or\nequal to your desired length ");
        System.out.println("of simulation " + lengthOfSimulation);
        return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain maxSegmentCapacity
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input the desired maximum number of cars going in ");
     System.out.println("the same direction inside\none block of a street: ");
     int maxSegmentCapacity = console.nextInt();
     if (maxSegmentCapacity < 1 || 
         maxSegmentCapacity > limitOfSegmentCapacity) {
        System.out.print("Desired maximum number of cars going in same ");
        System.out.println("direction within\none block of a street must be ");
        System.out.println("greater than 0 and ");
        System.out.print("less than\nor equal to ");
        System.out.println(limitOfSegmentCapacity);
        return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain minTimeLightGreen
     ///////////////////////////////////////////////////////////////////////
     System.out.println("Input the desired minimum time a light is green: ");
     int minTimeLightGreen = console.nextInt();
     if (minTimeLightGreen < 1 || 
         minTimeLightGreen > lengthOfSimulation) {
        System.out.print("Desired minimum time a light is green");
        System.out.print(" must be greater than 0 and ");
        System.out.print("less than\nor equal to ");
        System.out.print("your desired length of simulation ");
        System.out.println(lengthOfSimulation);
        return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain minTimeToChangeLight
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input the desired minimum time to change a ");
     System.out.println("red light to green: ");
     int minTimeToChangeLight = console.nextInt();
     if (minTimeToChangeLight < 1 || 
         minTimeToChangeLight > lengthOfSimulation) {
        System.out.print("Desired minimum time to change a red light ");
        System.out.print("to green must be greater than 0\nand ");
        System.out.print("less than or equal to ");
        System.out.print("your desired length of simulation ");
        System.out.println(lengthOfSimulation);
        return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain minTimeToTravelSegment
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input the desired minimum time for a car to travel one");
     System.out.println(" block: ");
     int minTimeToTravelSegment = console.nextInt();
     if (minTimeToTravelSegment < 1 || 
         minTimeToTravelSegment > lengthOfSimulation) {
        System.out.print("Desired minimum time for a car to travel one ");
        System.out.print("block must be greater than 0\nand ");
        System.out.print("less than or equal to ");
        System.out.print("your desired length of simulation ");
        System.out.println(lengthOfSimulation);
        return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain minTimeToChangeSegment
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input the desired minimum time for a car to travel ");
     System.out.print("from the end of one block\nto the beginning of ");
     System.out.println("another block: ");
     int minTimeToChangeSegment = console.nextInt();
     if (minTimeToChangeSegment < 1 || 
         minTimeToChangeSegment > lengthOfSimulation) {
        System.out.print("Desired minimum time for a car to travel from the ");
        System.out.print("end of one block to the\nbeginning ");
        System.out.print("of another block must be ");
        System.out.print("greater than or equal to 0 and\nless than or ");
        System.out.print("equal to your desired length of simulation ");
        System.out.println(lengthOfSimulation);
        return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain choiceOfThreeOptions
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input one of the following integers to indicate ");
     System.out.print("your additional choices (these\nare the *only* ");
     System.out.println("such choices possible with the current software):");
     System.out.println("  1  You want each car to only go straight");
     System.out.print("  2  You want each car never to turn left, and ");
     System.out.println("either to only go straight");
     System.out.println("     or to turn right exactly once");
     System.out.print("  3  You want each car to only go straight or to turn ");
     System.out.print("right exactly once\n     or to turn left ");
     System.out.println("exactly once");
     System.out.println("What is your choice? (input 1, 2, or 3): ");
     int choiceOfThreeOptions = console.nextInt();
     if (choiceOfThreeOptions < 1 || choiceOfThreeOptions > 3 ) {
        System.out.println("You must input 1, 2, or 3 to choose one option.");
        return;
     }
     if (choiceOfThreeOptions == 2) {
        System.out.print("When properly programmed, the simulation should ");
        System.out.print("make equally likely a car\ngoing straight or ");
        System.out.println("turning right exactly once.");
     }
     if (choiceOfThreeOptions == 3) {
        System.out.print("When properly programmed, the simulation should ");
        System.out.print("make equally likely a car\ngoing straight or ");
        System.out.print("turning right exactly once or turning left ");
        System.out.println("exactly once.");
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain probabilityOfCreatedCarAlwaysMovingStraight 
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input the desired probability of a created car ");
     System.out.println("always moving straight.");
     System.out.print("(if you chose option 1, you must now input 1.0 ");
     System.out.println("exactly): ");
     double probabilityOfCreatedCarAlwaysMovingStraight = console.nextDouble();
     if (choiceOfThreeOptions == 1 && 
         probabilityOfCreatedCarAlwaysMovingStraight != 1.0) {
       System.out.print("Since you chose option 1, your next input had ");
       System.out.println("to be the number 1.0,");
       System.out.print("for the desired probability of a created car ");
       System.out.println("always moving straight;");
       System.out.println("you failed to input that 1.0 value.");
       return;
     }

     ///////////////////////////////////////////////////////////////////////
     /////// Obtain probabilityOfArrivalPerIteration
     ///////////////////////////////////////////////////////////////////////
     System.out.print("Input the desired probability of arrival of a car ");
     System.out.println("per iteration: ");
     double probabilityOfArrivalPerIteration = console.nextDouble();

     ///////////////////////////////////////////////////////////////////////
     /////// Ask an instance of TrafficModel to doSimulation()
     ///////////////////////////////////////////////////////////////////////
     TrafficModel model = new TrafficModel(
                     idNumber,
                     limitOfSimulationLength,
                     limitOfNumberOfIntersectionsInOneDirection,
                     limitOfSegmentCapacity,
                     lengthOfSimulation,
                     numIntersectionsInOneDirection,
                     beginMarking,
                     endMarking,
                     maxSegmentCapacity,
                     minTimeLightGreen,
                     minTimeToChangeLight,
                     minTimeToTravelSegment,
                     minTimeToChangeSegment,
                     choiceOfThreeOptions,
                     probabilityOfCreatedCarAlwaysMovingStraight,
                     probabilityOfArrivalPerIteration);
     String result = model.doSimulation();
     System.out.println(result);
   }
}
