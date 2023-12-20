package CircusOfPlatesGame;

import Shapes.ImageObject;
import Shapes.Shape;
import java.util.Timer;
import java.util.TimerTask;

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
        hardGame.constants.add(ClownObject.getClownInstance());
        hardGame.controllable.remove(ClownObject.getClownInstance());
        hardGame.constants.add(hardGame.getLeftBasePlate());
        hardGame.controllable.remove(hardGame.getLeftBasePlate());
        hardGame.constants.add(hardGame.getRightBasePlate());
        hardGame.controllable.remove(hardGame.getRightBasePlate());
        hardGame.constants.addAll(hardGame.right);
        hardGame.constants.addAll(hardGame.left);
        hardGame.controllable.removeAll(hardGame.right);
        hardGame.controllable.removeAll(hardGame.left);

    }

}
