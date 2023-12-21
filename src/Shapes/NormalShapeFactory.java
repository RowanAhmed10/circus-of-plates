package Shapes;

import CircusOfPlatesGame.GameWorld;

public class NormalShapeFactory extends Factory {

    public ImageObject ShapeCreator(ShapeName shapeName, Color color, int xPos, int yPos, GameWorld world) {
        switch (shapeName) {
            case PLATE:
                return new Plate(xPos, yPos, getImageName(color, shapeName), color, world);
            case BALL:
                return new Ball(xPos, yPos, getImageName(color, shapeName), color, world);
            default:
                return null;
        }
    }

    public String getImageName(Color color, ShapeName shapeName) {
        if (shapeName != null) {
            String colorName = color.toString().toLowerCase();
            String shapeNameStr = shapeName.toString().toLowerCase();
            return colorName + shapeNameStr + ".png";
        }
        return null;
    }
}
