package mission2.assembly;

import mission2.Car;
import mission2.components.CarType;
import mission2.UserCommand;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CarTypeAssembleStrategy implements AssembleStrategy {
    private Integer maxExecutionCode;
    private Integer minExecutionCode;

    @Override
    public void assembleByCommand(Car car, UserCommand userCommand) {
        this.setMinMaxCode();
        if (isValid(userCommand)){
            for (CarType eachCarType : CarType.values()){
                if (eachCarType.executionCode == userCommand.getExecutionCode()){
                    System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", eachCarType.description);
                    car.setCarType(eachCarType);
                    break;
                }
            }
        } else {
            System.out.printf("ERROR :: 차량 타입은 %s ~ %s 범위만 선택 가능%n", minExecutionCode, maxExecutionCode);
            throw new RuntimeException();
        }
    }

    private boolean isValid(UserCommand userCommand) {
        int userInput = userCommand.getExecutionCode();
        for (CarType eachCarType : CarType.values()) {
            if (eachCarType.executionCode == userCommand.getExecutionCode()) {
                return true;
            }
        }
        return false;
    }

    private void setMinMaxCode(){
        List<CarType> carTypes = Arrays.stream(CarType.values()).sorted(Comparator.comparingInt(eachCarType -> eachCarType.executionCode)).toList();
        this.minExecutionCode = carTypes.get(0).executionCode;
        this.maxExecutionCode = carTypes.get(carTypes.size()-1).executionCode;
    }
}
