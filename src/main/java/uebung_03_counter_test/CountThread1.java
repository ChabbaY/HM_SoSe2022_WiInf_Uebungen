package uebung_03_counter_test;

/**
 * Thread-Klasse: Zaehler wird unsynchronisiert hochgezaehlt
 * @author Peter Mandl
 */
 
class CountThread1 extends Thread
{
    private CounterObject myCounter;
    private int myMaxCount;

    /*
     * Konstruktor
     */
    CountThread1(CounterObject c, int maxCount)
    {
        myCounter = c;
        myMaxCount = maxCount;
    }

    /*
     * run-Methode
     */
    public void run()
    {
        System.out.println("Thread " + getName() + " gestartet");

        /*
         *  Nicht synchronisierter Zugriff auf den Counter
         */
        for (int i=0;i<myMaxCount;i++){

            // Kritischer Bereich

                int c = myCounter.get();
                c++;
                myCounter.set(c);

            // Ende des kritischen Bereichs
        }
    }
}
