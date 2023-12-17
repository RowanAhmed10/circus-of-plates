package Shapes;

import CircusOfPlatesGame.GameWorld;

public abstract class Shape extends ImageObject {

    Color color;
    private GameWorld world;
    boolean isControllable = false;

    public Color getColor() {
        return color;
    }

    public Shape(int posX, int posY, String path, Color color, GameWorld world) {
        super(posX, posY, path);
        this.world = world;
        this.color = color;
    }

    @Override
    public void setY(int y) {
        if (!isControllable) {
            super.setY(y);
        }
    }
}
