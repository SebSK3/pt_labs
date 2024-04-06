package sebsk.pt.lab5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class MageRepository {
    private Collection<Mage> collection;

    public MageRepository() {
        collection = new ArrayList<Mage>();
    }
    public Optional<Mage> find(String name) {
        for (Mage mage : collection) {
            if (mage.getName().equals(name)) {
                return Optional.of(mage);
            }
        }
        return Optional.empty();
    }

    public void delete(String name) {
        Optional<Mage> mage = find(name);
        if (mage.isEmpty()) {
            throw new IllegalArgumentException("Mage does not exist. Name: " + name);
        }
        collection.remove(mage.get());
    }

    public void save(Mage mage) {
        if (!find(mage.getName()).isEmpty()) {
            throw new IllegalArgumentException("Mage already exists. Name: " + mage.getName());
        }
        collection.add(mage);
    }
}
