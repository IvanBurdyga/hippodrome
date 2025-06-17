import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class HippodromeTest {

    @ParameterizedTest
    @NullAndEmptySource
    void constructorHippodrome(List<Horse> value) {
        //when
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Hippodrome(value)
        );
        //then
        if (value == null) {
            assertEquals("Horses cannot be null.", exception.getMessage());
        } else if (value.isEmpty()) {
            assertEquals("Horses cannot be empty.", exception.getMessage());
        }
    }

    @Test
    void getHorses() {
        //given
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Horse horse = mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        //when
        List<Horse> receivedHorses = hippodrome.getHorses();
        //then
        assertEquals(horses, receivedHorses);
    }

    @Test
    void move() {
        //given
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        //when
        hippodrome.move();
        //then
        hippodrome.getHorses().forEach(horse -> verify(horse).move());
    }

    @Test
    void getWinner() {
        //given
        List<Horse> horses = new ArrayList<>();
        Horse winner = new Horse("winner", 7.6,7000.5);
        horses.add(winner);
        Horse horse1 = new Horse("horse1", 6.0,4567.89456);
        horses.add(horse1);
        Horse horse2 = new Horse("horse2", 6.2,2568.5548);
        horses.add(horse2);
        Horse horse3 = new Horse("horse3", 7.1,2000);
        horses.add(horse3);
        Horse horse4 = new Horse("horse4", 6.8,2222.22);
        horses.add(horse4);
        Hippodrome hippodrome = new Hippodrome(horses);
        //when
        Horse receivedWinner = hippodrome.getWinner();
        //then
        assertEquals(winner,receivedWinner);
    }

}
