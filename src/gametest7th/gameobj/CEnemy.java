/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.gameobj;

import gametest7th.controllers.ImgController;
import gametest7th.controllers.SceneController;
import gametest7th.utils.Path;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author user1
 */
public class CEnemy extends GameObject {

    private Image img;
    private int d;

    public CEnemy(int x, int y, int d) {
        super(x, y, 50, 50);
        this.d = d;
        img = SceneController.instance().irc().tryGetImage(new Path().img().actors().enemy());
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, painter().left(), painter().top(),
                painter().width(), painter().height(), null);
    }

    @Override
    public void update() {
        translateY(4);
        translateX(d);
    }
}
