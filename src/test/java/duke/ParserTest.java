package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class ParserTest {

    @Test
    public void satisfiesMinimumDoneCommandLength_validInput_trueReturned(){
        String[] commandWords = new String[] {"todo", "good"};
        assertTrue(new Parser().satisfiesMinimumDoneCommandLength(commandWords));
    }

    @Test
    public void satisfiesMinimumDoneCommandLength_invalidInput_falseReturned(){
        String[] commandWords = new String[] {"todo"};
        assertFalse(new Parser().satisfiesMinimumDoneCommandLength(commandWords));
    }

    @Test
    public void determineIfValidDoneCommand_NumberSmallerThanListSize_trueReturned() {
        String[] commandWords = new String[] {"done", "1"};
        int taskListSize = 5;
        assertTrue(Integer.parseInt(commandWords[1]) <= taskListSize);
    }

    @Test
    public void determineIfValidDoneCommand_NumberLargerThanListSize_trueReturned() {
        String[] commandWords = new String[] {"done", "100"};
        int taskListSize = 5;
        assertFalse(Integer.parseInt(commandWords[1]) <= taskListSize);
    }

    @Test
    public void determineIfValidDoneCommand_invalidTokenNotNumber_exceptionThrown() {
        try {
            String[] commandWords = new String[] {"done", "ThisIsNotANumber, pls give an exception"};
            int taskListSize = 5;
            assertFalse(Integer.parseInt(commandWords[1]) <= taskListSize);
        } catch (Exception e) {
            assertEquals("For input string: \"ThisIsNotANumber, pls give an exception\"", e.getMessage());
        }
    }

}