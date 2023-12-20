package CircusOfPlatesGame;

public class EasyGameWorld extends GameWorld {

    public EasyGameWorld(int width, int height)  {
        super(width, height);
    }

    @Override
    public void setGame() {
        SoundPlayer.playSound("circusMusic.WAV");
        createShapes();
        constants.add(background);
        controllable.add(ClownObject.getClownInstance());
    }
}
