package gametest7th.menumodule.menumodule;

import com.sun.glass.events.KeyEvent;
import gametest7th.controllers.SceneController;
import java.awt.Color;
import java.awt.Font;
import gametest7th.utils.GameKernel;
import java.io.IOException;
import javax.swing.JFrame;
import gametest7th.menumodule.menu.*;
import gametest7th.utils.Global;

public class Main {

    public static void main(String[] args) throws IOException {
        initTheme();
        JFrame jf = new JFrame();
        jf.setSize(1024, 760);
        jf.setTitle("MenuModule");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int[][] commands = {{KeyEvent.VK_ENTER, 0},
        {KeyEvent.VK_DEAD_CIRCUMFLEX, 1}};

        GI gi = new GI();
        GameKernel gk = new GameKernel.Builder(gi, Global.LIMIT_DELTA_TIME, Global.NANOSECOND_PER_UPDATE)
                .initListener(commands)
                .mouseForceRelease()
                .enableMouseTrack(gi)
                .keyTypedMode()
                .trackChar()
                .enableKeyboardTrack(gi)
                .gen();

        jf.add(gk);
        jf.setVisible(true);
        gk.run(true);
    }

    private static void initTheme() {
        Style simple = new Style.StyleOval(100, 100, true, new BackgroundType.BackgroundColor(Color.YELLOW))
                .setTextColor(new Color(128, 128, 128))
                .setHaveBorder(true)
                .setBorderColor(new Color(255, 215, 0))
                .setBorderThickness(5)
                .setTextFont(new Font("", Font.TYPE1_FONT, 30))
                .setText("Press");
        Style aa = new Style.StyleOval(100, 100, true, new BackgroundType.BackgroundColor(new Color(184, 134, 11)))
                .setTextColor(Color.BLACK)
                .setHaveBorder(true)
                .setBorderColor(new Color(230, 184, 0))
                .setBorderThickness(5)
                .setTextFont(new Font("", Font.TYPE1_FONT, 28))
                .setText("Press");
        Style im = new Style.StyleOval(100, 100, new BackgroundType.BackgroundImage(SceneController.instance().irc().tryGetImage("/gametest7th/menumodule/grass.png")))
                .setHaveBorder(true)
                .setBorderColor(Color.WHITE)
                .setBorderThickness(5)
                .setTextFont(new Font("", Font.TYPE1_FONT, 30))
                .setText("Press");

        Theme.add(new Theme(im, simple, aa));
    }
}
