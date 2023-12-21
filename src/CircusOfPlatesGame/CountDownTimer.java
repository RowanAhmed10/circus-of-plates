package CircusOfPlatesGame;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CountDownTimer {

    private int secDelay = 0;
    private int seconds = 60;
    private Date startDate;
    private Date endDate;

    public int getSecDelay() {
        return secDelay;
    }

    public int getSeconds() {
        return seconds;
    }

    public void secondsPassed() throws InterruptedException {

        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                seconds--;

            }

        };
        t.scheduleAtFixedRate(task, 0, 1000);

    }

    public void resumeTime() {
        endDate = new Date();

    }

    public void pauseTime() {
        startDate = new Date();
    }

    public int secondsPaused() {
        secDelay += (int) ((endDate.getTime() - startDate.getTime()) / 1000);
        return secDelay;
    }

    public int getTime() {
        return seconds + secDelay;
    }
}
