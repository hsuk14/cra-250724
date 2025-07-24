package mission1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CarAssemblerTest {

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
    void getValidExecutionCode_ShouldThrowException() {
        // given
        int testAssemblyStep = 0;
        String testUserInputA = "TEST";
        String testUserInputB = "100";
        // when
        // then
        assertThrows(RuntimeException.class, () -> {CarAssembler.getValidExecutionCode(testAssemblyStep, testUserInputA);});
        assertThrows(RuntimeException.class, () -> {CarAssembler.getValidExecutionCode(testAssemblyStep, testUserInputB);});
    }

    @Test
    void getValidExecutionCode_ShouldReturnValidExecutionCode() {
        // given
        int testAssemblyStep = 0;
        String testUserInput = "1";
        int expectedResult = 1;
        // when
        int actualResult = CarAssembler.getValidExecutionCode(testAssemblyStep, testUserInput);
        // then
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void initialize_test(){
        // given
        String expectedResult = "[H\033[2J";;
        // when
        CarAssembler.initialize();
        // then
        assertEquals(expectedResult, outputStreamCaptor.toString().trim());
    }

    @Test
    void executeAndGetNextStep_shouldThrowException() {
        // given
        int testStep = 100;
        int testExecutionCode = 1;
        // when
        // then
        assertThrows(RuntimeException.class, () -> {CarAssembler.executeAndGetNextStep(testStep, testExecutionCode);});
    }

    @Test
    void executeAndGetNextStep_shouldReturnProperStep() {
        // given
        int testExecutionCodeA = 1;
        int testStepA = 0;
        int expectedResultA = 1;
        int testStepB = 1;
        int expectedResultB = 2;
        int testStepC = 2;
        int expectedResultC = 3;
        int testStepD = 3;
        int expectedResultD = 4;
        int testStepE = 4;
        int expectedResultE = 4;
        int testExecutionCodeB = 0;
        int testStepF = 3;
        int expectedResultF = 2;
        int testStepG = 4;
        int expectedResultG = 0;
        // when
        int actualResultA = CarAssembler.executeAndGetNextStep(testStepA, testExecutionCodeA);
        int actualResultB = CarAssembler.executeAndGetNextStep(testStepB, testExecutionCodeA);
        int actualResultC = CarAssembler.executeAndGetNextStep(testStepC, testExecutionCodeA);
        int actualResultD = CarAssembler.executeAndGetNextStep(testStepD, testExecutionCodeA);
        int actualResultE = CarAssembler.executeAndGetNextStep(testStepE, testExecutionCodeA);
        int actualResultF = CarAssembler.executeAndGetNextStep(testStepF, testExecutionCodeB);
        int actualResultG = CarAssembler.executeAndGetNextStep(testStepG, testExecutionCodeB);
        // then
        assertEquals(expectedResultA, actualResultA);
        assertEquals(expectedResultB, actualResultB);
        assertEquals(expectedResultC, actualResultC);
        assertEquals(expectedResultD, actualResultD);
        assertEquals(expectedResultE, actualResultE);
        assertEquals(expectedResultF, actualResultF);
        assertEquals(expectedResultG, actualResultG);
    }

    @Test
    void executeProducedCar_ShouldPrintTest_WhenParamIsTestCode() {
        // given
        int testExecutionCodeTest = 2;
        String expectedResult = "Test...";
        // when
        // then
        CarAssembler.executeProducedCar(testExecutionCodeTest);
        assertTrue(outputStreamCaptor.toString().trim().contains(expectedResult));
    }

    @Test
    void executeProducedCar_ShouldThrowException() {
        // given
        int testExecutionCodeTest = 3;
        // when
        // then
        assertThrows(RuntimeException.class, () -> {CarAssembler.executeProducedCar(testExecutionCodeTest);});
    }

    @Test
    void showMenuByStep_shouldThrowException() {
        // given
        int testStep = 100;
        // when
        // then
        assertThrows(RuntimeException.class, () -> {CarAssembler.showMenuByStep(testStep);});
    }

    @Test
    void showMenuByStep_shouldPrintProperValue() {
        // given
        int testStepA = 0;
        String expectedResultA = "차량";
        int testStepB = 1;
        String expectedResultB = "엔진";
        int testStepC = 2;
        String expectedResultC = "제동";
        int testStepD = 3;
        String expectedResultD = "조향";
        int testStepE = 4;
        String expectedResultE = "멋진";
        // when
        // then
        CarAssembler.showMenuByStep(testStepA);
        assertTrue(outputStreamCaptor.toString().trim().contains(expectedResultA));
        System.out.flush();
        CarAssembler.showMenuByStep(testStepB);
        assertTrue(outputStreamCaptor.toString().trim().contains(expectedResultB));
        System.out.flush();
        CarAssembler.showMenuByStep(testStepC);
        assertTrue(outputStreamCaptor.toString().trim().contains(expectedResultC));
        System.out.flush();
        CarAssembler.showMenuByStep(testStepD);
        assertTrue(outputStreamCaptor.toString().trim().contains(expectedResultD));
        System.out.flush();
        CarAssembler.showMenuByStep(testStepE);
        assertTrue(outputStreamCaptor.toString().trim().contains(expectedResultE));
    }

    @Test
    void isValidRange_shouldReturnProperValue() {
        // given
        int testStepA = 0;
        int testStepB = 1;
        int testStepC = 2;
        int testStepD = 3;
        int testStepE = 4;
        int testAnsA = 5;
        int testAnsB = 1;
        // when
        boolean resultA = CarAssembler.isValidRange(testStepA, testAnsA);
        boolean resultB = CarAssembler.isValidRange(testStepB, testAnsA);
        boolean resultC = CarAssembler.isValidRange(testStepC, testAnsA);
        boolean resultD = CarAssembler.isValidRange(testStepD, testAnsA);
        boolean resultE = CarAssembler.isValidRange(testStepE, testAnsA);
        boolean resultF = CarAssembler.isValidRange(testStepE, testAnsB);
        // then
        assertFalse(resultA);
        assertFalse(resultB);
        assertFalse(resultC);
        assertFalse(resultD);
        assertFalse(resultE);
        assertTrue(resultF);
    }

    @Test
    void fail_shouldPrintProperValue() {
        // given
        String expectedResult = "TEST";
        // when
        CarAssembler.fail(expectedResult);
        // then
        assertTrue(outputStreamCaptor.toString().trim().contains(expectedResult));
    }

    @Test
    void isValidStep_shouldReturnProperValue() {
        // given
        int testStepTrue = 1;
        int testStepFalse = 100;
        // when
        boolean actualResultA =  CarAssembler.isValidStep(testStepTrue);
        boolean actualResultB =  CarAssembler.isValidStep(testStepFalse);
        // then
        assertTrue(actualResultA);
        assertFalse(actualResultB);
    }

}