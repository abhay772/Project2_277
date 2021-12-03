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
    public String getAttackTypeMenu(){
        return "1. Basic Attack\n2. Special Attack";
    }

    public int getNumAttackTypeMenuItems(){
        return 2;
    }

    public String getAttackMenu(int atkType){
        if(atkType == 1){
            return "1. Slam\n2. Tackle\n3. Punch";
        }
        else{

        }

    }

    public int getNumAttackMenuItems(int atkType){
        return atkType;
    }

    public String attack(Pokemon p, int atkType, int move){

    }

    public String getAttackString(int atkType, int move){
        if(atkType == 1) {
            switch (move) {
                case 1:
                    return "SLAMMED\n";
                case 2:
                    return "TACKLED\n";
                case 3:
                    return "PUNCHED\n";
            }
        }
        else{
            if(move == 1)
                return "used WATER GUN\n";
            else if(move == 2)
                return "used BUBBLE BEAM\n";
            else
                return "used WATERFALL\n";
            }
    }

    public int getType(){
        return 0;
    }

    public int getAttackDamage(int atkType, int move){

    }

    public double getAttackMultiplier(Pokemon p, int atkType){

    }

    public int getAttackBonus(int atkType){
        return atkType;
    }
}