import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;


public class BerlinClockConverterTest {

    Calendar cal;

    @Before
    public void before() {
        cal = Calendar.getInstance();
    }

    @Test
    public void testLowExtreme() {
        cal.set(2017, 10, 19, 00, 00, 00);
        assertEquals("XXXXXXXXXXXXXXXXXXXXXXXX", new BerlinClockConverter(cal).getBerlinClockTime());
    }

    @Test
    public void testMidday() {
        cal.set(2017, 10, 19, 12, 00, 00);
        assertEquals("XRRXXRRXXXXXXXXXXXXXXXXX", new BerlinClockConverter(cal).getBerlinClockTime());
    }

    @Test
    public void testHighExtreme() {
        cal.set(2017, 10, 19, 23, 59, 59);
        assertEquals("RRRRRRRRXRRYRRYRRYRRYYYY", new BerlinClockConverter(cal).getBerlinClockTime());
    }

    @Test
    public void testArbitraryTime() {
        cal.set(2017, 10, 19, 10, 51, 23);
        assertEquals("RRRXXXXXXRRYRRYRRYRXYXXX", new BerlinClockConverter(cal).getBerlinClockTime());
    }

}
