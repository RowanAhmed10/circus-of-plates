package CircusOfPlatesGame;

import Shapes.LivesFactory;
import Shapes.SpecialShape;
import Shapes.SpecialShapeFactory;
import Shapes.SpecialShapeName;
import java.util.Collections;

public class MediumGameWorld extends GameWorld {

    public MediumGameWorld(int width, int height) {
        super(width, height);
    }

    @Override
    public void setGame() {
        SoundPlayer.playSound("circusMusic.WAV");
        createShapes();
        constants.add(background);
        constants.add(new LivesFactory().LivesCreator(5, 10));
        constants.add(new LivesFactory().LivesCreator(45, 10));
        constants.add(new LivesFactory().LivesCreator(85, 10));
        controllable.add(ClownObject.getClownInstance());
        for (int i = 0; i < 5; i++) {
            SpecialShape specialShape = (SpecialShape) new SpecialShapeFactory().SpecialShapeCreator(SpecialShapeName.BOMB, (int) ((Math.random()) * (getWidth())), (int) (Math.random()) * getHeight() / 2 * -1);
            moveable.add(specialShape);
        }
        Collections.shuffle(moveable);
    }
}
