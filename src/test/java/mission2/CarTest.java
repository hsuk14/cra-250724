package mission2;

import mission2.components.CarBreak;
import mission2.components.CarType;
import mission2.components.Engine;
import mission2.components.Steering;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

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
    void run_whenEnginIsNotFail() {
        // given
        Car testCarA =  new Car();
        Car testCarB =  new Car();
        Car testCarC =  new Car();
        Car testCarD =  new Car();
        Car testCarE =  new Car();
        Car testCarF =  new Car();
        String expectedResult = "자동차가 동작되지 않습니다";
        testCarA.setCarType(CarType.SEDAN);
        testCarA.setBreak(CarBreak.CONTINENTAL);
        testCarB.setCarType(CarType.SUV);
        testCarB.setEngine(Engine.TOYOTA);
        testCarC.setCarType(CarType.TRUCK);
        testCarC.setEngine(Engine.WIA);
        testCarD.setEngine(Engine.FAIL);
        testCarD.setCarType(CarType.TRUCK);
        testCarD.setBreak(CarBreak.MANDO);
        testCarE.setBreak(CarBreak.BOSCH);
        testCarE.setSteering(Steering.MOBIS);
        testCarE.setCarType(CarType.SEDAN);
        testCarE.setEngine(Engine.TOYOTA);
        testCarF.setBreak(CarBreak.BOSCH);
        testCarF.setSteering(Steering.BOSCH);
        testCarF.setEngine(Engine.TOYOTA);
        testCarF.setCarType(CarType.TRUCK);
        // when
        // then
        testCarA.run();
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
        System.out.flush();
        testCarB.run();
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
        System.out.flush();
        testCarC.run();
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
        System.out.flush();
        testCarD.run();
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
        System.out.flush();
        testCarE.run();
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
        System.out.flush();
        testCarF.run();
        assertTrue(outputStreamCaptor.toString().contains("동작"));
        System.out.flush();
    }

    @Test
    void run_whenEnginIsFail() {
        // given
        Car testCar = new Car();
        testCar.setEngine(Engine.FAIL);
        testCar.setBreak(CarBreak.MANDO);
        testCar.setSteering(Steering.MOBIS);
        testCar.setCarType(CarType.SEDAN);
        // when
        testCar.run();
        // then
        assertTrue(outputStreamCaptor.toString().contains("엔진이 고장"));
        System.out.flush();

    }

    @Test
    void testRun() {
        // given
        Car testCarA =  new Car();
        Car testCarB =  new Car();
        Car testCarC =  new Car();
        Car testCarD =  new Car();
        Car testCarE =  new Car();
        Car testCarF =  new Car();
        String expectedResult = "사용 불가";
        testCarA.setCarType(CarType.SEDAN);
        testCarA.setBreak(CarBreak.CONTINENTAL);
        testCarB.setCarType(CarType.SUV);
        testCarB.setEngine(Engine.TOYOTA);
        testCarC.setCarType(CarType.TRUCK);
        testCarC.setEngine(Engine.WIA);
        testCarD.setEngine(Engine.FAIL);
        testCarD.setCarType(CarType.TRUCK);
        testCarD.setBreak(CarBreak.MANDO);
        testCarE.setBreak(CarBreak.BOSCH);
        testCarE.setSteering(Steering.MOBIS);
        testCarE.setCarType(CarType.SEDAN);
        testCarE.setEngine(Engine.TOYOTA);
        testCarF.setBreak(CarBreak.BOSCH);
        testCarF.setSteering(Steering.BOSCH);
        testCarF.setEngine(Engine.TOYOTA);
        testCarF.setCarType(CarType.TRUCK);
        // when
        // then
        testCarA.testRun();
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
        System.out.flush();
        testCarB.testRun();
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
        System.out.flush();
        testCarC.testRun();
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
        System.out.flush();
        testCarD.testRun();
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
        System.out.flush();
        testCarE.testRun();
        assertTrue(outputStreamCaptor.toString().contains(expectedResult));
        System.out.flush();
        testCarF.testRun();
        assertTrue(outputStreamCaptor.toString().contains("PASS"));
        System.out.flush();
    }
    }
