package model.Residency;

import model.Building;

import java.util.ArrayList;

public class Home extends Building {
    ArrayList<Floor> Floors = new ArrayList<>();
    private int numOfFloors=1;
    public void addFloor(){
        this.numOfFloors+=1;
        Floor x = new Floor();
        this.Floors.add(x);
    }

    public ArrayList<Floor> getFloors() {
        return Floors;
    }
}
