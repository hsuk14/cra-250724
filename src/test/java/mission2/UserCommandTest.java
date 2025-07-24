package mission2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCommandTest {

    @Test
    void getStep_shouldReturnProperStep() {
        // given
        int testStep = 1;
        String userInput = "1";
        UserCommand command = new UserCommand(testStep, userInput);
        // when
        int actualResult = command.getStep();
        // then
        assertEquals(testStep, actualResult);
    }

    @Test
    void setExecutionCodeBySelfAndGetExecutionCode() {
        // given
        int testStep = 1;
        String userInput = "1";
        UserCommand command = new UserCommand(testStep, userInput);
        int expectedResult = 1;
        // when
        command.setExecutionCodeBySelf();
        int actualResult = command.getExecutionCode();
        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void isInValid_shouldReturnProperValue() {
        // given
        int testStep = 1;
        String userInputTrue = "A";
        String userInputFalse = "1";
        UserCommand commandTrue = new UserCommand(1, userInputTrue);
        UserCommand commandFalse = new UserCommand(1, userInputFalse);
        // when
        boolean actualResultA = commandTrue.isInValid();
        boolean actualResultB = commandFalse.isInValid();
        // then
        assertTrue(actualResultA);
        assertFalse(actualResultB);
    }

    @Test
    void isUndoCode() {
        // given
        UserCommand testCommandTrue = new UserCommand(1, "0");
        UserCommand testCommandFalse = new UserCommand(1, "1");
        testCommandTrue.setExecutionCodeBySelf();
        testCommandFalse.setExecutionCodeBySelf();
        // when
        boolean actualResultA = testCommandTrue.isUndoCode();
        boolean actualResultB = testCommandFalse.isUndoCode();
        // then
        assertTrue(actualResultA);
        assertFalse(actualResultB);
    }

    @Test
    void isRunCode() {
        // given
        UserCommand testCommandTrue = new UserCommand(1, "1");
        UserCommand testCommandFalse = new UserCommand(1, "2");
        testCommandTrue.setExecutionCodeBySelf();
        testCommandFalse.setExecutionCodeBySelf();
        // when
        boolean actualResultA = testCommandTrue.isRunCode();
        boolean actualResultB = testCommandFalse.isRunCode();
        // then
        assertTrue(actualResultA);
        assertFalse(actualResultB);
    }

    @Test
    void isTestCode() {
        // given
        UserCommand testCommandTrue = new UserCommand(1, "2");
        UserCommand testCommandFalse = new UserCommand(1, "3");
        testCommandTrue.setExecutionCodeBySelf();
        testCommandFalse.setExecutionCodeBySelf();
        // when
        boolean actualResultA = testCommandTrue.isTestCode();
        boolean actualResultB = testCommandFalse.isTestCode();
        // then
        assertTrue(actualResultA);
        assertFalse(actualResultB);
    }
}