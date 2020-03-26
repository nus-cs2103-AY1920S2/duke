
/*
 * DukeResponse
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 11 February 2020
 *
 */

/**
 * Encapsulate both response and status in one class.
 * @author Daniel Alfred Widjaja
 */
public class DukeResponse {

    private String response;
    private int status;

    /**
     * Create the response object.
     * @param response The response text.
     * @param status The status of the response.
     */
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
