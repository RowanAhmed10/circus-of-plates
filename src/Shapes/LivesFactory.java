/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Shapes;

/**
 *
 * @author Win11
 */
public class LivesFactory extends Factory{
    
      public Lives LivesCreator( int xPos, int yPos) {
    return new Lives(xPos, yPos,"../Images/heart.png");
    
}
}
