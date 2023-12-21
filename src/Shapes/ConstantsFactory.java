package Shapes;

import CircusOfPlatesGame.GameWorld;
import static Shapes.ConstantsNames.BACKGROUND;
import static Shapes.ConstantsNames.LIVES;

public class ConstantsFactory extends Factory {

    public ImageObject ConstantsCreator(ConstantsNames constant, int xPos, int yPos, GameWorld world) {
        switch (constant) {
            case LIVES:
                return new Lives(xPos, yPos, "heart.png");
            case BACKGROUND:
                return new BackGround(0, 0, "background.png");
            default:
                return null;
        }
    }
}
