import java.util.Scanner;
import java.util.Vector;

public class Network {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("What is the number of WI-FI Connections?");
        int size = Integer.parseInt(scanner.nextLine());
        Router router= new Router(size);
        System.out.println("What is the number of devices Clients want to connect?");
        int devices = Integer.parseInt(scanner.nextLine());
        Vector<Thread> t=new Vector<Thread>() ;
        while(devices-->0){
            String device= scanner.nextLine();
            Thread temp= new Thread(new MyRunnable(device,router));
            t.add(temp);
        }
        scanner.close();
        for (Thread thread : t) {
            thread.start();
        }
    }
}
