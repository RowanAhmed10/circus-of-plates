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
        SpecialShape star = (SpecialShape) new SpecialShapeFactory().SpecialShapeCreator(SpecialShapeName.STAR, (int) (Math.random() * (getWidth())), (int) (Math.random() * getHeight() / 2 * -1));
        resolveIntersection(star);
        moveable.add(star);
    }
}
