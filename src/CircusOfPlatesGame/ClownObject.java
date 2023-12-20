package CircusOfPlatesGame;

import Shapes.ImageObject;

//3adlt 3leh ya ranim 
public final class ClownObject extends ImageObject {

    private static ClownObject clownInstance = null;

    // Modify the Clown's x and y position
    public static ClownObject getClownInstance() {
        if (clownInstance == null) {
            clownInstance = new ClownObject(290, 265, "../Images/clown.png");
        }
        return clownInstance;
    }

    private ClownObject(int posX, int posY, String path) {
        super(posX, posY, path);
    }

    @Override
    public void setY(int y) {

    }

// Clown moves horizontally only
    // gonna set x  in the controller
}
