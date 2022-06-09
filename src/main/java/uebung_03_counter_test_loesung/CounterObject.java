package uebung_03_counter_test_loesung;

/**
 * Einfaches Object mit einem Zaehler
 * @author Peter Mandl, Linus Englert
 */
 
class CounterObject 
{
    private int count = 0;

    CounterObject()
    {
        // Nichts zu konstruieren
    }

    void set(int newCount)
    {
        // Kuenstliche Verzoegerung
        try {
            Thread.sleep(20);
        }
        catch (Exception ignored) {}
        count = newCount;
    }

    int get()
    {
        return count;
    }
}
