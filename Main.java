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
        PokemonGenerator pokemonGenerator = PokemonGenerator.getInstance();
        int level = 1;
        Trainer trainer = null;
        boolean pass = false;

        while (!pass) {
            //text for Pokémon
            System.out.println("Choose your first pokemon: ");
            System.out.println("1. Charmander ");
            System.out.println("2. Bulbasaur ");
            System.out.println("3. Squirtle \n");

            try {
                switch (CheckInput.getIntRange(1,3)) {
                    //check what user entered and grant them their pokemon
                    case 1 -> {
                        Pokemon charmander = pokemonGenerator.getPokemon("Charmander");
                        trainer = new Trainer(trainerName, charmander);
                        pass = true;
                    }
                    case 2 -> {
                        Pokemon bulbasaur = pokemonGenerator.getPokemon("Bulbasaur");
                        trainer = new Trainer(trainerName, bulbasaur);
                        pass = true;
                    }
                    case 3 -> {
                        Pokemon squirtle = pokemonGenerator.getPokemon("Squirtle");
                        trainer = new Trainer(trainerName, squirtle);
                        pass = true;

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

                    Pokemon wild = pokemonGenerator.generateRandomPokemon(level);
                    System.out.println("A wild "+wild.getName() +" Pokemon has appeared.");
                    Pokemon trainerPokemon = trainer.getPokemon(0);

                    int numOfWildFightChoices = 4;

                    int fightMenuChoice = 0;

                    String wildFightChoice = """
                                            What do you want to do?\s
                                            1. Fight
                                            2. Use Potion
                                            3. Throw Poke Ball
                                            4. Run Away
                                            """;
                    while(true){

                        System.out.println(wildFightChoice);
                        fightMenuChoice = CheckInput.getIntRange(1,numOfWildFightChoices) ;

                        if (fightMenuChoice == 1) {
                            trainerPokemon = trainerAttack(trainer, wild);

                            if(trainerPokemon == null)
                            {
                                int atktype = (int) (Math.random() * wild.getNumAttackTypeMenuItems());
                                int move = (int) (Math.random() * wild.getNumAttackMenuItems(atktype));

                                int damage = (int) Math.round((wild.getAttackDamage(atktype, move) + wild.getAttackBonus(atktype)));
                                System.out.println(trainerName+" got attacked by the Wild Pokemon and took "+ damage);
                            }

                            if(trainerPokemon.getHp() == 0)
                            {
                                System.out.println("Your "+trainerPokemon.getName()+" fainted");

                                for(int i=0;i<trainer.getNumPokemon();i++)
                                {
                                    if(trainer.getPokemon(i).getHp() != 0)
                                    {
                                        trainerPokemon = trainer.getPokemon(i);
                                    }
                                }

                                System.out.println("You sent out "+trainerPokemon.getName());
                            }

                        }

                        else if (fightMenuChoice == 2) {
                            //On potion use
                            //heals the Pokemon to full
                            trainerPokemon.heal();
                            //and applies at random a buff
                            //trainerPokemon = (((int) (Math.random() * 2)) + 1) > 1 ? (new AttackUp(trainerPokemon)) : (new HpUp(trainerPokemon));
                            trainerPokemon = pokemonGenerator.addRandomBuff(trainerPokemon);

                            // wild Pokemon attacks.
                            int atktype = (int) (Math.random() * wild.getNumAttackTypeMenuItems());
                            int move = (int) (Math.random() * wild.getNumAttackMenuItems(atktype));

                            System.out.println(wild.attack(trainerPokemon,atktype,move));

                            // chance of debuff to the trainer Pokemon
                            if ((int) (Math.random() * 100) < 10) {
                                trainerPokemon = pokemonGenerator.addRandomDebuff(trainerPokemon);

                            }
                        }

                        else if (fightMenuChoice == 3) {
                            //attempt to catch Pokemon
                            boolean caught = trainer.catchPokemon(wild);

                            // If the catching attempt is a fail, the Pokemon attacks.
                            if (!caught) {
                                // Pokemon attacks.
                                int atktype = (int) (Math.random() * wild.getNumAttackTypeMenuItems());
                                int move = (int) (Math.random() * wild.getNumAttackMenuItems(atktype));

                                System.out.println(wild.attack(trainerPokemon,atktype,move));

                                // chance of Debuff to the trainer Pokemon
                                if ((int) (Math.random() * 100) < 10) {
                                    trainerPokemon = pokemonGenerator.addRandomDebuff(trainerPokemon);
                                }
                            }

                        } else if (fightMenuChoice == 4) {
                            //try and run away
                            int percentage = (int)(Math.random() * 100);
                            pass = false;

                            //chance to run away
                            if (percentage > 60) {
                                System.out.println("You successfully ran away.");
                                while (!pass) {
                                    switch ((int)(Math.random() * 4)) {
                                        case 0:
                                            //go direction and see if legal move
                                            if (trainer.goEast() != '0') {
                                                pass = true;
                                            }
                                        case 1:
                                            //go direction and see if legal move

                                            if (trainer.goNorth() == '0') {
                                                pass = true;
                                            }
                                        case 2:
                                            //go direction and see if legal move

                                            if (trainer.goSouth() == '0') {
                                                pass = true;
                                            }
                                        case 3:
                                            //go direction and see if legal move
                                            if (trainer.goWest() !=  '0') {
                                                pass = true;
                                            }
                                    }
                                }
                            } else {
                                //failed to run away
                                System.out.println("You failed to run away.");

                                // Pokemon attacks.
                                int atktype = (int) (Math.random() * wild.getNumAttackTypeMenuItems());
                                int move = (int) (Math.random() * wild.getNumAttackMenuItems(atktype));

                                System.out.println(wild.attack(trainerPokemon,atktype,move));

                                // chance of debuff to the trainer Pokemon
                                if ((int) (Math.random() * 100) < 10) {
                                    trainerPokemon = pokemonGenerator.addRandomDebuff(trainerPokemon);
                                }
                            }
                        }
                        if(pass)
                        {break;}
                    }

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
                    Pokemon gymPokemon = pokemonGenerator.generateRandomPokemon(level+2);
                    trainerPokemon = trainer.getPokemon(0);

                    int numOfGymFightChoices = 2;
                    String gymFightChoices = """
                                            What do you want to do?\s
                                            1. Fight
                                            2. Use Potion
                                            """;

                    while(true){

                        System.out.println(gymFightChoices);
                        fightMenuChoice = CheckInput.getIntRange(1,numOfGymFightChoices) ;

                        if (fightMenuChoice == 1) {
                            trainerPokemon = trainerAttack(trainer, gymPokemon);

                            if(trainerPokemon == null)
                            {
                                System.out.println("You left the Gym.");
                                break;
                            }

                            if(trainerPokemon.getHp() == 0)
                            {
                                System.out.println("Your "+trainerPokemon.getName()+" fainted");

                                for(int i=0;i<trainer.getNumPokemon();i++)
                                {
                                    if(trainer.getPokemon(i).getHp() != 0)
                                    {
                                        trainerPokemon = trainer.getPokemon(i);
                                    }
                                }

                                System.out.println("You sent out "+trainerPokemon.getName());
                            }

                        } else {
                            //On potion use
                            int index = trainer.getPokemonList().indexOf(String.valueOf(trainerPokemon));
                            trainer.usePotion(index+1);

                            // Gym Pokemon attacks.
                            int atktype = (int) (Math.random() * gymPokemon.getNumAttackTypeMenuItems());
                            int move = (int) (Math.random() * gymPokemon.getNumAttackMenuItems(atktype));

                            System.out.println(trainerPokemon.attack(gymPokemon,atktype,move));

                            // chance of Debuff to the trainer Pokemon
                            if ((int) (Math.random() * 100) < 10) {
                                trainerPokemon = ((int) (Math.random() * 100)) > 50 ? (new AttackDown(trainerPokemon)) : (new HpDown(trainerPokemon));
                            }
                        }
                    }

                    if(gymPokemon.getHp() == 0) {
                        switch (level % 3) {
                            case 1:
                                map.loadMap(1);
                            case 2:
                                map.loadMap(2);
                            case 0:
                                map.loadMap(3);
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
     */
    public static Pokemon trainerAttack(Trainer t, Pokemon wild){

        //loop battle system
        Pokemon p = null;

        for(int i=0;i<t.getNumPokemon();i++)
        {
            if(t.getPokemon(i).getHp() != 0)
            {
                p = t.getPokemon(i);
            }
        }

        if (p == null)
        {
            System.out.println("All of your Pokemon are at 0 hp.\n");
            return p;
        }

        //attack menu

        /*
        1. Basic Attack
        2. Special Attack
        */

        System.out.println(p.getAttackTypeMenu());
        int atktype = CheckInput.getIntRange(1,p.getNumAttackTypeMenuItems());

        // player attacks
        // attack menu and choice of move by the player
        System.out.println(p.getAttackMenu(atktype));
        int move = CheckInput.getIntRange(1, p.getNumAttackMenuItems(atktype));

        System.out.println(p.attack(wild, atktype,move));

        // chance of debuff to the wild pokemon
        if((int)(Math.random() * 100) < 25)
        {
            wild = ((int)(Math.random() * 100))  > 50 ? (new AttackDown(wild)) : (new HpDown(wild));
        }

        //Checking if the wild pokemon is alive
        if(wild.getHp()<1){return p;}

        // Pokemon attacks.
        atktype = (int)(Math.random() * wild.getNumAttackTypeMenuItems());
        move = (int)(Math.random() * wild.getNumAttackMenuItems(atktype));

        System.out.println(wild.attack(p,atktype,move));

        // chance of debuff to the trainer Pokemon
        if((int)(Math.random() * 100) < 10)
        {
            p = ((int)(Math.random() * 100))  > 50 ? (new AttackDown(p)) : (new HpDown(p));
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