//encapsulation
public class EnhancedPlayer {
    private String name, weapon;
    private int hitPoints;

    public EnhancedPlayer(String name, String weapon, int health) {
        this.name = name;
        if (health>0 && health<=100){
            this.hitPoints = health;
        }
        this.weapon = weapon;
    }
    public void loseHealth(int damage){
        this.hitPoints = this.hitPoints -damage;
        if (this.hitPoints <=0){
            System.out.println("Player knock out");
        }
    }

    public int getHealth() {
        return hitPoints;
    }
}
