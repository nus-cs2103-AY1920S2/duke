package com.duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TodoTest {

    @Test
    public void TestWriteForm() {
        assertEquals("T|0|test!", new Todo("test!").generateWriteFormat());
    }

}
