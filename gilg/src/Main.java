//import model.City;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        City[] cities = new City[2];
        String input = null;
        int turn = 0;
        while(true){
            input = scanner.nextLine();
            if(input.matches("([Yy])([Ii])([Ee])([Ll])([Dd])")){
                break;
            }
            else if(input.matches("([Dd])([On])([Nn])([Ee])")){
                turn++;

            }
            else if(checkRequest(turn%2, input, cities));
            else
                System.out.println("not possible");
        }
        cities[0].getScore();
        cities[1].getScore();
        return;
    }

    public static boolean checkRequest(int turn, String input, City[] cities){
        if(input.matches("([Aa])([Dd])([Dd]) ([Bb])([Ll])([Oo])([Cc])([Kk])")){ //add block
            if(cities[turn].addBlock()){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Aa])([Dd])([Dd]) ([Hh])([Oo])([Mm])([Ee]) \\d+ \\d+ \\d+")){ //add home
            int blockId, numberOfFloors, numberOfUnits;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[2]);
            numberOfFloors = Integer.parseInt(splitedInput[3]);
            numberOfUnits = Integer.parseInt(splitedInput[4]);
            if(cities[turn].addHome(blockId, numberOfFloors, numberOfUnits)){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Aa])([Dd])([Dd]) ([Aa])([Rr])([Mm])([Yy]) \\d+")){ //add army
            int blockId;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[2]);
            if(cities[turn].addArmy(blockId)){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Aa])([Dd])([Dd]) ([Dd])([Ee])([Ff])([Ee])([Nn])([Ss])([Ee]) \\d+")){ //add defense
            int blockId;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[2]);
            if(cities[turn].addDefense(blockId)){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Aa])([Dd])([Dd]) ([Bb])([Az])([Zz])([Aa])([Aa])([Rr]) \\d+")){ //add bazaar
            int blockId;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[2]);
            if(cities[turn].addBazaar(blockId)){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Re])([Ee])([Mm])([Oo])([Vv])([Ee]) \\d+")){ //remove block
            int blockId;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[1]);
            if(cities[turn].removeBlock(blockId)){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Re])([Ee])([Mm])([Oo])([Vv])([Ee]) \\d+ \\d+")){ //remove bazaar and army and defense
            int blockId, unitId;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[1]);
            unitId = Integer.parseInt(splitedInput[2]);
            if(cities[turn].removeBazaarOrArmyOrDefense(blockId, unitId)){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Uu])([Pp])([Gg])([Rr])([Aa])([Dd])([Ee]) \\d+")){ //upgrade block
            int blockId;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[1]);
            if(cities[turn].upgradeBlock(blockId)){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Uu])([Pp])([Gg])([Rr])([Aa])([Dd])([Ee]) \\d+ \\d+" +
                " ((([Ff])([Ll])([Oo])([Oo])([Rr]))|(([Uu])([Nn])([Ii])([Tt])))+")){ //upgrade home
            int blockId, homeId;
            int numberOfFloorToAdd = 0;
            int numberOfUnitToAdd = 0;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[1]);
            homeId = Integer.parseInt(splitedInput[2]);
            for(int i = 3; i < splitedInput.length; i++){
                if(input.matches("([Ff])([Ll])([Oo])([Oo])([Rr])")){
                    numberOfFloorToAdd++;
                }
                else if(input.matches("(([Uu])([Nn])([Ii])([Tt]))")){
                    numberOfUnitToAdd++;
                }
            }
            if(cities[turn].upgradeHome(blockId, numberOfFloorToAdd, numberOfUnitToAdd)){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Uu])([Pp])([Gg])([Rr])([Aa])([Dd])([Ee]) \\d+ \\d+")){ //upgrade army and defense
            int blockId, unitId;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[1]);
            unitId = Integer.parseInt(splitedInput[2]);
            if(cities[turn].upgradeArmyOrDefense(blockId, unitId)){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Aa])([Tt])([Tt])([Aa])([Cc])([Kk]) \\d+")){ //attack
            int blockId;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[1]);
            if(cities[turn].attack(blockId, cities[(turn + 1) % 2])){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Ll])([Oo])([Oo])([Tt]) \\d+")){ //loot
            int blockId;
            String[] splitedInput = input.split(" ");
            blockId = Integer.parseInt(splitedInput[1]);
            if(cities[turn].loot(blockId)){
                return true;
            }
            else
                return false;
        }
        else if(input.matches("([Ss])([Ee])([Ee]) ([Gg])([Ii])([Ll])([Ll])([Ss])")){ //see gills
            cities[turn].getMoney();
            return true;
        }
        else if(input.matches("([Ss])([Ee])([Ee]) ([Ss])([Cc])([Oo])([Rr])([Ee])")){ //see score
            cities[turn].getScore();
            return true;
        }
        else{
            return false;
        }
    }
}
