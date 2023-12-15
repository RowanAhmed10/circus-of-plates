package Shapes;

public class SpecialShapeFactory {

    public static SpecialShape SpecialPieceCreator(SpecialShapeName specialShapeName, int xPos, int yPos) {
        switch (specialShapeName) {
            case BOMB:
                return new Bomb(xPos, yPos, "../Images/bomb.png");
            case ICE:
                return new IceCube(xPos, yPos, "../Images/ice.png");
            case HEART:
                    return new IceCube(xPos, yPos, "../Images/heart.png");
            default:
                return null;
        }

    }
}
