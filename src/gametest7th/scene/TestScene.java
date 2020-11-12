package gametest7th.scene;

import gametest7th.camera.Camera;
import gametest7th.camera.MapInformation;
import gametest7th.camera.SmallMap;
import gametest7th.controllers.SceneController;
import gametest7th.gameobj.AirCraft;
import gametest7th.gameobj.CBoom;
import gametest7th.gameobj.CEnemy;
import gametest7th.gameobj.GameObject;
import gametest7th.gameobj.Map;
import gametest7th.utils.CommandSolver;
import gametest7th.utils.Global;
import static gametest7th.utils.Global.*;
import gametest7th.utils.Path;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class TestScene extends Scene {

    private AirCraft ac;
    private ArrayList<CEnemy> arr;
    private ArrayList<CBoom> ammo;
    private ArrayList<Camera> camArr;
    private Map mapImg;
    private SmallMap smallMap;

    @Override
    public void sceneBegin() {
        mapImg = new Map();
        ac = new AirCraft(0, 0);
        arr = new ArrayList<>();
        ammo = new ArrayList<>();
        camArr = new ArrayList<>();
        camArr.add(new Camera.Builder(700, 440).setChaseObj(ac).gen());
        smallMap = new SmallMap(new Camera.Builder(800, 600).setCameraWindowLocation(625, 440).setChaseObj(null).setCameraStartLocation(0, 0).gen(), 0.2, 0.2);
    }

    @Override
    public void sceneEnd() {
        ac = null;
        arr = null;
        ammo = null;
        camArr = null;
        smallMap = null;
        SceneController.instance().irc().clear();
    }

    @Override
    public void paint(Graphics g) {
        for (int k = 0; k < camArr.size(); k++) {
            Camera cam = camArr.get(k);
            cam.start(g);
            if (cam.isCollision(mapImg)) {
                mapImg.paint(g);
            }
            if (cam.isCollision(ac)) {
                ac.paint(g);
            }
            for (int i = 0; i < arr.size(); i++) {
                if (cam.isCollision(arr.get(i))) {
                    arr.get(i).paint(g);
                }
            }
            for (int i = 0; i < ammo.size(); i++) {
                if (cam.isCollision(ammo.get(i))) {
                    ammo.get(i).paint(g);
                }
            }
            cam.paint(g);
            cam.end(g);
            g.setColor(Color.red);
            int x = cam.cameraWindowX() + 20;
            int y = cam.cameraWindowY() + 20;
            g.drawString("這是第" + (k + 1) + " 個camera!!!", x, y);
        }
        smallMap.start(g);
        if (smallMap.isCollision(mapImg)) {
            mapImg.paint(g);
        }
        if (smallMap.isCollision(ac)) {
            // 使用接口Paint自定義畫出的方式
            SmallMap.Paint tmp = (Graphics g1, GameObject obj) -> {
                Color c = smallMap.getColor("" + obj.getClass());
                    g.setColor(c);
                    g.fillOval(obj.painter().left(), obj.painter().top(), 100, 100);
                    g.setColor(Color.BLACK);
            };
            tmp.paint(g, ac);
        }
        for (int i = 0; i < arr.size(); i++) {
            if (smallMap.isCollision(arr.get(i))) {
                smallMap.paint(g, arr.get(i), SceneController.instance().irc().tryGetImage(new Path().img().actors().enemy()), 50, 50);
            }
        }
        for (int i = 0; i < ammo.size(); i++) {
            if (smallMap.isCollision(ammo.get(i))) {
                smallMap.paint(g, ammo.get(i), Color.GREEN);
            }
        }
        smallMap.paint(g, camArr.get(0), Color.yellow); //畫追蹤的鏡頭框
        smallMap.end(g);
        g.setColor(Color.red);
        int x = smallMap.cameraWindowX() + 20;
        int y = smallMap.cameraWindowY() + 20;
        g.drawString("這是小地圖!!!", x, y);
    }

    private void start() {
        // 隨機產生
        if (Global.random(1, 100) >= 90 && arr.size() < 15) {
            arr.add(new CEnemy(random(0, MapInformation.mapInfo().right() - 200), 100, random(-5, 5)));
        }
    }

    private void enemyUpdate() {
        for (int i = 0; i < arr.size(); i++) {
            CEnemy tmp = arr.get(i);
            if (arr.get(i).outOfScreen()) {
                arr.remove(i--);
                continue;
            }
            if (ac.isCollision(arr.get(i))) {
                arr.remove(i);
//                SceneController.instance().change(new GameOverScene());
                continue;
            }
            arr.get(i).update();
        }
    }

    private void boomUpdate() {
        for (int i = 0; i < ammo.size(); i++) {
            CBoom boom = ammo.get(i);
            boom.update();
            if (boom.getState() != CBoom.State.NORMAL) {
                if (boom.getState() == CBoom.State.REMOVED) {
                    ammo.remove(i--);
                }
                continue;
            }
            if (boom.outOfScreen()) {
                ammo.remove(i--);
                continue;
            }
            for (int n = 0; n < arr.size(); n++) {
                if (boom.isCollision(arr.get(n))) {
                    boom.setState(CBoom.State.BOOM);
                    arr.remove(n);
                    break;
                }
            }
        }
    }

    @Override
    public void update() {
        start();
        for (int i = 0; i < camArr.size(); i++) {
            camArr.get(i).update();
        }
        ac.update();
        enemyUpdate();
        boomUpdate();
        smallMap.update();
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return null;
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return new CommandSolver.KeyListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                ac.keyPressed(commandCode, trigTime);
                for (int i = 0; i < camArr.size(); i++) {
                    camArr.get(i).keyPressed(commandCode, trigTime);
                }
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
                
                if (commandCode == Global.SPACE) {
                    ammo.add(new CBoom(ac.painter().centerX(), ac.painter().centerY()));
                }
                if (commandCode == Global.LEFT || commandCode == Global.RIGHT
                        || commandCode == Global.UP || commandCode == Global.DOWN) {
                    ac.setDir(Direction.NO);
                }

            }

            @Override
            public void keyTyped(char c, long trigTime) {
            }

        };
    }
}
