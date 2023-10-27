import LZ77.* ;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<Tag> compressed = new Vector<>();
        Scanner input = new Scanner(System.in);
        System.out.print("enter text to compress: ");
        String text = input.nextLine();
        while (text.length() == 0) {
            System.out.print("text is empty enter another: ");
            text = input.nextLine();
        }
        input.close();
        System.out.println(text);
        Compression c=new Compression();
        compressed=c.compress(text,0,0);
        for(int i=0;i<compressed.size();i++){
            System.out.println(compressed.elementAt(i).getPosition()+" "+compressed.elementAt(i).getLength()+" "+compressed.elementAt(i).getNext());
        }
    }

    
}
