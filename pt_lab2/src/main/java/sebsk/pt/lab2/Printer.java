package sebsk.pt.lab2;

import java.util.List;

public class Printer implements Runnable {
    private final ResultPrimes results;

    public Printer(ResultPrimes results) {
        this.results = results;
    }
    @Override
    public void run() {
        List<Integer> list;
        List<Integer> lastList = results.getResults();
        try {
            while (true) {
                Thread.sleep(5500);
                list = results.getResults();
                if (list.equals(lastList)) {
                    System.out.println("[RESULT] Last list was the same. Size: " + list.size());
                    continue;
                }
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("[RESULT] Result " + i + " prime: " + list.get(i));
                }
                lastList = list;
            }
        } catch (InterruptedException e) {
            System.out.println("Shutting down printer");
        }
    }

}
