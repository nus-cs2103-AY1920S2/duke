import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    public void runTest() {
        Parser parser = new Parser();
        String todo = parser.parse("todo");
        String deadline = parser.parse("deadline");
        String event = parser.parse("event");

        assert(todo.equals("add"));
        assert(deadline.equals("add"));
        assert(event.equals("add"));
    }
}