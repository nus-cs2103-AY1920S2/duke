package duke.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilsTest {

    @Test
    public void isValidNumericTest() {
        boolean correct = Utils.isNumeric("124112");
        assertTrue(correct);
    }

    @Test
    public void isInvalidNumericTest() {
        boolean correct = Utils.isNumeric("123fdsfa");
        assertFalse(correct);
    }
}
