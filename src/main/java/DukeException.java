public class DukeException extends Exception {

    //move the type of exception as enum

    protected String errorMessage = "     ☹ OOPS!!! ";
    public DukeException(String message) {
        super(message);

        switch (message) {
            case "emptyList":
                this.errorMessage += "There is no task yet. Please add some tasks.";
                break;

            case "doneMissingIndex":
                this.errorMessage += "The index number of a done query cannot be empty.";
                break;

            case "doneWrongIndexFormat":
                //Fallthrough
            case "deleteWrongIndexFormat":
                this.errorMessage += "Please use an integer as index number.";
                break;

            case "doneWrongIndexRange":
                //Fallthrough
            case "deleteWrongIndexRange":
                this.errorMessage += "There is only "+ Task.taskList.size() + " tasks. Please enter valid task index.";
                break;

            case "doneComplectedTask":
                this.errorMessage += "The task is already completed.";
                break;

            case "taskMissingDescription":
                this.errorMessage += "The description of a todo/deadline/event cannot be empty.";
                break;

            case "deadline&eventWrongDescriptionFormat":
                this.errorMessage += "Please follow the valid deadline/event creation format. " +
                        "\n       e.g. deadline description /by date ; event description /at date";
                break;

            case "randomInput":
                this.errorMessage += "I'm sorry, but I don't know what that means :-(";
                break;

            case "deleteMissingIndex":
                this.errorMessage += "Please enter the index of the task you want to delete.";
                break;

        }
    }

}
