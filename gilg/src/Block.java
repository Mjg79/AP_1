import java.util.ArrayList;

public class Block {
    private int level ;
    private ArrayList <Building> buildings = new ArrayList<>();
    boolean flag_defense = false;
    int ID ;

    public void addHome(int numOfFloors , int numOfUnit){
        Home home = new Home();
        for (int i = 0 ; i < numOfFloors ; i++) {
            home.addFloor();
        }
        for (Floor f : home.Floors) {
            f.addUnit(numOfUnit);
        }
      buildings.add(home);
    }

    public void addArmy() {
        buildings.add(new Army());
    }

    public void Upgrade(String string) {
        switch (string){
            case "home" :

        }
    }
}
