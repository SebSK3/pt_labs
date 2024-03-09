package sebsk.pt.lab2;


public class AddPrime implements Runnable {
    private final PrimesList primesList;

    public AddPrime(PrimesList primesList) {
        this.primesList = primesList;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            primesList.addPrime(i);
            System.out.println("[ADDED]: " + i);

            try {
                Thread.sleep(1000); // Simulate some processing time before producing the next prime
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
