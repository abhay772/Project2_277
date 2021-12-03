public class AttackDown extends PokemonDecorator {
/**
     * adds attack debuff to a pokemon and sends it to PokemonDecorator
     * @param p pokemon that is getting debuffed
     */
    public AttackDown(Pokemon p){
        super(p, "-ATK", 0);
    }
/**
     * Gets random number from 1-2 to subtract from damage because of the damage debuff
     * @param atkType Type of attack: 1) Basic, 2) Special
     * @return random amount of damage to be subtracted due to damage buff
     */
    public int getAttackBonus(int atkType){
        return super.getAttackBonus(atkType)-1;
    }
}
