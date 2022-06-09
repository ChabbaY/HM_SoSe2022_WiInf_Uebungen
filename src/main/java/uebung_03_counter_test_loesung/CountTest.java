package uebung_03_counter_test_loesung;

/**
 * Testklasse fuer synchronisierten und nicht synchronisierten Zugriff
 * @author Peter Mandl, Linus Englert
 */
class CountTest {
    public static void main(String[] args) {
        CounterObject c = new CounterObject();
        final int maxCount = 1000; // Jeder Thread soll 1000 mal um 1 erhoehen
        final int maxThread = 10;   // Drei Threads

        Thread[] tn = new Thread[maxThread];

        /*
         * Wir starten maxThread nebenlaeufige Threads und warten auf ihr
         * Ende
         */
        for (int i = 0; i < tn.length; i++) {
            tn[i] = new CountThread1(c, maxCount);
        }

        System.out.println("Test ohne Synchronisation geht jetzt los ...");

        try {
            long startTime = System.currentTimeMillis();

            // Threads starten
            for(Thread t : tn) {
                t.start();
            }

            // Warten auf das Ende der Threads
            for(Thread t : tn) {
                t.join();
            }

            long duration = System.currentTimeMillis() - startTime;
            System.out.printf("Threads liefen %d sec, %d ms%n", duration / 1000, duration % 1000);
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

        for (int i = 0; i < tn.length; i++) {
            tn[i] = new CountThread2(c, maxCount);
        }

        System.out.println("Test mit Synchronisation geht jetzt los ...");

        try {
            long startTime = System.currentTimeMillis();

            // Threads starten
            for(Thread t : tn) {
                t.start();
            }

            // Warten auf das Ende der Threads
            for(Thread t : tn) {
                t.join();
            }

            long duration = System.currentTimeMillis() - startTime;
            System.out.printf("Threads liefen %d sec, %d ms%n", duration / 1000, duration % 1000);
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