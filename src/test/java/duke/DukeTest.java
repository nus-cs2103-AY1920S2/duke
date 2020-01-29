// package duke;

// import static org.junit.jupiter.api.Assertions.assertEquals;

// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// public class DukeTest {
//     private final ByteArrayInputStream in = new ByteArrayInputStream(("Hello").getBytes());
//     private final ByteArrayOutputStream out = new ByteArrayOutputStream();
//     private final PrintStream outContent = new PrintStream(out);

//     @BeforeEach
//     public void setUpStreams() {
//         System.setOut(outContent);
//     }

//     @Test
//     public void testBot() {
//         // ByteArrayInputStream in = new ByteArrayInputStream(
//         // ("Hello" + System.lineSeparator() + "2" + System.lineSeparator() + "uwu" +
//         // System.lineSeparator() + "bye")
//         // .getBytes());
//         Duke bot = new Duke();
//         bot.start();
//         System.setIn(in);
//         // assertEquals("Hello\n2\nuwu\nBye. Hope to see you again soon!\n",
//         // outContent.toString());
//         assertEquals("Hello\n", outContent.toString());
//     }
// }
