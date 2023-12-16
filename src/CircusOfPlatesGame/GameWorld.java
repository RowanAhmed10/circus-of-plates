package CircusOfPlatesGame;

import Shapes.Ball;
import Shapes.BasePlate;
import Shapes.Bomb;
import Shapes.Color;
import Shapes.IceCube;
import Shapes.ImageObject;
import Shapes.LivesFactory;
import Shapes.NormalShapeFactory;
import Shapes.Plate;
import Shapes.Shape;
import Shapes.ShapeName;
import Shapes.SpecialShapeFactory;
import Shapes.Star;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.ArrayList;
import java.util.List;

public abstract class GameWorld implements World {

    private int score = 0, scoreShift = 10;
    private int width;
    private int height;
    private int CONTROL_SPEED = 20;
    private int SPEED = 10;
    private int MAX_MOVING_OBJECTS = 3;
    private ShapeColorCollection shapeNamesCollection;
    protected List<GameObject> constants = new ArrayList();
    protected List<GameObject> controllable = new ArrayList();
    protected List<GameObject> moveable = new ArrayList();
    private ClownObject clown;
    ImageObject background = new ImageObject(0, 0, "../Images/background.png");
    private SpecialShapeFactory special = new SpecialShapeFactory();
    private NormalShapeFactory Normal = new NormalShapeFactory();
    private LivesFactory live = new LivesFactory();
    private GameObject LeftTop;
    private GameObject RightTop;
    StopClownState stopState;
    StartClownState startState;
    private GameObject RightBasePlate;
    private GameObject LeftBasePlate;
    private final int LeftPlateX = 260;
    private final int RightPlateX = 440;

    public GameWorld(int width, int height) {
        this.shapeNamesCollection = new ShapeColorCollection();
        this.width = width;
        this.height = height;
        LeftBasePlate = new BasePlate(260, 330, "../Images/whiteplate.png");
        RightBasePlate = new BasePlate(440, 330, "../Images/whiteplate.png");
        setGame();
        LeftTop = LeftBasePlate;
        RightTop = RightBasePlate;
        controllable.add(LeftTop);
        controllable.add(RightTop);
    }

    private boolean AreTwoObjectsIntersected(GameObject object1, GameObject object2) {
        return (Math.abs((object1.getX() + object1.getWidth() / 2) - (object2.getX() + object2.getWidth() / 2)) <= object1.getWidth())
                && (Math.abs((object1.getY() + object1.getHeight() / 2) - (object2.getY() + object2.getHeight() / 2)) <= object1.getHeight());

    }

    private boolean PlateCaughtByLeftHand(GameObject shape) // check if clown caught the object with his lefthand
    {
        return AreTwoObjectsIntersected(LeftTop, shape);
    }

    private void UpdateLeftHand(GameObject shape) {

        if (shape instanceof Plate) {
            PlateIndexUpdate(LeftTop, shape);
        }
        if (shape instanceof Ball) {
            BallIndexUpdate(LeftTop, shape);
        }
if(shape instanceof Bomb|| shape instanceof IceCube)
{
    SpecialShapesChecker(shape);
   return;
}
        controllable.add(shape);
        moveable.remove(shape);
        LeftTop = shape;
    }
public void SpecialShapesChecker(GameObject shape)
{
if (shape instanceof Bomb)
{
    //add bomb actions "decrease lives"
    
    SoundPlayer.playSound("bombsound.WAV");
    moveable.remove(shape);

}
if (shape instanceof IceCube)
        {
        //add ice cube actions
            moveable.remove(shape);
        }
if (shape instanceof Star)
        {
        //double the current score 
             SoundPlayer.playSound("starsound.WAV");
            moveable.remove(shape);
        }
}
    private boolean PlateCaughtByRightHand(GameObject shape) // check if clown caught the object with his righthand
    {
        return AreTwoObjectsIntersected(RightTop, shape);

    }

    private void UpdateRightHand(GameObject shape) {

        if (shape instanceof Plate) {
            PlateIndexUpdate(RightTop, shape);
        }
        if (shape instanceof Ball) {
            BallIndexUpdate(RightTop, shape);
        }
if(shape instanceof Bomb|| shape instanceof IceCube)
{
    SpecialShapesChecker(shape);
   return;
}
        controllable.add(shape);
        moveable.remove(shape);
        RightTop = shape;
    }

    private void PlateIndexUpdate(GameObject topObject, GameObject shape) {
        int X = topObject instanceof Plate||topObject instanceof BasePlate ? topObject.getX() : topObject.getX() - 25;
        int Y = topObject instanceof Plate||topObject instanceof BasePlate ? topObject.getY() - shape.getHeight() + 25 : topObject.getY() - shape.getHeight()+10 ;
        shape.setX(X);
        shape.setY(Y);
    }

    private void BallIndexUpdate(GameObject topObject, GameObject shape) {
        int X = topObject instanceof Plate||topObject instanceof BasePlate ? topObject.getX() + 25 : topObject.getX();
         int Y = topObject instanceof Plate ||topObject instanceof BasePlate? topObject.getY() - shape.getHeight() + 20: topObject.getY() - shape.getHeight() ;
        shape.setX(X);
        shape.setY(Y);
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
        for (int i = 0; i < moveable.size(); i++) {
            GameObject obj = moveable.get(i);
            obj.setY((obj.getY() + 1));
            if (obj.getY() == getHeight()) {
                returnToTop(obj);
            }

            if (PlateCaughtByLeftHand(obj)) {
                UpdateLeftHand(obj);
            }
            if (PlateCaughtByRightHand(obj)) {
                UpdateRightHand(obj);
            }

        }
        //startState.freeze();//lesa hn3ml conditions to call freeze
        //stopState.freeze();
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
