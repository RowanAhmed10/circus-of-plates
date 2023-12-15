package Shapes;

public class LivesFactory extends Factory {

    public Lives LivesCreator(int xPos, int yPos) {
        return new Lives(xPos, yPos, "../Images/heart.png");
    }
}
