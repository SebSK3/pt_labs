package sebsk.pt.lab2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        int countersToStart = 1;
        for (int i=0; i<args.length; i++) {
            countersToStart = Integer.parseInt(args[i]);
        }
        PrimesList primeList = new PrimesList();
        ResultPrimes results = new ResultPrimes();

        // Zad. 1, 3, 4
        // Thread addPrime = new Thread(new AddPrime(primeList));
        // addPrime.start();
        List<Thread> calculators = new LinkedList<>();
        for (int i=0; i<countersToStart; i++) {
            Thread checkPrime = new Thread(new CheckPrime(primeList, results, "Counter" + i));
            checkPrime.start();
            calculators.add(checkPrime);
        }

        // Zad. 2 (printing results)
        Thread printer = new Thread(new Printer(results));
        printer.start();

        // Zad. 4 (user input)
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();
            // Zad 5. (graceful exit)
            if (userInput.equals("x")) {
                for (Thread calculatorThread : calculators) {
                    calculatorThread.interrupt();
                }
                printer.interrupt();
                break;
            }

            primeList.addPrime(Integer.parseInt(userInput));
        }

    }
}
