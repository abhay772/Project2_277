public class AttackUp extends PokemonDecorator {
/**
     * adds attack buff to a pokemon and sends it to PokemonDecorator
     * @param p pokemon that is getting buffed
     */
    public AttackUp(Pokemon p){
        super(p, "+ATK",0);
    }
/**
     * Gets random number from 1-2 to add to damage because of the damage buff
     * @param atkType Type of attack: 1) Basic, 2) Special
     * @return random amount of damage to be added due to damage buff
     */
    public int getAttackBonus(int atkType){
        int bonus = (int)(Math.random() * 2) + 1;
        return super.getAttackBonus(atkType)+bonus;
    }
}
