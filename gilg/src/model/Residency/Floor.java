package model.Residency;

public class Floor {
    private int numOFUnits=0;

    public int Persons() {
        return numOFUnits*5;
    }
    public void addUnit(int x){
        this.numOFUnits+=x;
    }
}
