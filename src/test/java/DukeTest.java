import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.time.LocalDate;

public class DukeTest {
    @Test

    public void taskTest() {
        Task myEvent = new Event("borrow book", LocalDate.parse("2009-12-03"));
        Task myDeadline = new Deadline("swimming", LocalDate.parse("2011-12-03"));
        Task myTodo = new Todo("borrow myself");

        myEvent.markAsDone();
        assertTrue(myEvent.isDone);
    }

    public void textParserTest() {
        String test1 = "Hi i am a guy";
        String test23 = "a/b/c/d/e/f";

        String[] myresult = { "Hi", "i am a guy"};
        String[] myresult2 = {"a", "b/c/d/e/f"};
        String[] myresult3 = {"a", "b", "c", "d", "e", "f"};

        String[] mytest1 = TextParser.myFirstParser(test1);
        String[] mytest2 = TextParser.mySecondParser(test23);
        String[] mytest3 = TextParser.myThirdParser(test23);

        assertArrayEquals(myresult, mytest1);
        assertArrayEquals(myresult2, mytest2);
        assertArrayEquals(myresult3, mytest3);

    }

}
