package gametest7th;

import gametest7th.utils.GameKernel;
import gametest7th.utils.Global;
import static gametest7th.utils.Global.*;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author user1
 */
public class GameTest7th {

    public static void main(String[] args) {
        JFrame jf = new JFrame();// 遊戲視窗本體
        jf.setTitle("Game Test 7th");// 視窗標題
        jf.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);// 視窗大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 關閉視窗時關閉程式

        int[][] command = {
            {KeyEvent.VK_LEFT, Global.LEFT},
            {KeyEvent.VK_RIGHT, Global.RIGHT},
            {KeyEvent.VK_UP, Global.UP},
            {KeyEvent.VK_DOWN, Global.DOWN},
            {KeyEvent.VK_SPACE, Global.SPACE},
            {KeyEvent.VK_A, Global.A},
            {KeyEvent.VK_D, Global.D}
        };
        
        GI gi = new GI();// 遊戲的本體(邏輯 + 畫面處理)
        
        GameKernel gk = new GameKernel.Builder(gi, LIMIT_DELTA_TIME, NANOSECOND_PER_UPDATE)
                .initListener(command)
                .enableKeyboardTrack(gi)
                .keyTypedMode()
                //                .enableMouseTrack(gi)
                .gen();

        jf.add(gk);

        jf.setVisible(true);
        gk.run(IS_DEBUG);
        System.out.print("HiHi");
    }

}
