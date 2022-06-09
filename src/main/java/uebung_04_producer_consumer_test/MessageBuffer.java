package uebung_04_producer_consumer_test;

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
    //TODO: Synchronisation des Methodenzugriffs ergaenzen
    public void putMessage(String msg) throws InterruptedException {
        while (!isEmpty()) {
            try {
                //TODO: eine sinnvolle Operation anstelle von throw ergaenzen
                throw new InterruptedException("Diese Anweisung muss durch eine sinnvolle ersetzt werden");
            } catch (InterruptedException e) {
                throw e;
            }
        }
        message = msg;
        //TODO: eine sinnvolle Operation ergaenzen
    }

    /**
     * Nachricht aus Puffer auslesen
     * @return Nachricht
     * @throws InterruptedException Unterbrechung des wait-Aufrufs
     */
    //TODO: Synchronisation des Methodenzugriffs
    public String getMessage() throws InterruptedException {
        while (isEmpty()) {
            try {
                //TODO: eine sinnvolle Operation anstelle von throw ergaenzen
                throw new InterruptedException("Diese Anweisung muss durch eine sinnvolle ersetzt werden");
            } catch (InterruptedException e) {
                throw e;
            }
        }
        String result = message;
        clearBuffer();
        //TODO: eine sinnvolle Operation ergaenzen
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
