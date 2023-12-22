package CircusOfPlatesGame;

import eg.edu.alexu.csd.oop.game.GameObject;

public class ShapeIterator implements Iterator {

    private int i;
    private ShapeColorCollection shapeColor;

    public ShapeIterator(ShapeColorCollection shapeColor) {
        this.shapeColor = shapeColor;
        this.i = 0;
    }

    @Override
    public boolean hasNext() {
        return (i < shapeColor.getShapeNames().size());
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return shapeColor.getShapeNames().get(i++);
        }
        return null;
    }

    @Override
    public void add(GameObject obj) {

    }

    @Override
    public void remove(GameObject obj) {

    }
}
