package mission2.assembly;

import mission2.Car;
import mission2.UserCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CarBreakAssembleStrategyTest {

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
    void assembleByCommand(){
        // given
        CarBreakAssembleStrategy assembleStrategy = new CarBreakAssembleStrategy();
        Car testCar = new Car();
        UserCommand testUserCommand = new UserCommand(1, "1");
        testUserCommand.setExecutionCodeBySelf();

        // when
        assembleStrategy.assembleByCommand(testCar, testUserCommand);

        // then
        assertTrue(outputStreamCaptor.toString().contains("제동장치를 선택"));
    }

    @Test
    void assembleByCommand_Fail(){
        // given
        CarBreakAssembleStrategy assembleStrategy = new CarBreakAssembleStrategy();
        Car testCar = new Car();
        UserCommand testUserCommand = new UserCommand(1, "5");
        testUserCommand.setExecutionCodeBySelf();

        // when
        // then
        assertThrows(RuntimeException.class, () -> assembleStrategy.assembleByCommand(testCar, testUserCommand));
        assertTrue(outputStreamCaptor.toString().contains("ERROR"));
    }

}