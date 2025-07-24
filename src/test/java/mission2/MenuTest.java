package mission2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void cleatSystemOut() {
        System.out.flush();
    }

    @Test
    void showMenuByStep_CarType(){
        // given
        String expectedResult = "차량 타입";
        int testStep = 0;
        // when
        Menu.showMenuByStep(testStep);
        // then
        assertTrue (outputStreamCaptor.toString().contains(expectedResult));
    }

    @Test
    void showMenuByStep_Engine(){
        // given
        String expectedResult = "엔진";
        int testStep = 1;
        // when
        Menu.showMenuByStep(testStep);
        // then
        assertTrue (outputStreamCaptor.toString().contains(expectedResult));
    }

    @Test
    void showMenuByStep_Break(){
        // given
        String expectedResult = "제동";
        int testStep = 2;
        // when
        Menu.showMenuByStep(testStep);
        // then
        assertTrue (outputStreamCaptor.toString().contains(expectedResult));
    }

    @Test
    void showMenuByStep_Steering(){
        // given
        String expectedResult = "조향";
        int testStep = 3;
        // when
        Menu.showMenuByStep(testStep);
        // then
        assertTrue (outputStreamCaptor.toString().contains(expectedResult));
    }

    @Test
    void showMenuByStep_RuneTest() {
        // given
        String expectedResult = "멋진";
        int testStep = 4;
        // when
        Menu.showMenuByStep(testStep);
        // then
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
    }

    @Test
    void showMenuByStep_ShouldThrowException(){
        // given
        int testStep = 5;
        // when
        // then
        assertThrows(RuntimeException.class, () -> Menu.showMenuByStep(testStep));
    }

}