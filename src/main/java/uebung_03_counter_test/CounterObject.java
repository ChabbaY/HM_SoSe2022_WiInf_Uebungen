package uebung_03_counter_test;

/**
 * Einfaches Object mit einem Zaehler
 * @author Peter Mandl
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
            Thread.sleep(10);
            }
        catch(Exception x) {}
        count = newCount;
    }

    int get()
    {
        return count;
    }
}
