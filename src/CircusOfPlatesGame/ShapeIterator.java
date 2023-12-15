package CircusOfPlatesGame;
public class ShapeIterator implements Iterator{
    private int i;

    public ShapeIterator(ShapeColorCollection shapeColor) {
        this.shapeColor = shapeColor;
        this.i=0;
    }

    private ShapeColorCollection shapeColor= new ShapeColorCollection();
    @Override
    public boolean hasNext() {
        return (i < shapeColor.getShapeNames().size());
    }

    @Override
    public Object next() {
        if(this.hasNext()){
            return shapeColor.getShapeNames().get(i++);
        }
        return null;
    }

}


