package uebung_02_threads_test_loesungwithcallable;

import java.util.concurrent.Callable;

/**
 * https://docs.oracle.com/en/java/javase/15/docs/api/java.base/java/util/concurrent/Callable.html
 */
public class MyThreadCallable implements Callable<String> {

    public String messageText;

    public MyThreadCallable(String messageText) {
        this.messageText = messageText;
    }

    @Override
    public String call() throws Exception {
        // Im Gegensatz zu run koennen bei call Exceptions geworfen werden

        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        System.out.println("Thread hat seine Prioritaet veraendert auf: " + Thread.currentThread().getPriority());

        // Thread gibt den Text 10 mal aus
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread " + Thread.currentThread().getId() + ": " + messageText);
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                // Ausnahme nicht behandelt
            }
        }
        // Return kann bei Callables genutzt werden, um ein Ergebnis zurueckzugeben, das mit
        // der Methode FutureTask.get(() im wartenden Thread (hier unser Main-Thread) ausgelesen werden kann.
        return "Ich - Thread " + Thread.currentThread().getId() + " - bin fertig mit der Verarbeitung";
    }
}