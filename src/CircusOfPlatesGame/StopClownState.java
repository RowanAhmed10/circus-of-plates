package CircusOfPlatesGame;

import Shapes.ImageObject;

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
    public void ElectricShock() {
        ClownObject.getClownInstance().setImage("clown.png");

        for (int i = 0; i < hardGame.moveable.size(); i++) {
            ImageObject obj = (ImageObject) hardGame.moveable.get(i);
            obj.setVisible(true);

        }

        for (int i = 0; i < hardGame.controllable.size(); i++) {
            ImageObject obj = (ImageObject) hardGame.controllable.get(i);
            obj.setVisible(true);

        }
    }
}
