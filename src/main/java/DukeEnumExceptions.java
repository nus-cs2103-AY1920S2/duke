public enum DukeEnumExceptions {
    /**
     * The enum class for checking for any errors of the input command
     *
     * @author Amos Cheong
     */
    LIST {
        public void checkError(String s1, String s2, TaskList list) throws DukeException {
            int numOfArgs = Integer.parseInt(s2);
            String[] allargs = s1.split("\\s+");

            if (allargs.length != numOfArgs)
                throw new DukeException("Invalid Input!");
        }
    },
    DONE {
        public void checkError(String s1, String s2, TaskList list) throws DukeException{
            try {
                int numOfArgs = Integer.parseInt(s2);
                String[] allargs = s1.split("\\s+");

                if (allargs.length != numOfArgs) {
                    throw new DukeException("'done' command should only have 2 arguments");
                } else if (Integer.parseInt(allargs[1]) > list.getSize()
                        || Integer.parseInt(allargs[1]) < 0) {
                    throw new DukeException("Task index is not found!");
                }
            } catch (NumberFormatException ex) {
                throw new DukeException("The second argument should be an integer!");
            }



        }
    },
    TODO {
        public void checkError(String s1, String s2, TaskList list) throws DukeException {
            int numOfArgs = Integer.parseInt(s2);
            String[] allargs = s1.split("\\s+", 2);

            if (numOfArgs > allargs.length) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } else if (list.hasDuplicates(new Todo(allargs[1]))) {
                throw new DukeException("OOPS!!! There is a same task already added into the list " +
                        "\n" + "or there is a clash of timing with one of the tasks in your list!");
            }
        }
    },
    DEADLINE {
        public void checkError(String s1, String s2, TaskList list) throws DukeException {
            int limit = s1.lastIndexOf("/by") - 1;

            // The variable limit will be used as an argument for the method substring.
            // If the limit is less than 9 in this case, the substring method won't work.
            // Therefore, it is important to test if the limit is less than 9
            if (s1.lastIndexOf(s2) == -1) {
                throw new DukeException("Please add a '/by' and then add the date/time subsequently " +
                        "\n" + "in the form of [Deadline] [Description] /by [yyyy-mm-dd] [time in 24-hour]");
            } else if (limit < 9) {
                throw new DukeException("No description! Please add description to your deadline");
            } else {
                String stringTest = s1.substring(9, limit);
                if ((stringTest.replaceAll("( )+", "")).equals("")) {
                    throw new DukeException("No description! Please add description to your deadline");
                }
            }

        }
    },
    EVENT {
        public void checkError(String s1, String s2, TaskList list) throws DukeException {
            int limit = s1.lastIndexOf("/at") - 1;

            // The variable limit will be used as an argument for the method substring.
            // If the limit is less than 6 in this case, the substring method won't work.
            // Therefore, it is important to test if the limit is less than 6
            if (s1.lastIndexOf(s2) == -1) {
                throw new DukeException("Please add a '/at' and then add the date/time subsequently " +
                        "\n" + "in the form of [Event] [Description] /at [yyyy-mm-dd] [time in 24-hour]");
            } else if (limit < 6) {
                throw new DukeException("No description! Please add description to your event");
            } else {
                String stringTest = s1.substring(6, limit);
                if ((stringTest.replaceAll("( )+", "")).equals("")) {
                    throw new DukeException("No description! Please add description to your event");
                }

            }
        }
    },
    DELETE {
        @Override
        public void checkError(String s1, String s2, TaskList list) throws DukeException {
            int numOfArgs = Integer.parseInt(s2);
            String[] allargs = s1.split("\\s+");

            if (allargs.length != numOfArgs) {
                throw new DukeException("'delete' command should only have 2 arguments");
            } else if (Integer.parseInt(allargs[1]) > list.getSize() ||
                    Integer.parseInt(allargs[1]) < 0) {
                throw new DukeException("Task index is not found!");
            }
        }
    },
    FIND {
        @Override
        public void checkError(String s1, String s2, TaskList list) throws DukeException {
            int numOfArgs = Integer.parseInt(s2);
            String[] commandarr = s1.split("\\s+", 2);

            if (commandarr.length != numOfArgs) {
                throw new DukeException("'find' command should have 2 arguments (find [keyword to find])");
            }
        }
    };

    public abstract void checkError(String s1, String s2, TaskList list) throws DukeException;
}