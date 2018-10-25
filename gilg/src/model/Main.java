package model;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
            else if(checkRequest(turn%2, input)){

            }
            else{
                System.out.println("not possible");
            }
        }

    }

    public static boolean checkRequest(int turn, String input){
        if(input.matches("([Aa])([Dd])([Dd]) ([Bb])([Ll])([Oo])([Cc])([Kk])")){ //add block

        }
        else if(input.matches("([Aa])([Dd])([Dd]) ([Hh])([Oo])([Mm])([Ee]) \\d+ \\d+ \\d+")){ //add home

        }
        else if(input.matches("([Aa])([Dd])([Dd]) ([Aa])([Rr])([Mm])([Yy]) \\d+")){ //add army

        }
        else if(input.matches("([Aa])([Dd])([Dd]) ([Dd])([Ee])([Ff])([Ee])([Nn])([Ss])([Ee]) \\d+")){ //add defense

        }
        else if(input.matches("([Aa])([Dd])([Dd]) ([Bb])([Az])([Zz])([Aa])([Aa])([Rr]) \\d+")){ //add bazaar

        }
        else if(input.matches("([Re])([Ee])([Mm])([Oo])([Vv])([Ee]) \\d+")){ //remove block

        }
        else if(input.matches("([Re])([Ee])([Mm])([Oo])([Vv])([Ee]) \\d+ \\d+")){ //remove bazaar and army and defense

        }
        else if(input.matches("([Uu])([Pp])([Gg])([Rr])([Aa])([Dd])([Ee]) \\d+")){ //upgrade block

        }
        else if(input.matches("([Uu])([Pp])([Gg])([Rr])([Aa])([Dd])([Ee]) \\d+ \\d+" +
                " (([Ff])([Ll])([Oo])([Oo])([Rr]))|(([Uu])([Nn])([Ii])([Tt]))")){ //upgrade home

        }
        else if(input.matches("([Uu])([Pp])([Gg])([Rr])([Aa])([Dd])([Ee]) \\d+ \\d+")){ //upgrade army and defense
            //if()
        }
        else if(input.matches("([Aa])([Tt])([Tt])([Aa])([Cc])([Kk]) \\d+")){ //attack

        }
        else if(input.matches("([Ll])([Oo])([Oo])([Tt]) \\d+")){ //loot

        }
        else if(input.matches("([Ss])([Ee])([Ee]) ([Gg])([Ii])([Ll])([Ll])([Ss]) \\d+ \\d+")){ //see gills

        }
        else if(input.matches("([Ss])([Ee])([Ee]) ([Ss])([Cc])([Oo])([Rr])([Ee]) \\d+ \\d+")){ //see score

        }
        else{
            return false;
        }
    }
}
