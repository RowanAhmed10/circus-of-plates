package Shapes;

import eg.edu.alexu.csd.oop.game.GameObject;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageObject implements GameObject {

    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean isControllable = false;
    private boolean visible;
    private final String folder = "../Images/";

    public ImageObject(int posX, int posY, String path) {
        this.x = posX;
        this.y = posY;

        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(folder + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isControllable() {
        return isControllable;
    }

    public void setControllable(boolean conrollable) {
        isControllable = conrollable;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int y) {
        if (!isControllable) {
            this.y = y;
        }
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    public void setImage(String path) {
        try {
            spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(folder + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
