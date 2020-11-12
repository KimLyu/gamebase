/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.gameobj;

import gametest7th.controllers.ImgController;
import gametest7th.controllers.SceneController;
import gametest7th.utils.Delay;
import gametest7th.utils.Path;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author user1
 */
public class CBoom extends GameObject {

    public enum State {
        NORMAL,
        BOOM,
        REMOVED
    }

    private Image img1, img2;
    private State state;
    private Delay delay;

    public CBoom(int x, int y) {
        super(x, y, 30, 30);
        state = State.NORMAL;
        img1 = SceneController.instance().irc().tryGetImage(new Path().img().objs().boom());
        img2 = SceneController.instance().irc().tryGetImage(new Path().img().objs().boom2());
        delay = new Delay(30);
    }

    @Override
    public void paintComponent(Graphics g) {
        switch (state) {
            case NORMAL:
                g.drawImage(img1,
                        painter().left(),
                        painter().top(),
                        painter().width(),
                        painter().height(), null);
                break;
            case BOOM:
                g.drawImage(img2,
                        painter().left(),
                        painter().top(),
                        painter().width(),
                        painter().height(), null);
                break;
        }
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public void update() {
        if (state == State.NORMAL) {
            translateY(-8);
        } else if (state == State.BOOM) {
            delay.play();
        }

        if (delay.count()) {
            state = State.REMOVED;
        }
    }

}
