public abstract class Entity {

  protected String name;
  protected int hp;
  protected int maxHp;
  /**
   *Entity constructor
   *@param n name of the Entity
   *@param h current hp of the Entity
   *@param m max hp of the Entity
   */
  public Entity(String n,int h,int m) {
    this.name = n;
    this.hp = h;
    this.maxHp = m;
  }
  /**
   *get hp of entity
   *@return int of current hp
   */
  public int getHp() {
    return this.hp;
  }
  /**
   *get max hp of entity
   *@return int of max hp
   */
  public int getMaxHp() {
    return this.maxHp;
  }
  /**
   *Take damage to entity
   *@parm damage the amount of damage you want to inflict
   */
  public void takeDamage(int damage) {
    //if damage was above their hp set their health to 0 to avoid negative damage
    if (damage > this.getHp()) {
      this.hp = 0;
    } else {
      //otherwise remove the damage from health
      this.hp = this.getHp() - damage;
    }
  }
  /**
   *Heal entity
   */
  public void heal() {
    //set current hp to max hp
    this.hp = this.maxHp;
  }
  /**
   *Get name of entity
   *@return String name of entity
   */
  public String getName() {
    return this.name;
  }
  /**
   *To string @Overridden 
   *@return Striug of name hp /maxhp
   */
  @Override
  public String toString() {
    return this.name + " HP: " + hp + "/" + maxHp;
  }
}