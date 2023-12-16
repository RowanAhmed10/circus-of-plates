package CircusOfPlatesGame;

import Shapes.LivesFactory;
import Shapes.SpecialShape;
import Shapes.SpecialShapeFactory;
import Shapes.SpecialShapeName;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class HardGameWorld extends GameWorld {

    int delayInSeconds = 5;
    boolean isFreezed = false;

    public HardGameWorld(int width, int height) {
        super(width, height);
    }

    private void freeze() {
        isFreezed = true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isFreezed = false;
            }
        }, delayInSeconds * 1000);
    }

    @Override
    public void setGame() {
        createShapes();
        constants.add(background);
        controllable.add(ClownObject.getClownInstance());
        constants.add(new LivesFactory().LivesCreator(5, 10));
        constants.add(new LivesFactory().LivesCreator(45, 10));
        constants.add(new LivesFactory().LivesCreator(85, 10));
        for (int i = 0; i < 5; i++) {
            SpecialShape specialShape1 = (SpecialShape) new SpecialShapeFactory().SpecialShapeCreator(SpecialShapeName.BOMB, (int) (Math.random() * (getWidth())), (int) (Math.random() * getHeight() / 2 * -1));
            moveable.add(specialShape1);
            SpecialShape specialShape2 = (SpecialShape) new SpecialShapeFactory().SpecialShapeCreator(SpecialShapeName.ICE, (int) (Math.random() * (getWidth())), (int) (Math.random() * getHeight() / 2 * -1));
            moveable.add(specialShape2);
        }
        Collections.shuffle(moveable);
        startState = new StartClownState(this);
        stopState = new StopClownState(this);
    }
}
