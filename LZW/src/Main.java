import java.util.Scanner;
import LZW.*;
import java.util.Vector;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
        System.out.println("please enter :\n1-compressoion\n2-decompression\n3-exit");
        int choice=scanner.nextInt();
        scanner.nextLine();
        if(choice==1){
            Vector<Integer> compressed =new Vector<>();
            fileManager file=new fileManager();
            Compression compressor =new Compression();
            System.out.print("please enter the path to the file you wanna compresse : ");
            String temp =scanner.nextLine();
            try{
                Path path = Paths.get(temp);
                Path basepath = Paths.get(System.getProperty("user.dir"));
                Path resolvedAbsolute = basepath.resolve(path);
                String fileContent = new String(Files.readAllBytes(resolvedAbsolute));
                compressed=compressor.compress(fileContent);
                System.out.print("please enter the path to the file you wanna save in ot : ");
                String savepath =scanner.nextLine();
                Path pathsave = Paths.get(savepath);
                Path basepathsave= Paths.get(System.getProperty("user.dir"));
                Path resolvedAbsolutesave = basepathsave.resolve(pathsave);
                file.write(resolvedAbsolutesave, compressed);
                }catch (Exception e ) {
                    System.out.println("Invalid path ");
                }
                
        }
        else if(choice==2){
            Vector<Integer> compressed =new Vector<>();
            fileManager file=new fileManager();
            Decompression decompressor =new Decompression();
            System.out.print("please enter the path to the file you wanna decompresse : ");
            String temp =scanner.nextLine();
            try{
                Path path = Paths.get(temp);
                Path basepath = Paths.get(System.getProperty("user.dir"));
                Path resolvedAbsolute = basepath.resolve(path);
                compressed=file.read(resolvedAbsolute);
                String result=decompressor.decompress(compressed);
                System.out.print("please enter the path to the file you wanna save in ot : ");
                String savepath =scanner.nextLine();
                Path pathsave = Paths.get(savepath);
                Path basepathsave= Paths.get(System.getProperty("user.dir"));
                Path resolvedAbsolutesave = basepathsave.resolve(pathsave);
                Files.write(resolvedAbsolutesave , result.getBytes(),StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("decompressed successfully");
                }catch (Exception e) {
                    System.out.println("Invalid path ");
            }
        }
        else break;
        }
        scanner.close();
    }
}

