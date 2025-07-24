package mission2;

public class UserCommand {
    private int step;
    private int executionCode;
    private String userInput;

    private static final int UNDO_CODE = 0, RUN_CODE = 1, TEST_CODE = 2;

    public UserCommand(int step, String userInput) {
        this.step = step;
        this.userInput = userInput;
    }

    public int getStep() {
        return step;
    }

    public int getExecutionCode() {
        return executionCode;
    }

    public void setExecutionCodeBySelf() {
        this.executionCode = Integer.parseInt(this.userInput);
    }

    public boolean isInValid(){
        int executionCode;
        try {
            this.setExecutionCodeBySelf();
            return false;
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            return true;
        }
    }

    public boolean isUndoCode(){
        return this.getExecutionCode() == UNDO_CODE;
    }

    public boolean isRunCode(){
        return this.getExecutionCode() == RUN_CODE;
    }

    public boolean isTestCode(){
        return this.getExecutionCode() == TEST_CODE;
    }
}
