/*******************************************************
/*
/* Project: Traffic Flow Simulation
/*
/* Programmers: Hunter Lambert
/*              Omar Farooq
/*              Tyler Barnett
/*              Hans-Peter Hoellwirth
/******************************************************/

import java.util.*;

public class TrafficModel extends Object {

    private ArrayList<Car> cars;
    
    public TrafficModel () {
        this.cars = new ArrayList<Car>();
    }

    public void runSimulation() {
        this.cars.add(0, new Car(false, 10, 1, 1, 2));
        this.cars.add(1, new Car(true, 11, 2, 2, 1));
        this.cars.add(2, new Car(true, 12, 2, 1, 3));
        
        for (int i=0; i < this.cars.size(); i++) {
            this.cars.get(i).move();
        }
    }
    
    // note: instance of Car can not be processed by class method
    //       also, class method can not have access to any instance of Segment!
    static void callNextSegment(int idNumber, int row, int col, int directionCarIsHeaded) {
        int nextRow = row;
        int nextCol = col;
        
        System.out.println("some instance of Segment is called");
        
        if (directionCarIsHeaded == Car.South) {
            nextRow--;
        }
        else if (directionCarIsHeaded == Car.North) {
            nextRow++;
        }
        else if (directionCarIsHeaded == Car.East) {
            nextCol++;
        }
        else if (directionCarIsHeaded == Car.West) {
            nextCol--;
        }
        System.out.println("Car " + idNumber
                               + " moves from row "
                               + row
                               + ", col "
                               + col
                               + " to row "
                               + nextRow
                               + ", col "
                               + nextCol);
    }

}