package sebsk.pt.lab2;

import java.util.LinkedList;
import java.util.Queue;

public class PrimesList {
    private final Queue<Integer> primes = new LinkedList<>();

    public synchronized void addPrime(int prime) {
        primes.add(prime);
        notify(); // Inform waiting threads that a new prime is available
    }

    public synchronized int getPrime() {
        while (primes.isEmpty()) {
            try {
                wait(); // Wait until there is a prime to retrieve
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return primes.poll();
    }
}
