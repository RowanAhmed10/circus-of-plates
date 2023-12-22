package CircusOfPlatesGame;

import Shapes.SpecialShape;
import Shapes.SpecialShapeFactory;
import Shapes.SpecialShapeName;

public class EasyGameWorld extends GameWorld {

    public EasyGameWorld(int width, int height) {
        super(width, height);
    }

    @Override
    public void setGame() {
        SoundPlayer.playSoundBackground("circusMusic.WAV");
        createShapes();
        constants.add(background);
        controllable.add(ClownObject.getClownInstance());
        SpecialShape star = (SpecialShape) new SpecialShapeFactory().SpecialShapeCreator(SpecialShapeName.STAR, getRandomX(), getRandomY());
//        resolveIntersection(star);
        moveable.add(star);
    }
}
