import java.lang.Math;
import java.util.Random;
import java.util.Scanner;

public abstract class Pokemon extends Entity {

  public static final double[][] battleTable = {
    {
      1,
      .5,
      2
    },
    {
      2,
      1,
      .5
    },
    {
      .5,
      2,
      1
    }
  };
  private Random random = new Random();
  private Scanner in = new Scanner(System.in);

  /**
   *Pokemon constructor
   *@param n sets teh name of the pokemon
   */
  public Pokemon(String n) {
    super(n, (int)(Math.random() * (25 - 20 + 1) + 20));
  }
  /**
   * Get special menu
   *@return String of the special menu
   */
  public abstract String getSpecialMenu();
  /**
   *Get the number of special menu Special Items
   *@return int of number of special menu items
   */

  public abstract int getNumSpecialMenuItems();
  /**
   *Special attacks a pokemon
   *@param p the selected pokemon
   *@param move the selected move
   *@return String the for the special attack
   */
  public abstract String specialAttack(Pokemon p, int move);
  /**
   * Displays teh basic menu
   *@return returns the menu string
   */
  public String getBasicMenu() {
    return "1. Slam \n2. Tackle\n3. Punch";
  }
  /**
   *Get the number of basic menu items
   *@return int of number of basic menu items
   */
  public int getNumBasicMenuItems() {
    return 3;
  }
  /**
   *Handles the basic attack of a pokemon
   *@param p the selected pokemon
   *@param move is the move you wish to use
   */
  public void basicAttack(Pokemon p, int move) {
    if (move == 1) {
      System.out.println(slam(p));
    } else if (move == 2) {
      System.out.println(tackle(p));
    } else if (move == 3) {
      System.out.println(punch(p));
    } else {
      System.out.println("Invalid Choice.");
    }
  }
  /**
   *Get the attack menu
   *@return string of attack menu text
   * @param atkType
   */
  public String getAttackMenu(int atkType) {
    return "1. Basic Attacks,\n2. Special Attacks";
  }
  /**
   *Get number of attack items
   *@return returning int value of number of attack menu items
   * @param atkType
   */
  public int getNumAttackMenuItems(int atkType) {
    return 3;
  }
  /**
   *Slam a pokemon
   *@param p pokemon you wish to slam
   *@return String for slam text
   */
  public String slam(Pokemon p) {
    int damage = random.nextInt(6);
    p.takeDamage(damage);
    return this.name + " slammed " + p.toString() + " and dealt " + damage + " damage.\n";
  }
  /**
   *Tackle a pokemon
   *@param p pokemon you wish to tackle
   *@return String of tackle text
   */
  public String tackle(Pokemon p) {
    int damage = random.nextInt(2) + 2;
    p.takeDamage(damage);
    return this.name + " tackled " + p.toString() + " and dealt " + damage + " damage.\n";
  }
  /**
   *Punch a pokemon
   *@param p pokemon you wish to punch
   *@return String of punch text
   */
  public String punch(Pokemon p) {
    int damage = random.nextInt(4) + 1;
    p.takeDamage(damage);
    return this.name + " punched " + p.toString() + " and dealt " + damage + " damage.\n";
  }
  /**
   *Get type of pokemon
   *@return int for pokemon type
   */
  public int getType() {
    int type = 0;

    if (this.name.equals("Squirtle") || this.name.equals("Staryu")) {
      type = 1;
    } else if (this.name.equals("Bulbasaur") || this.name.equals("Oddish")) {
      type = 2;
    }
    return type;
  }
}