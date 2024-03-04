package sebsk;

import java.util.HashMap;
import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        Boolean sorting = false;
        MageComparator comparator = null;
        for (int i=0; i<args.length; i++) {
            System.out.println(args[i]);
            if (args[i].equals("-s")) {
                sorting = true;
            }
            if (args[i].equals("-a")) {
                sorting = true;
                comparator = new MageComparator();
            }
        }


        Mage model = new Mage("a", 2, 4.0, sorting, comparator);
        Mage model2 = new Mage("b", 1, 2.0, sorting, comparator);
        Mage model3 = new Mage("c", 3, 2.0, sorting, comparator);
        Mage model4 = new Mage("d", 4, 2.0, sorting, comparator);
        Mage model5 = new Mage("e", 4, 2.0, sorting, comparator);
        Mage model6 = new Mage("f", 4, 2.0, sorting, comparator);
        model.apprentices.add(model2);
        model.apprentices.add(model3);
        model.apprentices.add(model5);
        model2.apprentices.add(model4);
        model2.apprentices.add(model5);
        model5.apprentices.add(model6);
        System.out.println(model.toString(0));
        Map<Mage, Integer> map = model.zad5(sorting);
        for (Map.Entry<Mage, Integer> entry : map.entrySet()) {
            System.out.println("Mage: " + entry.getKey().name + ", descentants: " + entry.getValue());
        }
    }
}
