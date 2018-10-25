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

    public int homeScore(){
        int scoreFloors = 0;
        int scoreUnits = 0;
        int scorePersons = 0;
        for(Floor f : Floors) {
            scoreFloors += f.floorScore();
            scoreUnits += f.totalUnitScore();
            scorePersons += f.numOfPersons();
        }
        return 10 + scoreFloors + scoreUnits * 2 + scorePersons * 3;

    }
}
