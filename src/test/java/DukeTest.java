import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void DukeTest() {
        String mypath = "C:\\Users\\SOHNB101\\Documents\\myduke\\duke\\data\\duke.txt";

        assertEquals(new Duke(mypath).getFilePath(), mypath);
    }
}
