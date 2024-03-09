package sebsk.pt.lab2;


public class CheckPrime implements Runnable {
    private final PrimesList checkPrime;

    public CheckPrime(PrimesList checkPrime) {
        this.checkPrime = checkPrime;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int prime = checkPrime.getPrime();
            System.out.println("[CHECK] IsPrime: " + prime + " : " + isPrime(prime));

            try {
                Thread.sleep(2000); // Simulate some processing time before consuming the next prime
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
