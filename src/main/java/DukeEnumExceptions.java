public enum DukeEnumExceptions {
    LIST {
        public boolean checkerror(String s1, String s2) {
            try {
                int numOfArgs = Integer.parseInt(s2);
                String[] allargs = s1.split("\\s+");

                if (allargs.length != numOfArgs)
                    throw new DukeException("Invalid Input!");
            } catch (DukeException ex) {
                Duke.HorizontalLine();
                System.out.println(ex.getMessage());
                Duke.HorizontalLine();
                return false;
            }
            return true;
        }
    },
    DONE {
        public boolean checkerror(String s1, String s2) {
            try {
                int numOfArgs = Integer.parseInt(s2);
                String[] allargs = s1.split("\\s+");

                if (allargs.length != numOfArgs) {
                    throw new DukeException("'done' command should only have 2 arguments");
                } else if (Integer.parseInt(allargs[1]) > Duke.listOfTask.size()) {
                    throw new DukeException("Task index is not found!");
                }
            } catch (DukeException ex) {
                Duke.HorizontalLine();
                System.out.println(ex.getMessage());
                Duke.HorizontalLine();
                return false;
            }
            return true;
        }
    },
    TODO {
        public boolean checkerror(String s1, String s2) {
            try {
                int numOfArgs = Integer.parseInt(s2);
                String[] allargs = s1.split("\\s+");

                if (numOfArgs > allargs.length)
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } catch (DukeException ex) {
                Duke.HorizontalLine();
                System.out.println(ex.getMessage());
                Duke.HorizontalLine();
                return false;
            }
            return true;
        }
    },
    DEADLINE {
        public boolean checkerror(String s1, String s2) {
            try {
                int limit = s1.lastIndexOf("/by") - 1;
                if (s1.lastIndexOf(s2) == -1)
                    throw new DukeException("Please add a '/by' and then add the date/time subsequently");
                else if (limit < 9)
                    throw new DukeException("No description! Please add description to your deadline");
            } catch (DukeException ex) {
                Duke.HorizontalLine();
                System.out.println(ex.getMessage());
                Duke.HorizontalLine();
                return false;
            }
            return true;
        }
    },
    EVENT {
        public boolean checkerror(String s1, String s2) {
            try {
                int limit = s1.lastIndexOf("/at") - 1;
                if (s1.lastIndexOf(s2) == -1)
                    throw new DukeException("Please add a '/at' and then add the date/time subsequently");
                else if (limit < 6)
                    throw new DukeException("No description! Please add description to your event");
            } catch (DukeException ex) {
                Duke.HorizontalLine();
                System.out.println(ex.getMessage());
                Duke.HorizontalLine();
                return false;
            }
            return true;
        }
    },
    DELETE {
        @Override
        public boolean checkerror(String s1, String s2) {
            try {
                int numOfArgs = Integer.parseInt(s2);
                String[] allargs = s1.split("\\s+");

                if (allargs.length != numOfArgs) {
                    throw new DukeException("'delete' command should only have 2 arguments");
                } else if (Integer.parseInt(allargs[1]) > Duke.listOfTask.size()) {
                    throw new DukeException("Task index is not found!");
                }
            } catch (DukeException ex) {
                Duke.HorizontalLine();
                System.out.println(ex.getMessage());
                Duke.HorizontalLine();
                return false;
            }
            return true;
        }
    };

    public abstract boolean checkerror(String s1, String s2);
}