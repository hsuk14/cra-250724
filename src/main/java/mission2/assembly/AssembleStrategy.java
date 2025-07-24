package mission2.assembly;

import mission2.Car;
import mission2.UserCommand;

public interface AssembleStrategy {

    void assembleByCommand(Car car, UserCommand userCommand);

}
