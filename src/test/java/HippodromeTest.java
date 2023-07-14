import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void whenConstructorHippodromeWithNull_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    void whenConstructorHippodromeWithNull_thenThrowRightMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void whenConstructorHippodromeWithEmptyList_thenThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void whenConstructorHippodromeWithEmptyList_thenThrowRightMessage() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void whenGetHorses_thenReturnHorsesInRightOrder() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse(Integer.toString(1), 2, 3));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertArrayEquals(horses.toArray(), hippodrome.getHorses().toArray());
    }

    @Test
    void whenHippodromeMove_thenAllHorsesMove() {
        ArrayList<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (int i = 0; i < 50; i++) {
            Mockito.verify(hippodrome.getHorses().get(i)).move();
        }
    }

    @Test
    void whenGetWinner_thenReturnHorseWithBiggestDistance() {
        ArrayList<Horse> horses = new ArrayList<>();
        double maxDistance = -1;
        Horse winner = null;
        for (int i = 0; i < 30; i++) {
            Horse horse = new Horse(Integer.toString(1), 2, i);
            horses.add(horse);
            if (horse.getDistance() > maxDistance) {
                maxDistance = horse.getDistance();
                winner = horse;
            }
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(winner, hippodrome.getWinner());
    }

}