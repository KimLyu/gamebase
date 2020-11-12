/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gametest7th.controllers;

import gametest7th.utils.Global;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

/**
 *
 * @author user1
 */
public class ImgController {

    private static class KeyPair {

        private String path;
        private Image img;

        public KeyPair(String path, Image img) {
            this.path = path;
            this.img = img;
        }
    }

    // 內容
    private Map<String, Image> imgMap;

    public ImgController() {
        imgMap = new HashMap<>();
    }

    public Image tryGetImage(String path) {
        if(imgMap.containsKey(path)){
            return imgMap.get(path);
        }
        return addImage(path);
    }

    private Image addImage(String path) {
        try {
            if (Global.IS_DEBUG) {
                System.out.println("load img from: " + path);
            }
            Image img = ImageIO.read(getClass().getResource(path));
            imgMap.put(path, img);
            return img;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void clear(){
        imgMap.clear();
    }
}
