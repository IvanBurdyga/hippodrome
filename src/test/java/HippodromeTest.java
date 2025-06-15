import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HippodromeTest {

    @ParameterizedTest
    @EmptySource
    @NullSource
    void HippodromeConstructor(List<Horse> value) {
        //when
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome(value);
                }
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
            Horse horse = Mockito.mock(Horse.class);
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
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        //when
        hippodrome.move();
        //then
        hippodrome.getHorses().forEach(horse -> Mockito.verify(horse).move());
    }

    @Test
    void getWinner() {
        //given
        List<Horse> horses = new ArrayList<>();
        Horse winner = new Horse("winner", 76,7000.5);
        horses.add(winner);
        Horse horse1 = new Horse("horse1", 60,4567.89456);
        horses.add(horse1);
        Horse horse2 = new Horse("horse2", 62,2568.5548);
        horses.add(horse2);
        Horse horse3 = new Horse("horse3", 71,2000);
        horses.add(horse3);
        Horse horse4 = new Horse("horse4", 68,2222.22);
        horses.add(horse4);
        Hippodrome hippodrome = new Hippodrome(horses);
        //when
        Horse receivedWinner = hippodrome.getWinner();
        //then
        assertEquals(winner,receivedWinner);
    }

}
