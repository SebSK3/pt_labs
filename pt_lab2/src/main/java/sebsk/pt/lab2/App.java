package sebsk.pt.lab2;

public class App {
    public static void main(String[] args) {
        PrimesList primeList = new PrimesList();

        Thread addPrime = new Thread(new AddPrime(primeList));
        Thread checkPrime = new Thread(new CheckPrime(primeList));

        checkPrime.start();
        addPrime.start();
    }
}
