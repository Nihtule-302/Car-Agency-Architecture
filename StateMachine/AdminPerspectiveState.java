package StateMachine;

import utility.Management;

public class AdminPerspectiveState implements State
{
    CarAgencyStateMachine machine;

    @Override
    public void enter(CarAgencyStateMachine machine) {
        this.machine = machine;
    }

    @Override
    public void Do()
    {
        handleAdmin();
        machine.SwitchState(new MainMenuState());
    }

    @Override
    public void exit() {

    }

    private static void handleAdmin() {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++");
        Management management = new Management();
        management.start();
    }
}
