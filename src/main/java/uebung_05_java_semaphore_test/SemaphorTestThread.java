package uebung_05_java_semaphore_test;
/**
 * Eigener Thread, der einen kritischen Abschnitt durchlaeuft.
 * @author Peter Mandl
 */
class SemaphorTestThread extends Thread {
    MySemaphor s1; // Referenz auf Semaphore

    public SemaphorTestThread(MySemaphor s) {
        s1 = s;
    }

    public void run() {
        // Eintritt in den kritischen Abschnitt, ggf. warten, bis er frei ist.
        s1.P();
        // Beginn des kritischen Abschnitts
         System.out.println(Thread.currentThread().getName() +
         " im kritischen Abschnitt");
        try {
            // Kuenstliche Verzoegerung
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Ende und Verlassen des kritischen Abschnitts
        System.out.println(Thread.currentThread().getName() +
         " verlaesst kritischen Abschnitt");
        s1.V();
    }
}