/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.scene;

import gametest7th.gameobj.GameObject;
import gametest7th.gameobj.TestObject1;
import gametest7th.utils.CommandSolver;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import maploader.MapInfo;
import maploader.MapLoader;

public class MapScene extends Scene {

    private ArrayList<GameObject> gameObjectArr = new ArrayList();

    @Override
    public void sceneBegin() {
        try {
            MapLoader mapLoader = new MapLoader("testMAP.bmp", "Test.txt");
            ArrayList<MapInfo> test = mapLoader.combineInfo();
            gameObjectArr = mapLoader.creatObjectArray("TestObject1", 8, test, new MapLoader.CompareClass() {
                @Override
                public GameObject compareClassName(String gameObject, String name, MapInfo mapInfo, int size) {
                    GameObject tmp = null;
                    if (gameObject.equals(name)) {
                        try {
                            tmp = new TestObject1(mapInfo.getX()* size, mapInfo.getY()*size, mapInfo.getSizeX() * size, mapInfo.getSizeY() * size);
                        } catch (IOException ex) {
                            Logger.getLogger(MapScene.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        return tmp;
                    }
                    return null;
                }
            });
//            for (int i = 0; i < test.size(); i++) {    //  這邊可以看array內容  {String name ,int x, int y, int xSize, int ySize}
//                System.out.println(test.get(i).getName());
//                System.out.println(test.get(i).getX());
//                System.out.println(test.get(i).getY());
//                System.out.println(test.get(i).getSizeX());
//                System.out.println(test.get(i).getSizeY());
//            }
            gameObjectArr.forEach(a-> System.out.println(a.getClass().getSimpleName()));
        } catch (IOException ex) {
            Logger.getLogger(MapScene.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        gameObjectArr.forEach(a -> a.paint(g));
    }

    @Override
    public void update() {
        
    }

    @Override
    public CommandSolver.MouseCommandListener mouseListener() {
        return null;
    }

    @Override
    public CommandSolver.KeyListener keyListener() {
        return null;
    }
}
