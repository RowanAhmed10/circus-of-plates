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
            SpecialShape bomb = (SpecialShape) new SpecialShapeFactory().SpecialShapeCreator(SpecialShapeName.BOMB, (int) ((Math.random()) * (getWidth())), (int) (Math.random()) * getHeight() / 2 * -1);
            moveable.add(bomb);
        }
        SpecialShape star = (SpecialShape) new SpecialShapeFactory().SpecialShapeCreator(SpecialShapeName.STAR, (int) (Math.random() * (getWidth())), (int) (Math.random() * getHeight() / 2 * -1));
        moveable.add(star);
        Collections.shuffle(moveable);
    }
}
