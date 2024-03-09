package sebsk.pt.lab2;

import java.util.LinkedList;
import java.util.Queue;

public class CheckPrime implements Runnable {
    static int[] historyOfCheckedPrimes;
    private Queue<Integer> primesToCheck;

    private Thread primeCheckerThread;

    CheckPrime() {
        this.primesToCheck = new LinkedList<>();
        this.primeCheckerThread = new Thread(this);
        this.primeCheckerThread.start();
    }

    public Thread getPrimeCheckerThread() {
        return primeCheckerThread;
    }
    @Override
    public void run() {
        while (!primeCheckerThread.isInterrupted()) {
            try {
                Integer primeToCheck = getNextPrimeToCheck();
                boolean isPrime = checkPrime(primeToCheck);
                System.out.println(primeToCheck + " is prime: " + isPrime);
                System.out.println("Size: " + primesToCheck.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
    public boolean checkPrime(Integer number) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
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

    public synchronized void addPrimeToCheck(Integer number) {
        System.out.println("Adding prime to check: " + number);
        primesToCheck.add(number);
        notify();
    }
    public void stopPrimeCheckerThread() {
        primeCheckerThread.interrupt();
    }
    public boolean primesEmpty() {
        return primesToCheck.isEmpty();
    }

    public synchronized Integer getNextPrimeToCheck() throws InterruptedException {
        while (primesToCheck.isEmpty() && !primeCheckerThread.isInterrupted()) {
            wait(); // Wait for a prime to be added.
        }
        return primesToCheck.poll();
    }
}
