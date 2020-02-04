import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest() throws IOException {
        assertEquals(2, 2);

        Duke duke = new Duke("./src/main/java/database.txt");
        duke.run();
    }
}