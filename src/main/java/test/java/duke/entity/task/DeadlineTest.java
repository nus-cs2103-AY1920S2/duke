package main.java.test.java.duke.entity.task;

import main.java.duke.entity.task.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void printTask_withDateTimeFormatChange_success() {
        assertEquals(new Deadline("do homework", "2020-02-10 19:00").printTask(), "[D][\u2718] do homework (by: Mon, 10 Feb 2020 07:00 PM)");
    }
}