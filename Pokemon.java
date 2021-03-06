/**
 * @author Nathanael Wolski
 * @author Sharvay Ajit
 */
public abstract class Pokemon extends Entity {
    /**
     * Effectiveness values for battling
     */
    public static final double [][] battleTable = {{1,.5,2},{2,1,.5},{.5,2,1}};

    /**
     * Creates a pokemon
     * @param n Name of the pokemon
     * Sends name and max hp to entity class
     */
    public Pokemon(String n,int h, int m){
        super(n,h,m);
    }
/**
     * choices for type of attack
     * @return types of attacks
     */
    public String getAttackTypeMenu(){
        return "1. Basic Attack\n2. Special Attack";
    }
/**
     * number of types of attacks
     * @return amount of attack types
     */
    public int getNumAttackTypeMenuItems(){
        return 2;
    }
/**
     * basic attack menu
     * @param atkType type of attack
     * @return menu displaying attacks
     */
    public String getAttackMenu(int atkType)
    {
        return "1. Slam\n2. Tackle\n3. Punch";
    };
    
    /**
     * number of attacks
     * @param atkType type of attack
     * @return number of attacks
     */
    public int getNumAttackMenuItems(int atkType)
    {
        return 3;
    };
/**
     * calculates damage from attack and applies it to the pokemon
     * @param p pokemon attacking
     * @param atkType attack type
     * @param move selects the move to use
     * @return result of the attack
     */
    public String attack(Pokemon p, int atkType, int move)
    {
        int damage = (int) Math.round((this.getAttackDamage(atkType, move) + this.getAttackBonus(atkType))
                * this.getAttackMultiplier(p, atkType));
        p.takeDamage(damage);
        return this.getName() + this.getAttackString(atkType, move) + p.getName() + " and dealt " + damage;
    }

    public String getAttackString(int atkType, int move)
    {

        System.out.println("Inside Pokemon");

        return switch (move) {
            case 1 -> " SLAMMED ";
            case 2 -> " TACKLED ";
            case 3 -> " PUNCHED ";
            default -> throw new IllegalStateException("Unexpected value: " + move);
        };
    }

    public int getType()
    {
        if(this instanceof Fire)
        {return 0;}
        else if(this instanceof Water)
        {return 1;}
        else if(this instanceof Grass)
        {return 2;}
        else
        {return -1;}
    }

    public int getAttackDamage(int atkType, int move)
    {

        return switch (move) {
            case 1 -> (int) (Math.random() * 4);
            case 2 -> (int) (Math.random() * 4) + 1;
            default -> (int) (Math.random() * 3) + 1;
        };
    }

    public double getAttackMultiplier(Pokemon p, int atkType)
    {
        if(atkType==1)
        {return 1;}
        return this.getAttackMultiplier(p,atkType);
    };

    public int getAttackBonus(int atkType)
    {
        return 0;
    };
}



/*/**
 * Menu for basic attacks
 * @return Menu
 */
    /*public String getBasicMenu(){
        return "1. Slam\n2. Tackle\n3. Punch";
    }

    /**
     * Number of basic attacks
     * @return Number of basic attacks
     */
    /*public int getNumBasicMenuItems(){
        return 3;
    }

    /**
     * Basic attack
     * @param p Pokemon attacking
     * @param move Specific attack
     * @return Result of attack
     */
    /*public String basicAttack(Pokemon p, int move){
        return "";
    }

    /**
     * Attack Menu
     * @return Types of attacks
     * @param atkType
     */
    /*public String getAttackMenu(int atkType){
        return "1. Basic Attack\n2. Special Attack";
    }

    /**
     * Number of types of attacks
     * @return Number of types of attacks
     * @param atkType
     */
    /*public int getNumAttackMenuItems(int atkType){
        return 2;
    }

    /**
     * Slam basic attack
     * @param p Pokemon attacking
     * @return Result of attack
     *//*
    public String slam(Pokemon p){
        int damage = (int)(Math.random() * 6);
        return "The base damage from this attack is " + damage;
    }
    public String tackle(Pokemon p){
        int damage = (int)(Math.random() * 2) + 2;
        return "The base damage from this attack is " + damage;
    }
    public String punch(Pokemon p){
        int damage = (int)(Math.random() * 4) + 1;
        return "The base damage from this attack is " + damage;
    }*/
