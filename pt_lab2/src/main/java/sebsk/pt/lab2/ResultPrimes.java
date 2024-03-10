package sebsk.pt.lab2;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ResultPrimes {
    private final List<Integer> results = new LinkedList<>();
    private final Lock lock = new ReentrantLock();

    public void addResult(int result) {
        lock.lock();
        try {
            results.add(result);
        } finally {
            lock.unlock();
        }
    }

    public List<Integer> getResults() {
        lock.lock();
        try {
            return new LinkedList<>(results); // Return a copy of the results to prevent modification
        } finally {
            lock.unlock();
        }
    }
}
