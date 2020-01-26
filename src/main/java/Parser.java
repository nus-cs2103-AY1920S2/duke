// To understand the user's command.
// After reading from Duke.java
// Then pass it to the corresponding classes to handle it

import javax.swing.text.DateFormatter;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    Ui ui = new Ui();
    private TaskList taskList = new TaskList();
    private Deadline_event_hash deadline_event_hash = new Deadline_event_hash();



    // To understand and comprehend the user's input
    void understand_user_input(String user_input) throws DukeException {
        if(user_input.contains("bye")) {
            ui.printBye();
            taskList.getList().clear();
            Duke.break_checker = true;
        } else if (user_input.contains("list")) {
            ui.printList(taskList);
        } else if (user_input.contains("done")) {
            Task finished_task = taskList.getList().get(split_done_string(" ", user_input));
            finished_task.setDone(true);
            ui.printDone(finished_task);
        } else if (user_input.contains("todo")) {
            Task new_todo_task = new Todo(user_input);
            new_todo_task.setDescription(new_todo_task.format_tasks("todo"));
            taskList.add_to_list(new_todo_task);
            ui.printTasks(new_todo_task, taskList.getList());
        } else if (user_input.contains("deadline")) {
            Deadline new_deadLine = new Deadline(user_input);
            new_deadLine.setDescription(user_input);
            new_deadLine.setBy(new_deadLine.format_tasks(user_input));
            new_deadLine.setD1();
            taskList.add_to_list(new_deadLine);


          //  deadline_event_hash.addToHashMap(new_deadLine.d1.toLocalDate().toString(), new_deadLine);
            ui.printTasks(new_deadLine, taskList.getList());
        } else if (user_input.contains("event")) {
            Event new_event = new Event(user_input);
            new_event.setDescription(user_input); 
            new_event.setAt(new_event.format_tasks(user_input));
            new_event.setD1();
            taskList.add_to_list(new_event);
            deadline_event_hash.addToHashMap(new_event.d1.toLocalDate().toString(), new_event);
            ui.printTasks(new_event, taskList.getList());
        } else if(user_input.contains("delete")) {
            Task deleted_task = taskList.getList().get(split_done_string(" ", user_input));
            taskList.remove_from_list(deleted_task);
            ui.printDelete(deleted_task, taskList);
        }
        // When the user types "show ..."
        // With the date as a string, then will check if got the event a not
        else if (user_input.contains("show")) {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String date = split_show_string("show", user_input);
            LocalDate localDate = LocalDate.parse(date,dateTimeFormatter);
            String formatted_date = localDate.toString();
        }

        else {
            ui.printDontUnderstandInput();
        }


    }

    // To split the string coming in from done
    // Returns the index of the string after the word
    int split_done_string(String regrex_wanted, String user_input) throws DukeException {
        String[] splitted_string = user_input.split(regrex_wanted);
        Integer array_index = Integer.valueOf(splitted_string[1]);

        if(array_index > taskList.size_of_list()-1) {
            ui.invalid_number_exception();
        }

        return array_index-1;
    }

    String split_show_string(String regrex_wanted, String user_input) throws DukeException {
        String[] splitted_string = user_input.split(regrex_wanted);
        System.out.println(splitted_string[1].trim());
        return splitted_string[1].trim();
    }


}
