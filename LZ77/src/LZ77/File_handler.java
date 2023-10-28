package LZ77;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

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
            data+= '\n' ;
        }

        return data ;
    }
    public String Read_compressed(){
        String data= "" ;
        return data ;
    }
    public void Write_Compressed(Vector<Tag> tags) throws IOException {
        String compressed = "" ;
        int max_length = 0 ;
        int max_position = 0 ;
        int bits_for_length, bits_for_position ;
        for(int i=0;i< tags.size();i++){
            if(tags.elementAt(i).getLength()>max_length)
                max_length = tags.elementAt(i).getLength() ;
            if(tags.elementAt(i).getPosition()>max_position)
                max_position = tags.elementAt(i).getPosition() ;
        }
        bits_for_length = (int)Math.ceil(Math.log(max_length+1) / Math.log(2)) ;
        bits_for_position = (int)Math.ceil(Math.log(max_position+1) / Math.log(2)) ;
        for(int i=0;i<tags.size();i++){
            String binary_length = Integer.toBinaryString(tags.elementAt(i).getLength()) ;
            String length_size = "%" + bits_for_length + "s" ;
            binary_length =  String.format(length_size, binary_length).replaceAll(" ", "0");
            String binary_position = Integer.toBinaryString(tags.elementAt(i).getPosition()) ;
            String position_size = "%" + bits_for_position + "s" ;
            binary_position = String.format(position_size, binary_position).replaceAll(" ", "0");
            String binary_character =  Integer.toBinaryString(tags.elementAt(i).getNext()) ;
            compressed += binary_position ;
            compressed += binary_length ;
            compressed += binary_character ;
        }
        String filename ;
        Scanner input = new Scanner(System.in) ;
        System.out.print("enter file name and path for the compressed file: ");
        filename = input.nextLine() ;
        FileWriter file = new FileWriter(filename) ;
        file.write(compressed);
        file.close();

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
