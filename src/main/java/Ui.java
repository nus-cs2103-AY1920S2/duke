// Deals with interactions with the user

class Ui {

    private TaskList taskList = new TaskList();

    // Will Do Stuff depending on user's input
     void user_input(String input) throws DukeException{
        String lines = "        ____________________________________________________________";
        String space = "        ";
        if(input.contains("bye")) {
            System.out.println(lines);
            System.out.println("        Bye. Hope to see you again soon");
            System.out.println(lines);
            taskList.getList().clear();
            Duke.break_checker = true;
        } else if (input.equals("list")) {
            System.out.println(lines);
            taskList.print_elements();
            System.out.println(lines);
        } else  if (input.contains("done")) {
            // To split the done with the number
            // Will think of a smarter way tomorrow.
            String[] splited_string = input.split(" ");
            Integer number = Integer.valueOf(splited_string[1]);

            // If you enter more than the list.
            if (number > taskList.size_of_list() - 1) {
                throw new DukeException("You have entered an invalid number!");
            }

            Task finished_task = taskList.getList().get(number - 1);
            finished_task.setDone(true);
            System.out.println(lines);
            System.out.println(space + "Nice! I've marked this task as done");
            System.out.println(space + " [" + finished_task.getStatusIcon() + "]"
                    + " " + finished_task.getDescription());
            System.out.println(lines);

        }
        // If the input consists of to-do
        else if (input.contains("todo")) {

            Task new_todo_task = new Todo(input);
            String todo_task = new_todo_task.format_tasks("todo");
            new_todo_task.setDescription(todo_task);
            taskList.add_to_list(new_todo_task);
            Print.print(new_todo_task, taskList.getList());
        }

        // If the task is a deadline
        else if (input.contains("deadline")) {

            Deadline new_deadline = new Deadline(input, "");
            new_deadline.setDescription(input);
            new_deadline.setBy(new_deadline.format_tasks(input));
            taskList.add_to_list(new_deadline);
            new_deadline.setD1();
            Print.print(new_deadline, taskList.getList());



        } else if (input.contains("event")) {
            Event new_event = new Event(input, "");
            new_event.setDescription(input);
            new_event.setAt(new_event.format_tasks(input));
            taskList.add_to_list(new_event);
            new_event.setD1();
            Print.print(new_event, taskList.getList());


        } else if (input.contains("delete")) {
            String[] splited_string = input.split(" ");
            Integer number = Integer.valueOf(splited_string[1]);
            Task deleted_task = taskList.getList().get(number - 1);
            taskList.remove_from_list(deleted_task);
            Print.print_delete(deleted_task, taskList.size_of_list());

        } else {
            System.out.println(lines);
            throw new DukeException(" OOPS!!! I'm sorry but I do not know what taht means :-(");
        }
    }

}
