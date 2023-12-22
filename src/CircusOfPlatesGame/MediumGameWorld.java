package CircusOfPlatesGame;

import Shapes.ConstantsFactory;
import Shapes.ConstantsNames;
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
        SoundPlayer.playSoundBackground("circusMusic.WAV");
        createShapes();
        constants.add(background);
         constants.add(new ConstantsFactory().ConstantsCreator(ConstantsNames.LIVES,5, 10,this));
         constants.add(new ConstantsFactory().ConstantsCreator(ConstantsNames.LIVES,45, 10,this));
        constants.add(new ConstantsFactory().ConstantsCreator(ConstantsNames.LIVES,85, 10,this));
        controllable.add(ClownObject.getClownInstance());
        for (int i = 0; i < 5; i++) {
            SpecialShape bomb = (SpecialShape) new SpecialShapeFactory().SpecialShapeCreator(SpecialShapeName.BOMB, (int) ((Math.random()) * (getWidth())), (int) (Math.random()) * getHeight() / 2 * -1);
            resolveIntersection(bomb);
            moveable.add(bomb);
        }
        SpecialShape star = (SpecialShape) new SpecialShapeFactory().SpecialShapeCreator(SpecialShapeName.STAR, (int) (Math.random() * (getWidth())), (int) (Math.random() * getHeight() / 2 * -1));
        resolveIntersection(star);
        moveable.add(star);
        Collections.shuffle(moveable);
    }
}
