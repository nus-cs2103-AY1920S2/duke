import java.time.DateTimeException;
import java.util.Scanner;

/** Creates task objects. */
public class Factory {

    /**
     * Create Todo object.
     *
     * @param desc Todo description
     * @return Todo object
     */
    public Task buildTodoFromCloud(String desc) {
        return new Todo(desc);
    }

    /**
     * Create Note object.
     *
     * @param desc note tag description
     * @param note note desciption
     * @return Note object
     */
    public Task buildNoteFromCloud(String desc, String note) {
        return new Note(desc, note);
    }

    /**
     * Create Deadline object.
     *
     * @param desc Deadline description.
     * @param dateTimeStr Deadline due datetime.
     * @return Deadline object.
     */
    public Task buildDeadlineFromCloud(String desc, String dateTimeStr) {
        TaskDate td = new TaskDate(dateTimeStr);
        if (td.getLocalDate() != null) {
            return new Deadline(desc, td);
        } else {
            return null;
        }
    }

    /**
     * Create Event object.
     *
     * @param desc Event description.
     * @param dateTimeStr1 Event start datetime.
     * @param dateTimeStr2 Event end datetime
     * @return Event object.
     */
    public Task buildEventFromCloud(String desc, String dateTimeStr1, String dateTimeStr2) {
        TaskDate td = new TaskDate(dateTimeStr1);
        TaskDate td2 = new TaskDate(dateTimeStr2);
        if (td.getLocalDate() != null && td2.getLocalDate() != null) {
            return new Event(desc, td, td2);
        } else {
            return null;
        }
    }

}
