package sebsk.pt.lab4;
import javax.persistence.*;

@Entity
public class Mage {

    @Id
    private String name;

    @Column(name = "level")
    private int level;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Tower tower;

    public Mage() {}

    public Mage(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public Tower getTower() {
        return tower;
    }

    @Override
    public String toString() {
        if (this.tower == null) {
            return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", tower=null" +
                '}';
        }
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", tower=" + tower.getName() +
                '}';
    }
}
