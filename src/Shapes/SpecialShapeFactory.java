package Shapes;

public class SpecialShapeFactory extends Factory {

    public SpecialShape SpecialShapeCreator(SpecialShapeName specialShapeName, int xPos, int yPos) {
        switch (specialShapeName) {
            case BOMB:
                return new Bomb(xPos, yPos, "bomb.png");
            case SHOCK:
                return new Shock(xPos, yPos, "zzzz.png");
            case STAR:
                return new Star(xPos, yPos, "star.png");
            default:
                return null;
        }
    }
}
