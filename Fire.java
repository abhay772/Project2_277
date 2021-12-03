
/**
 * @author Abhay Solanki
 */
public class Fire extends Pokemon {

  public Fire(String n, int h, int m) {
    super(n,h,m);
  }

  /**
   * Get selected attack menu
   *
   * @return String of the selected attack menu
   */
  @Override
  public String getAttackMenu(int atkType) {

    // Return the basic attack menu,
    // if atkType type is 1
    // else return the special Attack menu
    if (atkType == 1) {
      return "1. Slam\n2. Tackle\n3. Punch";
    }
    return "1. Ember\n2. Fire Blast\n3. Fire Punch";
  }

  /**
   * Get the number of selected attack menu items
   *
   * @return int of number of selected attack menu items
   */
  @Override
  public int getNumAttackMenuItems(int atkType) {
    return 3;
  }

  /**
   * Returns the appropriate attack descriptor for the move
   *
   * @param atkType Type of attack: 1) Basic, 2) Special
   * @param move the selected move
   * @return String for the selected attack
   */
  @Override
  public String getAttackString(int atkType, int move) {
    System.out.println("Inside Fire");

    String atkString ="";


    if(atkType == 1) {
      return super.getAttackString(atkType, move);
    }

    else
    {
      switch (move) {
        case 1:
          atkString = " used EMBER on ";
        case 2:
          atkString = " used FIRE BLAST on ";
        case 3:
          atkString = " used FIRE PUNCH on ";
      }
    }
    return atkString;
  }

  /**
   * Calculates the damage done by a move
   *
   * @param atkType Type of attack: 1) Basic, 2) Special
   * @param move the selected move
   * @return damage for the selected attack
   */
  @Override
  public int getAttackDamage(int atkType, int move)
  {
    int damage=0;

    if (atkType==1) {
      return super.getAttackDamage(atkType, move);
    }

    else
    {
      switch (move) {
        case 1:
          damage = (int) (Math.random() * 6);
        case 2:
          damage = (int) (Math.random() * 2)+2;
        case 3:
          damage = (int) (Math.random() * 4)+1;
      }
    }
    return damage;
}

  /**
   * Calculates the damage multiplier against Pokemon p
   *
   * @param p Pokemon getting receiving damage
   * @param atkType Type of attack: 1) Basic, 2) Special
   * @return Damage multiplier for the damage
   */
  @Override
  public double getAttackMultiplier(Pokemon p, int atkType)
  {
    return Pokemon.battleTable[0][p.getType()];
  }
}