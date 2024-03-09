package sebsk.pt.lab2;


public class CheckPrime implements Runnable {
    private final PrimesList checkPrime;
    private final String name;

    public CheckPrime(PrimesList checkPrime, String name) {
        this.checkPrime = checkPrime;
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int prime = checkPrime.getPrime();
            System.out.println("[CHECK] " + name + ": " + prime + " : " + isPrime(prime));

            try {
                Thread.sleep(2500); // Simulate some processing time before consuming the next prime
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean isPrime(int number) {
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
