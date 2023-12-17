package Shapes;

import CircusOfPlatesGame.ClownObject;
import CircusOfPlatesGame.GameWorld;

public class BasePlate extends Shape {

    private boolean isLeft;

    public BasePlate(int posX, int posY, String path, GameWorld world, boolean left) {
        super(posX, posY, path, Color.WHITE, world);
        isLeft = left;
    }

    @Override
    public void setY(int y) {

    }

    @Override
    public void setX(int x) {
        int shift = isLeft ? -30 : 150;
        ClownObject clown = ClownObject.getClownInstance();
        super.setX(clown.getX() + shift);
    }
}
