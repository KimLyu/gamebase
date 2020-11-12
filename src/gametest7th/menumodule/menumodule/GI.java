package gametest7th.menumodule.menumodule;


import gametest7th.controllers.SceneController;
import gametest7th.menumodule.scene.MenuScene;
import gametest7th.utils.*;
import gametest7th.utils.CommandSolver.KeyListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GI implements GameKernel.GameInterface, CommandSolver.MouseCommandListener, CommandSolver.KeyListener {

    public GI() throws IOException {
        SceneController.instance().change(new MenuScene());
    }

    @Override
    public void paint(Graphics g) { //繪畫
        SceneController.instance().paint(g);
    }

    @Override
    public void update() { //邏輯
        SceneController.instance().update();
    }

    @Override
    public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
        if (SceneController.instance().mouseListener() != null) {
            SceneController.instance().mouseListener().mouseTrig(e, state, trigTime);
        }
    }

    @Override
    public void keyPressed(int commandCode, long trigTime) {
        KeyListener kl = SceneController.instance().keyListener();
        if (kl != null) {
            kl.keyPressed(commandCode, trigTime);
        }
    }

    @Override
    public void keyReleased(int commandCode, long trigTime) {
        KeyListener kl = SceneController.instance().keyListener();
        if (kl != null) {
            kl.keyReleased(commandCode, trigTime);
        }
    }

    @Override
    public void keyTyped(char c, long trigTime) {
        if (SceneController.instance().keyListener() != null) {
            SceneController.instance().keyListener().keyTyped(c, trigTime);
        }
    }

}
