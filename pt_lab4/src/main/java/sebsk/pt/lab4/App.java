package sebsk.pt.lab4;



public class App {
public static void main(String[] args) {
        // Create SessionFactory and Session
        Database db = new Database();
        Seed seed = new Seed(db);
    }
}
