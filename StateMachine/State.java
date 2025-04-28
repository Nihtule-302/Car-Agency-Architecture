package StateMachine;

public interface State {
    void enter(CarAgencyStateMachine machine);

    void Do();

    void exit();
}
