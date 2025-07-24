package mission2.assembly;

import mission2.Car;
import mission2.components.Engine;
import mission2.UserCommand;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class EngineAssembleStrategy implements AssembleStrategy {
    private Integer maxExecutionCode;
    private Integer minExecutionCode;

    @Override
    public void assembleByCommand(Car car, UserCommand userCommand) {
        this.setMinMaxCode();
        if (isValid(userCommand)) {
            for (Engine eachEngine : Engine.values()) {
                if (eachEngine.executionCode == userCommand.getExecutionCode()) {
                    System.out.printf("%s 엔진을 선택하셨습니다.\n", eachEngine.description);
                    car.setEngine(eachEngine);
                    break;
                }
            }
        }
        else {
            System.out.printf("ERROR :: 엔진은 %s ~ %s 범위만 선택 가능%n", minExecutionCode, maxExecutionCode);
            throw new RuntimeException();
        }
    }

    private boolean isValid(UserCommand userCommand) {
        int userInput = userCommand.getExecutionCode();
        for (Engine each : Engine.values()) {
            if (each.executionCode == userCommand.getExecutionCode()) {
                return true;
            }
        }
        return false;
    }

    private void setMinMaxCode() {
        List<Engine> engines = Arrays.stream(Engine.values()).sorted(Comparator.comparingInt(eachEngine -> eachEngine.executionCode)).toList();
        this.minExecutionCode = engines.get(0).executionCode;
        this.maxExecutionCode = engines.get(engines.size() - 1).executionCode;
    }
}
