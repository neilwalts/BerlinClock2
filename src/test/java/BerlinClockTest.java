import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;


public class BerlinClockTest {

    Calendar cal;

    @Before
    public void before() {
        cal = Calendar.getInstance();
    }

    @Test
    public void testLowExtreme() {
        cal.set(2017, 10, 19, 00, 00, 00);
        assertEquals("XXXXXXXXXXXXXXXXXXXXXXXX", new BerlinClock().getBerlinClockTime(cal));
    }

    @Test
    public void testMidday() {
        cal.set(2017, 10, 19, 12, 00, 00);
        assertEquals("XRRXXRRXXXXXXXXXXXXXXXXX", new BerlinClock().getBerlinClockTime(cal));
    }

    @Test
    public void testHighExtreme() {
        cal.set(2017, 10, 19, 23, 59, 59);
        assertEquals("RRRRRRRRXRRYRRYRRYRRYYYY", new BerlinClock().getBerlinClockTime(cal));
    }

    @Test
    public void testArbitraryTime() {
        cal.set(2017, 10, 19, 10, 51, 23);
        assertEquals("RRRXXXXXXRRYRRYRRYRXYXXX", new BerlinClock().getBerlinClockTime(cal));
    }

}
