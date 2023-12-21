package CircusOfPlatesGame;

import Shapes.Shock;
import java.util.Timer;
import java.util.TimerTask;

public class CatchShockCommand implements CatchCommand {

    private final GameWorld world;
    private final Shock shock;

    public CatchShockCommand(GameWorld world, Shock shock) {
        this.shock = shock;
        this.world = world;
    }

    public void execute() {
        SoundPlayer.playSound("electricShock.WAV");
        world.setIsFrozen(true);
        world.startState.ElectricShock();

        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                world.setIsFrozen(false);
                world.stopState.ElectricShock();
            }
        };
        t.schedule(task, 3000);
        world.moveable.remove(shock);
    }

}
