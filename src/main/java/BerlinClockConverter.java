import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Calendar;

public class BerlinClockConverter {

    private final static int NUMBER_OF_FIVE_HOUR_LAMPS_IN_ROW = 4;
    private final static int NUMBER_OF_ONE_HOUR_LAMPS_IN_ROW = 4;
    private final static int NUMBER_OF_FIVE_MINUTE_LAMPS_IN_ROW = 11;
    private final static int NUMBER_OF_ONE_MINUTE_LAMPS_IN_ROW = 4;
    private final static int FIVE_UNITS_OF_TIME = 5;
    private final String LAMP_YELLOW = "Y";
    private final String LAMP_RED = "R";
    private final String LAMP_OFF = "X";
    private final String FIFTHTEEN_MINUTES_UNFORMATTED = LAMP_RED + LAMP_RED + LAMP_RED;
    private final String FIFTHTEEN_MINUTES_FORMATTED = LAMP_RED + LAMP_RED + LAMP_YELLOW;

    private Calendar calendar;
    private static Logger logger;
    private String berlinClockMinutes;
    private String berlinClockHours;
    private String berlinClockSeconds;
    private String berlinClockTime;
    private int numberOfFiveHourLampsToTurnOn;
    private int numberOfOneHourLampsToTurnOn;
    private int numberOfFiveMinuteLampsToTurnOn;
    private int numberOfOneMinuteLampsToTurnOn;

    public BerlinClockConverter(Calendar calendar) {
        this.calendar = calendar;
        logger = (Logger) LogManager.getLogger(BerlinClockConverter.class);

        setNumberOfLampsToTurnOn();
        setBerlinClockTime();
    }

    public String getBerlinClockTime() {
        return berlinClockTime;
    }

    private void setNumberOfLampsToTurnOn() {
        numberOfFiveHourLampsToTurnOn = calendar.get(Calendar.HOUR_OF_DAY) / FIVE_UNITS_OF_TIME;
        numberOfOneHourLampsToTurnOn = calendar.get(Calendar.HOUR_OF_DAY) % FIVE_UNITS_OF_TIME;
        numberOfFiveMinuteLampsToTurnOn = calendar.get(Calendar.MINUTE) / FIVE_UNITS_OF_TIME;
        numberOfOneMinuteLampsToTurnOn = calendar.get(Calendar.MINUTE) % FIVE_UNITS_OF_TIME;
    }

    public void setBerlinClockTime() {
        setBerlinClockHours();
        setBerlinClockMinutes();
        setBerlinClockSeconds();
        berlinClockTime = berlinClockSeconds + berlinClockHours + berlinClockMinutes;
        logger.info("Real time: " + calendar.getTime() + " Berlin Clock: " + berlinClockTime);
    }

    private void setBerlinClockHours() {
        berlinClockHours =
                buildBerlinClockRow(NUMBER_OF_FIVE_HOUR_LAMPS_IN_ROW, numberOfFiveHourLampsToTurnOn) +
                        buildBerlinClockRow(NUMBER_OF_ONE_HOUR_LAMPS_IN_ROW, numberOfOneHourLampsToTurnOn);
    }

    private void setBerlinClockMinutes() {
        berlinClockMinutes =
                getColorMinutesLamps(buildBerlinClockRow(NUMBER_OF_FIVE_MINUTE_LAMPS_IN_ROW, numberOfFiveMinuteLampsToTurnOn)) +
                        getColorMinutesLamps(buildBerlinClockRow(NUMBER_OF_ONE_MINUTE_LAMPS_IN_ROW, numberOfOneMinuteLampsToTurnOn));
    }

    private String getColorMinutesLamps(String berlinClockRow) {
        if (berlinClockRow.length() == NUMBER_OF_FIVE_MINUTE_LAMPS_IN_ROW) {
            return berlinClockRow.replace(FIFTHTEEN_MINUTES_UNFORMATTED, FIFTHTEEN_MINUTES_FORMATTED);
        }
        return berlinClockRow.replace(LAMP_RED, LAMP_YELLOW);
    }

    private void setBerlinClockSeconds() {
        if ((calendar.get(Calendar.SECOND) % 2) == 1) {
            berlinClockSeconds = LAMP_RED;
        }
        berlinClockSeconds = LAMP_OFF;
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
