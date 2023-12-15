package Shapes;

public abstract class Shape extends ImageObject {

    Color color;

    public Color getColor() {
        return color;
    }

    public Shape(int posX, int posY, String path, Color color) {
        super(posX, posY, path);
    }
}
