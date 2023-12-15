/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CircusOfPlatesGame;


import Shapes.ImageObject;

//3adlt 3leh ya ranim 
public final class ClownObject extends ImageObject{


    private static ClownObject clownInstance = new ClownObject(290,265,"../Images/clown.png");//ghyrt pos
    
//Modify the Clown's x and y position 
   
    public static ClownObject getClownInstance() { //wasn't static
        if(clownInstance==null){//added el if
        clownInstance = new ClownObject(100,200,"../Images/clown.png.png");
        }
      return clownInstance;
    }

    private ClownObject(int posX, int posY, String path) {
        super(posX, posY, path);
    }

    @Override
    public void setY(int y) {
   
    }
    // Clown moves horizontally only 
    // gonna set x  in the controller
}
