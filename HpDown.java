public class HpDown extends PokemonDecorator{
    /**
 * adds health debuff to a pokemon
 * @param p pokemon receiving debuff
 */
    public HpDown(Pokemon p)
    {
        super(p,"-HP",-1);
    }
}
