package CircusOfPlatesGame;

import Shapes.ImageObject;

public class StartClownState extends ClownState {

    private HardGameWorld hardGame;
    ImageObject object = ClownObject.getClownInstance();

    public StartClownState(HardGameWorld hardGame) {
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
        hardGame.controllable.remove(ClownObject.getClownInstance());
        hardGame.constants.add(ClownObject.getClownInstance());
    }
}
