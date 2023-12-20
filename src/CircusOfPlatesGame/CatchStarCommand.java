package CircusOfPlatesGame;

import Shapes.Star;

public class CatchStarCommand implements CatchCommand {

    private final GameWorld world;
    private final Star star;

    public CatchStarCommand(GameWorld world, Star star) {
        this.star = star;
        this.world = world;
    }

    public void execute() {
        world.moveable.remove(star);
        SoundPlayer.playSound("starSound.WAV");
        world.setScore(world.getScore() * 2);
        star.setVisible(false);
    }
}
