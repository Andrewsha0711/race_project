import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Racer {

    private static final int MAX_DEF_WIDTH = 128;

    Image img;

    double speed;
    double maxSpeed;
    double x;
    double y;
    double finish;
    String id;

    public Racer(double xValue, double yValue, double maxSpeedValue, double finishX, String id)
            throws IOException {

        char number = id.charAt(id.length() - 1);
        BufferedImage buffimage = ImageIO.read(new File("res\\car" + number + ".png"));

        double sizeCoef = (double) buffimage.getHeight() / (double) buffimage.getWidth();
        this.img = buffimage.getScaledInstance(MAX_DEF_WIDTH, (int) (MAX_DEF_WIDTH * sizeCoef),
                Image.SCALE_DEFAULT);
        // this.img = new
        // ImageProcessor().getImage(buffimage).getScaledInstance(MAX_DEF_WIDTH,
        // (int)(MAX_DEF_WIDTH*sizeCoef), Image.SCALE_DEFAULT);

        this.maxSpeed = maxSpeedValue;
        this.speed = 0;
        this.x = xValue;
        this.y = yValue;
        this.finish = finishX;
        this.id = id;
    }

    public void paint(Graphics g) {
        g = (Graphics2D) g;
        g.drawImage(img, (int) this.x, (int) this.y, null);
    }

    public void move() {
        this.x += this.getCurrentSpeed();
    }

    public double getCurrentSpeed() {
        double speed = (Math.random() * (this.maxSpeed - this.maxSpeed / 2) + this.maxSpeed / 2);
        return speed;
    }

    public synchronized void setResult(ArrayList<Racer> result) {
        synchronized (result) {
            result.add(this);
        }
        // result.add(this);
        System.out.println(this.id);
    }
}
