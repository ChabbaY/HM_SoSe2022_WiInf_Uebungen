package uebung_04_producer_consumer_test_loesung;

/**
 * Producer-Thread: Produziert eine vorgegebene Menge an Nachrichten und legt sie im
 * gemeinsamen Puffer ab
 * @author Peter Mandl  
 */
public class MessageProducer extends Thread {

    private final MessageBuffer BUFFER = MessageBuffer.getInstance();
    private final int LOOPS;

    public MessageProducer(int loops) {
        this.LOOPS = loops;
    }

    public void run() {
        for (int i = 0; i < LOOPS; i++) {

            try {
                BUFFER.putMessage("Text #" + i);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                System.out.println("MessageProducer-Thread beendet sich");
                return;
            }
        }
    }
}
