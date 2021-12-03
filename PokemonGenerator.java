import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map;


public class PokemonGenerator {

    private HashMap<String, String> pokemon = new HashMap<String, String>();
    private static PokemonGenerator instance = null;

    private PokemonGenerator(){
        String file = "PokemonList.txt";
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
    public Pokemon generateRandomPokemon(int level) {


        Iterator<Map.Entry<String, String>> pokeIterator = pokemon.entrySet().iterator();
        int randIndex = (int) (Math.random() * pokemon.size());

        int counter = 0;

        Map.Entry mapElement = null;
        while (pokeIterator.hasNext() && counter != randIndex) {
            mapElement = pokeIterator.next();
            counter++;
        }

        assert mapElement != null;
        return getPokemon((String) mapElement.getKey());
    }

    public Pokemon getPokemon(String name){

        Pokemon newPokemon = null;
        int mH = (int)(Math.random() * 6) + 20;
        String type = pokemon.get(name);
        switch (type) {
            case ("Fire") -> newPokemon = new Fire(name, mH, mH);
            case ("Water") -> newPokemon = new Water(name, mH, mH);
            case ("Grass") -> newPokemon = new Grass(name, mH, mH);
        }
        return newPokemon;
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


