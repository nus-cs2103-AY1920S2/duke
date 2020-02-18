package test;

import sampletest.Task;
import sampletest.Events;
import sampletest.Deadlines;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * CS2103 Individual Project.
 * JunitTest is responsible for testing the Task, Events and Deadlines classes.
 * @author Wei Cheng
 */
public class JunitTest {

    /**
     * Test the Task class.o
     */

    @Test
    public void initiateTaskTest() {
        Task sample = new Task("Code java");
        String expectedDescription = "Code java";
        String actualDescription = sample.getDescription();
        String expectedStatus = "No";
        String actualStatus = sample.checkDone();
        assertEquals(actualDescription, expectedDescription);
        assertEquals(actualStatus, expectedStatus);
    }

    /**
     * Test the Event class.
     */

    @Test
    public void initiateEventTest() {
        Events sample = new Events("Java for Life","2020-01-29");
        String expectedDate = "29 JANUARY 2020";
        String actualDate = sample.getTime();
        assertEquals(expectedDate,actualDate);
    }

    /**
     * Test the Deadlines class.
     */

    @Test
    public void initiateDeadlinesTest() {
        Deadlines sample = new Deadlines("Java for Life","2020-01-30");
        String expectedDate = "30 JANUARY 2020";
        String actualDate = sample.getTime();
        assertEquals(expectedDate,actualDate);
    }

}
