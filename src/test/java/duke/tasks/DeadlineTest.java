package cathulhu.tasks;

import cathulhu.CathulhuException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    /**
     * Checks if the Deadline toString method works as expected
     */
    public void toStringTest() {
        String response;
        try{
            Deadline dl = new Deadline("CS2103 IP", "2020-01-28 23:59");
            dl.markAsDone();
            response = dl.toString();
        } catch (CathulhuException e) {
            response = e.getMessage();
        }
        assertEquals("[D][Y] CS2103 IP (by: Jan 28 2020 23:59 )", response);
    }

    @Test
    /**
     * Checks if the toDataString method works as expected
     */
    public void toDataStringTest() {
        String response;
        try{
            response = new Deadline("CS2103", "2020-01-28").toDataString();
        } catch (CathulhuException e) {
            response = e.getMessage();
        }
        assertEquals("D:;:0:;:CS2103:;:2020-01-28", response);
    }
}
