package CircusOfPlatesGame;

import CircusOfPlatesGame.Container;
import CircusOfPlatesGame.Iterator;
import CircusOfPlatesGame.MovableIterator;
import eg.edu.alexu.csd.oop.game.GameObject;


import java.util.ArrayList;
import java.util.List;

public class MoveableCollection implements Container {
    private List<GameObject> moveable;

    public MoveableCollection(List<GameObject> moveable) {
        this.moveable = moveable;
    }

    public List<GameObject> getMoveable() {
        return moveable;
    }

    @Override
    public Iterator getIterator() {
        return new MovableIterator(this);
    }
}
