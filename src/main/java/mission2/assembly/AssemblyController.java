package mission2.assembly;

import mission2.*;

public class AssemblyController {
    private static final int CAR_TYPE_IDX = 0;
    private static final int ENGINE_IDX = 1;
    private static final int BREAK_SYSTEM_IDX = 2;
    private static final int STEERING_SYSTEM_IDX = 3;
    private static final int RUN_TEST_IDX = 4;

    public static AssembleStrategy getAssembleStrategy(UserCommand userCommand) {
        if (userCommand.getStep() == CAR_TYPE_IDX) {
            return new CarTypeAssembleStrategy();
        }
        if (userCommand.getStep() == ENGINE_IDX) {
            return new EngineAssembleStrategy();
        }
        if (userCommand.getStep() == BREAK_SYSTEM_IDX) {
            return new CarBreakAssembleStrategy();
        }
        if (userCommand.getStep() == STEERING_SYSTEM_IDX) {
            return new SteeringAssembleStrategy();
        }
        throw new RuntimeException("일치하는 step이 없습니다.");
    }
}
