import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {


    @Test
    void whenNameIsNull_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () ->
                        new Horse(null, 100.0, 10.1)
        );
    }

    @Test
    void whenNameIsNull_thenReturnRightMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () ->
                        new Horse(null, 100.0, 10.1)
        );
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t"})
    void whenNameConsistOfSpaces_thenThrowIllegalArgumentException(String string) {
        assertThrows(IllegalArgumentException.class,
                () ->
                        new Horse(string, 100.0, 10.1)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\t"})
    void whenNameConsistOfSpaces_thenReturnRightMessage(String string) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () ->
                        new Horse(string, 100.0, 10.1)
        );
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    //checking both border valuable and random negative
    @ParameterizedTest
    @ValueSource(doubles = {(1 / -Double.MAX_VALUE), -100, -Double.MAX_VALUE})
    void whenSpeedIsNegative_thenThrowIllegalArgumentException(Double d) {
        assertThrows(IllegalArgumentException.class,
                () ->
                        new Horse("horse", d, 10.1)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {(1 / -Double.MAX_VALUE), -100, -Double.MAX_VALUE})
    void whenSpeedIsNegative_thenReturnRightMessage(Double d) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () ->
                        new Horse("horse", d, 10.1)
        );
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(doubles = {(1 / -Double.MAX_VALUE), -100, -Double.MAX_VALUE})
    void whenDistanceIsNegative_thenThrowIllegalArgumentException(Double d) {
        assertThrows(IllegalArgumentException.class,
                () ->
                        new Horse("horse", 100.0, d)
        );
    }

    @ParameterizedTest
    @ValueSource(doubles = {(1 / -Double.MAX_VALUE), -100, -Double.MAX_VALUE})
    void whenDistanceIsNegative_thenReturnRightMessage(Double d) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () ->
                        new Horse("horse", 100.0, d)
        );
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void whenCallGetName_thenReturnName() {
        Horse horse = new Horse("horse", 100.0, 10.1);
        assertEquals("horse", horse.getName());
    }

    @Test
    void whenCallGetSpeed_thenReturnSpeed() {
        Horse horse = new Horse("horse", 100.0, 10.1);
        assertEquals(100.0, horse.getSpeed());
    }

    @Test
    void whenCallGetDistance_thenReturnDistance() {
        Horse horse = new Horse("horse", 100.0, 10.1);
        assertEquals(10.1, horse.getDistance());
    }

    @Test
    void whenCreateHorseWithTwoParams_thenDistanceIsZero() {
        Horse horse = new Horse("horse", 100.0);
        assertEquals(0, horse.getDistance());
    }


    @Test
    void whenCallHorseMove_thenCallRandomMethodWithRightBoard() {
        try (MockedStatic<Horse> staticHorse = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("horse", 1.0, 100.0);
            horse.move();
            staticHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }


    @Test
    void whenCallHorseMove_thenDistanceIsCalculatedByFormula() {
        try (MockedStatic<Horse> staticHorse = Mockito.mockStatic(Horse.class)) {
            staticHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.7);
            Horse horse = new Horse("horse", 1);
            horse.move();
            assertEquals(horse.getSpeed() * 0.7, horse.getDistance());
        }
    }


}