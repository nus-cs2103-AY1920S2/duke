package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for {@code FrequencyParser}.
 */
public class FrequencyParserTest {
    /**
     * Tests the {@code parseFrequency} static method.
     */
    @Test
    public void testFrequencyParser() {
        // Assumption is that the input is already cleaned and lowered.
        assertEquals(Frequency.DAILY, FrequencyParser.parseFrequency("every day"));
        assertEquals(Frequency.DAILY, FrequencyParser.parseFrequency("day"));
        assertEquals(Frequency.DAILY, FrequencyParser.parseFrequency("daily"));
        assertEquals(Frequency.FORTNIGHTLY, FrequencyParser.parseFrequency("every 2 weeks"));
        assertEquals(Frequency.FORTNIGHTLY, FrequencyParser.parseFrequency("every two weeks"));
        assertEquals(Frequency.FORTNIGHTLY, FrequencyParser.parseFrequency("2 weeks"));
        assertEquals(Frequency.FORTNIGHTLY, FrequencyParser.parseFrequency("biweekly"));
        assertEquals(Frequency.FORTNIGHTLY, FrequencyParser.parseFrequency("fortnightly"));
        assertEquals(Frequency.WEEKLY, FrequencyParser.parseFrequency("weekly"));
        assertEquals(Frequency.WEEKLY, FrequencyParser.parseFrequency("every week"));
        assertEquals(Frequency.WEEKLY, FrequencyParser.parseFrequency("week"));
        assertEquals(Frequency.MONTHLY, FrequencyParser.parseFrequency("every month"));
        assertEquals(Frequency.MONTHLY, FrequencyParser.parseFrequency("monthly"));
        assertEquals(Frequency.MONTHLY, FrequencyParser.parseFrequency("month"));
    }
}
