public class DukeWorker {

    public String handleRequest(String request) {
        if (request.toLowerCase().equals("bye")) {
            return "Bye ciao adios";
        }
        return request;
    }
}
