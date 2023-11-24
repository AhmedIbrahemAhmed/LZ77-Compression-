//important note: this program prints to log file
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import java.util.Random;
class Network {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("What is the number of WI-FI Connections?");
        int size = Integer.parseInt(scanner.nextLine());
        File file = new File("log.txt") ;
        try {
            if(!file.createNewFile()){
                file.delete() ;
                file = new File("log.txt") ;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Router router= new Router(size,file);
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
        System.out.println("wait until program finish and open log file to see the result");
    }
}
class Semaphore {
    protected int counter ;
    public Semaphore(){
        counter = 0 ;
    }
    public Semaphore(int size){
        this.counter = size ;
    }
    public synchronized void waiting(){
        counter-- ;
        if(counter<0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public synchronized void signal(){
        counter++ ;
        if(counter<= 0){
            System.out.print("");
            notify();
        }
    }
}
class MyRunnable implements Runnable {
    private String device;
    private Router router;
    public MyRunnable(String device,Router router){
        this.device=device;
        this.router=router;
    }
    public void run(){
        String parameters[]=device.split(" ");
        Device temp =new Device(parameters[0], parameters[1]);
        try {
            router.arrive(temp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
class Device {
    protected String deviceName;
    protected String type ;

    public Device(String name, String type) {
        this.deviceName = name;
        this.type = type;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String name) {
        this.deviceName = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void onlineActivity(){
        Random random = new Random() ;
        int rand = random.nextInt(2000)+1;
        try {
            Thread.sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect(){
        Random random = new Random() ;
        int rand = random.nextInt(2000)+1;
        try {
            Thread.sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void arrive(){
        Random random = new Random() ;
        int rand = random.nextInt(2000)+1;
        try {
            Thread.sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void occupy(){
        Random random = new Random() ;
        int rand = random.nextInt(2000)+1;
        try {
            Thread.sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void connect(){
        Random random = new Random() ;
        int rand = random.nextInt(2000)+1 ;
        try {
            Thread.sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class Router {
    private Semaphore mutux;
    private Semaphore connections;
    private Device[] devices;
    private int size;
    private int maxsize;
    private File file ;
    public Router(int max, File file){
        this.file = file ;
        devices= new Device[max];
        for(int i=0;i<max;i++){
            devices[i]=null;
        }
        mutux=new Semaphore(1);
        connections= new Semaphore(max);
        size=0;
        maxsize=max;
    }
    public void arrive(Device temp) throws IOException {
        temp.arrive();
        FileWriter writer = new FileWriter(file,true) ;
        mutux.waiting();
        if(size==maxsize){
            writer.write("- ("+temp.getDeviceName()+")("+temp.getType()+")arrived and waiting\n");
            writer.close();
        }
        else{
            writer.write("- ("+temp.getDeviceName()+")("+temp.getType()+")arrived\n");
            writer.close();
            size++;
        }
        mutux.signal();

        connections.waiting();
        int order=-1;
        mutux.waiting();
        for(int i=0;i<maxsize;i++){
            if(devices[i]==null)
            {
                devices[i]=temp;
                order=i;
                break;
            }
        }

        mutux.signal();
        writer = new FileWriter(file,true) ;
        writer.write("- Connection "+(order+1)+": "+temp.getDeviceName()+" Occupied\n");
        writer.close();
        temp.occupy();
        writer = new FileWriter(file,true) ;
        writer.write("- Connection "+(order+1)+": "+temp.getDeviceName()+" Log in\n");
        writer.close();
        temp.connect();
        writer = new FileWriter(file,true) ;
        writer.write("- Connection "+(order+1)+": "+temp.getDeviceName()+" performs online activity\n");
        writer.close();
        temp.onlineActivity();
        writer = new FileWriter(file,true) ;
        writer.write("- Connection "+(order+1)+": "+temp.getDeviceName()+ " Logged out\n");
        writer.close();
        temp.disconnect();
        mutux.waiting();
        size--;
        devices[order]=null;
        mutux.signal();
        connections.signal();
        writer.close();
    }


}


