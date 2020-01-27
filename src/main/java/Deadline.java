
    public class Deadline extends Task {
        protected String by;

        public Deadline(int done, String task_name, String by) {
            super("deadline",done, task_name);
            this.by = by;
        }

        String getBy(){
            return by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }