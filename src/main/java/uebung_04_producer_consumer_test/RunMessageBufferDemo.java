package uebung_04_producer_consumer_test;

/**
 * Test fuer Producer/Consumer-Beispiel
 * Es werden je ein Producer und ein Consumer Thread gestartet und es wird auf das Ende
 * der beiden Threads gewartet
 * Das Programm ist noch unvollstaendig.
 * @author Peter Mandl  
 */
public class RunMessageBufferDemo {

    /**
     * @param args nicht verwendet
     */
    public static void main(String[] args) {

        int loops = 30;

        // Ein Produzent und ein Konsument werden erzeugt,
        MessageProducer p = new MessageProducer(loops);
        MessageConsumer c = new MessageConsumer(loops);

        // ... dann gestartet
        p.start();
        c.start();

        // ... und nun wird auf deren Ende gewartet
        try {
            p.join();
            c.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
