/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package CircusOfPlatesGame;

import static CircusOfPlatesGame.ClownObject.getClownInstance;
import java.util.Collections;
import Shapes.*;
import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import java.util.ArrayList;
import java.util.List;
import Shapes.NormalShapeFactory;
import Shapes.SpecialShapeFactory;


public class GameWorld implements World {
    
    private int level;
    private int score=0;
    private int width;
    private int height;
    private int CONTROL_SPEED=10;
    private int SPEED=10;
    private int MAX_MOVING_OBJECTS=3;
    private ShapeColorCollection shapeNamesCollection;
    private ClownObject clown;
    private ArrayList<GameObject> constants = new ArrayList();
    private ArrayList<GameObject> controllable = new ArrayList();
    private ArrayList<GameObject> moveable = new ArrayList();

    ImageObject background = new ImageObject(0,0,"../Images/background.png");
    SpecialShapeFactory special = new SpecialShapeFactory();

    public GameWorld(int width,int height,int level) {
        this.shapeNamesCollection=new ShapeColorCollection();
        this.width=width;
        this.height=height;
        this.level=level;
        setGame();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    
    
    //bashof which level w fill arraylist
    //add movable and controllable
    public void setGame(){
        for (int i = 0; i < MAX_MOVING_OBJECTS; i++) {

            for (Iterator iter = shapeNamesCollection.getIterator() ; iter.hasNext();) {
                Color shapeColor = (Color) iter.next();

                Shape shape1=(Shape)new NormalShapeFactory().ShapeCreator(ShapeName.PLATE,shapeColor,(int)((Math.random())*(getWidth())),(int)((Math.random()+0.5)*getHeight()/2*-1));
                Shape shape2=(Shape)new NormalShapeFactory().ShapeCreator(ShapeName.BALl,shapeColor,(int)((Math.random())*(getWidth())),(int)((Math.random()+0.5)*getHeight()/4*-1));
                moveable.add(shape2);
                moveable.add(shape1);
            }
        }
        if(level==0){
            constants.add(background);
            controllable.add(clown=getClownInstance());

        }
        if(level==1){
            constants.add(background);
            constants.add(special.SpecialPieceCreator(SpecialShapeName.HEART, 5, 10));
            constants.add(special.SpecialPieceCreator(SpecialShapeName.HEART, 45, 10));
            constants.add(special.SpecialPieceCreator(SpecialShapeName.HEART, 85, 10));
            controllable.add(clown=getClownInstance());
            for(int i=0;i<5;i++){
                SpecialShape specialShape =(SpecialShape) new SpecialShapeFactory().SpecialPieceCreator(SpecialShapeName.BOMB,(int)((Math.random())*(getWidth())),(int)(Math.random())*getHeight()/2*-1);
                moveable.add(specialShape);
            }
        Collections.shuffle(moveable);

        }
        if(level==2){
            constants.add(background);
            controllable.add(clown=getClownInstance());
            constants.add(special.SpecialPieceCreator(SpecialShapeName.HEART, 5, 10));
            constants.add(special.SpecialPieceCreator(SpecialShapeName.HEART, 45, 10));
            constants.add(special.SpecialPieceCreator(SpecialShapeName.HEART, 85, 10));
            for(int i=0;i<5;i++){
                SpecialShape specialShape1 =(SpecialShape) new SpecialShapeFactory().SpecialPieceCreator(SpecialShapeName.BOMB,(int)(Math.random()*(getWidth())),(int)(Math.random()*getHeight()/2*-1));
                moveable.add(specialShape1);
                SpecialShape specialShape2 =(SpecialShape) new SpecialShapeFactory().SpecialPieceCreator(SpecialShapeName.ICE,(int)(Math.random()*(getWidth())),(int)(Math.random()*getHeight()/2*-1));
                moveable.add(specialShape2);
            }
            Collections.shuffle(moveable);

        }
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constants;
 }

    @Override
    public ArrayList<GameObject> getMovableObjects() {
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
        for(GameObject obj:constants){
            ((ImageObject)obj).setVisible(true);
        }
        
         for(GameObject obj:controllable){
            ((ImageObject)obj).setVisible(true);
        }

         for(GameObject obj :moveable){
             obj.setY((obj.getY() + 1));
             if (obj.getY() == getHeight()) {
               returnToTop(obj);
             }

         }
    return true;
    }

    @Override
    public String getStatus() {
        return "Score: "+score;
   }

    @Override
    public int getSpeed() {
     return SPEED;
    }

    @Override
    public int getControlSpeed() {
     return CONTROL_SPEED;
    }
    public void returnToTop(GameObject obj){
        obj.setY(-1 * (int) (Math.random() * getHeight())/2);
        obj.setX((int) (Math.random() * getWidth()));
    }

}
