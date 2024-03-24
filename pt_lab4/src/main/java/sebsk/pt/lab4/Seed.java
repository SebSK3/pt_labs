package sebsk.pt.lab4;


public class Seed {
    Database db;
    public Seed(Database db) {
        this.db = db;
        seedDatabase();
    }

    public void seedDatabase() {
        addSingleMage();
        addSingleTower();
        addTowerWithMages();
        getSingleMage();
        getSingleTower();
        deleteSingleMage();
        deleteSingleTower();
        dumpDatabase();
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

    public void dumpDatabase() {
        db.dumpDatabase();
    }

}
