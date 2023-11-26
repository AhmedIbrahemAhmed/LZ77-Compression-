package standardHuffman;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class File_handler {
    public String Read_Original() throws IOException {
        Scanner scanner = new Scanner(System.in) ;
        String data = "" ;
        System.out.print("enter file name and path: ");
        String filename = scanner.nextLine() ;
        File file = new File(filename) ;
        while(!file.exists()){
            System.out.println("file doesn't exist try again");
            System.out.print("enter file name and path: ");
            filename = scanner.nextLine() ;
            file = new File(filename) ;
        }
        Path path = Paths.get(filename) ;
        List<String> lines = Files.readAllLines(path);
        for(int i=0;i< lines.size();i++){
            data+= lines.get(i) ;
            if(!(i== lines.size()-1))
                data+= '\n' ;
        }

        return data ;
    }
    public CompressionParsed Read_compressed() throws IOException {
        String filename ;
        StringBuilder binary_data= new StringBuilder();
        Scanner input = new Scanner(System.in) ;
        System.out.print("enter file name and path for the compressed file: ");
        filename = input.nextLine() ;
        File file = new File(filename) ;
        while(!file.exists()){
            System.out.println("file doesn't exist try again");
            System.out.print("enter file name and path: ");
            filename = input.nextLine() ;
            file = new File(filename) ;
        }
        Path path = Paths.get(filename) ;
        byte[] bytes = Files.readAllBytes(path);
        for (byte aByte : bytes) {
            binary_data.append(String.format("%8s", Integer.toBinaryString(aByte & 0xFF)).replace(' ', '0'));
        }
        HashMap<Character,Integer> frequencies = new HashMap<>() ;
        int numberOfCharacters =  Integer.parseInt(binary_data.substring(0,7), 2) ;
        for(int i = 7;i < (numberOfCharacters*11)+7; i += 11){

            char character = (char) Integer.parseInt(binary_data.substring(i,i+7), 2);
            int freq = Integer.parseInt(binary_data.substring(i+7,i+11), 2) +1 ;
            frequencies.put(character,freq) ;
        }
        int numberOfZeros = Integer.parseInt(binary_data.substring((numberOfCharacters*11 +7),(numberOfCharacters*11)+10), 2) ;
        String compressedStream = binary_data.substring((numberOfCharacters*11)+10, binary_data.length()-numberOfZeros) ;
        CompressionParsed parsed = new CompressionParsed(frequencies,compressedStream) ;
        return parsed ;
    }
    public void Write_Compressed(String compressed) throws IOException {

        String filename ;
        Scanner input = new Scanner(System.in) ;
        System.out.print("enter file name and path for the compressed file: ");
        filename = input.nextLine() ;
        int size = compressed.length()/8 ;
//        array of bytes representing the binary format of compressed text to store in the file
        byte[] bytes = new byte[size] ;
//        copying the binary format to the byte array
        for(int i=0;i<compressed.length();i+=8){
            int temp = Integer.parseInt(compressed.substring(i,i+8), 2) ;
            byte b = (byte)temp ;
            bytes[i/8] = b ;
        }
//        writing on file
        Files.write(new File(filename).toPath(), bytes);


    }
    public void Write_Decompressed(String text) throws IOException {
        String filename ;
        Scanner input = new Scanner(System.in) ;
        System.out.print("enter file name and path for the decompressed file: ");
        filename = input.nextLine() ;
        FileWriter file = new FileWriter(filename) ;
        file.write(text);
        file.close();

    }

}
