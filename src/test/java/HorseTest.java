import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HorseTest {

    private final String name = "Horse";
    private final double speed = 50.5;
    private final double distance = 500.5;

    @ParameterizedTest
    @NullAndEmptySource
    void constructorHorse(String name) {
        //when
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, 1, 1)
        );
        //then
        if (name == null) {
            assertEquals("Name cannot be null.", exception.getMessage());
        } else if (name.isEmpty()) {
            assertEquals("Name cannot be blank.", exception.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "name,-1,1,",
            "name,1,-1,",
    })
    void constructorHorse(String name, double speed, double distance) {
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Horse(name, speed, distance)
        );
        //then
        if (speed < 0) {
            assertEquals("Speed cannot be negative.", exception.getMessage());
        } else if (distance < 0) {
            assertEquals("Distance cannot be negative.", exception.getMessage());
        }
    }

    @Test
    void getName() {
        //given
        Horse horse = new Horse(name, speed, distance);
        //when
        String receivedName = horse.getName();
        //then
        assertEquals(name, receivedName);
    }

    @Test
    void getSpeed() {
        //given
        Horse horse = new Horse(name, speed, distance);
        //when
        double receivedSpeed = horse.getSpeed();
        //then
        assertEquals(speed, receivedSpeed);
    }

    @Test
    void getDistance() {
        //given
        Horse horse = new Horse(name, speed, distance);
        //when
        double receivedDistance = horse.getDistance();
        //then
        assertEquals(distance, receivedDistance);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.7})
    void move(double value) {
        //given
        Horse horse = new Horse(name, speed, distance);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(value);
            //when
            horse.move();
            //then
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2d, 0.9d));
            assertEquals(distance + speed * value, horse.getDistance());
        }
    }
}
