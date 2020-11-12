package gametest7th.gameobj;

import gametest7th.camera.MapInformation;
import java.awt.Graphics;

public class Map extends GameObject{
    
    
    public Map(){
        super(400, 300, 800, 600);
        MapInformation.setMapInfo(0, 0, 800, 600);
    }
    
    @Override
    public void paintComponent(Graphics g) {
    }

    @Override
    public void update() {
        
    }
    
}
