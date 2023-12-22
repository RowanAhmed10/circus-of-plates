package CircusOfPlatesGame;

import eg.edu.alexu.csd.oop.game.GameObject;

public class MovableIterator implements Iterator {

    private MoveableCollection moveableCollection;
    private int i;

    public MovableIterator(MoveableCollection moveableCollection) {
        this.moveableCollection = moveableCollection;
        this.i = 0;
    }

    @Override
    public boolean hasNext() {
        return i < moveableCollection.getMoveable().size();
    }

    @Override
    public Object next() {
        return moveableCollection.getMoveable().get(i++);
    }

    @Override
    public void add(GameObject obj) {
        moveableCollection.getMoveable().add(obj);

    }

    @Override
    public void remove(GameObject obj) {
        moveableCollection.getMoveable().remove(obj);
    }
}
