
    public class Event extends Task {
        protected String at;

        public Event(String task_name, String at) {
            super(task_name);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }