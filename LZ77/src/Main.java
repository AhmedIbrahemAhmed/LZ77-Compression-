import LZ77.* ;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
    Vector<Tag> lastcomperssion = new Vector<>();
    while(true){
        Scanner input = new Scanner(System.in);
        System.out.print("1-compress. \n2-decompress.\n3-exist.\nplease enter your choice :");
        int choice=input.nextInt();
        if(choice==1){
            Compression compression=new Compression();
            System.out.print("1-read form the console. \n2-read form file.\nplease enter your choice :");
            int compressChoice=input.nextInt();
            if(compressChoice==1){
                System.out.print("enter text to compress: ");
                String text = input.nextLine();
                while (text.length() == 0) {
                    System.out.print("text is empty enter another: ");
                    text = input.nextLine();
                }
                lastcomperssion=compression.compress(text, 0, 0);
                System.out.println("the result :");
                for(int i=0;i<lastcomperssion.size();i++){
                    System.out.println(lastcomperssion.elementAt(i).getPosition()+" "+lastcomperssion.elementAt(i).getLength()+" "+lastcomperssion.elementAt(i).getNext());
                }
            }
        }
        else if(choice==2){
            Decompression decompression=new Decompression();
            Vector<Tag> tempVector= new Vector<>();
            System.out.print("1-read form the console.\n2-use the last comperssion. \n3-read form file.\nplease enter your choice :");
            int decompressChoice=input.nextInt();
            if(decompressChoice==1){
                System.out.print("please enter the number of tags :");
                int tagNumber=input.nextInt();
                System.out.println("please enter the tags  as lines of position lenght nextChar .");
                for(int i=0;i<tagNumber;i++){
                    System.out.println("enter tag "+(i+1)+" .");
                    int positionTemp=input.nextInt();
                    int lenghtTemp=input.nextInt();
                    char nextCharTemp=input.next().charAt(0);
                    Tag tempTag=new Tag(lenghtTemp, positionTemp, nextCharTemp);
                    tempVector.add(tempTag);
                }
                String result=decompression.decompress(tempVector);
                System.out.println(" The result : "+result);
            }
            else if(decompressChoice==2){
                String result=decompression.decompress(lastcomperssion);
                System.out.println(result);
            }
        } 
        else {
            input.close();
            return;
        }
    }
    }
}
