package CircusOfPlatesGame;

import Shapes.*;
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
    private int MAX_MOVING_OBJECTS = 5;
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
    private ImageObject RightBasePlate=new BasePlate(260, 330, "../Images/whiteplate.png",this,false);
    private ImageObject LeftBasePlate= new BasePlate(440, 330, "../Images/whiteplate.png",this,true);

    List<Shape> right = new ArrayList();
    List<Shape> left = new ArrayList();

    public GameWorld(int width, int height) {
        this.shapeNamesCollection = new ShapeColorCollection();
        this.width = width;
        this.height = height;


        setGame();
        LeftTop = LeftBasePlate;
        RightTop = RightBasePlate;
//        left.add(LeftBasePlate);
//        right.add(RightBasePlate);
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

    private void UpdateLeftHand(Shape shape) {
        left.add(shape);

        if (shape instanceof Plate) {
            PlateIndexUpdate(LeftTop, shape);
        }
        if (shape instanceof Ball) {
            BallIndexUpdate(LeftTop, shape);
        }
//if(shape instanceof Bomb|| shape instanceof IceCube)
//{
//    SpecialShapesChecker(shape);
//   return;
//}
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

    private void UpdateRightHand(Shape shape) {

        right.add(shape);
        if (shape instanceof Plate) {
            PlateIndexUpdate(RightTop, shape);
        }
        if (shape instanceof Ball) {
            BallIndexUpdate(RightTop, shape);
        }
//if(shape instanceof Bomb|| shape instanceof IceCube)
//{
//    SpecialShapesChecker(shape);
//   return;
//
//        }
        controllable.add(shape);
        moveable.remove(shape);
        RightTop = shape;
    }

    private void PlateIndexUpdate(GameObject topObject, ImageObject shape) {
       int X = topObject instanceof Plate||topObject instanceof BasePlate ? topObject.getX() : topObject.getX() - 25;
        int Y = topObject instanceof Plate||topObject instanceof BasePlate ? topObject.getY() - shape.getHeight() + 25 : topObject.getY() - shape.getHeight()+10 ;
        shape.setX(X);
        shape.setY(Y);
        shape.setConrollable(true);

    }

    private void BallIndexUpdate(GameObject topObject, ImageObject shape) {
        int X = topObject instanceof Plate||topObject instanceof BasePlate ? topObject.getX() + 25 : topObject.getX();
         int Y = topObject instanceof Plate ||topObject instanceof BasePlate? topObject.getY() - shape.getHeight() + 20: topObject.getY() - shape.getHeight() ;
        shape.setX(X);
        shape.setY(Y);
        shape.setConrollable(true);
    }

    protected void createShapes() {
        for (int i = 0; i < MAX_MOVING_OBJECTS; i++) {
            for (Iterator iter = shapeNamesCollection.getIterator(); iter.hasNext();) {
                Color shapeColor = (Color) iter.next();
                Shape shape1 = (Shape) new NormalShapeFactory().ShapeCreator(ShapeName.PLATE, shapeColor, (int) ((Math.random()) * (getWidth())), (int) ((Math.random()) * getHeight() / 2 * -1),this);
                Shape shape2 = (Shape) new NormalShapeFactory().ShapeCreator(ShapeName.BALL, shapeColor, (int) ((Math.random()) * (getWidth())), (int) ((Math.random()) * getHeight() / 4 * -1),this);
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

            ImageObject obj = (ImageObject)moveable.get(i);
            obj.setY((obj.getY() + 1));

            if (obj.getY() == getHeight()) {
                returnToTop(obj);
            }

            if (PlateCaughtByLeftHand(obj)) {
                UpdateLeftHand((Shape) obj);
               addScore();
            }
            if (PlateCaughtByRightHand(obj)) {
                UpdateRightHand((Shape) obj);
                addScore();
            }



        }
        //startState.freeze();//lesa hn3ml conditions to call freeze
        //stopState.freeze();
        return true;
    }

    public void addScore() {
        List<Shape> rightS = new ArrayList();
        List<Shape> leftS = new ArrayList();
        for (int i = constants.size() - 1; i >= 0; i--) {
            GameObject object = constants.get(i);
            if (!(object instanceof Shape)) {
                continue;
            }
            if (object.getX() > constants.get(0).getX()) {
                right.add((Shape) object);
            } else {
                left.add((Shape) object);
            }
        }
        addScore(leftS);
        addScore(rightS);
    }

    public void addScore(List<Shape> list) {
        if (canAddScore(list)) {
            score += scoreShift;
            for (int i = 1; i < 3; i++) {
//              ImageObject shape= (ImageObject) list.get(list.size()-i);
//              shape.setVisible(false);
//              list.remove(list.get(list.size()-i));
        controllable.remove(list.get(list.size()-i));
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
        obj.setX((int)(Math.random() * getWidth()));
    }



    public boolean isInLeftStack(Shape shape){
        for(int i=0;i<left.size();i++){
           if(left.get(i)==shape)
               return true;
        }
        return false;
    }
    public boolean isInRightStack(Shape shape){
        for(int i=0;i<right.size();i++){
            if(right.get(i)==shape)
                return true;
        }
        return false;
    }

}
