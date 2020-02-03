package duke;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DukeTest {

  @Test
  public void testDelete() {
    Duke duke = new Duke();
    String actual = duke.run("todo sleep 100 years\ndelete 1\nlist", true);
    String expected =
        "    ____________________________________________________________\n"
            + "    Got it. I've added this task:\n"
            + "    [T][X] sleep 100 years\n"
            + "    Now you have 1 tasks in the list.\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Noted. I've removed this task: \n"
            + "    [T][X] sleep 100 years\n"
            + "    Now you have 0 tasks in the list.\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Here are the tasks in your list:\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Bye. Hope to see you again soon!\n"
            + "    ____________________________________________________________\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testDone() {
    Duke duke = new Duke();
    String actual =
        duke.run(
            "todo sleep 100 years\nevent fireworks /at 2019-10-12 20:00 to 2019-10-12 21:00\ndone 1\nlist",
            true);
    String expected =
        "    ____________________________________________________________\n"
            + "    Got it. I've added this task:\n"
            + "    [T][X] sleep 100 years\n"
            + "    Now you have 1 tasks in the list.\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Got it. I've added this task:\n"
            + "    [E][X] fireworks (at: 12 Oct. 2019 20:00pm to 12 Oct. 2019 21:00pm)\n"
            + "    Now you have 2 tasks in the list.\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Nice! I've marked this task as done:\n"
            + "    [T][V] sleep 100 years\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Here are the tasks in your list:\n"
            + "    1.[T][V] sleep 100 years\n"
            + "    2.[E][X] fireworks (at: 12 Oct. 2019 20:00pm to 12 Oct. 2019 21:00pm)\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Bye. Hope to see you again soon!\n"
            + "    ____________________________________________________________\n";
    assertEquals(expected, actual);
  }

  @Test
  public void testTasks() {
    Duke duke = new Duke();
    String actual =
        duke.run(
            "todo study 24 hours\ndeadline get six packs /by 2020-02-29 09:00\nevent holidaysss /at 2020-05-01 20:00 to 2019-08-01 20:00\ndone 1\nlist",
            true);
    String expected =
        "    ____________________________________________________________\n"
            + "    Got it. I've added this task:\n"
            + "    [T][X] study 24 hours\n"
            + "    Now you have 1 tasks in the list.\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Got it. I've added this task:\n"
            + "    [D][X] get six packs (by: 29 Feb. 2020 09:00am)\n"
            + "    Now you have 2 tasks in the list.\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Got it. I've added this task:\n"
            + "    [E][X] holidaysss (at: 01 May 2020 20:00pm to 01 Aug. 2019 20:00pm)\n"
            + "    Now you have 3 tasks in the list.\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Nice! I've marked this task as done:\n"
            + "    [T][V] study 24 hours\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Here are the tasks in your list:\n"
            + "    1.[T][V] study 24 hours\n"
            + "    2.[D][X] get six packs (by: 29 Feb. 2020 09:00am)\n"
            + "    3.[E][X] holidaysss (at: 01 May 2020 20:00pm to 01 Aug. 2019 20:00pm)\n"
            + "    ____________________________________________________________\n"
            + "    ____________________________________________________________\n"
            + "    Bye. Hope to see you again soon!\n"
            + "    ____________________________________________________________\n";
    assertEquals(expected, actual);
  }

    @Test
    public void testFind() {
        Duke duke = new Duke();
        String actual =
                duke.run(
                        "todo study 24 hours\ndeadline get six packs /by 2020-08-01 09:00\nevent holidaysss /at 2020-05-01 20:00 to 2020-08-01 20:00\ndone 1\nlist\nfind 01 Aug. 2020",
                        true);
        String expected =
                "    ____________________________________________________________\n"
                        + "    Got it. I've added this task:\n"
                        + "    [T][X] study 24 hours\n"
                        + "    Now you have 1 tasks in the list.\n"
                        + "    ____________________________________________________________\n"
                        + "    ____________________________________________________________\n"
                        + "    Got it. I've added this task:\n"
                        + "    [D][X] get six packs (by: 01 Aug. 2020 09:00am)\n"
                        + "    Now you have 2 tasks in the list.\n"
                        + "    ____________________________________________________________\n"
                        + "    ____________________________________________________________\n"
                        + "    Got it. I've added this task:\n"
                        + "    [E][X] holidaysss (at: 01 May 2020 20:00pm to 01 Aug. 2020 20:00pm)\n"
                        + "    Now you have 3 tasks in the list.\n"
                        + "    ____________________________________________________________\n"
                        + "    ____________________________________________________________\n"
                        + "    Nice! I've marked this task as done:\n"
                        + "    [T][V] study 24 hours\n"
                        + "    ____________________________________________________________\n"
                        + "    ____________________________________________________________\n"
                        + "    Here are the tasks in your list:\n"
                        + "    1.[T][V] study 24 hours\n"
                        + "    2.[D][X] get six packs (by: 01 Aug. 2020 09:00am)\n"
                        + "    3.[E][X] holidaysss (at: 01 May 2020 20:00pm to 01 Aug. 2020 20:00pm)\n"
                        + "    ____________________________________________________________\n"
                        + "    ____________________________________________________________\n"
                        + "    Here are the matching tasks in your list:\n"
                        + "    1.[D][X] get six packs (by: 01 Aug. 2020 09:00am)\n"
                        + "    2.[E][X] holidaysss (at: 01 May 2020 20:00pm to 01 Aug. 2020 20:00pm)\n"
                        + "    ____________________________________________________________\n"
                        + "    ____________________________________________________________\n"
                        + "    Bye. Hope to see you again soon!\n"
                        + "    ____________________________________________________________\n";
        assertEquals(expected, actual);
    }
}
