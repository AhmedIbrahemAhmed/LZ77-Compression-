package twoDimensionsPredictiveCoding;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public int[][] Read_compressed(String path) throws IOException {
        StringBuilder binary_data= new StringBuilder();
        File file = new File(path) ;
        Scanner input = new Scanner(System.in) ;
        while(!file.exists()){
            System.out.println("file doesn't exist try again");
            System.out.print("enter file name and path: ");
            path = input.nextLine() ;
            file = new File(path) ;
        }
        Path mypath = Paths.get(path) ;
        byte[] bytes = Files.readAllBytes(mypath);
        for(int i=0;i<bytes.length ;i++){
            binary_data.append(String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0'));
        }
        int width = Integer.parseInt(binary_data.substring(0,16), 2) ;
        int height = Integer.parseInt(binary_data.substring(16,32), 2) ;
        int[][] quantized = new int[width][height] ;
        int cellSize = Integer.parseInt(binary_data.substring(32,40), 2) ;
        for(int i=0;i< width ;i++){
            quantized[i][0] = Integer.parseInt(binary_data.substring(40 + (i* 8),48 + (i*8) ), 2) ;
        }
        int temp = (width* 8) + 40 ;
        for(int i = 0; i<height;i++){
            quantized[0][i] = Integer.parseInt(binary_data.substring(i,i+8), 2) ;
        }



        return quantized ;
    }
    public void write_compressed(int[][] quantized, String Path) throws IOException{
        StringBuilder compressed = new StringBuilder();
        String binary_num,binaryHeight,binaryWidth ;
        binaryWidth =  Integer.toBinaryString(quantized.length) ;
        binaryWidth = String.format("%16s",binaryWidth).replaceAll(" ", "0");
        binaryHeight = Integer.toBinaryString(quantized[0].length) ;
        binaryHeight = String.format("%16s",binaryHeight).replaceAll(" ","0") ;
        compressed.append(binaryWidth) ;
        compressed.append(binaryHeight) ;
        for(int i=0;i<quantized.length;i++){
            binary_num = Integer.toBinaryString(quantized[i][0]) ;
            binary_num = String.format("%8s",binary_num).replaceAll(" ", "0");
            compressed.append(binary_num);
        }
        for(int i=0;i< quantized[0].length;i++){
            binary_num = Integer.toBinaryString(quantized[0][i]) ;
            binary_num = String.format("%8s",binary_num).replaceAll(" ", "0");
            compressed.append(binary_num);
        }
        int max = quantized[1][1] ;
        for(int i=1;i< quantized.length;i++){
            for(int j=1;j< quantized[0].length;j++){
                if(quantized[i][j]> max)
                    max = quantized[i][j] ;
            }
        }
        int bits_for_cell = (int)Math.ceil(Math.log(max) / Math.log(2)) ;
        String binaryBitsForCell ;
        binaryBitsForCell = Integer.toBinaryString(bits_for_cell) ;
        binaryBitsForCell = String.format("%8s",binaryBitsForCell).replaceAll(" ", "0");
        compressed.append(binaryBitsForCell);
        for(int i=1;i<quantized.length;i++){
            for(int j=1;j< quantized[0].length;j++){
                String binaryCell = Integer.toBinaryString(quantized[i][j]) ;
                String cellSize = "%" + bits_for_cell + "s" ;
                binaryCell = String.format(cellSize,binaryCell).replaceAll(" ","0") ;
                compressed.append(binaryCell) ;
            }
        }
        int length = compressed.length() ;
        int rest = 8 - (length % 8) ;
        for(int i=0;i<rest;i++){
            compressed.append("0") ;
        }
        int size = compressed.length()/8 ;
        byte[] bytes = new byte[size] ;
//        copying the binary format to the byte array
        for(int i=0;i<compressed.length();i+=8){
            int temp = Integer.parseInt(compressed.substring(i,i+8), 2) ;
            byte b = (byte)temp ;
            bytes[i/8] = b ;
        }
//        writing on file
        Files.write(new File(Path).toPath(), bytes);
    }

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
