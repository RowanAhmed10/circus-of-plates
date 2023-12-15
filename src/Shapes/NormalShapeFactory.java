package Shapes;

public class NormalShapeFactory   extends Factory{

    public ImageObject ShapeCreator(ShapeName shapeName, Color color, int xPos, int yPos) {

        switch (shapeName) {
            case PLATE:

                return new Plate(xPos, yPos, getImagePath(color,shapeName), color);

            case BALL:
                return new Ball(xPos, yPos,getImagePath(color,shapeName), color);
            default:
                return null;
        }

    }
     public String getImagePath(Color color, ShapeName shapeName) {
        if (shapeName != null) {
            String colorName = color.toString().toLowerCase();
            String shapeNameStr = shapeName.toString().toLowerCase();
            return "../Images/"+colorName + shapeNameStr + ".png";
        }
        return null;
    }
    //makanha msh hna bs for now 

}
