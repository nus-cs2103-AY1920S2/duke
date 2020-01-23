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
                } else if (Integer.parseInt(allargs[1]) > Duke.count) {
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
                if (s1.lastIndexOf(s2) == -1)
                    throw new DukeException("Please add a '/by' and then add the date/time subsequently");
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
                if (s1.lastIndexOf(s2) == -1)
                    throw new DukeException("Please add a '/at' and then add the date/time subsequently");
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