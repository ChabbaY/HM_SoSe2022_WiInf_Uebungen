package uebung_04_producer_consumer_test_loesung;

/**
 * Message-Puffer fuer Producer-Consumer-Beispiel (unvollstaendig)
 * @author Peter Mandl  
 */

public class MessageBuffer {

    private static MessageBuffer instance = null;
    private String message = null;

    /**
     * Nachrichtenpuffer genau einmal erzeugen. Ist er schon angelegt, wird nur die vorhandene
     * Referenz zurueckgegeben
     * @return Referenz auf Nachrichtenpuffer
     */
    public static MessageBuffer getInstance() {
        if (instance == null) instance = new MessageBuffer();
        return instance;
    }

    /**
     * Nachricht in Puffer einstellen
     * @param msg Nachricht
     * @throws InterruptedException Unterbrechung des wait-Aufrufs
     */
    public synchronized void putMessage(String msg) throws InterruptedException {
        while (!isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " : Stelle Nachricht #" + msg.substring(6) + " ein");
        message = msg;
        notifyAll();
    }

    /**
     * Nachricht aus Puffer auslesen
     * @return Nachricht
     * @throws InterruptedException Unterbrechung des wait-Aufrufs
     */
    public synchronized String getMessage() throws InterruptedException {
        while (isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String result = message;
        System.out.println(Thread.currentThread().getName() + " : hole Nachricht '" + message + "' ab");
        clearBuffer();
        notifyAll();
        return result;
    }

    /**
     * Pruefen, ob Puffer leer ist
     * @return true = Puffer ist leer, false = Puffer ist nicht leer
     */
    private boolean isEmpty() {
        return (message == null);
    }

    /**
     * Puffer loeschen, d. h. alle Nachrichten entfernen, sofern welche drinnen sind
     */
    private void clearBuffer() {
        message = null;
    }
}
