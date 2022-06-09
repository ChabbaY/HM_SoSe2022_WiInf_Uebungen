package uebung_03_counter_test_loesung;

/**
 * Thread-Klasse: Zaehler wird synchronisiert hochgezaehlt
 * @author Peter Mandl, Linus Englert
 */

class CountThread2 extends Thread {
    private final CounterObject myCounter;
    private final int myMaxCount;

    /*
     * Konstruktor
     */
    CountThread2(CounterObject c, int maxCount) {
        myCounter = c;
        myMaxCount = maxCount;
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

            // Kritischer Bereich

            synchronized (myCounter) {
                int c = myCounter.get();
                c++;
                myCounter.set(c);
            }

            // Ende des kritischen Bereichs
        }
    }
}
