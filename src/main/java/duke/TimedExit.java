package duke;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimedExit {
    Timer timer = new Timer();
    TimerTask exitApp = new TimerTask() {
        public void run() {
            System.exit(0);
        }
    };

    public TimedExit() {
        timer.schedule(exitApp, new Date(System.currentTimeMillis() + 2 * 1000));
    }

}