package Shapes;

import CircusOfPlatesGame.ClownObject;
import CircusOfPlatesGame.GameWorld;

public class Ball extends Shape {

    private GameWorld world;

    public Ball(int posX, int posY, String path, Color color, GameWorld world) {
        super(posX, posY, path, color, world);
        this.world = world;
    }

    @Override
    public void setX(int x) {
        ClownObject clown = ClownObject.getClownInstance();
        if (world.isInLeftStack(this)) {
            super.setX(clown.getX());
        } else if (world.isInRightStack(this)) {
            super.setX(clown.getX() + 180);
        }
    }
}
