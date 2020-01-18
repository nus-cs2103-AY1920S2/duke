public class Todo extends Task {

    String formatted_todo_string = "";

     Todo(String description) {
        super(description);
    }

    @Override
    String format_tasks() {
        // Needed the space after "to do" due to space formatting.
        String[] splited_string = super.getDescription().split("todo ");
        StringBuilder task = new StringBuilder();

        for(int i=1; i< splited_string.length; i++) {
            if(i==splited_string.length-1) {
                task.append(splited_string[i]);
            } else {
                task.append(splited_string[i]).append(" ");
            }
        }
        return task.toString();
    }

    @Override
    void setDescription(String s) {
        super.setDescription(s);
    }


    @Override
    public String toString() {
        return  " [T]" + super.toString();
    }


}
