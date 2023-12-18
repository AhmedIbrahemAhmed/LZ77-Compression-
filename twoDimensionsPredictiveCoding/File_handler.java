package twoDimensionsPredictiveCoding;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class File_handler {
    public int[][]  Read_Original(String path) throws IOException {
            // Load the image
            BufferedImage image = ImageIO.read(new File(path));

            // Get image dimensions
            int width = image.getWidth();
            int height = image.getHeight();


            int[][] redlValues = new int[width][height];

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int rgb = image.getRGB(i, j);
                    int redValue = (rgb >> 16) & 0xFF;
                    redlValues[i][j] = redValue;

                }
            }


        return redlValues;
    }
//    public int[][] Read_compressed(Path path) throws IOException {
//
//
//    }

    public void Write_Decompressed(int[][] pixelValues ,String path ) throws IOException {
        int width = pixelValues.length;
        int height = pixelValues[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Set pixel values in the image
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int redValue = pixelValues[i][j];
                int rgb = (redValue << 16) | (redValue << 8) | redValue;

                // Set the pixel in the image
                image.setRGB(i, j, rgb);
            }
        }

        // Save the image to a file
        try {
            ImageIO.write(image, "JPEG", new File(path+".jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
