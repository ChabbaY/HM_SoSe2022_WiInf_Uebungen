package uebung_02_threads_test_loesungwithcallable;

import java.util.concurrent.FutureTask;

/**
 *  Funktion:
 *    Ein einfacher Java-Thread wird erzeugt.
 *    Der Thread gibt 10 mal einen Text auf die Konsole aus und beendet sich
 *    dann. Nach jeder Ausgabe wartet der Thread ca. 2 Sekunden.
 *    Der Main-Thread wartet auf dessen Ende und beendet sich dann ebenfalls
 *
 *  Implementierung:
 *    Klasse zur Erzeugung des eigenen Threads mittels Callable and RunnableFuture:
 *     https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/concurrent/Callable.html
 * 	   https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/concurrent/Future.html
 *	   https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/concurrent/RunnableFuture.html 
 *
 *  Runnables versus Callables:
 *    Waehrend das Runnable-Interface eine Schnittstelle zum Umsetzen von Multithreading bietet, akzeptiert dieses keine
 *    Parameter und gibt keine Werte zurueck. Damit ist Runnable vorzugsweise für Situationen geeignet, in denen nicht
 *    nach einem Ergebnis der Thread-Ausfuehrung gesucht wird. Beispielsweise bei der Protokollierung eingehender Ereignisse.
 *	  Das Callable-Interface unterstuetzt ebenfalls die Umsetzung von Multithreading und zusaetzlich das einfache Uebergeben
 *	  von Parametern bei Aufruf und return von Werten. (Callables wurden in Java 1.5 eingefuehrt)
 *
 *    Weiter bietet die Methodensignatur des Runnable keine "throws"-Klausel, womit es keine Moeglichkeit gibt,
 *    checked Exceptions weiter zu propagieren. Callables unterstützt das Propagieren hingegen.
 *
 *  FutureTask:
 *    Die Implementierung eines Future-Objekts, das Runnable ist. Die erfolgreiche Ausfuehrung der Run-Methode im neu
 *    erzeugten Thread fuehrt zur Beendigung des Future und ermoeglicht den einfachen Zugriff auf die Ergebnisse.
 *    {@link #clone()} * Beispiel fuer Thread-Erzeugung in Java.
 *    
 * 	@author Maximilian Auch
 */
public class myThreadTestCallable {

    public static void main(String args[]) {

        // Referenz auf eine Instanz des eigenen Threads
        MyThreadCallable t1;
        MyThreadCallable t2;

        // Thread instanziieren
        t1 = new MyThreadCallable("...auf und nieder immer wieder...");
        t2 = new MyThreadCallable("...links und rechts und links und rechts");

        // Erzeugen von FutureTasks
        FutureTask<String> futureTask1 = new FutureTask<>(t1);
        FutureTask<String> futureTask2 = new FutureTask<>(t2);

        System.out.println("Start von main");
        System.out.println("Beobachten sie den Java-Prozess im Taskmanager");
        System.out.println("Betrachten Sie die Spalte Threads");
        System.out.println("Wieviele Threads hat der Prozess?");

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }

        // Thread starten
        try {
            Thread thread1 = new Thread(futureTask1);
            Thread thread2 = new Thread(futureTask2);
            thread1.start();
            thread2.start();

            //thread.join(); // Durch den Einsatz von FutureTask (Implementierung des RunnableFuture-Interfaces) ist es
                             // nicht mehr notwenig .join() aufzurufen, da die Methode .get() in FutureTask auf das Ende
                             // des Threads wartet. Die .get Methode ist zudem ueberladen und laesst das Setzen eines
                             // Timeouts zu: .get(long timeout, TimeUnit unit)

            System.out.println(futureTask1.get());
            System.out.println(futureTask2.get());
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        System.out.println("Wieviele Threads hat der Prozess nun?");

        try {
            Thread.sleep(20000);
        } catch (Exception e) {
        }

        System.out.println("Ende von main");
    }
}