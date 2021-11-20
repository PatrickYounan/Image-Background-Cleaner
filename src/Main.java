import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Patrick
 */
public final class Main {

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("image.png"));
        int[][] pixels = new int[image.getWidth()][image.getHeight()];
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgba = image.getRGB(x, y);

                int a = (rgba >> 24) & 0xFF;
                int r = (rgba >> 16) & 0xFF;
                int g = (rgba >> 8) & 0xFF;
                int b = rgba & 0xFF;

                if (r == 255 && g == 255 && b == 255 && a == 255) {
                    pixels[x][y] = ((0) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8)  | ((b & 0xFF));
                    continue;
                }
                pixels[x][y] = rgba;
            }
        }

        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                newImage.setRGB(x, y, pixels[x][y]);
            }
        }
        ImageIO.write(newImage, "png", new File("newImage.png"));
    }

}
