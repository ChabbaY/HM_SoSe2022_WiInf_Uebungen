package uebung_04_producer_consumer_test;

/**
 * Producer-Thread: Produziert eine vorgegebene Menge an Nachrichten und legt sie im
 * gemeinsamen Puffer ab
 * @author Peter Mandl  
 */
public class MessageProducer extends Thread {

    private MessageBuffer buffer = MessageBuffer.getInstance();
    private int loops;

    public MessageProducer(int loops) {
        this.loops = loops;
    }

    public void run() {
        for (int i = 0; i < loops; i++) {

            try {
                System.out.println(this.getName() + " : Stelle Nachricht #" + i + " ein");
                buffer.putMessage("Text #" + i);
            } catch (InterruptedException e) {
                System.out.println(e.toString());
                System.out.println("MessageProducer-Thread beendet sich");
                return;
            }
        }
    }
}
