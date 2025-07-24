package mission2;

import mission2.assembly.AssembleStrategy;
import mission2.assembly.AssemblyController;

import java.util.Scanner;

public class CarFactory {
    private static final String CLEAR_SCREEN = "\033[H\033[2J";
    private static final String EXIT_CODE = "exit";
    private static final int PRODUCE_STEP = 4;


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int step = 0;
        Car car = new Car();
        while (true){
            initialize();
            Menu.showMenuByStep(step);
            String userInput = scanner.nextLine().trim();
            // exit code 먼저 처리
            if (userInput.equalsIgnoreCase(EXIT_CODE)) {
                System.out.println("바이바이");
                break;
            }
            UserCommand userCommand = new UserCommand(step, userInput);
            if (userCommand.isInValid()){
                DisplayBuffer.delay(800);
                continue;
            }
            if (userCommand.isUndoCode()) {
                if (step == PRODUCE_STEP) {
                    step = 0;
                    car = new Car();
                } else if (step > 0 && step < PRODUCE_STEP) {
                    step--;
                }
                continue;
            }
            if (step == PRODUCE_STEP){
                if (userCommand.isRunCode()){
                    car.run();
                    DisplayBuffer.delay(2000);
                } else if (userCommand.isTestCode()){
                    System.out.println("Test...");
                    DisplayBuffer.delay(1500);
                    car.testRun();
                    DisplayBuffer.delay(2000);
                }
            }  else {
                AssembleStrategy assembleStrategy = AssemblyController.getAssembleStrategy(userCommand);
                try {
                    assembleStrategy.assembleByCommand(car, userCommand);
                    step++;
                } catch (RuntimeException e){
                    continue;
                } finally {
                    DisplayBuffer.delay(800);
                }
            }
        }
        scanner.close();
    }

    private static void initialize() {
        System.out.print(CLEAR_SCREEN);
        System.out.flush();
    }

}