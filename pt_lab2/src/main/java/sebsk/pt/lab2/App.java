package sebsk.pt.lab2;


public class App
{
    public static void main( String[] args )
    {
        CheckPrime checkPrime = new CheckPrime();

        checkPrime.addPrimeToCheck(17);
        checkPrime.addPrimeToCheck(23);
        checkPrime.addPrimeToCheck(31);
        checkPrime.addPrimeToCheck(17);
        checkPrime.addPrimeToCheck(23);
        checkPrime.addPrimeToCheck(31);
        checkPrime.addPrimeToCheck(17);
        checkPrime.addPrimeToCheck(23);
        checkPrime.addPrimeToCheck(31);
        checkPrime.addPrimeToCheck(17);
        checkPrime.addPrimeToCheck(23);
        checkPrime.addPrimeToCheck(31);
        checkPrime.addPrimeToCheck(17);
        checkPrime.addPrimeToCheck(23);
        checkPrime.addPrimeToCheck(31);
        // Wait for the background thread to finish
        try {
            Thread.sleep(1600);
            //checkPrime.getPrimeCheckerThread().join();
            // checkPrime.stopPrimeCheckerThread(); // early exit
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
