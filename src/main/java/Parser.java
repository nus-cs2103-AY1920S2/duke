// To understand the user's command.
// After reading from Duke.java
// Then pass it to the corresponding classes to handle it

public class Parser {

    Ui ui = new Ui();
    String user_input;
    private TaskList taskList = new TaskList();


    // Gets the user's input
    public Parser(String user_input) {
        this.user_input = user_input;
    }

    // To understand and comprehend the user's input
    void understand_user_input() throws DukeException {
        if(this.user_input.contains("bye")) {
            ui.printBye();
            taskList.getList().clear();
            Duke.break_checker = true;
        } else if (this.user_input.contains("list")) {
            ui.printList(taskList);
        } else if (this.user_input.contains("done")) {
            Task finished_task = taskList.getList().get(split_done_string(" "));
            finished_task.setDone(true);
            ui.printDone(finished_task);
        } else if (this.user_input.contains("todo")) {
            Task new_todo_task = new Todo(this.user_input);
            new_todo_task.setDescription(new_todo_task.format_tasks("todo"));
            taskList.add_to_list(new_todo_task);
            ui.printTasks(new_todo_task, taskList.getList());
        } else if (this.user_input.contains("deadline")) {
            Deadline new_deadLine = new Deadline(this.user_input);
            new_deadLine.setDescription(this.user_input);
            new_deadLine.set_by_at(new_deadLine.format_tasks(this.user_input));
            taskList.add_to_list(new_deadLine);
            new_deadLine.setD1();
            ui.printTasks(new_deadLine, taskList.getList());
        } else if (this.user_input.contains("event")) {
            Event new_event = new Event(this.user_input);
            new_event.setDescription(this.user_input);
            new_event.set_by_at(new_event.format_tasks(this.user_input));
            taskList.add_to_list(new_event);
            new_event.setD1();
            ui.printTasks(new_event, taskList.getList());
        } else if(this.user_input.contains("delete")) {
            Task deleted_task = taskList.getList().get(split_done_string(" "));
            taskList.remove_from_list(deleted_task);
            ui.printDelete(deleted_task, taskList);
        } else {
            ui.printDontUnderstandInput();
        }


    }

    // To split the string coming in from done
    int split_done_string(String regrex_wanted) throws DukeException {
        String[] splitted_string = this.user_input.split(regrex_wanted);
        Integer array_index = Integer.valueOf(splitted_string[1]);

        if(array_index > taskList.size_of_list()-1) {
            ui.invalid_number_exception();
        }

        return array_index-1;
    }


}
