import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class BerlinClockTimer {

    public BerlinClockTimer() {
        new Timer().schedule(new BerlinClockTimerTask(), 0, 1000);
    }

    public static void main(String[] args) {
        new BerlinClockTimer();
    }

    class BerlinClockTimerTask extends TimerTask {
        public void run() {
            new BerlinClock(Calendar.getInstance()).getBerlinClockTime();
        }
    }
}