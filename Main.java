import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map;

/**
 * Author: Abhay Solanki, Sharvay Ajit and Nathanael Wolski
 */
public class Main {
    /**
     * Introduction to program, communicates with user and has them enter their name, choose a pokemon, and choose an initial direction to begin moving in
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Prof Oak: Hello Trainer! What is your name?");
        String name = CheckInput.getString();
        System.out.println("It's great to finally meet you " + name + "! Choose your pokemon!\n1. Charmander\n2. Bulbasaur\n3. Squirtle");
        int pokemonChoice = CheckInput.getIntRange(1,3);
        Map map = new Map();
        Trainer player;
        if(pokemonChoice == 1) {
            Charmander charr = new Charmander();
            player = new Trainer(name, charr , map);
        }
        else if(pokemonChoice == 2) {
            Bulbasaur bulb = new Bulbasaur();
            player = new Trainer(name, bulb , map);
        }
        else {
            Squirtle squirt = new Squirtle();
            player = new Trainer(name, squirt , map);
        }
        System.out.println(player.toString());
        int menuChoice = mainMenu();
        char curTile;
        while(menuChoice != 5){
            if(menuChoice == 1)
                curTile = player.goNorth();
            else if(menuChoice == 2)
                curTile = player.goEast();
            else if(menuChoice == 3)
                curTile = player.goSouth();
            else if(menuChoice == 4)
                curTile = player.goWest();

        }








    }

    /**
     * lets the user either return to main menu, choose a direction to travel in, or quit
     * @return
     */
    public static int mainMenu(){
        System.out.println("Main Menu: ");
        System.out.println("1. Go North");
        System.out.println("2. Go East");
        System.out.println("3. Go South");
        System.out.println("4. Go West");
        System.out.println("5. Quit");
        return CheckInput.getIntRange(1,5);
    }

    /**
     * chooses a random pokemon when you encounter a pokemon into the wild
     * @return
     */
    public static Pokemon chooseRandomPokemon(){
        int rand = (int)(Math.random() * 6);
        if(rand == 0) {
            Squirtle sq = new Squirtle();
            return sq;
        }
        else if(rand == 1){
            Staryu star = new Staryu();
            return star;
        }
        else if(rand == 2){
            Ponyta pon = new Ponyta();
            return pon;
        }
        else if(rand == 3){
            Charmander ch = new Charmander();
            return ch;
        }
        else if(rand == 4){
            Bulbasaur bul = new Bulbasaur();
            return bul;
        }
        else{
            Oddish od = new Oddish();
            return od;
        }
    }

    /**
     * Runs when trainer chooses to fight
     * @param t
     * @param p
     */
    public static void trainerAttack(Trainer t, Pokemon p){
        Pokemon wild = chooseRandomPokemon();
    }

    /**
     * store for user to interact and buy from
     * @param t
     */
    public static void store(Trainer t){

    }
}
