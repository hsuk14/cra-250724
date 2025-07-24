package mission2.assembly;

import mission2.Car;
import mission2.components.Steering;
import mission2.UserCommand;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SteeringAssembleStrategy implements AssembleStrategy {
    private Integer maxExecutionCode;
    private Integer minExecutionCode;

    @Override
    public void assembleByCommand(Car car, UserCommand userCommand) {
        this.setMinMaxCode();
        if (isValid(userCommand)) {
            for (Steering each : Steering.values()) {
                if (each.executionCode == userCommand.getExecutionCode()) {
                    car.setSteering(each);
                    System.out.printf("%s 조향장치를 선택하셨습니다.\n", each.description);
                    break;
                }
            }
        }
        else{
            System.out.printf("ERROR :: 조향장치는 %s ~ %s 범위만 선택 가능%n", minExecutionCode, maxExecutionCode);
            throw new RuntimeException();
        }
    }

    private boolean isValid(UserCommand userCommand) {
        int userInput = userCommand.getExecutionCode();
        for (Steering each : Steering.values()) {
            if (each.executionCode == userCommand.getExecutionCode()) {
                return true;
            }
        }
        return false;
    }

    private void setMinMaxCode() {
        List<Steering> steerings = Arrays.stream(Steering.values()).sorted(Comparator.comparingInt(each -> each.executionCode)).toList();
        this.minExecutionCode = steerings.get(0).executionCode;
        this.maxExecutionCode = steerings.get(steerings.size() - 1).executionCode;
    }
}
