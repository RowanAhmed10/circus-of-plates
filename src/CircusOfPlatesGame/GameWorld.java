package CircusOfPlatesGame;

import Shapes.Color;
import Shapes.ImageObject;
import Shapes.NormalShapeFactory;
import Shapes.Shape;
import Shapes.ShapeName;
import Shapes.SpecialShapeFactory;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.ArrayList;
import java.util.List;

public abstract class GameWorld implements World {

    private int score = 0, scoreShift = 10;
    private int width;
    private int height;
    private int CONTROL_SPEED = 10;
    private int SPEED = 10;
    private int MAX_MOVING_OBJECTS = 3;
    private ShapeColorCollection shapeNamesCollection;
    protected List<GameObject> constants = new ArrayList();
    protected List<GameObject> controllable = new ArrayList();
    protected List<GameObject> moveable = new ArrayList();
    ImageObject background = new ImageObject(0, 0, "../Images/background.png");
    SpecialShapeFactory special = new SpecialShapeFactory();

    public GameWorld(int width, int height) {
        this.shapeNamesCollection = new ShapeColorCollection();
        this.width = width;
        this.height = height;
        setGame();
    }

    protected void createShapes() {
        for (int i = 0; i < MAX_MOVING_OBJECTS; i++) {
            for (Iterator iter = shapeNamesCollection.getIterator(); iter.hasNext();) {
                Color shapeColor = (Color) iter.next();
                Shape shape1 = (Shape) new NormalShapeFactory().ShapeCreator(ShapeName.PLATE, shapeColor, (int) ((Math.random()) * (getWidth())), (int) ((Math.random() + 0.5) * getHeight() / 2 * -1));
                Shape shape2 = (Shape) new NormalShapeFactory().ShapeCreator(ShapeName.BALL, shapeColor, (int) ((Math.random()) * (getWidth())), (int) ((Math.random() + 0.5) * getHeight() / 4 * -1));
                moveable.add(shape2);
                moveable.add(shape1);
            }
        }
    }

    public abstract void setGame();

    @Override
    public List<GameObject> getConstantObjects() {
        return constants;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return moveable;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return controllable;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean refresh() {
        for (GameObject obj : constants) {
            ((ImageObject) obj).setVisible(true);
        }

        for (GameObject obj : controllable) {
            ((ImageObject) obj).setVisible(true);
        }
        for (GameObject obj : moveable) {
            obj.setY((obj.getY() + 1));
            if (obj.getY() == getHeight()) {
                returnToTop(obj);
            }
        }
        return true;
    }

    public void addScore() {
        List<Shape> right = new ArrayList();
        List<Shape> left = new ArrayList();
        for (int i = constants.size() - 1; i >= 0; i--) {
            GameObject object = constants.get(i);
            if (!(object instanceof Shape)) {
                continue;
            }
            if (object.getX() > controllable.get(0).getX()) {
                right.add((Shape) object);
            } else {
                left.add((Shape) object);
            }
        }
        addScore(left);
        addScore(right);
    }

    public void addScore(List<Shape> list) {
        if (canAddScore(list)) {
            score += scoreShift;
            for (int i = 0; i < 3; i++) {
                constants.remove(list.get(list.size() - 1));
            }
        }
    }

    protected boolean canAddScore(List<Shape> list) {
        if (list.size() < 3) {
            return false;
        }
        Color color1 = list.get(list.size() - 1).getColor();
        Color color2 = list.get(list.size() - 2).getColor();
        Color color3 = list.get(list.size() - 3).getColor();
        return color1 == color2 && color1 == color3;
    }

    @Override
    public String getStatus() {
        return "Score: " + score;
    }

    @Override
    public int getSpeed() {
        return SPEED;
    }

    @Override
    public int getControlSpeed() {
        return CONTROL_SPEED;
    }

    public void returnToTop(GameObject obj) {
        obj.setY(-1 * (int) (Math.random() * getHeight()) / 2);
        obj.setX((int) (Math.random() * getWidth()));
    }
}
