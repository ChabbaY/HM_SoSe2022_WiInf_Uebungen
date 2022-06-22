package uebung_05_java_semaphore_test_loesung;

/**
 * Semaphor-Klasse, ohne Warteschlange (nicht fair)
 */
class MySemaphor {
    private final int max;  // Anzahl der maximal moeglichen Threads im kritischen Abschnitt
    private int free; // Anzahl der verfuegbaren Plaetze im kritischen Abschnitt
                      // (so viele Threads duerfen noch in den kritischen Abschnitt rein)

    private int waiting; // Anzahl der in der P-Operation mit wait wartenden
    // Threads (so viele Threads moechten aktuell in den kritischen Abschnitt rein)

    /**
     * Konstruktion der Semaphore mit einer Obergrenze Bei dieser Art der
     * Konstruktion kann man die Anzahl der Threads, die nebenlaeufig im
     * kritischen Abschnitt sein duerfen, angeben.
     */
    public MySemaphor(int i) {
        if (i >= 0) {
            max = i;
        } else {
            max = 1;
        }
        free = max;
        waiting = 0;
    }

    /**
     * P-Operation
     */
    public synchronized void P() {
        while (free <= 0) {
            waiting++; // Anzahl der wartenden Threads erhoehen
            try {
                System.out.println(Thread.currentThread().getName()
                        + " muss in P()-Operation warten");
                System.out.println(Thread.currentThread().getName()
                        + " Es warten derzeit " + waiting + " Threads");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waiting--; // Anzahl der wartenden Threads vermindern
            System.out.println(Thread.currentThread().getName()
                    + " in P-Operation(), zurueck aus wait; es warten derzeit "
                    + waiting + " Threads");
        }
        free--; // Jetzt erst Semaphorezaehler vermindern
    }

    /**
     * V-Operation
     */
    public synchronized void V() {
        free++; // Semaphorzaehler erhoehen
        if (waiting > 0) {
            // Nur wenn ein anderer Thread wartet,
            // diesen mit notify benachrichtigen
            System.out.println(Thread.currentThread().getName()
                    + " V()-Operation ausgefuehrt, andere Threads duerfen wieder");
            System.out.println(Thread.currentThread().getName()
                    + " In V()-Operation, es warten derzeit " + waiting
                    + " Threads");
            this.notify();
        }
    }
}