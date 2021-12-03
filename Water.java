public class Water extends Pokemon{
    public Water(String n, int h, int m) {
        super(n, h, m);
    }
    public String getAttackMenu(int atkType){
        if(atkType == 1){
            return super.getAttackMenu(atkType);
        }
        else
            return "1. Water Gun\n2. Bubble Beam\n3. Waterfall";
    }
    public int getNumAttackMenuItems(int atkType){
            return 3;
    }
    public String getAttackString(int atkType, int move){
        if(atkType == 1)
            return super.getAttackString(atkType, move);
        else{
            if(move == 1)
                return "used WATER GUN\n";
            else if(move == 2)
                return "used BUBBLE BEAM\n";
            else
                return "used WATERFALL\n";
        }
    }
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
    public int getAttackMultiplier(Pokemon p, int atkType){
        if(atkType == 1)
            return 1;
        else
            return (int) Pokemon.battleTable[1][p.getType()];
    }
}
