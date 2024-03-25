package sebsk.pt.lab4;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Database {
    SessionFactory factory;
    public Database() {
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertMage(Mage mage) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(mage);
        session.getTransaction().commit();
        System.out.println("Mage saved successfully!");
    }
    public void insertTower(Tower tower) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(tower);
        session.getTransaction().commit();
        System.out.println("Tower saved successfully!");
    }
    public Mage getMageByName(String name) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();
        Mage mage = session.get(Mage.class, name);
        session.getTransaction().commit();
        return mage;
    }
    public Tower getTowerByName(String name) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Tower tower = session.get(Tower.class, name);
        session.getTransaction().commit();
        return tower;
    }

    public void deleteMageByName(String name) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Mage mage = session.get(Mage.class, name);
        Tower tower = mage.getTower();

        tower.getMages().remove(mage);

        mage.setTower(null);
        session.delete(mage);
        session.getTransaction().commit();
    }

    public void deleteTowerByName(String name) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Tower tower = session.get(Tower.class, name);
        List<Mage> mages = tower.getMages();
        for (Mage mage : mages) {
            mage.setTower(null);
        }
        mages.clear();
        session.delete(tower);
        session.getTransaction().commit();
    }

    public List<Mage> getMagesAboveLevel(int level) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query<Mage> query = session.createQuery("FROM Mage WHERE level > :level", Mage.class);
        query.setParameter("level", level);
        List<Mage> mages = query.getResultList();
        session.getTransaction().commit();
        return mages;
    }

    public List<Tower> getTowersBelowHeight(int height) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query<Tower> query = session.createQuery("FROM Tower WHERE height < :height", Tower.class);
        query.setParameter("height", height);
        List<Tower> towers = query.getResultList();
        session.getTransaction().commit();
        return towers;
    }

    public List<Mage> getMagesAboveLevelFromTower(int level, Tower tower) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query<Mage> query = session.createQuery("FROM Mage WHERE level > :level and tower = :tower", Mage.class);
        query.setParameter("level", level);
        query.setParameter("tower", tower);
        List<Mage> mages = query.getResultList();
        session.getTransaction().commit();
        return mages;
    }


    public void dumpDatabase() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Mage> mages = session.createQuery("FROM Mage", Mage.class).getResultList();;
        for (Mage mage : mages) {
                System.out.println(mage);
        }
        List<Tower> towers = session.createQuery("FROM Tower", Tower.class).getResultList();;
        for (Tower tower : towers) {
                System.out.println(tower);
        }
        session.getTransaction().commit();

    }
}
