package duke.main;

import duke.exception.DukeException;

public class Ui {
    public String showDukeError(DukeException e) {
        return e.toString();
    }

    public String showDateTimeError() {
        return "☹ DATE FORMAT is yyyy/mm/dd!\n" 
                + "TIME FORMAT is HHmm!\n";
    }

    public String reply(String reply) {
        return reply;
    }

    public String goodBye() {
        return "Yes. FINALLY. Hope never to see you again!";
    }

    public String sayHi() {
        return "Arghhhh... It's you again.";
    }
}