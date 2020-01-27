
    public class Todo extends Task {

        Todo(int done, String task_name){

            super("todo",done, task_name);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }
