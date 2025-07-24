package mission1;

import java.util.Scanner;

public class CarAssembler {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    private static final String EXIT_CODE = "exit";

    private static final int CAR_TYPE_IDX = 0;
    private static final int ENGINE_IDX = 1;
    private static final int BREAK_SYSTEM_IDX = 2;
    private static final int STEERING_SYSTEM_IDX = 3;
    private static final int RUN_TEST_IDX = 4;

    private static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    private static final int ENGINE_GM = 1, ENGINE_TOYOTA = 2, ENGINE_WIA = 3;
    private static final int BREAK_MANDO = 1, BRAKE_CONTINENTAL = 2, BRAKE_BOSCH = 3;
    private static final int STEERING_BOSCH = 1, STEERING_MOBIS = 2;
    private static final int RUN_CODE = 1, TEST_CODE = 2;

    private static int[] carComponents = new int[5];
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int assemblyStep = CAR_TYPE_IDX;

        while (isValidStep(assemblyStep)) {
            int executionCode;
            initialize();
            showMenuByStep(assemblyStep);
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase(EXIT_CODE)) {
                System.out.println("바이바이");
                break;
            }
            try {
                executionCode = getValidExecutionCode(assemblyStep, userInput);
            } catch (RuntimeException re) {
                delay(800);
                continue;
            }
            assemblyStep = executeAndGetNextStep(assemblyStep, executionCode);
            delay(800);
        }

        scanner.close();
    }

    public static int getValidExecutionCode(int assemblyStep, String userInput){
        int executionCode;
        try {
            executionCode = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            throw new RuntimeException("사용자가 입력한 문자가 잘못됨");
        }
        if (!isValidRange(assemblyStep, executionCode)) {
            throw new RuntimeException("사용자가 입력한 문자가 잘못됨");
        }
        return executionCode;
    }

    public static void initialize() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

    public static int executeAndGetNextStep(int step, int executionCode) {
        if (executionCode == 0) {
            if (step == RUN_TEST_IDX) {
                step = CAR_TYPE_IDX;
            } else if (step > CAR_TYPE_IDX && step < RUN_TEST_IDX) {
                step--;
            }
            return step;
        }
        switch (step) {
            case CAR_TYPE_IDX:
                selectCarType(executionCode);
                return ENGINE_IDX;
            case ENGINE_IDX:
                selectEngine(executionCode);
                return BREAK_SYSTEM_IDX;
            case BREAK_SYSTEM_IDX:
                selectBrakeSystem(executionCode);
                return STEERING_SYSTEM_IDX;
            case STEERING_SYSTEM_IDX:
                selectSteeringSystem(executionCode);
                return RUN_TEST_IDX;
            case RUN_TEST_IDX:
                executeProducedCar(executionCode);
                return RUN_TEST_IDX;
        }
        throw new RuntimeException("잘못된 step으로 처리할 수 없습니다.");
    }

    public static void executeProducedCar(int executionCode) {
        if (executionCode == RUN_CODE) {
            runProducedCar();
            delay(2000);
        } else if (executionCode == TEST_CODE) {
            System.out.println("Test...");
            delay(1500);
            testProducedCar();
            delay(2000);
        }
        else {
            throw new RuntimeException("잘못된 입력으로 처리할 수 없습니다.");
        }
    }

    public static void showMenuByStep(int step) {
        switch (step) {
            case CAR_TYPE_IDX:
                showCarTypeMenu(); break;
            case ENGINE_IDX:
                showEngineMenu(); break;
            case BREAK_SYSTEM_IDX:
                showBrakeMenu(); break;
            case STEERING_SYSTEM_IDX:
                showSteeringMenu(); break;
            case RUN_TEST_IDX:
                showRunTestMenu(); break;
            default:
                throw new RuntimeException("잘못된 step 값 :: "  + step);
        }
        System.out.print("INPUT > ");
    }

    public static void showCarTypeMenu() {
        System.out.println("        ______________");
        System.out.println("       /|            |");
        System.out.println("  ____/_|_____________|____");
        System.out.println(" |                      O  |");
        System.out.println(" '-(@)----------------(@)--'");
        System.out.println("===============================");
        System.out.println("어떤 차량 타입을 선택할까요?");
        System.out.println("1. Sedan");
        System.out.println("2. SUV");
        System.out.println("3. Truck");
        System.out.println("===============================");
    }

    public static void showEngineMenu() {
        System.out.println("어떤 엔진을 탑재할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. GM");
        System.out.println("2. TOYOTA");
        System.out.println("3. WIA");
        System.out.println("4. 고장난 엔진");
        System.out.println("===============================");
    }

    public static void showBrakeMenu() {
        System.out.println("어떤 제동장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. MANDO");
        System.out.println("2. CONTINENTAL");
        System.out.println("3. BOSCH");
        System.out.println("===============================");
    }

    public static void showSteeringMenu() {
        System.out.println("어떤 조향장치를 선택할까요?");
        System.out.println("0. 뒤로가기");
        System.out.println("1. BOSCH");
        System.out.println("2. MOBIS");
        System.out.println("===============================");
    }

    public static void showRunTestMenu() {
        System.out.println("멋진 차량이 완성되었습니다.");
        System.out.println("어떤 동작을 할까요?");
        System.out.println("0. 처음 화면으로 돌아가기");
        System.out.println("1. RUN");
        System.out.println("2. Test");
        System.out.println("===============================");
    }

    public static boolean isValidRange(int step, int ans) {
        switch (step) {
            case CAR_TYPE_IDX:
                if (ans < 1 || ans > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
            case ENGINE_IDX:
                if (ans < 0 || ans > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
            case BREAK_SYSTEM_IDX:
                if (ans < 0 || ans > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
            case STEERING_SYSTEM_IDX:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
            case RUN_TEST_IDX:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
        }
        return true;
    }

    public static void selectCarType(int executionCode) {
        carComponents[CAR_TYPE_IDX] = executionCode;
        String name = executionCode == SEDAN ? "Sedan" : executionCode == SUV ? "SUV" : executionCode == TRUCK ? "Truck" : "잘못된 TYPE";
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", name);
    }

    public static void selectEngine(int executionCode) {
        carComponents[ENGINE_IDX] = executionCode;
        String name = executionCode == ENGINE_GM ? "GM" : executionCode == ENGINE_TOYOTA ? "TOYOTA" : executionCode == ENGINE_WIA ? "WIA" : "없는 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }

    public static void selectBrakeSystem(int executionCode) {
        carComponents[BREAK_SYSTEM_IDX] = executionCode;
        String name = executionCode == BREAK_MANDO ? "MANDO" : executionCode == BRAKE_CONTINENTAL ? "CONTINENTAL" : executionCode == BRAKE_BOSCH ? "BOSCH": "없는 브레이크";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }

    public static void selectSteeringSystem(int executionCode) {
        carComponents[STEERING_SYSTEM_IDX] = executionCode;
        String name = executionCode == STEERING_BOSCH ? "BOSCH" : executionCode == STEERING_MOBIS ? "MOBIS" : "잘못된 조향계";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }

    public static boolean isValidCar() {
        if (carComponents[CAR_TYPE_IDX] == SEDAN && carComponents[BREAK_SYSTEM_IDX] == BRAKE_CONTINENTAL) return false;
        if (carComponents[CAR_TYPE_IDX] == SUV   && carComponents[ENGINE_IDX] == ENGINE_TOYOTA)       return false;
        if (carComponents[CAR_TYPE_IDX] == TRUCK && carComponents[ENGINE_IDX] == ENGINE_WIA)          return false;
        if (carComponents[CAR_TYPE_IDX] == TRUCK && carComponents[BREAK_SYSTEM_IDX] == BREAK_MANDO)  return false;
        if (carComponents[BREAK_SYSTEM_IDX] == BRAKE_BOSCH && carComponents[STEERING_SYSTEM_IDX] != STEERING_BOSCH) return false;
        return true;
    }

    public static void runProducedCar() {
        if (!isValidCar()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (carComponents[ENGINE_IDX] == 4) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        String[] carTypes = {"", "Sedan", "SUV", "Truck"};
        String[] engineTypes = {"", "GM", "TOYOTA", "WIA"};
        String[] breakTypes = {"", "Mando", "Continental", "Bosch"};
        String[] steeringTypes = {"", "Bosch", "Mobis"};
        System.out.printf("Car Type : %s\n", carTypes[carComponents[CAR_TYPE_IDX]]);
        System.out.printf("Engine   : %s\n", engineTypes[carComponents[ENGINE_IDX]]);
        System.out.printf("Brake    : %s\n", breakTypes[carComponents[BREAK_SYSTEM_IDX]]);
        System.out.printf("Steering : %s\n", steeringTypes[carComponents[STEERING_SYSTEM_IDX]]);
        System.out.println("자동차가 동작됩니다.");
    }

    public static void testProducedCar() {
        if (carComponents[CAR_TYPE_IDX] == SEDAN && carComponents[BREAK_SYSTEM_IDX] == BRAKE_CONTINENTAL) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (carComponents[CAR_TYPE_IDX] == SUV && carComponents[ENGINE_IDX] == ENGINE_TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (carComponents[CAR_TYPE_IDX] == TRUCK && carComponents[ENGINE_IDX] == ENGINE_WIA) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (carComponents[CAR_TYPE_IDX] == TRUCK && carComponents[BREAK_SYSTEM_IDX] == BREAK_MANDO) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (carComponents[BREAK_SYSTEM_IDX] == BRAKE_BOSCH && carComponents[STEERING_SYSTEM_IDX] != STEERING_BOSCH) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    public static void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }

    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    public static boolean isValidStep(int step){
        return step == CAR_TYPE_IDX || step == BREAK_SYSTEM_IDX || step == ENGINE_IDX || step == STEERING_SYSTEM_IDX || step == RUN_TEST_IDX;
    }
}