package uebung_06_standard_semaphore_test_loesung;

/**
 * Einfaches Object mit einem Zaehler
 */
 
public class CounterObject 
{
    private int count = 0;

    CounterObject()
    {
        // Nichts zu konstruieren
    }

    void set(int newCount)
    {
        // Kuenstliche Verzoegerung um 0.1 Sek.
        try {Thread.sleep(1);} catch(Exception ignored) {}
        count = newCount;
    }

    int get()
    {
        return count;
    }
}
