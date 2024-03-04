package sebsk;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Mage implements Comparable<Mage> {
    public String name;
    public int level;
    public double power;
    public Set<Mage> apprentices;
    public Mage(String name, int level, double power, boolean sorting, MageComparator comparator) {
        this.name = name;
        this.level = level;
        this.power = power;
        if (comparator != null) {
            this.apprentices = new TreeSet<>(comparator);
        } else if (sorting) {
            this.apprentices = new TreeSet<>();
        } else {
            this.apprentices = new HashSet<>();
        }
    }

    public String toString(int depth) {
        StringBuilder str = new StringBuilder();
        for (int i=0; i<depth; i++) {
            str.append("-");
        }
        str.append("Name: " + name + "; Level: " + level + "; Power: " + power + "\n");

        depth++;
        for (Mage mage : apprentices) {
            str.append(mage.toString(depth));
        }
        return str.toString();
    }

    public int hashCode() {
        return Objects.hash(name + level + power + apprentices);
    }
    public boolean equals(Mage mage) {
        if (mage == this) {
            return true;
        }
        if (hashCode() == mage.hashCode()) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Mage comparingMage) {
        if (this.name.compareTo(comparingMage.name) != 0) {
            return this.name.compareTo(comparingMage.name);
        } else {
            // Same name, different level
            if (this.level != comparingMage.level) {
                return this.level - comparingMage.level;
            } else {
                if (this.power != comparingMage.power) {
                    if (this.power < comparingMage.power) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return 0;
            }
        }
    }
    public Map<Mage, Integer> getAllDescentants(Map<Mage, Integer> map, Integer descentants) {
        return null;
    }

    public Map<Mage, Integer> zad5(Boolean sorting) {
        Map<Mage, Integer> map;
        Set<Mage> visited = new HashSet<Mage>();
        if (sorting) {
            map = new TreeMap<Mage, Integer>();
        } else {
            map = new HashMap<Mage, Integer>();
        }
        descentants(this, map, visited);
        return map;
    }
    private int descentants(Mage mage, Map<Mage, Integer> map, Set<Mage> visited) {
        int count = 0;
        if (visited.contains(mage)) {
            return 0;
        }

        visited.add(mage);

        for (Mage apprentice : mage.apprentices) {
            count += 1 + descentants(apprentice, map, visited);
        }

        map.put(mage, count);
        return count;
    }

}
