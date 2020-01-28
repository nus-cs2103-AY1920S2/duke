public class Coder {
    public String encode (Task t) {
        return t.encode();
    }

    public Task decode (String code) {
        String[] keys = code.split(" | ");

        String type = keys[0];
        Task t = null;

        switch (type) {
            case "T":
                t = new Todo(keys[2]);
                break;
            case "D":
                t = new Deadline(keys[2], keys[3]);
                break;
            case "E":
                t = new Event(keys[2], keys[3]);
                break;
            default:
                break;
        }

        if (keys[1] == "1") {
            t.tick();
        }

        return t;
    }
}
