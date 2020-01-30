package duke.other;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {


    @Test
    void stringifyDetailsTest() {
        String[] replyArr = {"event", "CNY event /at 2020/1/30"};
        assertEquals(" CNY event /at 2020/1/30", Parser.stringifyDetails(replyArr));
    }
}