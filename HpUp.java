public class HpUp extends PokemonDecorator{
/**
 * adds health buff to a pokemon
 * @param p pokemon receiving buff
 */
    public HpUp(Pokemon p)
    {
        super(p,"+HP",(int)(Math.random() * 2) + 1);
    }
}
