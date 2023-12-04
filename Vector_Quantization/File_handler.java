package Vector_Quantization;
import java.io.*;
import java.io.IOException;
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

            // Create a 2D array to store pixel values
            int[][] pixelValues = new int[width][height];

            // Iterate through each pixel and store its grayscale value
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int rgb = image.getRGB(i, j);
                    int grayValue = (rgb >> 16) & 0xFF; // Assuming a grayscale image

                    // Store the grayscale value in the 2D array
                    pixelValues[i][j] = grayValue;
                }
            }
            // Now 'pixelValues' contains the grayscale values of each pixel in the image
        return pixelValues;
    }
    // public CompressionParsed Read_compressed(Path path) throws IOException {
        
    // }
    // public void Write_Compressed(String compressed,String path) throws IOException {
      
    // }
    public void Write_Decompressed(int [][] pixelValues ,String path ) throws IOException {
        int width = pixelValues.length;
        int height = pixelValues[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        // Set pixel values in the image
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int grayValue = pixelValues[i][j];
                int rgb = (grayValue << 16) | (grayValue << 8) | grayValue; // Creating grayscale RGB value

                // Set the pixel in the image
                image.setRGB(i, j, rgb);
            }
        }

        // Save the image to a file
        try {
            ImageIO.write(image, "png", new File(path+".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
