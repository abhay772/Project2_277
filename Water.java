/**
* 
*/

public interface Water {
  public String specialMenu = "1. Water gun\n2. Bubble beam\n3. Waterfall";
  final int numSpecialMenuItems = 3;
    /**
    *Performs special move to pokemon
    *@param p pokemon you wish to attack
    *@return String of move flavor text
    */
  String waterGun(Pokemon p);
    /**
    *Performs special move to pokemon
    *@param p pokemon you wish to attack
    *@return String of move flavor text
    */
  String bubbleBeam(Pokemon p);
   /**
    *Performs special move to pokemon
    *@param p pokemon you wish to attack
    *@return String of move flavor text
    */
  String waterfall(Pokemon p);
}