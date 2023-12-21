package CircusOfPlatesGame;

import Shapes.ImageObject;
import Shapes.Plate;
import Shapes.Shape;
import eg.edu.alexu.csd.oop.game.GameObject;
import java.util.List;

public class CatchPlateCommand implements CatchCommand {

    private final GameWorld world;
    private final Plate plate;
    private final List<Shape> list;

    public CatchPlateCommand(GameWorld world, Plate plate, List<Shape> list) {
        this.plate = plate;
        this.world = world;
        this.list = list;
    }

    public void execute() {
        GameObject top = list.get(list.size() - 1);
        list.add(plate);
        world.updatePlateIndex(top, (ImageObject) plate);
        world.controllable.add(plate);
        world.moveable.remove(plate);
        if (plate.getY() == 0) {
            world.endGame();
        }
    }
}
