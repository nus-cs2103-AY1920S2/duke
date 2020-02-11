package duke.tasks;

import java.time.LocalDate;
//Adapted from https://nus-cs2103-ay1920s2.github.io/website/schedule/week2/project.html

/**
 * Encapsulates an abstract dated task of the user.
 * Dated tasks have a date whose semantics is subclass-defined.
 */
public abstract class DatedTask extends Task {
    /**
     * Constructs a new DatedTask instance.
     * @param description Task description
     */
    public DatedTask(String description) {
        super(description);
    }
    
    /**
     * Returns the date associated with the `DatedTask`.
     * @return Associated `LocalDate` date
     */
    public abstract LocalDate getDate();
}

