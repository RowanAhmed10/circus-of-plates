package CircusOfPlatesGame;

import Frontend.GameOver;
import Frontend.MainMenu;
import Shapes.*;
import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;

public abstract class GameWorld implements World {

    private int score = 0;
    private final int scoreShift = 10;
    private final int width;
    private final int height;
    private final int CONTROL_SPEED = 20;
    private final int SPEED = 10;
    private final int MAX_MOVING_OBJECTS = 5;
    private final ShapeColorCollection shapeNamesCollection;
    protected List<GameObject> constants = new ArrayList();
    protected List<GameObject> controllable = new ArrayList();
    protected List<GameObject> moveable = new ArrayList();
    protected ImageObject background = new ImageObject(0, 0, "../Images/background.png");
    private SpecialShapeFactory special = new SpecialShapeFactory();
    private NormalShapeFactory normal = new NormalShapeFactory();
    private LivesFactory live = new LivesFactory();
    protected StopClownState stopState;
    protected StartClownState startState;
    private final Shape rightBasePlate = new BasePlate(260, 330, "../Images/whiteplate.png", this, false);
    private final Shape leftBasePlate = new BasePlate(440, 330, "../Images/whiteplate.png", this, true);
    private GameOver gameOver = new GameOver();
    private MainMenu menu;
    private CountDownTimer countDown = new CountDownTimer();
    private boolean isFrozen=false;
   
    private Timer timer = new Timer();
    private TimerTask endGame = new TimerTask() {
        @Override
        public void run() {
          endGame();
      }};
    

    List<Shape> right = new ArrayList();
    List<Shape> left = new ArrayList();

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

    
    private boolean areTwoObjectsIntersected(GameObject object1, GameObject object2) {
        return (Math.abs((object1.getX() + object1.getWidth() / 2) - (object2.getX() + object2.getWidth() / 2)) <= object1.getWidth())
                && (Math.abs((object1.getY() + object1.getHeight() / 2) - (object2.getY() + object2.getHeight() / 2)) <= object1.getHeight());
    }

    private boolean plateCaughtByLeftHand(GameObject shape) // check if clown caught the object with his lefthand
    {
        return areTwoObjectsIntersected(left.get(left.size() - 1), shape);
    }

    private void updateHand(List<Shape> list, GameObject shape) {
        GameObject top = list.get(list.size() - 1);


        if (shape instanceof Plate) {
            list.add((Shape)shape);
            plateIndexUpdate(top, (ImageObject)shape);
            if(shape.getY()==0){
                endGame();
            }
        }
       else if (shape instanceof Ball) {
            list.add((Shape)shape);
            ballIndexUpdate(top,(ImageObject) shape);
             if(shape.getY()==0){
                endGame();
            }
        }
       else if(shape instanceof Bomb|| shape instanceof Shock)
{
    specialShapesChecker(shape);
   return;
}
        if(isFrozen==true){
            if(plateCaughtByRightHand(shape)){
                right.add((Shape) shape);
            }
         constants.add(shape);
          
        }
        else
        {controllable.add(shape);}
        moveable.remove(shape);
    }

    public void specialShapesChecker(GameObject shape) {
        if (shape instanceof Bomb) {
            //add bomb actions "decrease lives"
            
            SoundPlayer.playSound("bombsound.WAV");
           ((Bomb) shape).setVisible(false);
            moveable.remove(shape);
            System.out.println(constants.size());
            if(constants.size()>2) {

                ImageObject heart = (ImageObject) constants.get(constants.size() - 1);
                heart.setVisible(false);
                constants.remove(constants.size() - 1);
            }
            else{
                   endGame(); 
               }}
        if (shape instanceof Shock) {
            
             SoundPlayer.playSound("electricShock.WAV");
              isFrozen = true;
              startState.ElectricShock();
            
              Timer t=new Timer(); 
              TimerTask task = new TimerTask(){
                  @Override
                  public void run() {
                      isFrozen=false;
                     stopState.ElectricShock();
                    
                     
                  }};
                      t.schedule(task,5000);
            
                  moveable.remove(shape);
          }
        if(shape instanceof IceCube){
            //incase 3malna el ice
        }
        
        
        
        if (shape instanceof Star) {
            //double the current score 
            SoundPlayer.playSound("starsound.WAV");
            moveable.remove(shape);
        }
    }
       private void StarAction(GameObject shape)
       {
       moveable.remove(shape);
       SoundPlayer.playSound("starSound.WAV");
       score=score*2; 
      
       }

    private boolean plateCaughtByRightHand(GameObject shape) // check if clown caught the object with his righthand
    {
        return areTwoObjectsIntersected(right.get(right.size() - 1), shape);

    }

    private void plateIndexUpdate(GameObject topObject, ImageObject shape) {
        int X = topObject instanceof Plate || topObject instanceof BasePlate ? topObject.getX() : topObject.getX() - 25;
        int Y = topObject instanceof Plate || topObject instanceof BasePlate ? topObject.getY() - shape.getHeight() + 25 : topObject.getY() - shape.getHeight() + 10;
        shape.setX(X);
        shape.setY(Y);
        shape.setConrollable(true);
    }

    private void ballIndexUpdate(GameObject topObject, ImageObject shape) {
        int X = topObject instanceof Plate || topObject instanceof BasePlate ? topObject.getX() + 25 : topObject.getX();
        int Y = topObject instanceof Plate || topObject instanceof BasePlate ? topObject.getY() - shape.getHeight() + 20 : topObject.getY() - shape.getHeight();
        shape.setX(X);
        shape.setY(Y);
        shape.setConrollable(true);
    }

    protected void createShapes() {
        for (int i = 0; i < MAX_MOVING_OBJECTS; i++) {
            for (Iterator iter = shapeNamesCollection.getIterator(); iter.hasNext();) {
                Color shapeColor = (Color) iter.next();
                Shape shape1 = (Shape) new NormalShapeFactory().ShapeCreator(ShapeName.PLATE, shapeColor, (int) ((Math.random()) * (getWidth())), (int) ((Math.random()) * getHeight() / 2 * -1), this);
                Shape shape2 = (Shape) new NormalShapeFactory().ShapeCreator(ShapeName.BALL, shapeColor, (int) ((Math.random()) * (getWidth())), (int) ((Math.random()) * getHeight() / 4 * -1), this);
                moveable.add(shape2);
                moveable.add(shape1);
            }
        }
    }
    
    public void endGame(){
         menu.pause.doClick();
         gameOver.setVisible(true);
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

  if (isFrozen==false)
{
        for (GameObject obj : controllable) {
            ((ImageObject) obj).setVisible(true);

        }
        for (int i = 0; i < moveable.size(); i++) {

            ImageObject obj = (ImageObject) moveable.get(i);
            obj.setY((obj.getY() + 1));

            if (obj.getY() == getHeight()) {
                returnToTop(obj);
            }

            if (plateCaughtByLeftHand(obj)) {

                    updateHand(left, obj);
                    addScore();

            }
            if (plateCaughtByRightHand(obj)) {

                    updateHand(right, (obj));
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
        
        int x=countDown.getTime();
        if(x<=0){
           x=0; 
        }
        return "Score: " + score+"  Time: " + x+"s";
        
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
}
