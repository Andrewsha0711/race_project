import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageProcessor {

    ImageProcessor() {}

    public BufferedImage getImage(BufferedImage source) {

        BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(),
                source.getType());

        for (int x = 0; x < source.getWidth(); x++) {
            for (int y = 0; y < source.getHeight(); y++) {

                Color color = new Color(source.getRGB(x, y));

                int blue = color.getBlue();
                int red = color.getRed();
                int green = color.getGreen();

                int grey = (int) (red * 0.299 + green * 0.587 + blue * 0.114);

                int newRed = grey;
                int newGreen = grey;
                int newBlue = grey;

                Color newColor = new Color(newRed, newGreen, newBlue);

                result.setRGB(x, y, newColor.getRGB());
            }
        }
        return result;
    }
}
