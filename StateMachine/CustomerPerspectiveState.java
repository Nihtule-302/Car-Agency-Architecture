package StateMachine;

import utility.Talk;

public class CustomerPerspectiveState implements State {
    CarAgencyStateMachine machine;

    @Override
    public void enter(CarAgencyStateMachine machine) {
        this.machine = machine;
    }

    @Override
    public void Do() {
        handleCustomer();
        machine.SwitchState(new MainMenuState());
    }

    @Override
    public void exit() {

    }

    private void handleCustomer() {
        System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
        Talk talk = new Talk();
        talk.greetings();
        talk.howCanIHelp();
        System.out.println("Thank you for visiting The ASP Car Agency.\nBye!");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }
}
