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
  public abstract String getSpecialMenu();
  public abstract int getNumSpecialMenuItems();
  public abstract String specialAttack(Pokemon p, int move);

  /**
   * Menu for basic attacks
   * @return Menu
   */
  public String getBasicMenu(){
    return "1. Slam\n2. Tackle\n3. Punch";
  }

  /**
   * Number of basic attacks
   * @return Number of basic attacks
   */
  public int getNumBasicMenuItems(){
    return 3;
  }

  /**
   * Basic attack
   * @param p Pokemon attacking
   * @param move Specific attack
   * @return Result of attack
   */
  public String basicAttack(Pokemon p, int move){
    return "";
  }

  /**
   * Attack Menu
   * @return Types of attacks
   * @param atkType
   */
  public String getAttackMenu(int atkType){
    return "1. Basic Attack\n2. Special Attack";
  }

  /**
   * Number of types of attacks
   * @return Number of types of attacks
   * @param atkType
   */
  public int getNumAttackMenuItems(int atkType){
    return 2;
  }

  /**
   * Slam basic attack
   * @param p Pokemon attacking
   * @return Result of attack
   */
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
  }
  public int getType(){
    return 0;
  }

  protected abstract String getAttackString(int atkType, int move);

  protected abstract int getAttackDamage(int atkType, int move);

  protected abstract double getAttackMultiplier(Pokemon p, int atkType);

  protected abstract int getAttackBonus(int atkType);
}
