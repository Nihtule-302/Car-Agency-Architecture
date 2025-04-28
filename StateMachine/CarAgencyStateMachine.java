package StateMachine;

public class CarAgencyStateMachine {
    private State state;

    public void Start() {
        setState(new MainMenuState());
        state.enter(this);

        state.Do();
    }

    private void setState(State state) {
        this.state = state;
    }

    public void SwitchState(State state) {
        state.exit();

        setState(state);
        state.enter(this);

        state.Do();
    }


}

