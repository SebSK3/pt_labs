package sebsk.pt.lab2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrimesList {
    private final Queue<Integer> primes = new LinkedList<>();
    private final Lock lock = new ReentrantLock();

    public synchronized void addPrime(int prime) {
        lock.lock();
        try {
            primes.add(prime);
        } finally {

            lock.unlock();
        }
        notify(); // Inform waiting threads that a new prime is available
    }

    public synchronized int getPrime() throws InterruptedException {
        while (primes.isEmpty()) {
            wait(1000);
        }
        int prime = -1;
        lock.lock();
        try {
            prime = primes.poll();
        } finally {
            lock.unlock();
        }

        return prime;
    }
}
