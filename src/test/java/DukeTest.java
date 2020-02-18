import duke.Duke;
import duke.exception.InvalidDateException;
import duke.exception.InvalidIndexException;
import duke.exception.MissingInfoException;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    Duke duke;

    public DukeTest() {
        duke = new Duke();
    }

    @Test
    public void testInvalidIndex() {
        /*
        Test cases:
        - done 10
        - done a
        - delete 10
        - delete a
        - edit 10 werk
        - edit a werk
         */
        String expected = new Ui().getError(new InvalidIndexException().getMessage());
        String[] inputs = {"done 10", "done a", "delete 10", "delete a", "edit 10 werk", "edit a werk"};
        String response;
        for (String i: inputs) {
            response = duke.getResponse(i);
            assertEquals(response, expected);
        }
    }

    @Test
    public void testEmptyDesc() {
        /*
        Test cases:
        - todo
        - deadline
        - event
        - delete
        - done
        - find
        - edit
         */
        String[] inputs = {"todo", "deadline", "event", "delete", "done", "find", "edit"};
        String expected;
        String response;
        for (String i: inputs) {
            expected = new Ui().getError(new MissingInfoException(i, false).getMessage());
            response = duke.getResponse(i);
            assertEquals(expected, response);
        }
    }

    @Test
    public void testMissingDate() {
        /*
        Test cases:
        - deadline some description
        - deadline some description /by
        - event some description
        - event some description /at
         */
        String[] taskTypes = {"deadline", "event"};
        String expected;
        String response;
        for (String type: taskTypes) {
            expected = new Ui().getError(new MissingInfoException(type, true).getMessage());
            response = duke.getResponse(type + " some description");
            assertEquals(expected, response);
            if (type.equals("deadline")) {
                response = duke.getResponse(type + " some description /by");
            } else if (type.equals("event")) {
                response = duke.getResponse(type + " some description /at");
            }
            assertEquals(expected, response);
        }
    }

    @Test
    public void testInvalidDate() {
        /*
        Test cases:
        - deadline some description /by 2020-15-01
        - event some description /at 2020-15-01
        - edit 1 some description /by 2020-15-01
        - edit 1 some description /at 2020-15-01
         */
        String expected = new Ui().getError(new InvalidDateException().getMessage());
        String[] inputs = { "deadline some description /by 2020-15-01",
            "event some description /at 2020-15-01",
            "edit 1 some description /by 2020-15-01",
            "edit 1 some description /at 2020-15-01" };
        String response;
        for (String i: inputs) {
            response = duke.getResponse(i);
            assertEquals(response, expected);
        }
    }
}