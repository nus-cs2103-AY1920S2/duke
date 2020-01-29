import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    void parseTest1() {
        String testCase = "";

        try {
            Parser.parse(testCase, " ");
            Assertions.fail();
        } catch (AelitaException e) {
            assertTrue(e instanceof EmptyInputAelitaException);
        }
    }

    @Test
    void parseTest2() {
        String testCase = "todo individual project";
        try {
            String[] output = Parser.parse(testCase, " ");
            assertEquals("todo", output[0]);
            assertEquals("individual", output[1]);
            assertEquals("project", output[2]);

        } catch (EmptyInputAelitaException e) {
            Assertions.fail();
        }
    }

    @Test
    void parseTest3() {
        String testCase = "deadline individual project /by 2020-01-01";
        try {
            String[] output = Parser.parse(testCase, "/");
            assertEquals("deadline individual project ", output[0]);
            assertEquals("by 2020-01-01", output[1]);

        } catch (EmptyInputAelitaException e) {
            Assertions.fail();
        }
    }

    @Test
    void parseTest4() {
        String testCase = "2-4pm";
        try {
            String[] output = Parser.parse(testCase, "-");
            assertEquals("2", output[0]);
            assertEquals("4pm", output[1]);

        } catch (EmptyInputAelitaException e) {
            Assertions.fail();
        }
    }
}
