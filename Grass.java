public interface Grass {
  public String specialMenu = "1. Vine Whip\n2. Razor Leaf\n3. Solar Beam";
  public final int numSpecialMenuItems = 3;
  /**
    *Performs special move to pokemon
    *@param p pokemon you wish to attack
    *@return String of move flavor text
    */
  String vineWhip(Pokemon p);
    /**
    *Performs special move to pokemon
    *@param p pokemon you wish to attack
    *@return String of move flavor text
    */
  String razorLeaf(Pokemon p);
    /**
    *Performs special move to pokemon
    *@param p pokemon you wish to attack
    *@return String of move flavor text
    */
  String solarBeam(Pokemon p);
}
