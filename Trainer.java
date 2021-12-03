import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Trainer extends Entity {

    private int money;
    private int potions;
    private int pokeballs;
    private Point loc;
    private Map map;
    private ArrayList < Pokemon > pokemon = new ArrayList < Pokemon > ();

    /**
     * Default constructor for the Trainer class which
     * intializes Starting money, potions, pokeballs, map and pokemon.
     * @param n This is the name of the Trainer
     * @param p This is the starting pokemon
     */
    Trainer(String n, Pokemon p) {
        super(n, 25, 25);
        pokemon.add(p);
        this.money = 25;
        this.pokeballs = 5;
        this.potions = 1;
        loc = map.findStart();
    }

    /**
     * returns the current amount of money Trainer has.
     * @return returns the current amount of money Trainer has.
     */
    public int getMoney() {
        return this.money;
    }

    /**
     * Subtracts the amount passed in from Trainer's money
     * @param amt, Amount to spend.
     */
    public boolean spendMoney(int amt) {
        //check if you have enough money
        if (amt <= this.money) {
            this.money -= amt;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds the amount passed in to Trainer's money.
     * @param amt, Amount of moeny to recieve
     */
    public void recieveMoney(int amt) {
        this.money += amt;
    }

    /**
     * Checks if the Trainer has a potion.
     * @return Returns true if the Trainer has a potion, false if not.
     */
    public boolean hasPotion() {
        //check if user has a potion
        if (this.potions > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a potion to Trainer's potion bag.
     */
    public void recievePotion() {
        this.potions += 1;
    }

    /**
     * Heals the Pokemon at the given index to full.
     * @param pokeindex, Index of the Pokemon to heal.
     */
    public void usePotion(int pokeindex) {
        //use potion on pokemon
        if (hasPotion()) {
            pokemon.get(pokeindex-1).heal();
            this.potions -= 1;
        }
        PokemonGenerator.getInstance().addRandomBuff(pokemon.get(pokeindex-1));
    }

    /**
     * Checks if the Trainer has any pokeballs.
     * @return true if the Triner has a pokeball, false if not.
     */
    public boolean hasPokeball() {
        //check if you have any pokeballs
        if (this.pokeballs > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds a pokeball to the Trainer's bag.
     */
    public void recievePokeball() {
        this.pokeballs += 1;
    }

    /**
     * This method gives the Trainer a chance to catch a pokemon.
     * The difficulty of catching the Pokemon depends on its remaining hp.
     * @param p, the Pokemon to catch.
     * @return true if the Pokemon was caught, false if not.
     */
    public boolean catchPokemon(Pokemon p) {
        Random random = new Random();
        int percentage = random.nextInt(100) + 1;
        double missRate = (p.getHp() / (double) p.getMaxHp()) * 100.0;
        //catch rate
        if (percentage > missRate) {
            //caught
            pokeballs -= 1;
            pokemon.add(p);
            System.out.println("Shake...Shake...Shake...");
            System.out.println("Your caught " + p.name);
            return true;
        } else {
            //not caught
            pokeballs -= 1;
            System.out.println("Shake...Shake...Shake...");
            System.out.println(p.name + " got away");
            return false;
        }
    }

    /**
     * Returns the current location of the Trainer.
     * @return  Returns the current location of the Trainer.
     */
    public Point getLocation() {
        return loc;
    }

    /**
     * Moves the Trainer to the immediate North tile on the Map, if it is not out of bounds.
     * @return Returns the event character if the move was valid, and '0' if the move failed.
     */
    public char goNorth(){
        //user goes north
        if (loc.x > 0) {
            map.reveal(this.loc);
            loc.x -= 1;
            return map.getCharAtLoc(loc);
        } else {
            //illegal move
            System.out.println("A mysterious force is blocking you!");
            return '0';
        }
    }

    /**
     * Moves the Trainer to the immediate East tile on the Map, if it is not out of bounds.
     * @return Returns the event character if the move was valid, and '0' if the move failed.
     */
    public char goEast(){
        //user goes east
        if (loc.y < 4) {
            map.reveal(this.loc);
            loc.y += 1;
            return map.getCharAtLoc(loc);
        } else {
            //illegal move
            System.out.println("A mysterious force is blocking you!");
            return '0';
        }
    }

    /**
     * Moves the Trainer to the immediate South tile on the Map, if it is not out of bounds.
     * @return Returns the event character if the move was valid, and '0' if the move failed.
     */
    public char goSouth(){
        //user goes south
        if (loc.x < 4) {
            map.reveal(this.loc);
            loc.x += 1;
            return map.getCharAtLoc(loc);
        } else {
            //illegal move
            System.out.println("A mysterious force is blocking you!");
            return '0';
        }

    }

    /**
     * Moves the Trainer to the immediate West tile on the Map, if it is not out of bounds.
     * @return Returns the event character if the move was valid, and '0' if the move failed.
     */
    public char goWest(){
        if (loc.y > 0) {
            map.reveal(this.loc);
            loc.y -= 1;
            return map.getCharAtLoc(loc);
        } else {
            System.out.println("A mysterious force is blocking you!");
            return '0';
        }
    }

    /**
     * Returns the number of Pokemon with the Trainer.
     * @return Returns the number of Pokemon with the Trainer.
     */
    public int getNumPokemon() {
        return pokemon.size();
    }

    /**
     * Heals all the Pokemon with the Trainer.
     */
    public void healAllPokemon() {
        for (int i = 0; i < pokemon.size(); i++) {
            pokemon.get(i).heal();
        }
    }

    public void buffAllPokemon() {
        for(Pokemon p: pokemon) {
            p = PokemonGenerator.getInstance().addRandomBuff(p);
        }
    }

    public void debuffAllPokemon() {
        for(Pokemon p: pokemon) {
            p = PokemonGenerator.getInstance().addRandomDebuff(p);
        }
    }

    /**
     * Returns the Pokemon at the given index.
     * @param index is the index of the Pokemon to return.
     * @return Returns the Pokemon at the given index.
     */
    public Pokemon getPokemon(int index) {
        return pokemon.get(index);
    }

    /**
     * Returns the list of all Pokemon with the Trainer.
     * @return  List of all the pokemon with the Trainer..
     */
    public String getPokemonList() {

        StringBuilder display = new StringBuilder();
        for (int i = 0; i < this.getNumPokemon(); i++) {
            display.append(i + 1).append(". ").append(this.getPokemon(i)).append("\n");
        }
        return display.toString();
    }

    public Pokemon removePokemon(int index)
    {
        Pokemon temp = pokemon.get(index-1);
        pokemon.remove(index-1);
        return temp;
    }

    /**
     * Return a string displaying all the information of the Trainer, and the Map with current location at the end.
     * @return Return a string displaying all the information of the Trainer, and the Map with current location at the end.
     */
    @Override
    public String toString() {
        String output = String.format("%s HP: %d / %d\nMoney: %d\nPotions: %d\nPoke Balls: %d\n---Pokemon---\n%s", this.name, this.getHp(),
                this.getMaxHp(), getMoney(), this.potions, this.pokeballs, getPokemonList());
        //we added the map under
        output += "\nMap: \n"+map.mapToString(getLocation());
        return output;
    }

}