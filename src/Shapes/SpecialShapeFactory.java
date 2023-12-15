package Shapes;

public class SpecialShapeFactory extends Factory {

    public SpecialShape SpecialShapeCreator(SpecialShapeName specialShapeName, int xPos, int yPos) {
        switch (specialShapeName) {
            case BOMB:
                return new Bomb(xPos, yPos, "../Images/bomb.png");
            case ICE:
                return new IceCube(xPos, yPos, "../Images/ice.png");
            default:
                return null;
        }
    }
}
