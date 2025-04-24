package StateMachine;

public class ExitState implements State
{
    CarAgencyStateMachine machine;

    @Override
    public void enter(CarAgencyStateMachine machine) {
        this.machine = machine;
    }

    @Override
    public void Do() {
        exitProgram();
    }

    @Override
    public void exit() {

    }

    private static void exitProgram() {
        System.out.println("Thank you for visiting The ASP Car Agency.\nGoodbye!");
        System.exit(0);
    }
}
