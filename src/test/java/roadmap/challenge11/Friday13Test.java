package roadmap.challenge11;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class Friday13Test {

    @Test
    void checkTrue() {
        assertTrue(App11.checkFriday13(2023, 10));
    }

    @Test
    void checkFalse() {
        assertFalse(App11.checkFriday13(2023, 9));
    }

    @Test
    void checkTrueInLeapYear() {
        assertTrue(App11.checkFriday13(2004, 2));
    }

    @Test
    void checkInvalidMonthException() {
        assertThrows(DateTimeException.class, () -> App11.checkFriday13(2023, 13));
    }
}