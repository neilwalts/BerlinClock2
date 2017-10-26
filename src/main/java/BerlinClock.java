import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Calendar;

public class BerlinClock {

    private final static int FIVE_HOUR_LAMPS = 4;
    private final static int ONE_HOUR_LAMPS = 4;
    private final static int FIVE_MINUTE_LAMPS = 11;
    private final static int ONE_MINUTE_LAMPS = 4;
    private final static int FIVE_UNITS_OF_TIME = 5;
    private final String LAMP_YELLOW = "Y";
    private final String LAMP_RED = "R";
    private final String LAMP_OFF = "X";
    private Calendar calendar;
    private static Logger logger;

    public BerlinClock(Calendar calendar) {
        this.calendar = calendar;
        logger = (Logger) LogManager.getLogger(BerlinClock.class);
    }

    public String getBerlinClockTime() {
        String berlinClockTime = getSeconds() + getHours() + getMinutes();
        logger.info("Real time: " + calendar.getTime() + " Berlin Clock: " + berlinClockTime);
        return berlinClockTime;
    }

    private String getHours() {
        return buildBerlinClockRow(FIVE_HOUR_LAMPS, calendar.get(Calendar.HOUR_OF_DAY) / FIVE_UNITS_OF_TIME) +
                buildBerlinClockRow(ONE_HOUR_LAMPS, calendar.get(Calendar.HOUR_OF_DAY) % FIVE_UNITS_OF_TIME);
    }

    private String getMinutes() {
        return colorMinutesLamps(buildBerlinClockRow(FIVE_MINUTE_LAMPS,
                calendar.get(Calendar.MINUTE) / FIVE_UNITS_OF_TIME))
                + colorMinutesLamps(buildBerlinClockRow(ONE_MINUTE_LAMPS,
                calendar.get(Calendar.MINUTE) % FIVE_UNITS_OF_TIME));
    }

    private String colorMinutesLamps(String berlinClockRow) {
        if (berlinClockRow.length() == FIVE_MINUTE_LAMPS) {
            return berlinClockRow.replace(LAMP_RED + LAMP_RED + LAMP_RED, LAMP_RED + LAMP_RED + LAMP_YELLOW);
        }
        return berlinClockRow.replace(LAMP_RED, LAMP_YELLOW);
    }

    private String getSeconds() {
        if ((calendar.get(Calendar.SECOND) % 2) == 1) {
            return LAMP_RED;
        }
        return LAMP_OFF;
    }

    private String buildBerlinClockRow(int numberOfLampsInRow, int numberOfLampsToTurnOn) {
        StringBuilder rowOfLamps = new StringBuilder();
        for (int i = 0; i < numberOfLampsInRow; i++) {
            if (i < numberOfLampsToTurnOn) {
                rowOfLamps.append(LAMP_RED);
            } else {
                rowOfLamps.append(LAMP_OFF);
            }
        }
        return rowOfLamps.toString();
    }
}
