package uebung_04_producer_consumer_test;

/**
 * Consumer-Thread: Holt eine vorgegebene Anzahl an Nachrichten aus
 * dem gemeinsam den Puffer.
 * @author Peter Mandl  
 */
public class MessageConsumer extends Thread {

    private MessageBuffer buffer = MessageBuffer.getInstance();
    private int loops;

    public MessageConsumer(int loops) {
        this.loops = loops;
    }

    public void run() {
        for (int i = 0; i < loops; i++) {
            try {
                String msg = buffer.getMessage();
                System.out.println(this.getName() + " : hole Nachricht '" + msg + "' ab");
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                System.out.println("MessageConsumer-Thread beendet sich");
                return;
            }
        }
    }
}
