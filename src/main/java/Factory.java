import java.time.DateTimeException;
import java.util.Scanner;

/** Creates task objects. */
public class Factory {

    /**
     * Creates a Todo object.
     *
     * @param input user input string.
     * @return Todo object.
     */
    public Todo createTodo(String input) {
        return new Todo(input);
    }

    /**
     * Creates a Deadline object.
     *
     * @param input user input string.
     * @return Deadline object.
     */
    public Deadline createDeadline(String input) {
        int indexCut = input.indexOf("/by");
        String desc = input.substring(0, indexCut - 1);
        String by = input.substring(indexCut + 4);
        TaskDate td = new TaskDate(by);
        return new Deadline(desc, td);
    }

    /**
     * Creates an Event object.
     *
     * @param input user input string.
     * @return Event object.
     */
    public Event createEvent(String input) {
        Event event = null;
        Scanner sc2 = new Scanner(System.in);
        TaskDate tdEnd = null;
        boolean isValid = false;

        while (!isValid) {
            try {
                System.out.println("Event end date and time: ");
                String endDate = sc2.nextLine();
                tdEnd = new TaskDate(endDate);
                isValid = true;

                int indexCut = input.indexOf("/at");
                String desc = input.substring(0, indexCut - 1);
                String at = input.substring(indexCut + 4);
                TaskDate tdStart = new TaskDate(at);
                event = new Event(desc, tdStart, tdEnd);

            } catch(ArrayIndexOutOfBoundsException e) {
                System.err.println("Invalid input, please follow the format {dd/mm/yyyy hhmm}");
            } catch(DateTimeException e) {
                System.err.println("Invalid date!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return event;
    }

}
