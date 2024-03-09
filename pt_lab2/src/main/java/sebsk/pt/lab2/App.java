package sebsk.pt.lab2;

public class App {
    public static void main(String[] args) {
        PrimesList primeList = new PrimesList();

        Thread addPrime = new Thread(new AddPrime(primeList));
        Thread checkPrime = new Thread(new CheckPrime(primeList, "first"));
        Thread checkPrime2 = new Thread(new CheckPrime(primeList, "second"));

        checkPrime.start();
        addPrime.start();
        checkPrime2.start();
    }
}
