package CircusOfPlatesGame;

import Shapes.LivesFactory;
import Shapes.SpecialShape;
import Shapes.SpecialShapeFactory;
import Shapes.SpecialShapeName;
import java.util.Collections;

public class HardGameWorld extends GameWorld {

    public HardGameWorld(int width, int height) {
        super(width, height);
    }

    @Override
    public void setGame() {
        SoundPlayer.playSound("circusMusic.WAV");
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
        SpecialShape specialShape3 = (SpecialShape) new SpecialShapeFactory().SpecialShapeCreator(SpecialShapeName.STAR, (int) (Math.random() * (getWidth())), (int) (Math.random() * getHeight() / 2 * -1));
        moveable.add(specialShape3);
        Collections.shuffle(moveable);
        startState = new StartClownState(this);
        stopState = new StopClownState(this);
    }
}
