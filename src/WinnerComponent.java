import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class WinnerComponent extends JComponent {
    private static final int DEF_WIDTH = 720;
    private static final int DEF_HEIGHT = 360;

    public Dimension getPreferredSize() {
        return new Dimension(DEF_WIDTH, DEF_HEIGHT);
    }

    public WinnerComponent() {

    }

    public void paintComponent(Graphics gh) {
        Graphics2D g = (Graphics2D) gh;
        g.drawString("Сравнение массивов", 0, 0);
        g.setColor(Color.black);
    }
}
