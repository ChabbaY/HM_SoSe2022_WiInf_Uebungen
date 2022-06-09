package uebung_03_counter_test;

/**
 * Testklasse fuer synchronisierten und nicht synchronisierten Zugriff
 * @author Peter Mandl
 */
class CountTest
{
    public static void main(String[] args)
    {
        CounterObject c = new CounterObject();
        final int maxCount = 1000; // Jeder Thread soll 1000 mal um 1 erhoehen
        final int maxThread = 3;   // Drei Threads

        /*
         * Wir starten drei nebenlaeufige Threads und warten auf ihr
         * Ende
         */
        Thread t1 = new CountThread1(c, maxCount);
        Thread t2 = new CountThread1(c, maxCount);
        Thread t3 = new CountThread1(c, maxCount);

        System.out.println("Test ohne Synchronisation geht jetzt los ...");

        try {

            // Threads starten
            t1.start();
            t2.start();
            t3.start();

            // Warten auf das Ende der Threads
            t1.join();
            t2.join();
            t3.join();

        }
        catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        finally {
            System.out.println("Counter am Ende des Tests, Wert sollte "+maxCount*maxThread+" sein, "
                    + "berechneter Wert " + c.get());
            System.out.println("Test ohne Synchronisation ist zu Ende");
        }

        /*
         * Wir starten drei nebenlaeufige Threads und warten auf ihr
         * Ende
         */

        c.set(0); // Zaehler auf 0 setzen

        t1 = new CountThread2(c, maxCount);
        t2 = new CountThread2(c, maxCount);
        t3 = new CountThread2(c, maxCount);

        System.out.println("Test mit Synchronisation geht jetzt los ...");

        try {

            // Threads starten
            t1.start();
            t2.start();
            t3.start();

            // Warten auf das Ende der Threads
            t1.join();
            t2.join();
            t3.join();
        }
        catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        finally {
            System.out.println("Counter am Ende des Tests, Wert sollte "+maxCount*maxThread+" sein, "
                    + "berechneter Wert " + c.get());
            System.out.println("Test mit Synchronisation ist zu Ende");
        }
    }
}