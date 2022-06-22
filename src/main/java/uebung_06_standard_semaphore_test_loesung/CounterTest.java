package uebung_06_standard_semaphore_test_loesung;

import java.util.concurrent.Semaphore;

/**
 * Testklasse fuer synchronisierten Zugriff mittels Standard-Semaphor
 * @author: Peter Mandl
 */
public class CounterTest
{
    public static void main(String[] args)
    {
        /*
         * Hier muss der Standardsemaphor initalisiert werden
         */
        Semaphore s1 = new Semaphore(1);

        CounterObject c = new CounterObject();
        final int maxCount = 100; // Jeder Thread soll 100 mal um 1 erhoehen
        final int maxThread = 30;

        /*
         * Wir erzeugen mehrere nebenlaeufige Threads und warten auf deren
         * Ende
         */

        Thread[] threadArray = new Thread[maxThread];
        for (int i = 0; i < maxThread; i++) {
            /*
             * der Semaphor muss bei der Thread-Erzeugung uebergeben werden
             */
            threadArray[i] = new CounterThread(c, maxCount, s1);
        }

        try {
            // Threads starten
            for (int i = 0; i < maxThread; i++) {
                threadArray[i].start();
            }

            // Warten auf das Ende der Threads
            for (int i = 0; i < maxThread; i++) {
                threadArray[i].join();
            }
        }
        catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
        finally {
            System.out.println("Counter am Ende des Tests, Wert sollte " + (maxThread * maxCount) + " sein, "
                    + "berechneter Wert " + c.get());
        }
    }
}