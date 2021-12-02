import java.util.Scanner;
import java.util.Random;
import java.io.FileNotFoundException;

/**
 *Date: 10/21/2021
 *Project 1
 *@author ESPINOSA, ARMANDO ,SOLANKI, ABHAY
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(System.in);
        //favortext
        System.out.println("Prof. Oak: Hello there new trainer, what is your name?");
        String trainerName = in .nextLine();
        System.out.println("Great to meet you, " + trainerName);
        System.out.println("And no I won't send you into danger, just so you can get your first pokemon.");

        Map map = Map.getInstance();
        Trainer trainer = null;

        boolean cont = true;
        while (cont == true) {
            //text for pokemon
            System.out.println("Choose your first pokemon: ");
            System.out.println("1. Charmander ");
            System.out.println("2. Bulbasaur ");
            System.out.println("3. Squirtle \n");

            try {
                switch ( in .nextInt()) {
                    //check what user entered and grant them their pokemon
                    case 1:
                        Pokemon charmander = new Charmander();
                        trainer = new Trainer(trainerName, charmander, map);
                        cont = false;
                        break;
                    case 2:
                        Pokemon bulbasaur = new Bulbasaur();
                        trainer = new Trainer(trainerName, bulbasaur, map);
                        cont = false;
                        break;
                    case 3:
                        Pokemon squirtle = new Squirtle();
                        trainer = new Trainer(trainerName, squirtle, map);
                        cont = false;
                        break;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Error:" + ex);
                System.out.println("Invalid input.");
                continue;
            }
        }
        int selection = 0;
        do {
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
            Random random = new Random();
            int chance = random.nextInt(100);
            //System.out.println("character:" + map.getCharAtLoc(trainer.getLocation()));
            char place = map.getCharAtLoc(trainer.getLocation());
            switch (place) {

                //n nonthing
                //c city
                //f finish
                //s start
                //i item
                //p person - is removed after defeated and we can make them do whatever
                case 'w':
                    trainerAttack(trainer, chooseRandomPokemon());
                    map.removeCharAtLoc(trainer.getLocation());

                case 'c':
                    store(trainer);
                case 'p':
                    int rs = random.nextInt(3);
                    if (rs == 1) {
                        System.out.println("You found a pokeball and a potion in the wild.");
                        trainer.recievePotion();
                        trainer.recievePokeball();

                    } if (rs == 2) {
                    System.out.println("You found 5 dollars on the ground.(Theif!!)");
                    trainer.recieveMoney(5);
                }if (rs == 3) {
                    String[] DamagingEvents = {
                            "You were jumped by team rocket",
                            "You fell in a diglet hole.",
                            "Somebody tazed you!",
                            "You stumbled into an sleeping Ancient Charmander."
                    };

                    int messageSelection = random.nextInt(DamagingEvents.length);
                    System.out.println(DamagingEvents[messageSelection]);
                    System.out.println("You take 10 damage.");
                    trainer.takeDamage(10);
                    map.removeCharAtLoc(trainer.getLocation());

                }
                case 'f':
                    System.out.println("You've found the finish...");

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


                default:
            }
        } while (selection != 5);

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

    /**
     * This method gives you a random pokemon
     * @return Returns a random Pokemon.
     */
    public static Pokemon chooseRandomPokemon() {
        Pokemon[] listOfPokemons = {
                new Charmander(),
                new Bulbasaur(),
                new Squirtle(),
                new Ponyta(),
                new Staryu(),
                new Oddish()
        };
        Random random = new Random();
        int selection = random.nextInt(6);

        return listOfPokemons[selection];

    }

    //BATTLE LOOP
    /**
     * This method allows a Trainer and a wild pokemon to battle.
     * @param t is the Trainer battling.
     * @param wild is the the wild pokemon.
     * @exception FileNotFoundException throws an Exception if it fails to find the file for the next map.
     */
    public static void trainerAttack(Trainer t, Pokemon wild) throws FileNotFoundException {

        Random random = new Random();
        Scanner in = new Scanner(System.in);
        int fightMenuChoice = 0;
        //loop battle system
        System.out.println("A wild " + wild.name + " has appeared.");
        System.out.println("Choose your pokemon\n");
        Pokemon p;
        int pokeIndex;

        while (true) {
            try {
                //get pokemon you wish to use
                System.out.println(t.getPokemonList());
                pokeIndex = in .nextInt();
                p = t.getPokemon(pokeIndex - 1);
                break;
            } catch (Exception e) {
                System.out.println("Enter an valid selection number, between 1 - " + t.getPokemonList().length());
            }
        }

        while (fightMenuChoice != 4) {
            //fight menu
            System.out.println("What do you want to do? " +
                    "\n1. Fight" +
                    "\n2. Use Potion" +
                    "\n3. Throw Poke Ball" +
                    "\n4. Run Away");

            //get the choice
            fightMenuChoice = in.nextInt();
            
            boolean pokemonAlive = false;
            
            for(int i=0;i<t.getNumPokemon();i++)
            {
                if(t.getPokemon(i).getHp() != 0)
                {pokemonAlive = true;
                    break;}
            }

            if (fightMenuChoice == 1) {

          /*
          1. Basic Attack
          2. Special Attack
          */

                if (!pokemonAlive)
                    {System.out.println("All of your Pokemon are at 0 hp.\nYou can run away or use a potion.\n");}
                
                while(wild.getHp() != 0 && p.getHp() != 0) {
                    //attack menu
                    System.out.println(p.getAttackMenu(atkType));
                    int choice = in.nextInt();

                    if (choice == 1) {

                        while (true && p.getHp() != 0) {

                            //basic menu
                            System.out.println(p.getBasicMenu());
                            choice = in.nextInt();

                            if (choice < p.getNumBasicMenuItems()+1 && choice > -1) break;
                            System.out.println("Enter a number between 1 - " + p.getNumBasicMenuItems()+"\n");
                        }
                        p.basicAttack(wild, choice);

                        // Pokemon attacks.
                        int BasicOrSpecial = random.nextInt(2);
                        if (BasicOrSpecial == 1) {
                            wild.basicAttack(p, random.nextInt(p.getNumBasicMenuItems()) + 1);
                        } else {
                            System.out.println(wild.specialAttack(p, random.nextInt(p.getNumSpecialMenuItems()) + 1));
                        }
                        break;
                    }
                    else if (choice == 2 && p.getHp() != 0) {
                        //special attack menu
                        while (true && p.getHp() != 0 ) {
                            System.out.println(p.getSpecialMenu());
                            choice = in.nextInt();
                            if (choice < p.getNumSpecialMenuItems()+1 && choice > 0) break;
                            System.out.println("Enter a number between 1 - " + p.getNumSpecialMenuItems());
                        }
                        System.out.println(p.specialAttack(wild, choice));

                        // Pokemon attacks.
                        int BasicOrSpecial = random.nextInt(2);
                        if (BasicOrSpecial == 1) {
                            wild.basicAttack(p, random.nextInt(p.getNumBasicMenuItems()) + 1);
                        } else {
                            System.out.println(wild.specialAttack(p, random.nextInt(p.getNumSpecialMenuItems()) + 1));
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

                int BasicOrSpecial = random.nextInt(2);
                if (BasicOrSpecial == 1) {
                    wild.basicAttack(p, random.nextInt(p.getNumBasicMenuItems()) + 1);
                } else {
                    System.out.println(wild.specialAttack(p, random.nextInt(p.getNumSpecialMenuItems()) + 1));
                }
            } else if (fightMenuChoice == 3) {
                //attempt to catch pokemon
                boolean caught = t.catchPokemon(wild);
                // If the catching attempt is a fail, the pokemon attacks.
                if (caught == false) {
                    int BasicOrSpecial = random.nextInt(2);
                    if (BasicOrSpecial == 1) {
                        wild.basicAttack(p, random.nextInt(p.getNumBasicMenuItems()) + 1);
                    } else {
                        System.out.println(wild.specialAttack(p, random.nextInt(p.getNumSpecialMenuItems()) + 1));
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

                        switch (random.nextInt(4)) {
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
                    int BasicOrSpecial = random.nextInt(2);
                    if (BasicOrSpecial == 1) {
                        wild.basicAttack(p, random.nextInt(p.getNumBasicMenuItems()) + 1);
                    } else {
                        System.out.println(wild.specialAttack(p, random.nextInt(p.getNumSpecialMenuItems()) + 1));
                    }
                }
            }
        }
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