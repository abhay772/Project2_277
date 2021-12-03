
public class Grass extends Pokemon{
    public Grass(String n, int h, int m) {
        super(n, h, m);
    }
    public String getAttackMenu(int atkType){
        if(atkType == 1){
            return super.getAttackMenu(atkType);
        }
        else
            return "1. Vine Whip\n2. Razor Leaf\n3. Solar Beam";
    }
    public int getNumAttackMenuItems(int atkType){
        return 3;
    }
    public String getAttackString(int atkType, int move){
        if(atkType == 1)
            return super.getAttackString(atkType, move);
        else{
            if(move == 1)
                return "used VINE WHIP on";
            else if(move == 2)
                return "used RAZOR LEAF on";
            else
                return "used SOLAR BEAM on";
        }
    }
    public int getAttackDamage(int atkType, int move){
        if(atkType == 1)
            return super.getAttackDamage(atkType, move);
        else{
            if(move == 1)
                return (int)(Math.random() * 3) + 1;
            else if(move == 2)
                return (int)(Math.random() * 3) + 2;
            else
                return (int)(Math.random() * 6) ;
        }
    }
    public double getAttackMultiplier(Pokemon p, int atkType){
        if(atkType == 1)
            return 1;
        else
            return  Pokemon.battleTable[1][p.getType()];
    }
}
