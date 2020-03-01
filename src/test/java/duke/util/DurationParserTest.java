package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Period;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for {@code FrequencyParser}.
 */
public class DurationParserTest {
    /**
     * Tests the {@code parseDuration} static method.
     */
    @Test
    public void testParseDuration() {
        // Assumption is that the string is already lowercase.
        assertEquals(Duration.ofHours(5), DurationParser.parseDuration("5 hours"));
        assertEquals(Period.ofDays(2), DurationParser.parseDuration("2 days"));
        assertEquals(Period.ofWeeks(10), DurationParser.parseDuration("10 weeks"));
        assertEquals(Period.ofMonths(1), DurationParser.parseDuration("1 month"));
        assertEquals(Period.ofYears(3), DurationParser.parseDuration("3 years"));
    }

    /**
     * Tests the {@code parseDurationToString} static method.
     */
    @Test
    public void testParseDurationToString() {
        // Assumption is that the string is already lowercase.
        assertEquals("5 hours", DurationParser.parseDurationToString("5 hour"));
        assertEquals("2 days", DurationParser.parseDurationToString("2 day"));
        assertEquals("10 weeks", DurationParser.parseDurationToString("10 week"));
        assertEquals("1 month", DurationParser.parseDurationToString("1 months"));
        assertEquals("3 years", DurationParser.parseDurationToString("3 years"));
    }
}
