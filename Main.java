import java.io.FileNotFoundException;
import java.security.cert.CertPathChecker;
import java.util.*;

/**
 * Author: Abhay Solanki, Sharvay Ajit and Nathanael Wolski
 */import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;

/**
 *Date: 10/21/2021
 *Project 1
 *@author ESPINOSA, ARMANDO ,SOLANKI, ABHAY
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {


        //flavor text
        System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
        String trainerName = CheckInput.getString();
        System.out.println("Great to meet you, " + trainerName);
        System.out.println("And no I won't send you into danger, just so you can get your first pokemon.");

        Map map = Map.getInstance();
        int level = 1;
        Trainer trainer = null;

        boolean cont = true;
        while (cont) {
            //text for Pokémon
            System.out.println("Choose your first pokemon: ");
            System.out.println("1. Charmander ");
            System.out.println("2. Bulbasaur ");
            System.out.println("3. Squirtle \n");

            try {
                switch (in.nextInt()) {
                    //check what user entered and grant them their pokemon
                    case 1 -> {
                        Pokemon charmander = new Charmander();
                        trainer = new Trainer(trainerName, charmander, map);
                        cont = false;
                    }
                    case 2 -> {
                        Pokemon bulbasaur = new Bulbasaur();
                        trainer = new Trainer(trainerName, bulbasaur, map);
                        cont = false;
                    }
                    case 3 -> {
                        Pokemon squirtle = new Squirtle();
                        trainer = new Trainer(trainerName, squirtle, map);
                        cont = false;
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error:" + ex);
                System.out.println("Invalid input.");
            }
        }
        int selection = 0;
        while (true){
            //selection
            System.out.println(trainer.toString());
            selection = mainMenu();
            //moving here
            //checking char
            //wild events
            if (selection == 1) {
                trainer.goNorth();
            }
            if (selection == 2) {
                trainer.goSouth();
            }
            if (selection == 3) {
                trainer.goEast();
            }
            if (selection == 4) {
                trainer.goWest();
            }
            if (selection == 5) {
                System.out.println("Game over");
                break;
            }
            //should auto switch map if f

            int chance = (int)(Math.random() * 100);
            //System.out.println("character:" + map.getCharAtLoc(trainer.getLocation()));
            char place = map.getCharAtLoc(trainer.getLocation());
            switch (place) {

                //n nonthing
                //c city
                //w wild Pokemon fight
                //f finish - Gym
                //s start
                //i item
                //p person - is removed after defeated
                case 'w':
                    System.out.println("A wild Pokemon has appeared.");
                    trainerAttack(trainer, PokemonGenerator.generateRandomPokemon(level));

                    int numOfWildFightChoices = 4;

                    int fightMenuChoice = 0;

                    String wildFightChoice = """
                                            What do you want to do?\s
                                            1. Fight
                                            2. Use Potion
                                            3. Throw Poke Ball
                                            4. Run Away
                                            """;

                    map.removeCharAtLoc(trainer.getLocation());

                case 'c':
                    store(trainer);
                case 'p':
                    int rs = (int) (Math.random() * 3);

                    if (rs == 1) {
                        System.out.println("You found a Pokeball and a potion in the wild.");
                        trainer.recievePotion();
                        trainer.recievePokeball();

                    }

                    if (rs == 2) {
                    System.out.println("You found 5 dollars on the ground.(Thief!!)");
                    trainer.recieveMoney(5);
                    }

                    if (rs == 3) {
                    String[] DamagingEvents = {
                            "You were jumped by team rocket",
                            "You fell in a diglet hole.",
                            "Somebody tazed you!",
                            "You stumbled into an sleeping Ancient Charmander."
                    };

                    int messageSelection = (int)(Math.random() * DamagingEvents.length);
                    System.out.println(DamagingEvents[messageSelection]);
                    System.out.println("You take 10 damage.");
                    trainer.takeDamage(10);
                    map.removeCharAtLoc(trainer.getLocation());
                }

                case 'f':
                    System.out.println("You've found the Gym...");
                    Pokemon gymPokemon = PokemonGenerator.generateRandomPokemon(level+2);
                    Pokemon trainerPokemon = trainer.getPokemon(0);

                    int numOfGymFightChoices = 2;
                    String gymFightChoice = """
                                            What do you want to do?\s
                                            1. Fight
                                            2. Use Potion
                                            """;
                    fightMenuChoice = CheckInput.getIntRange(1,2) ;

                    if(fightMenuChoice == 1) {
                        trainerPokemon = trainerAttack(trainer, gymPokemon);
                    }
                    else
                    {
                        //On potion use
                        //heals the pokemon to full
                        trainerPokemon.heal();
                        //And selects at random a buff
                        trainerPokemon = (((int)(Math.random() * 2)) +1 ) > 1 ? (new AttackUp(trainerPokemon)) : (new HpUp(trainerPokemon));
                    }

                    if(gymPokemon.getHp() == 0) {
                        switch (level % 3) {
                            case 1:
                                Map.loadMap(1);
                            case 2:
                                Map.loadMap(2);
                            case 0:
                                Map.loadMap(3);
                        }

                        trainer.buffAllPokemon();
                    }


                case 'i':
                    String award = (chance > 50) ? ("You found 1 pokeball!") : ("You found 1 potion!");
                    int amount = (chance > 50) ? 1 : 0;
                    if (amount == 1) {
                        trainer.recievePotion();
                        System.out.println(award);
                        map.removeCharAtLoc(trainer.getLocation());
                    }if (amount == 0){
                    trainer.recievePokeball();
                    System.out.println(award);
                    map.removeCharAtLoc(trainer.getLocation());
                }
            }
        }

    }
    /**
     * This method displays the directional menu
     * @return returns user selection
     */
    public static int mainMenu() {

        Scanner scanner = new Scanner(System.in);
        int selection = 0;

        try {
            System.out.println("Main menu:");
            System.out.println("1. Go North");
            System.out.println("2. Go South");
            System.out.println("3. Go East");
            System.out.println("4. Go West");
            System.out.println("5. Quit");
            selection = scanner.nextInt();
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input.");
        }
        return selection;
    }

    //BATTLE LOOP
    /**
     * This method allows a Trainer and a wild pokemon to battle.
     * @param t is the Trainer battling.
     * @param wild is the wild pokemon.
     * @exception FileNotFoundException throws an Exception if it fails to find the file for the next map.
     */
    public static Pokemon trainerAttack(Trainer t, Pokemon wild) throws FileNotFoundException {

        //loop battle system

        Pokemon p = null;

        //fight menu
        System.out.println("""
                What do you want to do?\s
                1. Fight
                2. Use Potion
                3. Throw Poke Ball
                4. Run Away""");

        //get the choice
        int fightMenuChoice = CheckInput.getIntRange(1,4);

        for(int i=0;i<t.getNumPokemon();i++)
        {
            if(t.getPokemon(i).getHp() != 0)
            {
                p = t.getPokemon(i);
            }
        }

        if (fightMenuChoice == 1) {

            /*
            1. Basic Attack
            2. Special Attack
            */

            if (p == null)
            {
                System.out.println("All of your Pokemon are at 0 hp.\nYou can run away or use a potion.\n");
                return p;
            }

            while(wild.getHp() != 0 && p.getHp() != 0) {
                //attack menu
                System.out.println(p.getAttackMenu());
                int choice = CheckInput.getInt();

                if (choice == 1) {

                    while (p.getHp() != 0) {

                        //basic menu
                        System.out.println(p.getBasicMenu());
                        choice = CheckInput.getInt();

                        if (choice < p.getNumBasicMenuItems()+1 && choice > -1) break;
                        System.out.println("Enter a number between 1 - " + p.getNumBasicMenuItems()+"\n");
                    }
                    p.basicAttack(wild, choice);

                    // Pokemon attacks.
                    int BasicOrSpecial = (int)(Math.random() * 2);
                    if (BasicOrSpecial == 1) {
                        wild.basicAttack(p, (int)(Math.random() * p.getNumBasicMenuItems()) + 1);
                    } else {
                        System.out.println(wild.specialAttack(p, (int)(Math.random() * p.getNumSpecialMenuItems()) + 1));
                    }
                    break;
                }
                else if (choice == 2 && p.getHp() != 0) {
                    //special attack menu
                    while (true && p.getHp() != 0 ) {
                        System.out.println(p.getSpecialMenu());
                        choice = CheckInput.getInt();
                        if (choice < p.getNumSpecialMenuItems()+1 && choice > 0) break;
                        System.out.println("Enter a number between 1 - " + p.getNumSpecialMenuItems());
                    }
                    System.out.println(p.specialAttack(wild, choice));

                    // Pokemon attacks.
                    int BasicOrSpecial = (int)(Math.random() * 2);
                    if (BasicOrSpecial == 1) {
                        wild.basicAttack(p, (int)(Math.random() * p.getNumBasicMenuItems()) + 1);
                    } else {
                        System.out.println(wild.specialAttack(p, (int)(Math.random() * p.getNumSpecialMenuItems()) + 1));
                    }
                    break;



                } else {
                    System.out.println("Enter either 1 or 2");
                    continue;
                }
            }
        }
        else if (fightMenuChoice == 2) {
            //uses a potion
            t.usePotion(pokeIndex);

            int BasicOrSpecial = (int)(Math.random() * 2);
            if (BasicOrSpecial == 1) {
                wild.basicAttack(p, (int)(Math.random() * p.getNumBasicMenuItems()) + 1);
            } else {
                System.out.println(wild.specialAttack(p, (int)(Math.random() * p.getNumSpecialMenuItems()) + 1));
            }
        } else if (fightMenuChoice == 3) {
            //attempt to catch pokemon
            boolean caught = t.catchPokemon(wild);
            // If the catching attempt is a fail, the pokemon attacks.
            if (caught == false) {
                int BasicOrSpecial =(int)(Math.random() * 2);
                if (BasicOrSpecial == 1) {
                    wild.basicAttack(p, (int)(Math.random() * p.getNumBasicMenuItems()) + 1);
                } else {
                    System.out.println(wild.specialAttack(p, (int)(Math.random() * p.getNumSpecialMenuItems()) + 1));
                }
            }

        } else if (fightMenuChoice == 4) {
            //try and run away
            int percentage = random.nextInt(100);
            boolean pass = false;
            //chance to run away
            if (percentage > 60) {
                System.out.println("You successfully ran away.");
                do {

                    switch ((int)(Math.random() * 4)) {
                        case 0:
                            //go direction and see if legal move
                            if (t.goEast() == '0') {
                                continue;
                            } else {
                                pass = true;
                                break;
                            }
                        case 1:
                            //go direction and see if legal move

                            if (t.goNorth() == '0') {
                                continue;
                            } else {
                                pass = true;
                                break;
                            }
                        case 2:
                            //go direction and see if legal move

                            if (t.goSouth() == '0') {
                                continue;
                            } else {
                                pass = true;
                                break;
                            }
                        case 3:
                            //go direction and see if legal move
                            if (t.goWest() == '0') {
                                continue;
                            } else {
                                pass = true;
                                break;
                            }
                    }
                } while (pass = false);
            } else {
                //failed to run away
                System.out.println("You failed to run away.");
                fightMenuChoice = 0;

                // Pokemon attacks.
                int BasicOrSpecial = (int)(Math.random() * 2);
                if (BasicOrSpecial == 1) {
                    wild.basicAttack(p, (int)(Math.random() * p.getNumBasicMenuItems()) + 1);
                } else {
                    System.out.println(wild.specialAttack(p, (int)(Math.random() * p.getNumSpecialMenuItems()) + 1));
                }
            }
        }


        return p;
    }

    /**
     * Allows the Trainer to shop at a store.
     * @param t is the trainer entering the store
     */
    public static void store(Trainer t) {
        System.out.println("Hello! What can I help you with?");
      /*
      1. Buy Potions - $5
      2. Buy Poke Balls - $3
      3. Exit
      */
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        int store = 0;
        do {
            //check if user wants the store or the hospital
            System.out.println("1. Store");
            System.out.println("2. Pokemon Hopstial");
            store = scanner.nextInt();
            switch (store) {
                case 2:
                    //hospital route
                    System.out.println(" Hello! Welcome to the Pokemon Hospital.");
                    System.out.println("I'll fix your poor pokemon up in a jiffy!");
                    System.out.println("There you go! See you again soon.");
                    t.healAllPokemon();
                    choice = 3;
                    break;
                //anything else aka the store
                default:
                    try {
                        //store menu
                        System.out.println("Hello! What can I help you with?");
                        System.out.println("1. Buy Potions - $5");
                        System.out.println("2. Buy Poke Balls - $3");
                        System.out.println("3. Exit");
                        choice = scanner.nextInt();
                        switch (choice) {
                            //check the user input
                            case 1:
                                //check if they can afford a potion
                                if (t.getMoney() >= 5) {
                                    //give potion
                                    System.out.println("Heres your potion.");
                                    t.spendMoney(5);
                                    t.recievePotion();
                                    continue;
                                }
                            case 2:
                                //check if htey can afford a pokeball
                                if (t.getMoney() >= 3) {
                                    //give pokeball
                                    System.out.println("Heres your pokeball");
                                    t.spendMoney(3);
                                    t.recievePokeball();
                                    continue;
                                }
                            case 3:
                                //user wanted to exit
                                System.out.println("Thank you, come again soon!");
                                break;
                            default:
                                continue;
                        }
                    } catch (NumberFormatException ex) {
                        //catch if the user tries to enter something else
                        System.out.println("Invalid input.");
                        continue;
                    }
            }
        } while (choice != 3);
    }
}