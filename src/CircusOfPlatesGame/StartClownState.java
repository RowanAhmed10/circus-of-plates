package CircusOfPlatesGame;

import Shapes.ImageObject;

public class StartClownState extends ClownState {

    private HardGameWorld hardGame;

    public StartClownState(HardGameWorld hardGame) {
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
        hardGame.constants.add(ClownObject.getClownInstance());
        hardGame.controllable.remove(ClownObject.getClownInstance());
        hardGame.constants.add(hardGame.getLeftBasePlate());
        hardGame.controllable.remove(hardGame.getLeftBasePlate());
        hardGame.constants.add(hardGame.getRightBasePlate());
        hardGame.controllable.remove(hardGame.getRightBasePlate());
        hardGame.constants.addAll(hardGame.right);
        hardGame.constants.addAll(hardGame.left);
        hardGame.controllable.removeAll(hardGame.right);
        hardGame.controllable.removeAll(hardGame.left);

    }

    @Override
    public void ElectricShock() {
        ClownObject.getClownInstance().setImage("electricShock.png");

        for (int i = 0; i < hardGame.moveable.size(); i++) {
            ImageObject obj = (ImageObject) hardGame.moveable.get(i);
            obj.setVisible(false);

        }
        for (int i = 1; i < hardGame.controllable.size(); i++) {
            ImageObject obj = (ImageObject) hardGame.controllable.get(i);
            obj.setVisible(false);

        }

    }

}
