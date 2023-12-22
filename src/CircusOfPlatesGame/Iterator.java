package CircusOfPlatesGame;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.font.GlyphMetrics;

public interface Iterator {

    public boolean hasNext();

    public Object next();
    public void add(GameObject obj);
    public void remove(GameObject obj);
}
