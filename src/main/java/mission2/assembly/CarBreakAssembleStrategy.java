package mission2.assembly;

import mission2.Car;
import mission2.components.CarBreak;
import mission2.UserCommand;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CarBreakAssembleStrategy implements AssembleStrategy {
    private Integer maxExecutionCode;
    private Integer minExecutionCode;

    @Override
    public void assembleByCommand(Car car, UserCommand userCommand) {
        this.setMinMaxCode();
        if (isValid(userCommand)) {
            for (CarBreak each : CarBreak.values()) {
                if (each.executionCode == userCommand.getExecutionCode()) {
                    car.setBreak(each);
                    System.out.printf("%s 제동장치를 선택하셨습니다.\n", each.description);
                    break;
                }
            }
        } else {
            System.out.printf("ERROR :: 제동장치는 %s ~ %s 범위만 선택 가능%n", minExecutionCode, maxExecutionCode);
            throw new RuntimeException();
        }
    }

    private boolean isValid(UserCommand userCommand) {
        int userInput = userCommand.getExecutionCode();
        for (CarBreak each : CarBreak.values()) {
            if (each.executionCode == userCommand.getExecutionCode()) {
                return true;
            }
        }
        return false;
    }

    private void setMinMaxCode() {
        List<CarBreak> breaks = Arrays.stream(CarBreak.values()).sorted(Comparator.comparingInt(each -> each.executionCode)).toList();
        this.minExecutionCode = breaks.get(0).executionCode;
        this.maxExecutionCode = breaks.get(breaks.size() - 1).executionCode;
    }
}
