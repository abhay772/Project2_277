public abstract class PokemonDecorator extends Pokemon {


    protected Pokemon pokemon;

    /**
     * Constructor for a Pokemon Decorator
     *
     * @param p Pokemon to be decorated with a (de)buff(s)
     * @param extraName description of the buffs and debuffs to be added to the Name of the Pokemon
     * @param extraHp hp change to be added to the Pokemon
     */
    public PokemonDecorator(Pokemon p, String extraName, int extraHp) {
        super(p.getName()+extraName,p.getHp(),p.getMaxHp()+extraHp);
        pokemon = p;
    }

    /**
     * Returns the selected attack menu
     *
     * @param atkType Type of attack: 1) Basic, 2) Special
     * @return attack menu for the given attack type
     */
    public String getAttackMenu(int atkType)
    {
        return pokemon.getAttackMenu(atkType);
    }

    /**
     * Returns the number of available choices for the attack menu
     *
     * @param atkType Type of attack: 1) Basic, 2) Special
     * @return number of available choices in the attack menu for the given attack type
     */
    public int getNumAttackMenuItems(int atkType)
    {
        return pokemon.getNumAttackMenuItems(atkType);
    }

    /**
     * Returns the descriptor of the selected move
     *
     * @param atkType Type of attack: 1) Basic, 2) Special
     * @param move Choice of a move from the attack menu
     * @return descriptor of the selected move
     */
    public String getAttackString(int atkType,int move)
    {
        return pokemon.getAttackString(atkType,move);
    }

    /**
     * Returns the damage of the selected move
     *
     * @param atkType Type of attack: 1) Basic, 2) Special
     * @param move Choice of a move from the attack menu
     * @return damage of the selected move
     */
    public int getAttackDamage(int atkType,int move)
    {
        return pokemon.getAttackDamage(atkType,move);
    }

    /**
     * Returns the damage multiplier of the selected move against Pokemon p
     *
     * @param p Pokemon that is being that is receiving the damage
     * @param atkType Type of attack: 1) Basic, 2) Special
     * @return damage multiplier of the selected move against Pokemon p
     */
    public double getAttackMultiplier(Pokemon p,int atkType)
    {
        if(atkType==1)
        {return 1;}
        return pokemon.getAttackMultiplier(p,atkType);
    }

    /**
     * Returns any bonuses to damage based on the type of damage
     *
     * @param atkType Type of attack: 1) Basic, 2) Special
     * @return any bonuses to damage based on the type of damage
     */
    public int getAttackBonus(int atkType)
    {
        return 0;
    }

    public int getType()
    {
        if(pokemon instanceof Fire)
        {return 0;}
        else if(pokemon instanceof Water)
        {return 1;}
        else if(pokemon instanceof Grass)
        {return 2;}
        else
        {return -1;}
    }
}
