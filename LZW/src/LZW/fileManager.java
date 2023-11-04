package LZW;

import java.util.Vector;
import java.util.Collections;
import java.nio.file.*;
import java.io.*;

public class fileManager {
    public void write (Path path ,Vector<Integer> compressed){
        int max = Collections.max(compressed);
        String binaryString = Integer.toBinaryString(max);
        int maxlength =binaryString.length();
        String result = Integer.toBinaryString(maxlength-1);
        
        while(result.length()<4){
            result="0"+result;
        }
        
        for(int i : compressed){
            binaryString = Integer.toBinaryString(i);
            while(binaryString.length()!=maxlength){
                binaryString="0"+binaryString;
            }
            result += binaryString;
        }
       
        while(result.length()%8!=0){
            result +="0";
        }
        int size = result.length()/8 ;
        byte[] bytes = new byte[size] ;
        for(int i=0;i<result.length();i+=8){
            int temp = Integer.parseInt(result.substring(i,i+8), 2) ;
            byte b = (byte)temp ;
            bytes[i/8] = b ;
        }
        try{
        Files.write(path, bytes, StandardOpenOption.CREATE, StandardOpenOption.WRITE,StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("File successfully written.");
        }catch(Exception e) {
        e.printStackTrace();
        }
    }
    public Vector<Integer> read(Path path){
        String file="";
        Vector <Integer> result = new Vector<>();
        try (InputStream inputStream = new FileInputStream(path.toString())) {
            int byteRead;
            while ((byteRead = inputStream.read()) != -1) {
                String binaryString = String.format("%8s", Integer.toBinaryString(byteRead & 0xFF)).replace(' ', '0');
                file += binaryString;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
        int bitsize = Integer.parseInt(file.substring(0,4),2);
        bitsize++;
        for (int i = 4; i < file.length(); i += bitsize) {
            int endIndex = Math.min(i + bitsize, file.length());

            String chunk = file.substring(i, endIndex);
            if(chunk.length()==bitsize){
                
                    int chunkValue = Integer.parseInt(chunk,2);
                    if(chunkValue!=0)
                    result.add(chunkValue);
                } 
            }
            }catch (NumberFormatException e) {
                    System.err.println("Invalid chunk format. Unable to parse as an integer: ");
            }
        return  result;
    }
        
}
