package mission2.assembly;

import mission2.UserCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssemblyControllerTest {

    @Test
    void getAssembleStrategy_shouldReturnProperStrategy() {
        // given
        CarTypeAssembleStrategy expectedResultA = new CarTypeAssembleStrategy();
        EngineAssembleStrategy expectedResultB = new EngineAssembleStrategy();
        CarBreakAssembleStrategy expectedResultC = new CarBreakAssembleStrategy();
        SteeringAssembleStrategy expectedResultD = new SteeringAssembleStrategy();
        UserCommand userCommandA = new UserCommand(0, "1");
        UserCommand userCommandB = new UserCommand(1, "1");
        UserCommand userCommandC = new UserCommand(2, "1");
        UserCommand userCommandD = new UserCommand(3, "1");

        // when
        AssembleStrategy actualResultA = AssemblyController.getAssembleStrategy(userCommandA);
        AssembleStrategy actualResultB = AssemblyController.getAssembleStrategy(userCommandB);
        AssembleStrategy actualResultC = AssemblyController.getAssembleStrategy(userCommandC);
        AssembleStrategy actualResultD = AssemblyController.getAssembleStrategy(userCommandD);

        // then
        assertEquals(expectedResultA.getClass(), actualResultA.getClass());
        assertEquals(expectedResultB.getClass(), actualResultB.getClass());
        assertEquals(expectedResultC.getClass(), actualResultC.getClass());
        assertEquals(expectedResultD.getClass(), actualResultD.getClass());
    }

    @Test
    void getAssembleStrategy_shouldThrowException() {
        // given
        UserCommand userCommand = new UserCommand(100, "1");

        // when
        // then
        assertThrows(RuntimeException.class, () -> AssemblyController.getAssembleStrategy(userCommand));
    }

}