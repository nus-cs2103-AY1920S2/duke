import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    ExitCommand exit = new ExitCommand();
    AddCommand add = new AddCommand("todo read book");
    @Test
    public void isExit() {
        assertEquals(true, exit.isExit());
        assertEquals(false, add.isExit());
    }
}
