package uebung_06_standard_semaphore_test_loesung;

/*
 * Der Standard-Semaphor muss importiert werden
 */
import java.util.concurrent.Semaphore;

public class CounterThread extends Thread {

    private final CounterObject myCounter;
    private final int myMaxCount;

    /*
     * auch hier ist eine Variable fuer den Semaphor notwendig
     */
    Semaphore s1;

    /*
     * Konstruktor
     */
    /*
     * Passen Sie den Konstruktur so an, dass ein Standardsemaphor uebergeben wird
     */
    CounterThread(CounterObject c, int maxCount, Semaphore s1) {
        this.myCounter = c;
        this.myMaxCount = maxCount;
        this.s1 = s1;
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
             * Betreten des kritischen Abschnitts mit Hilfe einer
             * Standardsemaphor-Methode
             */
            try {
                System.out.println(Thread.currentThread().getName()
                        + " wartet ...");
                s1.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Krititscher Abschnitt
            System.out.println(Thread.currentThread().getName()
                    + " im kritischen Abschnitt");
            int c = myCounter.get();
            c++;
            myCounter.set(c);

            // Ende des kritischen Bereichs
            /*
             * Verlassen des kritischen Abschnitts mit Hilfe einer
             * Standardsemaphor-Methode
             */
            System.out.println(Thread.currentThread().getName()
                    + " verlässt");
            s1.release();
        }
    }
}
