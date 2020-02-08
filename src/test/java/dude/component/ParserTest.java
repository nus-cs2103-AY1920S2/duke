package dude.component;

import dude.command.AddTaskCommand;
import dude.command.ByeCommand;
import dude.command.CheckDateCommand;
import dude.command.Command;
import dude.command.ListCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    void parse_emptyString_exceptionThrown() {
        try {
            Parser.parse("");
            fail();
        } catch (ParsingException e) {
            assertEquals("Sorry mate, I didn't catch your drift", e.getMessage());
        }
    }

    @Test
    void parse_singleWordCommandWithWhiteSpace_correctCommandClassReturned() {
        Command byeCommand = assertDoesNotThrow(() -> Parser.parse("    bye"));
        Command todayCommand = assertDoesNotThrow(() -> Parser.parse("  today  "));
        Command listCommand = assertDoesNotThrow(() -> Parser.parse(" list          "));

        assertTrue(byeCommand instanceof ByeCommand);
        assertTrue(todayCommand instanceof CheckDateCommand);
        assertTrue(listCommand instanceof ListCommand);
    }

    @Test
    void parse_incorrectDateFormat_exceptionThrown() {
        String dateString = "2000/01/01";
        try {
            Parser.parse("deadline xxx /by " + dateString);
            fail();
        } catch (ParsingException e) {
            assertEquals("I don't understand this date: " + dateString, e.getMessage());
            assertEquals("deadline description /by yyyy-mm-dd", e.getUsageMsgs()[0]);
        }
    }

    @Test
    void parse_correctDateFormat_correctCommandClassReturned() {
        Command eventCommand = assertDoesNotThrow(() ->
                Parser.parse("event blah blah /from 2060-12-31 /to 2070-01-14"));
        assertTrue(eventCommand instanceof AddTaskCommand);
    }

    @Test
    void parse_incorrectArgumentOrder_exceptionThrown() {
        try {
            Parser.parse("event todo /to 2000-01-01 /from 2001-12-31");
            fail();
        } catch (ParsingException e) {
            assertEquals("Look's like your command is incomplete, mate", e.getMessage());
            assertEquals("event description /from yyyy-mm-dd /to yyyy-mm-dd", e.getUsageMsgs()[0]);
        }
    }
}