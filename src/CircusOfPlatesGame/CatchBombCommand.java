package CircusOfPlatesGame;

import Shapes.Bomb;
import Shapes.ImageObject;

public class CatchBombCommand implements CatchCommand {

    private final GameWorld world;
    private final Bomb bomb;

    public CatchBombCommand(GameWorld world, Bomb bomb) {
        this.bomb = bomb;
        this.world = world;
    }

    public void execute() {
        SoundPlayer.playSound("bombsound.WAV");
        bomb.setVisible(false);
        world.moveable.remove(bomb);
        ImageObject heart = (ImageObject) world.constants.get(world.constants.size() - 1);
        heart.setVisible(false);
        world.constants.remove(world.constants.size() - 1);
        if (world.constants.size() == 1) {
            world.endGame();
        }
    }
}
