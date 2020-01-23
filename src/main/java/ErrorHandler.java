public class ErrorHandler {

    public ErrorHandler() {

    }

    public String handleError(String errorDescription, Task.Types type) {

        Exceptions ex = null;

        if(errorDescription.equals("EmptyDescription")) {

            ex = new EmptyDescriptionError(type);

        }  else if (errorDescription.equals("EmptyDate")) {

            ex = new EmptyDateError(type);

        } else if(errorDescription.equals("InvalidInput")) {

            ex = new InvalidInputError();

        } else if(errorDescription.equals("MissingTaskNumber")) {

            ex = new MissingTaskNumberError();
        }

        return ex.errorMessage();
    }

}
