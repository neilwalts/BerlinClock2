import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimerTask {

    private BerlinClock berlinClock = new BerlinClock();
    private Timer timer;

    public MyTimerTask() {
        timer = new Timer();
        timer.schedule(new BerlinClockTimeTask(), 0, 1000);
    }

    public static void main(String[] args) {
        new MyTimerTask();
    }

    class BerlinClockTimeTask extends TimerTask {
        public void run() {
            berlinClock.getBerlinClockTime(Calendar.getInstance());
        }
    }

}