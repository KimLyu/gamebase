package gametest7th.gameobj;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class TestObject1 extends gametest7th.gameobj.GameObject implements gametest7th.utils.GameKernel.GameInterface {

    public TestObject1(int x, int y, int width, int height) throws IOException {
        super(x, y, width, height);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.red);
        g.fillRect(this.painter().left(), this.painter().top(), this.painter().width(), this.painter().height());
        g.setColor(Color.BLACK);
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
