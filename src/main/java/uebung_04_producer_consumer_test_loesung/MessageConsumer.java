package uebung_04_producer_consumer_test_loesung;

/**
 * Consumer-Thread: Holt eine vorgegebene Anzahl an Nachrichten aus
 * dem gemeinsam den Puffer.
 * @author Peter Mandl  
 */
public class MessageConsumer extends Thread {

    private final MessageBuffer BUFFER = MessageBuffer.getInstance();
    private final int LOOPS;

    public MessageConsumer(int loops) {
        this.LOOPS = loops;
    }

    public void run() {
        for (int i = 0; i < LOOPS; i++) {
            try {
                String msg = BUFFER.getMessage();
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                System.out.println("MessageConsumer-Thread beendet sich");
                return;
            }
        }
    }
}