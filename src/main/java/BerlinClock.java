import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Calendar;

public class BerlinClock {

    private final static int FIVE_HOUR_LAMPS = 4;
    private final static int ONE_HOUR_LAMPS = 4;
    private final static int FIVE_MINUTE_LAMPS = 11;
    private final static int ONE_MINUTE_LAMPS = 4;
    private final String LAMP_YELLOW = "Y";
    private final String LAMP_RED = "R";
    private final String LAMP_OFF = "X";

    private static Logger logger = (Logger) LogManager.getLogger(BerlinClock.class);

    public String getBerlinClockTime(Calendar calendar) {

        String berlinClockTime = getSeconds(calendar) +
                getHoursOrMinutes(FIVE_HOUR_LAMPS, calendar.get(Calendar.HOUR_OF_DAY) / 5) +
                getHoursOrMinutes(ONE_HOUR_LAMPS, calendar.get(Calendar.HOUR_OF_DAY) % 5) +
                getHoursOrMinutes(FIVE_MINUTE_LAMPS, calendar.get(Calendar.MINUTE) / 5)
                        .replace(LAMP_RED + LAMP_RED + LAMP_RED,
                                LAMP_RED + LAMP_RED + LAMP_YELLOW) +
                getHoursOrMinutes(ONE_MINUTE_LAMPS, calendar.get(Calendar.MINUTE) % 5)
                        .replace(LAMP_RED,
                                LAMP_YELLOW);

        logger.info("Real time: " + calendar.getTime() + " Berlin Clock" + berlinClockTime);

        return berlinClockTime;
    }

    private String getSeconds(Calendar calendar) {
        if ((calendar.get(Calendar.SECOND) % 2) == 1) {
            return LAMP_RED;
        }
        return LAMP_OFF;
    }

    private String getHoursOrMinutes(int numberOfLamps, int berlinClockRow) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < numberOfLamps; i++) {
            if (i < berlinClockRow) {
                stringBuilder.append(LAMP_RED);
            } else {
                stringBuilder.append(LAMP_OFF);
            }
        }
        return stringBuilder.toString();
    }
}
