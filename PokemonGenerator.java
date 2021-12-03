import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Nathanael Wolski
 */
public class PokemonGenerator {

    private HashMap<String, String> pokemon = new HashMap<String, String>();
    private static PokemonGenerator instance = null;
    private PokemonGenerator(){
        String file = "src/PokemonList.txt";
        try {
            Scanner read = new Scanner(new File(file));
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] lineSplit = line.split(",");
                pokemon.put(lineSplit[0], lineSplit[1]);
            }
        }
        catch(FileNotFoundException e){
            System.out.print(e);
        }
    }
    public static PokemonGenerator getInstance(){
        if(instance == null)
            return instance = new PokemonGenerator();
        else
            return instance;
    }
    public Pokemon generateRandomPokemon(int level){
        List<String> val = new ArrayList<String>(pokemon.values());
        Pokemon newPokemon = null;
        int ranIndex = new Random().nextInt(val.size());
        int mH = (int)(Math.random() * 6) + 20;
        String pokName = pokemon.get(val.get(ranIndex));
        if(pokemon.get(pokName).equals(("Fire")))
            newPokemon = new Fire(pokName, mH, mH);
        else if(pokemon.get(pokName).equals(("Water")))
            newPokemon = new Water(pokName, mH, mH);
        else if(pokemon.get(pokName).equals(("Grass")))
            newPokemon = new Grass(pokName, mH, mH);
        for(int i = 0; i < level - 1; i++){
            newPokemon = this.addRandomBuff(newPokemon);
        }
        return newPokemon;
    }
    public Pokemon getPokemon(String name){
        Pokemon newPokemon;
        int mH = (int)(Math.random() * 6) + 20;
        String type = pokemon.get(name);
        if(type.equals(("Fire")))
            return newPokemon = new Fire(name, mH, mH);
        else if(type.equals(("Water")))
            return newPokemon = new Water(name, mH, mH);
        else if(type.equals(("Grass")))
            return newPokemon = new Grass(name, mH, mH);
    }
    public Pokemon addRandomBuff(Pokemon p){
        if(((new Random().nextInt(2)) == 1)){
            p = new AttackUp(p);
        }
        else{
            p = new HpUp(p);
        }
        return p;
    }
    public Pokemon addRandomDebuff(Pokemon p){
        if(((new Random().nextInt(2)) == 1)){
            p = new AttackDown(p);
        }
        else{
            p = new HpDown(p);
        }
        return p;
    }
}
