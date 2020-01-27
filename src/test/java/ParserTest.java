import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void getIndexTest() {
        Parser p = new Parser("done 2");
        Parser q = new Parser("delete 3");

        try {
            Assertions.assertEquals(p.getIndex(), 2);
            Assertions.assertEquals(q.getIndex(), 3);
        }
        catch (DukeException e) {
            Assertions.fail();
        }
    }

    @Test
    public void getCommandTest() {
        Parser p = new Parser("done 2");
        Parser q = new Parser("delete 2");
        Parser r = new Parser("deadline test /by 2020-01-28");

            Assertions.assertEquals(p.getCommand(), "done");
            Assertions.assertEquals(q.getCommand(), "delete");
            Assertions.assertEquals(r.getCommand(), "deadline");
    }

    @Test
    public void getTimingTest() {
        Parser p = new Parser("deadline test /by 2020-01-28");
        Parser q = new Parser("deadline test2 /by 2020-03-28 1800");
        Parser r = new Parser("event test3 /at Monday 2pm");

        try {
            Assertions.assertEquals(p.getTiming(), "2020-01-28");
            Assertions.assertEquals(q.getTiming(), "2020-03-28 1800");
            Assertions.assertEquals(r.getTiming(), "Monday 2pm");

        }
        catch (DukeException e){
            Assertions.fail();
        }
    }
}
