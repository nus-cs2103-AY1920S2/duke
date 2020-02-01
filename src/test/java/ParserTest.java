import org.junit.jupiter.api.Test;

class ParserTest {
    @Test
    public void runTest() {
        Parser parser = new Parser();
        Command command1 = parser.parse("todo");
        Command command2 = parser.parse("deadline");
        Command command3 = parser.parse("event");

        assert(command1 == Command.ADD);
        assert(command2 == Command.ADD);
        assert(command3 == Command.ADD);
    }
}