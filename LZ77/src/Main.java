import LZ77.* ;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException {
    Vector<Tag> lastCompression = new Vector<>();
    while(true){
        Scanner input = new Scanner(System.in);
        System.out.print("1-compress. \n2-decompress.\n3-exit.\nplease enter your choice : ");
        int choice=input.nextInt();
        if(choice==1){
            Compression compression=new Compression();
            System.out.print("1-read form the console. \n2-read form file.\nplease enter your choice : ");
            int compressChoice=input.nextInt();
            if(compressChoice==1){
                input.nextLine();
                System.out.print("enter text to compress: ");
                String text = input.nextLine();
                while (text.length() == 0) {
                    System.out.print("text is empty enter another: ");
                    text = input.nextLine();
                }
                lastCompression =compression.compress(text, 0, 0);
                System.out.println("the result :");
                for(int i = 0; i< lastCompression.size(); i++){
                    System.out.println(lastCompression.elementAt(i).getPosition()+" "+ lastCompression.elementAt(i).getLength()+" "+ lastCompression.elementAt(i).getNext());
                }
            }
            else{
                File_handler file = new File_handler();
                lastCompression =compression.compress(file.Read_Original(), 0, 0);
                System.out.println("compressed successfully");
            }
            File_handler file = new File_handler() ;
            file.Write_Compressed(lastCompression);
        }
        else if(choice==2){
            File_handler file = new File_handler() ;
            Decompression decompression=new Decompression();
            Vector<Tag> tempVector= new Vector<>();
            System.out.print("1-read form the console.\n2-use the last compression. \n3-read form file.\nplease enter your choice : ");
            int decompressChoice=input.nextInt();
            if(decompressChoice==1){
                System.out.print("please enter the number of tags : ");
                int tagNumber=input.nextInt();
                System.out.println("please enter the tags  as lines of position length nextChar .");
                for(int i=0;i<tagNumber;i++){
                    System.out.println("enter tag "+(i+1)+" .");
                    int positionTemp=input.nextInt();
                    int lengthTemp =input.nextInt();
                    char nextCharTemp=input.next().charAt(0);
                    Tag tempTag=new Tag(lengthTemp, positionTemp, nextCharTemp);
                    tempVector.add(tempTag);
                }
                String result=decompression.decompress(tempVector);
                file.Write_Decompressed(result);
                System.out.println("decompressed successfully");
            }
            else if(decompressChoice==2){
                String result=decompression.decompress(lastCompression);
                file.Write_Decompressed(result);
                System.out.println("decompressed successfully");
            }
            else if(decompressChoice==3){
                tempVector = file.Read_compressed() ;
                String result=decompression.decompress(tempVector);
                file.Write_Decompressed(result);
                System.out.println("decompressed successfully");
            }
        } 
        else {
            input.close();
            return;
        }
    }
    }
}
