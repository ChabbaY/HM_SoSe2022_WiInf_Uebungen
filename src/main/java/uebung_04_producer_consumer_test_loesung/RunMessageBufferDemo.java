package uebung_04_producer_consumer_test_loesung;

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

        final int loops = 30;

        final int COUNT_P = 10, COUNT_C = 11;
        Thread[] prodcon = new Thread[COUNT_P + COUNT_C];

        for (int i = 0; i < prodcon.length; i++) {
            if (i < COUNT_P) {
                prodcon[i] = new MessageProducer(loops * COUNT_C);
            } else {
                prodcon[i] = new MessageConsumer(loops * COUNT_P);
            }
        }

        for (Thread t : prodcon) {
            t.start();
        }

        try {
            for (Thread t : prodcon) {
                t.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
