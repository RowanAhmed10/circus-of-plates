package CircusOfPlatesGame;

public class StopClownState extends ClownState {

    private HardGameWorld hardGame;

    public StopClownState(HardGameWorld hardGame) {
        this.hardGame = hardGame;
    }

    public HardGameWorld getHardGame() {
        return hardGame;
    }

    public void setHardGame(HardGameWorld hardGame) {
        this.hardGame = hardGame;
    }

    @Override
    public void freeze() {
        hardGame.constants.remove(ClownObject.getClownInstance());
        hardGame.controllable.add(ClownObject.getClownInstance());
    }
}
