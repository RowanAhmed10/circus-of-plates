package CircusOfPlatesGame;

import Frontend.GameOver;
import Frontend.MainMenu;
import Shapes.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class GameWorld implements World {

    private int score = 0;
    private final int scoreShift = 10;
    private final int width;
    private final int height;
    private final int CONTROL_SPEED = 30;
    private final int SPEED = 10;
    private final int MAX_MOVING_OBJECTS = 5;
    private final ShapeColorCollection shapeNamesCollection;
    protected List<GameObject> constants = new ArrayList();
    protected List<GameObject> controllable = new ArrayList();
    protected List<GameObject> moveable = new ArrayList();
    protected ImageObject background = new ConstantsFactory().ConstantsCreator(ConstantsNames.BACKGROUND, 0, 0, this);
    protected StopClownState stopState;
    protected StartClownState startState;
    private final Shape rightBasePlate = new BasePlate(260, 420, "whiteplate.png", this, false);
    private final Shape leftBasePlate = new BasePlate(440, 420, "whiteplate.png", this, true);
    private GameOver gameOver = new GameOver();
    private MainMenu menu;
    private CountDownTimer countDown = new CountDownTimer();
    private boolean isShocked = false;

    private Timer timer = new Timer();
    private TimerTask endGame = new TimerTask() {
        @Override
        public void run() {
            endGame();
        }
    };

    List<Shape> right = new ArrayList();
    List<Shape> left = new ArrayList();
    MoveableCollection moveableCollection = new MoveableCollection(moveable);

    public GameWorld(int width, int height) {
        this.shapeNamesCollection = new ShapeColorCollection();
        this.width = width;
        this.height = height;
        setGame();
        left.add(leftBasePlate);
        right.add(rightBasePlate);
        controllable.add(leftBasePlate);
        controllable.add(rightBasePlate);
        timer.schedule(endGame, 60000);
        try {
            countDown.secondsPassed();
        } catch (InterruptedException ex) {
            Logger.getLogger(GameWorld.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean areTwoObjectsIntersected(GameObject object1, GameObject object2) {
        return (Math.abs((object1.getX() + object1.getWidth() / 2) - (object2.getX() + object2.getWidth() / 2)) <= object1.getWidth())
                && (Math.abs((object1.getY() + object1.getHeight() / 2) - (object2.getY() + object2.getHeight() / 2)) <= object1.getHeight());
    }

    private boolean catchShapeByLeftHand(GameObject shape) {
        return areTwoObjectsIntersected(left.get(left.size() - 1), shape);
    }

    private boolean catchShapeByRightHand(GameObject shape) {
        return areTwoObjectsIntersected(right.get(right.size() - 1), shape);
    }

    private void updateHand(List<Shape> list, GameObject shape) {
        CatchCommand command = null;
        if (shape instanceof Plate) {
            command = new CatchPlateCommand(this, (Plate) shape, list);
        } else if (shape instanceof Ball) {
            command = new CatchBallCommand(this, (Ball) shape, list);
        } else if (shape instanceof Bomb) {
            command = new CatchBombCommand(this, (Bomb) shape);
        } else if (shape instanceof Shock) {
            command = new CatchShockCommand(this, (Shock) shape);
        } else {
            command = new CatchStarCommand(this, (Star) shape);
        }
        command.execute();
    }

    public void updatePlateIndex(GameObject topObject, ImageObject shape) {
        int X = topObject instanceof Plate || topObject instanceof BasePlate ? topObject.getX() : topObject.getX() - 25;
        int Y = topObject instanceof Plate || topObject instanceof BasePlate ? topObject.getY() - shape.getHeight() + 25 : topObject.getY() - shape.getHeight() + 10;
        shape.setX(X);
        shape.setY(Y);
        shape.setControllable(true);
    }

    public void updateBallIndex(GameObject topObject, ImageObject shape) {
        int X = topObject instanceof Plate || topObject instanceof BasePlate ? topObject.getX() + 25 : topObject.getX();
        int Y = topObject instanceof Plate || topObject instanceof BasePlate ? topObject.getY() - shape.getHeight() + 20 : topObject.getY() - shape.getHeight();
        shape.setX(X);
        shape.setY(Y);
        shape.setControllable(true);
    }

    protected void createShapes() {
        for (int i = 0; i < MAX_MOVING_OBJECTS; i++) {
            for (Iterator iter = shapeNamesCollection.getIterator(); iter.hasNext();) {
                Color shapeColor = (Color) iter.next();
                Shape shape1 = (Shape) new NormalShapeFactory().ShapeCreator(ShapeName.PLATE, shapeColor, getRandomX(), getRandomY(), this);
                Shape shape2 = (Shape) new NormalShapeFactory().ShapeCreator(ShapeName.BALL, shapeColor, getRandomX(), getRandomY(), this);
//                resolveIntersection(shape1);
                moveableCollection.getIterator().add(shape1);
//                resolveIntersection(shape2);
                moveableCollection.getIterator().add(shape2);
            }
        }
    }

    protected int getRandomX() {
        int margin = 30;
        return (int) ((Math.random()) * (getWidth() - 2 * margin)) + margin;
    }

    protected int getRandomY() {
        return (int) ((Math.random()) * getHeight() * -1);
    }

    public void endGame() {
        menu.pause.doClick();
        gameOver.setVisible(true);
        SoundPlayer.stopSound("circusMusic.WAV");
    }

//    public void alterPosition(GameObject shape) {
////int counter=0;
//        while (isIntersectingWithMovable(shape)) {
//            shape.setX((int) (Math.random() * getWidth()));
//            shape.setY((int) (Math.random() * getHeight() / 2 * -1));
//        }
////            counter++;
////            if(counter>1000)
////                shape.setX((int) (Math.random() * getWidth()));
////            shape.setY((int) (Math.random() * getHeight() / 2 * -1));
////                break;
////        }
//    }
//    public void resolveIntersection(GameObject obj) {
//        if (isIntersectingWithMovable(obj)) {
//            System.out.println("Intersected with a moving object");
//            alterPosition(obj);
//        }
//    }
    public abstract void setGame();

    @Override
    public boolean refresh() {

        for (GameObject obj : constants) {
            ((ImageObject) obj).setVisible(true);
        }

        if (isShocked == false) {
            for (GameObject obj : controllable) {
                ((ImageObject) obj).setVisible(true);
            }
            for (Iterator iter = moveableCollection.getIterator(); iter.hasNext();) {
                ImageObject obj = (ImageObject) iter.next();
                obj.setY((obj.getY() + 1));

                if (obj.getY() == getHeight()) {
                    returnToTop(obj);
                }
                if (catchShapeByLeftHand(obj)) {
                    updateHand(left, obj);
                    addScore();
                }
                if (catchShapeByRightHand(obj)) {
                    updateHand(right, obj);
                    addScore();
                }
            }
            gameOver.setScore(this.score);
        }
        return true;
    }

    public void addScore() {
        addScore(left);
        addScore(right);
    }

    public void addScore(List<Shape> list) {
        if (canAddScore(list)) {
            score += scoreShift;
            for (int i = 1; i <= 3; i++) {
                Shape shape = list.get(list.size() - 1);
                shape.setVisible(false);
                list.remove(shape);
                controllable.remove(shape);
            }
        }
    }

//    public boolean isIntersectingWithMovable(GameObject shape) {
//        for (Iterator iter = moveableCollection.getIterator(); iter.hasNext();) {
//            if (areTwoObjectsIntersected(shape, (GameObject) iter.next())) {
//                return true;
//            }
//        }
//        return false;
//    }
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
        int x = countDown.getTime();
        if (x <= 0) {
            x = 0;
        }
        return "Score: " + score + "  Time: " + x + "s";
    }

    public void returnToTop(GameObject obj) {
        moveable.remove(obj);
        obj.setY(-1 * (int) (Math.random() * getHeight()) / 8);
        obj.setX(getRandomX());
//        resolveIntersection(obj);
        moveable.add(obj);
    }

    public boolean isInLeftStack(Shape shape) {
        for (int i = 0; i < left.size(); i++) {
            if (left.get(i) == shape) {
                return true;
            }
        }
        return false;
    }

    public boolean isInRightStack(Shape shape) {
        for (int i = 0; i < right.size(); i++) {
            if (right.get(i) == shape) {
                return true;
            }
        }
        return false;
    }

    public Shape getRightBasePlate() {
        return rightBasePlate;
    }

    public Shape getLeftBasePlate() {
        return leftBasePlate;
    }

    public CountDownTimer getCountDown() {
        return countDown;
    }

    public TimerTask getEndGame() {
        return endGame;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setMenu(MainMenu menu) {
        this.menu = menu;
    }

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
    public int getSpeed() {
        return SPEED;
    }

    @Override
    public int getControlSpeed() {
        return CONTROL_SPEED;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setIsShocked(boolean isShocked) {
        this.isShocked = isShocked;
    }

}
