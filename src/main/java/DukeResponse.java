
public class DukeResponse {

    private String response;
    private int status;

    public DukeResponse(String response, int status) {
        this.response = response;
        this.status = status;
    }

    public String getResponse() {
        return response;
    }

    public int getStatus() {
        return status;
    }
}
