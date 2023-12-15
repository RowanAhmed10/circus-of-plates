/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CircusOfPlatesGame;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.GameEngine.GameController;
import java.awt.Color;

/**
 *
 * @author nourayman
 */
public class StartGame {
    final GameController gamecontroller = GameEngine.start("Circus of Plates",new GameWorld(800,800,1),Color.BLACK);
    
}
