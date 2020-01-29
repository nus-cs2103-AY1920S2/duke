package duke.other;

import duke.command.DeleteCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {


    @Test
    void detailsTest() {
        String[] replyArr = {"event","CNY event /at 2020/1/30"};
        assertEquals(" CNY event /at 2020/1/30", Parser.details(replyArr) );
    }
}