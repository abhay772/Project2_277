public class AttackUp extends PokemonDecorator {

    public AttackUp(Pokemon p){
        super(p, "+ATK",0);
    }

    public int getAttackBonus(int atkType){
        int bonus = (int)(Math.random() * 2) + 1;
        return super.getAttackBonus(atkType)+bonus;
    }
}
