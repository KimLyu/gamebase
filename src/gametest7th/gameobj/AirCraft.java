/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.gameobj;

import gametest7th.controllers.SceneController;
import gametest7th.utils.CommandSolver;
import gametest7th.utils.CommandSolver.KeyListener;
import gametest7th.utils.Global.Direction;
import static gametest7th.utils.Global.*;
import gametest7th.utils.Path;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;

/**
 *
 * @author user1
 */
public class AirCraft extends GameObject implements CommandSolver.MouseCommandListener, KeyListener {

    private Image img;
    private Direction dir;

    public AirCraft(int x, int y) {
        super(x, y, 55, 55, x, y, 50, 50);

        dir = Direction.NO;
        img = SceneController.instance().irc().tryGetImage(new Path().img().actors().aircraft());
    }

    public void move() {
        switch(dir){
            case RIGHT:
                if(!touchRight()){
                    translateX(4);
                }
                break;
            case LEFT:
                if(!touchLeft()){
                    translateX(-4);
                }
                break;
            case UP:
                if(!touchTop()){
                    translateY(-4);
                }
                break;
            case DOWN:
                if(!touchBottom()){
                    translateY(4);
                }
                break;
        }
    }
    
    public void setDir(Direction dir){
        this.dir = dir;
    }

    public void changeDir(int x) {
        if (painter().left() > x) {
            dir = Direction.LEFT;
        } else {
            dir = Direction.RIGHT;
        }
    }

    @Override
    public void update() {
        move();
    }

    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
        if (state == CommandSolver.MouseState.MOVED) {
            changeDir(e.getX());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(),
                painter().width(), painter().height(), null);
    }

    @Override
    public void keyPressed(int commandCode, long trigTime) {
        switch(commandCode){
            case LEFT:
                dir = Direction.LEFT;
                break;
            case RIGHT:
                dir = Direction.RIGHT;
                break;
            case UP:
                dir = Direction.UP;
                break;
            case DOWN:
                dir = Direction.DOWN;
                break;
        }
    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {
    }

    @Override
    public void keyTyped(char c, long trigTime) {
    }
}
