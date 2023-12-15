package Shapes;

public class NormalShapeFactory {

    public static ImageObject ShapeCreator(ShapeName shapeName, Color color, int xPos, int yPos) {

        switch (shapeName) {
            case PLATE:

                return new Plate(xPos, yPos, getImagePath(color,shapeName), color);

            case BALl:
                return new Ball(xPos, yPos,getImagePath(color,shapeName), color);
            default:
                return null;
        }

    }
     public static String getImagePath(Color color, ShapeName shapeName) {
        if (shapeName != null) {
            String colorName = color.toString().toLowerCase();
            String shapeNameStr = shapeName.toString().toLowerCase();
            System.out.println(colorName + shapeNameStr + ".png");
            return "../Images/"+colorName+shapeNameStr+".png";
        }
        return null;
    }
    //makanha msh hna bs for now 

}
