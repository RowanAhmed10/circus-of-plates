package CircusOfPlatesGame;

import Shapes.ImageObject;
import Shapes.Lives;



public class StopClownState extends ClownState {

    private HardGameWorld hardGame;

    public StopClownState(HardGameWorld hardGame) {
        this.hardGame = hardGame;
    }

    public HardGameWorld getHardGame() {
        return hardGame;
    }

    public void setHardGame(HardGameWorld hardGame) {
        this.hardGame = hardGame;
    }

    @Override
    public void freeze() {
       
        hardGame.controllable.add(ClownObject.getClownInstance());
        hardGame.constants.remove(ClownObject.getClownInstance());
        hardGame.controllable.add(hardGame.getLeftBasePlate());
        hardGame.constants.remove(hardGame.getLeftBasePlate());
        hardGame.controllable.add(hardGame.getRightBasePlate());
        hardGame.constants.remove(hardGame.getRightBasePlate());
        hardGame.controllable.addAll(hardGame.right);
        hardGame.controllable.addAll(hardGame.left);
        hardGame.constants.removeAll(hardGame.right);
        hardGame.constants.removeAll(hardGame.left);
       
             
      
        }

    @Override
   public void ElectricShock() {
    ClownObject.getClownInstance().SetImage("../Images/clown.png");
    
     for (int i = 0; i < hardGame.moveable.size(); i++) {
           ImageObject obj = (ImageObject) hardGame.moveable.get(i);
           obj.setVisible(true);
          
     }
     
      for (int i =0; i < hardGame.controllable.size(); i++) {
           ImageObject obj = (ImageObject) hardGame.controllable.get(i);
           obj.setVisible(true);
         
     }
    }
    }

