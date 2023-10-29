package LZ77;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import  java.nio.file.Files ;
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
    public String Read_compressed(){
        String filename ;
        String data= "" ;
        Scanner input = new Scanner(System.in) ;
        System.out.print("enter file name and path for the decompressed file: ");
        filename = input.nextLine() ;






        return data ;
    }
    public void Write_Compressed(Vector<Tag> tags) throws IOException {
        String compressed = "" ;
        int max_length = 0 ;
        int max_position = 0 ;
        int max_character = 0 ;
        int bits_for_length, bits_for_position,bits_for_characters ;
//        calculating the maximum number in pos, length and character
        for(int i=0;i< tags.size();i++){
            if(tags.elementAt(i).getLength()>max_length)
                max_length = tags.elementAt(i).getLength() ;
            if(tags.elementAt(i).getPosition()>max_position)
                max_position = tags.elementAt(i).getPosition() ;
            if(tags.elementAt(i).getNext()>max_character)
                max_character = tags.elementAt(i).getNext() ;
        }
//        calculating the maximum number of bits for each variable
        bits_for_length = (int)Math.ceil(Math.log(max_length+1) / Math.log(2)) ;
        bits_for_position = (int)Math.ceil(Math.log(max_position+1) / Math.log(2)) ;
        bits_for_characters = (int)Math.ceil(Math.log(max_character+1) / Math.log(2)) ;

//        getting the binary format for the overhead
        String binary_bits_length_size, binary_bits_position_size, binary_bits_char_size ;
        binary_bits_position_size = Integer.toBinaryString(bits_for_position) ;
        binary_bits_position_size =  String.format("%4s", binary_bits_position_size).replaceAll(" ", "0");
        binary_bits_length_size = Integer.toBinaryString(bits_for_length) ;
        binary_bits_length_size =  String.format("%4s", binary_bits_length_size).replaceAll(" ", "0");
        binary_bits_char_size = Integer.toBinaryString(bits_for_characters) ;
        binary_bits_char_size =  String.format("%5s", binary_bits_char_size).replaceAll(" ", "0");
        compressed+= binary_bits_position_size ;
        compressed += binary_bits_length_size ;
        compressed += binary_bits_char_size ;

//        transforming the compressed text to binary format
        for(int i=0;i<tags.size();i++){
            String binary_length = Integer.toBinaryString(tags.elementAt(i).getLength()) ;
            String length_size = "%" + bits_for_length + "s" ;
            binary_length =  String.format(length_size, binary_length).replaceAll(" ", "0");
            String binary_position = Integer.toBinaryString(tags.elementAt(i).getPosition()) ;
            String position_size = "%" + bits_for_position + "s" ;
            binary_position = String.format(position_size, binary_position).replaceAll(" ", "0");
            String binary_character =  Integer.toBinaryString(tags.elementAt(i).getNext()) ;
            String character_size = "%" + bits_for_characters + "s" ;
            binary_character =  String.format(character_size, binary_character).replaceAll(" ", "0");
            compressed += binary_position ;
            compressed += binary_length ;
            compressed += binary_character ;
        }
        String filename ;
        Scanner input = new Scanner(System.in) ;
        System.out.print("enter file name and path for the compressed file: ");
        filename = input.nextLine() ;
//        calculating the number of added zeros to the end of file to make it dividable by 8
        int length = compressed.length() ;
        int rest = 8- (length % 8) ;
//        adding the zeros to the end
        for(int i=0;i<rest;i++){
            compressed += "0"  ;
        }

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
//        FileWriter file = new FileWriter(filename) ;
//        file.write(compressed);
//        file.close();

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
