public class AttackDown extends PokemonDecorator {
    public AttackDown(Pokemon p){
        super(p, "-ATK", -1);
    }
    public int getAttackBonus(int atkType){
        int bonus = (int)(Math.random() * (-2)) - 1;
        return super.getAttackBonus(bonus);
    }
}
