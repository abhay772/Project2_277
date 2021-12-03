public class Water extends Pokemon{
    public Water(String n, int h, int m) {super(n, h, m);}

    /**
     * Get selected attack menu
     *
     * @return String of the selected attack menu
     */
    public String getAttackMenu(int atkType){
        // Return the basic attack menu,
        // if atkType type is 1
        // else return the special Attack menu
        if (atkType == 1) {
            return "1. Slam\n2. Tackle\n3. Punch";
        }
        else
            return "1. Water Gun\n2. Bubble Beam\n3. Waterfall";
    }

    /**
     * Get the number of selected attack menu items
     *
     * @return int of number of selected attack menu items
     */
    @Override
    public int getNumAttackMenuItems(int atkType){

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
    public String getAttackString(int atkType, int move){

        System.out.println("Inside Water");

        String atkString ="";

        if(atkType == 1) {
            return super.getAttackString(atkType, move);
        }

        else
        {
            switch (move) {
                case 1:
                    atkString = " used WATER GUN on ";
                case 2:
                    atkString = " used BUBBLE BEAM on ";
                case 3:
                    atkString = " used WATERFALL on ";
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
    public int getAttackDamage(int atkType, int move){
        if(atkType == 1)
            return super.getAttackDamage(atkType, move);
        else{
            if(move == 1)
                return (int)(Math.random() * 5) + 1;
            else if(move == 2)
                return (int)(Math.random() * 2) + 1;
            else
                return (int)(Math.random() * 4) + 1;
        }
    }

    public double getAttackMultiplier(Pokemon p, int atkType){
        if(atkType == 1)
            return 1;
        return Pokemon.battleTable[1][p.getType()];
    }
}
