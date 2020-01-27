public class ToDo extends Task {


    public ToDo(String date, String taskDescription) {

        super(date, taskDescription);

    }

    @Override
    public String toString() {

        return "[" + Types.ToDo + "]"
                + "[" + super.getStatus() + "]"
                + " " + taskDescription;
    }

    @Override
    public Types getType() {

        return Types.ToDo;

    }
}
