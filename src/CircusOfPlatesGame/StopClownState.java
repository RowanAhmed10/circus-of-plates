package CircusOfPlatesGame;



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
    }

