package model.Residency;

public class Floor {
    private int numOFUnits = 0;


    public int numOfPersons() {
        return numOFUnits * 5;
    }

    public void addUnit(int x){
        this.numOFUnits += x;
    }

    public int unitScore(){
        return 2 + this.numOfPersons();
    }

    public int totalUnitScore(){
        return numOFUnits * this.unitScore();
    }
    public int floorScore(){
        int score = 0;
        int numPeople = numOfPersons();
        return 3 + numPeople * 2 + totalUnitScore();
    }


}
