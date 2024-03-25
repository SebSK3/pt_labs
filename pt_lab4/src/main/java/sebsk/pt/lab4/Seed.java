package sebsk.pt.lab4;

import java.util.List;

public class Seed {
    Database db;
    public Seed(Database db) {
        this.db = db;
        seedDatabase();
    }

    public void seedDatabase() {
        // Ex. 2
        addSingleMage();
        addSingleTower();
        addTowerWithMages();
        getSingleMage();
        getSingleTower();
        // Ex. 3
        deleteSingleMage();
        deleteSingleTower();
        // Ex. 4
        dumpDatabase();
        // Example 1
        allMagesAboveLevel();
        // Example 2
        allTowersBelowHeight();
        // Example 3
        allMagesAboveLevelFromSpecificTower();
    }

    public void addSingleMage() {
        Mage mage = new Mage("John Doe", 25);
        db.insertMage(mage);
    }
    public void addSingleTower() {
        Tower tower = new Tower("Wieza", 3);
        db.insertTower(tower);
    }

    public void addTowerWithMages() {
        Tower tower = new Tower("Wieza z magami", 5);
        Mage mage1 = new Mage("Mag1", 3);
        Mage mage2 = new Mage("Mag2", 5);
        Mage mage3 = new Mage("Mag3", 7);
        tower.addMage(mage1);
        tower.addMage(mage2);
        tower.addMage(mage3);
        db.insertTower(tower);
    }

    public void getSingleMage() {
        Mage mage = db.getMageByName("Mag1");
        System.out.println(mage);
    }
    public void getSingleTower() {
        Tower tower = db.getTowerByName("Wieza z magami");
        System.out.println(tower);
    }

    public void deleteSingleMage() {
        db.deleteMageByName("Mag2");
    }
    public void deleteSingleTower() {
        db.deleteTowerByName("Wieza z magami");
    }
    public void allMagesAboveLevel() {
        List<Mage> mages = db.getMagesAboveLevel(4);
        for (Mage mage : mages) {
            System.out.println(mage);
        }
    }
    public void allTowersBelowHeight() {
        List<Tower> mages = db.getTowersBelowHeight(4);
        for (Tower mage : mages) {
            System.out.println(mage);
        }
    }

    public void allMagesAboveLevelFromSpecificTower() {
        Tower wieza = db.getTowerByName("Wieza z magami");
        List<Mage> mages = db.getMagesAboveLevelFromTower(4, wieza);
        for (Mage mage : mages) {
            System.out.println(mage);
        }
    }
    public void dumpDatabase() {
        db.dumpDatabase();
    }

}
