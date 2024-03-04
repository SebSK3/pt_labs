package sebsk;

import java.util.Comparator;

public class MageComparator implements Comparator<Mage> {
    @Override
    public int compare(Mage arg0, Mage arg1) {
        if (arg0.level != arg1.level) {
            return arg0.level - arg1.level;
        } else {
            // Same name, different level
            if (arg0.name.compareTo(arg1.name) != 0) {
                return arg0.name.compareTo(arg1.name);
            } else {
                if (arg0.power != arg1.power) {
                    if (arg0.power < arg1.power) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                return 0;
            }
        }
    }
}
