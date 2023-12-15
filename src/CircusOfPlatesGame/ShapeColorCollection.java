package CircusOfPlatesGame;

import Shapes.Color;

import java.util.ArrayList;

public class ShapeColorCollection implements ImageContainer {
    private ArrayList<Color> shapeColor=new ArrayList<>();
    private int i;

    public ShapeColorCollection() {
        for(i=0;i<4;i++){
            shapeColor.add(Color.values()[i]);
        }
    }

    @Override
    public Iterator getIterator() {
        return
                new ShapeIterator(this);
    }


    public ArrayList<Color>getShapeNames() {
        return shapeColor;
    }

}
