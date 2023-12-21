package CircusOfPlatesGame;

import Shapes.Ball;
import Shapes.ImageObject;
import Shapes.Shape;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.List;

public class CatchBallCommand implements CatchCommand {

    private final GameWorld world;
    private final Ball ball;
    private final List<Shape> list;

    public CatchBallCommand(GameWorld world, Ball ball, List<Shape> list) {
        this.ball = ball;
        this.world = world;
        this.list = list;
    }

    @Override
    public void execute() {
        GameObject top = list.get(list.size() - 1);
        list.add(ball);
        world.updateBallIndex(top, (ImageObject) ball);
        world.controllable.add(ball);
        world.moveable.remove(ball);
        if (ball.getY() == 0) {
            world.endGame();
        }
    }

}
