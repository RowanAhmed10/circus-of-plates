package Shapes;

public class SpecialShapeFactory extends Factory {

    public SpecialShape SpecialShapeCreator(SpecialShapeName specialShapeName, int xPos, int yPos) {
        switch (specialShapeName) {
            case BOMB:
                return new Bomb(xPos, yPos, "../Images/bomb.png");
            case ICE:
                return new Shock(xPos, yPos, "../Images/zzzz.png");
            case STAR:
                return new Star(xPos, yPos, "../Images/star.png");
            default:
                return null;
        }
    }
}
