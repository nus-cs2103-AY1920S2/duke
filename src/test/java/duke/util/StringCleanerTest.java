package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * JUnit test class for {@code StringCleaner}.
 */
public class StringCleanerTest {
    /**
     * Tests the string cleaning methods.
     */
    @Test
    public void testStringCleaner() {
        assertEquals("This is the cleaned string.", StringCleaner.cleanString("  This is the cleaned string.   "));
        assertEquals("this is lowered.", StringCleaner.cleanAndLowerString("  ThIs iS LOWERed.   "));
    }
}
