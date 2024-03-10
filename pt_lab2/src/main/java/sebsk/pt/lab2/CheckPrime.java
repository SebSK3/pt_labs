package sebsk.pt.lab2;

import java.util.Random;

public class CheckPrime implements Runnable {
    private final PrimesList checkPrime;
    private final ResultPrimes results;
    private final String name;

    public CheckPrime(PrimesList checkPrime, ResultPrimes results, String name) {
        this.checkPrime = checkPrime;
        this.name = name;
        this.results = results;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(4001)+1000); // Simulate some processing time before consuming the next prime
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int prime = checkPrime.getPrime();
            boolean isPrime = checkPrime(prime);
            System.out.println("[CHECK] " + name + ": " + prime + " : " + isPrime);
            if (isPrime) {
                results.addResult(prime);
            }
        }
    }
    private boolean checkPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
