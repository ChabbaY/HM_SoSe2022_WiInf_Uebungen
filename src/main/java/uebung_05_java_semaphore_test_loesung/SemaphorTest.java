package uebung_05_java_semaphore_test_loesung;

/**
 * @author Peter Mandl
 * 
 *         Semaphor-Testklasse
 */
public class SemaphorTest {
    public static MySemaphor s1;

    public static void main(String[] args) {
        SemaphorTest sem = new SemaphorTest();
        sem.work();
    }

    /**
     * Die Methode work fuehrt die eigentliche Initialisierungsarbeit aus: -
     * Threads werden gestartet - Das Ende der Threads wird abgewartet
     */
    void work() {
        s1 = new MySemaphor(1); // Semaphore zur Threadsynchronisation

        int maxThreads = 1000000; // maximale Anzahl nebenlaeufiger Threads

        Thread[] tlist = new Thread[maxThreads]; // Liste mit Threads

         System.out.println(Thread.currentThread().getName()
                 + ": Start des Tests");

        for (int i = 0; i < maxThreads; i++) {

            try {
                tlist[i] = new Thread(new SemaphorTestThread(s1));
                tlist[i].setName("MyThread_" + Integer.toString(i+1));
                tlist[i].start();
                Thread.sleep(1); // Verhalten des Programmes aendert sich mit der Schlafzeit
                System.out.println(tlist[i].getName() + " gestartet");
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                System.out.println("Bei "+i+" Threads wurde Exception geworfen und das Programm beendet!");
                System.exit(0);
            }catch (Exception e){
                System.out.println(e.toString());
            }
        }

        /*
         * Auf das Ende der Threads warten
         */
        for (int i = 1; i < maxThreads; i++) {

            try {
                tlist[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()
                + ": Programmende");
    }
}