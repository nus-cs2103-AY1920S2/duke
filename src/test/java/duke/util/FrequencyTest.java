package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for {@code Frequency} enum.
 */
public class FrequencyTest {
    private static final int NUM_FREQUENCY = 4;

    /**
     * Tests the integrity of the {@code Frequency} enum.
     */
    @Test
    public void testFrequency() {
        assertEquals(NUM_FREQUENCY, Frequency.values().length);
        try {
            Frequency.valueOf("DAILY");
            Frequency.valueOf("WEEKLY");
            Frequency.valueOf("FORTNIGHTLY");
            Frequency.valueOf("MONTHLY");
        } catch (IllegalArgumentException e) {
            fail();
        }
    }
}
