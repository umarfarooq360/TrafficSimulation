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

public class Car extends Object {

    public static int South = 1;
    public static int North = 2;
    public static int East  = 3;
    public static int West  = 4;
    
    private boolean marked;
    private int idNumber;
    private int row;
    private int col;
    private int directionCarIsHeaded;
    private int entranceTime;
    private int exitTime;
    
    public Car () {
        
    }

    public Car (boolean marked, int idNumber,
                int row, int col, int directionCarIsHeaded) {
        this.marked = marked;
        this.idNumber = idNumber;
        this.row = row;
        this.col = col;
        this.directionCarIsHeaded = directionCarIsHeaded;
    }

    public int getRow() {
        return this.row;
    }
    
    public int getCol() {
        return this.col;
    }
    
    public int getDirectionCarIsHeaded() {
        return this.directionCarIsHeaded;
    }
    
    public void move() {
        TrafficModel.callNextSegment(this.idNumber, this.row, this.col,
                                     this.directionCarIsHeaded);
    }
    
    public String toString() {
        return "Car " + this.idNumber;
    }

}