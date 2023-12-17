/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shapes;

import CircusOfPlatesGame.ClownObject;
import CircusOfPlatesGame.GameWorld;

/**
 *
 * @author Win11
 */
public class BasePlate extends Constants{
    private GameWorld world;
    private boolean isLeft;


    public BasePlate(int posX, int posY, String path, GameWorld world,boolean left) {
        super(posX, posY, path);
        this.world=world;
        isLeft=left;
    }

    @Override
    public void setY(int y) {

    }

    @Override
    public void setX(int x) {
        int shift=isLeft?-30:150;
        ClownObject clown=ClownObject.getClownInstance();


                super.setX(clown.getX()+shift);


        }




}
