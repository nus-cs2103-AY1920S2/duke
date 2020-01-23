
    public class Todo extends Task {

        Todo(String task_name){
            super(task_name);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }
