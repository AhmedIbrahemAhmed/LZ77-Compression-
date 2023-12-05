package Vector_Quantization;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class File_handler {
    public Image  Read_Original(String path) throws IOException {
            // Load the image
            BufferedImage image = ImageIO.read(new File(path));

            // Get image dimensions
            int width = image.getWidth();
            int height = image.getHeight();

            
            int[][] redlValues = new int[width][height];
            int[][] greenValues = new int[width][height];
            int[][] blueValues = new int[width][height];
            
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    int rgb = image.getRGB(i, j);
                    
                    
                    int blueValue = rgb  & 0xFF;
                    int greenValue = (rgb >> 8) & 0xFF;
                    int redValue = (rgb >> 16) & 0xFF; 
                    redlValues[i][j] = redValue;
                    greenValues[i][j] = greenValue;
                    blueValues[i][j] = blueValue;
                }
            }
            Image result = new Image(redlValues, greenValues, blueValues);
            
        return result;
    }
    public CompressionParsedRPG Read_compressed(Path path) throws IOException {
        StringBuilder binary_data= new StringBuilder();
        byte[] bytes = Files.readAllBytes(path);
        for (byte aByte : bytes) {
            binary_data.append(String.format("%8s", Integer.toBinaryString(aByte & 0xFF)).replace(' ', '0'));
        }
        int i=0;
        //red
        CompressionParsed red= new CompressionParsed(null, null, 0, 0);
        
        int temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
        i+=8;
        red.setRows(temp);
        temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
        i+=8;
        red.setCols(temp);
        int codebookSize=Integer.parseInt(binary_data.substring(i,i+8), 2)+1 ;
        i+=8;
        int codeBooklenght =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
        i+=8;
       
        Vector<int[][]> tempVector=new Vector<>();
        
        for(int ii=0;ii<codebookSize;ii++){
             int [] [] tempcodeBook= new int [codeBooklenght][codeBooklenght];
            for( int j=0;j<codeBooklenght;j++){

                for(int k=0;k<codeBooklenght;k++){
                    temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
                    i+=8;
                    
                    tempcodeBook[j][k]=temp;
                   
                }
                 
            }
            
            tempVector.add(tempcodeBook);
             
        }
        red.setCodeBook(tempVector);
        
        
        int corpressedLenght= Integer.parseInt(binary_data.substring(i,i+16), 2) ;
        i+=16;
        int[] tempCompressedStream=new int [corpressedLenght];
        for(int ii=0;ii<corpressedLenght;ii++){
            temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
            i+=8;
            tempCompressedStream[ii]=temp;
        }
        red.setCompressedStream(tempCompressedStream);
        //green
        CompressionParsed green= new CompressionParsed(null, null, 0, 0);
        temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
        i+=8;
        green.setRows(temp);
        temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
        i+=8;
        green.setCols(temp);
         codebookSize=Integer.parseInt(binary_data.substring(i,i+8), 2)+1 ;
        i+=8;
         codeBooklenght =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
        i+=8;

         tempVector=new Vector<>();
        for(int ii=0;ii<codebookSize;ii++){
                int[][]    tempcodeBook= new int [codeBooklenght][codeBooklenght];
            for( int j=0;j<codeBooklenght;j++){
                for(int k=0;k<codeBooklenght;k++){
                    temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
                    i+=8;
                    tempcodeBook[j][k]=temp;
                }
            }
            tempVector.add(tempcodeBook);
        }
        green.setCodeBook(tempVector);
         corpressedLenght= Integer.parseInt(binary_data.substring(i,i+16), 2) ;
        i+=16;
         tempCompressedStream=new int [corpressedLenght];
        for(int ii=0;ii<corpressedLenght;ii++){
            temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
            i+=8;
            tempCompressedStream[ii]=temp;
        }
        green.setCompressedStream(tempCompressedStream); 
        //blue
        CompressionParsed blue= new CompressionParsed(null, null, 0, 0);
        temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
        i+=8;
        blue.setRows(temp);
        temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
        i+=8;
        blue.setCols(temp);
         codebookSize=Integer.parseInt(binary_data.substring(i,i+8), 2)+1 ;
        i+=8;
         codeBooklenght =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
        i+=8;
      
         tempVector=new Vector<>();
        for(int ii=0;ii<codebookSize;ii++){
            int[][]    tempcodeBook= new int [codeBooklenght][codeBooklenght];
            for( int j=0;j<codeBooklenght;j++){
                for(int k=0;k<codeBooklenght;k++){
                    temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
                    i+=8;
                    tempcodeBook[j][k]=temp;
                }
            }
            tempVector.add(tempcodeBook);
        }
        blue.setCodeBook(tempVector);
         corpressedLenght= Integer.parseInt(binary_data.substring(i,i+16), 2) ;
        i+=16;
         tempCompressedStream=new int [corpressedLenght];
        for(int ii=0;ii<corpressedLenght;ii++){
            temp =  Integer.parseInt(binary_data.substring(i,i+8), 2) ;
            i+=8;
            tempCompressedStream[ii]=temp;
        }
        blue.setCompressedStream(tempCompressedStream); 
        CompressionParsedRPG result = new CompressionParsedRPG(red, green, blue);
        
        return result;

    }
     public void Write_Compressed(CompressionParsedRPG stream,String path) throws IOException {
        StringBuilder compressed = new StringBuilder();
        String temp = Integer.toBinaryString(stream.getRed().getRows());
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        
        compressed.append(temp);
        temp= Integer.toBinaryString(stream.getRed().getCols());
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        
        compressed.append(temp);
        temp= Integer.toBinaryString(stream.getRed().getCodeBook().size()-1);
        
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        
        compressed.append(temp);
        temp= Integer.toBinaryString(stream.getRed().getCodeBook().elementAt(0).length);
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        
        compressed.append(temp);
        for(int i=0;i<stream.getRed().getCodeBook().size();i++){
            for(int j=0;j<stream.getRed().getCodeBook().elementAt(i).length;j++){
                for(int k=0;k<stream.getRed().getCodeBook().elementAt(i).length;k++){
                    temp= Integer.toBinaryString(stream.getRed().getCodeBook().elementAt(i)[j][k]);
                    temp = String.format("%8s",temp).replaceAll(" ", "0") ;
                    compressed.append(temp);
                }
            }
        }
        temp= Integer.toBinaryString(stream.getRed().getCompressedStream().length);
        
        temp = String.format("%16s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        for(int i=0;i<stream.getRed().getCompressedStream().length;i++){
            temp= Integer.toBinaryString(stream.getRed().getCompressedStream()[i]);
            temp = String.format("%8s",temp).replaceAll(" ", "0") ;
            compressed.append(temp);
        }
        temp = Integer.toBinaryString(stream.getGreen().getRows());
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        temp= Integer.toBinaryString(stream.getGreen().getCols());
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        temp= Integer.toBinaryString(stream.getGreen().getCodeBook().size()-1);
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        temp= Integer.toBinaryString(stream.getGreen().getCodeBook().elementAt(0).length);
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        for(int i=0;i<stream.getGreen().getCodeBook().size();i++){
            
            for(int j=0;j<stream.getGreen().getCodeBook().elementAt(i).length;j++){
                for(int k=0;k<stream.getGreen().getCodeBook().elementAt(i).length;k++){
                    temp= Integer.toBinaryString(stream.getGreen().getCodeBook().elementAt(i)[j][k]);
                    temp = String.format("%8s",temp).replaceAll(" ", "0") ;
                    compressed.append(temp);
                }
            }
        }
        temp= Integer.toBinaryString(stream.getGreen().getCompressedStream().length);
        temp = String.format("%16s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        for(int i=0;i<stream.getGreen().getCompressedStream().length;i++){
            temp= Integer.toBinaryString(stream.getGreen().getCompressedStream()[i]);
            temp = String.format("%8s",temp).replaceAll(" ", "0") ;
            compressed.append(temp);
        }
        temp = Integer.toBinaryString(stream.getBlue().getRows());
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        temp= Integer.toBinaryString(stream.getBlue().getCols());
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        temp= Integer.toBinaryString(stream.getBlue().getCodeBook().size()-1);
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        temp= Integer.toBinaryString(stream.getBlue().getCodeBook().elementAt(0).length);
        temp = String.format("%8s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        for(int i=0;i<stream.getBlue().getCodeBook().size();i++){
            for(int j=0;j<stream.getBlue().getCodeBook().elementAt(i).length;j++){
                for(int k=0;k<stream.getBlue().getCodeBook().elementAt(i).length;k++){
                    temp= Integer.toBinaryString(stream.getBlue().getCodeBook().elementAt(i)[j][k]);
                    temp = String.format("%8s",temp).replaceAll(" ", "0") ;
                    compressed.append(temp);
                }
            }
        }
        temp= Integer.toBinaryString(stream.getBlue().getCompressedStream().length);
        temp = String.format("%16s",temp).replaceAll(" ", "0") ;
        compressed.append(temp);
        for(int i=0;i<stream.getBlue().getCompressedStream().length;i++){
            temp= Integer.toBinaryString(stream.getBlue().getCompressedStream()[i]);
            temp = String.format("%8s",temp).replaceAll(" ", "0") ;
            compressed.append(temp);
        }
        
        int size = compressed.length()/8 ;
//        array of bytes representing the binary format of compressed text to store in the file
        byte[] bytes = new byte[size] ;
//        copying the binary format to the byte array
        for(int i=0;i<compressed.length();i+=8){
            if(i+8>compressed.length())break;
            int temp2 = Integer.parseInt(compressed.substring(i,i+8), 2) ;
            byte b = (byte)temp2 ;
            bytes[i/8] = b ;
        }
//        writing on file
        Files.write(new File(path).toPath(), bytes);


    }
    public void Write_Decompressed(Image pixelValues ,String path ) throws IOException {
        int width = pixelValues.getBlue().length;
        int height = pixelValues.getBlue()[0].length;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Set pixel values in the image
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int redValue = pixelValues.getRed()[i][j];
                int greenValue = pixelValues.getGreen()[i][j];
                int blueValue = pixelValues.getBlue()[i][j];
                int rgb = (redValue << 16) | (greenValue << 8) | blueValue; 

                // Set the pixel in the image
                image.setRGB(i, j, rgb);
            }
        }

        // Save the image to a file
        try {
            ImageIO.write(image, "png", new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
