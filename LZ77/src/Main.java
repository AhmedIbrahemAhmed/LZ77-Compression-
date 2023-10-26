import LZ77.* ;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("enter text to compress: ");
        String text = input.nextLine();
        while (text.length() == 0) {
            System.out.print("text is empty enter another: ");
            text = input.nextLine();
        }
        System.out.println(text);
        compress(text);
        Tag tag = new Tag(3, 3, 'a');
    }

    public static void compress(String text) {

        int search_window = 10;
        Vector<Tag> tags = new Vector<>();
        Tag tag = new Tag(0, 0, text.charAt(0));
        tags.add(tag);
        for (int i = 1; i < text.length(); i++) {
            if (i <= search_window) {
                for (int j = 0; j < i; j++) {
                    if (text.charAt(i) == tags.elementAt(j).getNext()) {
                        i++;
                        j++;
                        if (j == tags.size()) {
//                           Tag temp = new Tag()
                            break;
                        }
                    }
                    else {
                    }
                }
            }

        }
    }
}
