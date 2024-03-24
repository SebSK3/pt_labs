package sebsk.pt.lab4;

import java.util.List;
import java.util.LinkedList;

import javax.persistence.*;

@Entity
public class Tower {
    @Id
    private String name;

    private int height;
    @OneToMany(mappedBy = "tower", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Mage> mages;

    public Tower() {}

    public Tower(String name, int height) {
        this.name = name;
        this.height = height;
        mages = new LinkedList<Mage>();
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getHeight() {
        return height;
    }

    public void addMage(Mage mage) {
        mage.setTower(this);
        mages.add(mage);
    }
    public List<Mage> getMages() {
        return mages;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tower {name='").append(name).append("', height=").append(height).append(", mages = [\n");
        for (int i = 0; i < mages.size(); i++) {
            sb.append(mages.get(i));
            if (i < mages.size() - 1) {
                sb.append(", \n");
            }
        }
        sb.append("\n]}");
        return sb.toString();
    }
}
