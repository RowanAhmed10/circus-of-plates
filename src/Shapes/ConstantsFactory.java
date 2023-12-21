package Shapes;

import CircusOfPlatesGame.GameWorld;
import static Shapes.ConstantsNames.BACKGROUND;
import static Shapes.ConstantsNames.LIVES;

// Add the import statement for ConstantsNames
import Shapes.ConstantsNames;

public class ConstantsFactory extends Factory {

    public ImageObject ConstantsCreator(ConstantsNames constant, int xPos, int yPos, GameWorld world) {
        switch (constant) {
            case LIVES:
                return new Lives(xPos, yPos, "../Images/heart.png");
            case BACKGROUND:
                return new BackGround(0, 0, "../Images/background.png");
            default:
                return null;
        }
    }
}
