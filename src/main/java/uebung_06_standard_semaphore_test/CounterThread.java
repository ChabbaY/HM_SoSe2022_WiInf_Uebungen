package uebung_06_standard_semaphore_test;

/*
 * TODO Der Standard-Semaphor muss importiert werden
 */

public class CounterThread extends Thread {

    private CounterObject myCounter;
    private int myMaxCount;

    /*
     * TODO auch hier ist eine Variable fuer den Semaphor notwendig
     */

    /*
     * Konstruktor
     */
    /*
     * TODO Passen Sie den Konstruktur so an, dass ein Standardsemaphor uebergeben wird
     */
    CounterThread(CounterObject c, int maxCount) {
        this.myCounter = c;
        this.myMaxCount = maxCount;
    }

    /*
     * run-Methode
     */
    public void run() {
        System.out.println("Thread " + getName() + " gestartet");

        /*
         *  Synchronisierter Zugriff auf den Counter
         */
        for (int i = 0; i < myMaxCount; i++) {

            // Kritischer Bereich betreten

            /*
             * TODO Betreten des kritischen Abschnitts mit Hilfe einer
             * Standardsemaphor-Methode
             */

            // Krititscher Abschnitt
            int c = myCounter.get();
            c++;
            myCounter.set(c);

            // Ende des kritischen Bereichs
            /*
             * TODO Verlassen des kritischen Abschnitts mit Hilfe einer
             * Standardsemaphor-Methode
             */
        }
    }
}
