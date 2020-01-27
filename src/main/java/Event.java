
    public class Event extends Task {
        protected String at;

        public Event(int done, String task_name, String at) {
            super("event",done, task_name);
            this.at = at;
        }

        String getAt(){
            return at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }