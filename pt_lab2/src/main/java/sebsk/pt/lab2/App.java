package sebsk.pt.lab2;

import java.util.List;

public class App {
    public static void main(String[] args) {
        PrimesList primeList = new PrimesList();
        ResultPrimes results = new ResultPrimes();

        // Zad. 1
        Thread addPrime = new Thread(new AddPrime(primeList));
        Thread checkPrime = new Thread(new CheckPrime(primeList, results, "first"));
        Thread checkPrime2 = new Thread(new CheckPrime(primeList, results, "second"));

        checkPrime.start();
        addPrime.start();
        checkPrime2.start();

        // Zad. 2
        List<Integer> list;
        List<Integer> lastList = results.getResults();
        try {

            while (true) {
                Thread.sleep(5500);
                list = results.getResults();
                if (list.equals(lastList)) {
                    System.out.println("Last list was the same. Size: " + list.size());
                    continue;
                }
                for (int i = 0; i < list.size(); i++) {
                    System.out.println("Result " + i + " prime: " + list.get(i));
                }
                lastList = list;
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
        }

    }
}
