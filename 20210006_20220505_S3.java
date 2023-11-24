import java.util.Scanner;
import java.util.Vector;
import java.util.Random;
class Network {
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
        router.arrive(temp);
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
        int rand = random.nextInt(8000)+1;
        try {
            Thread.sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect(){
        Random random = new Random() ;
        int rand = random.nextInt(1000)+1;
        try {
            Thread.sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void connect(){
        Random random = new Random() ;
        int rand = random.nextInt(8000)+1 ;
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

    public Router(int max){
        devices= new Device[max];
        for(int i=0;i<max;i++){
            devices[i]=null;
        }
        mutux=new Semaphore(1);
        connections= new Semaphore(max);
        size=0;
        maxsize=max;
    }
    public void arrive(Device temp){
        mutux.waiting();
        if(size==maxsize){
            System.out.println("- ("+temp.getDeviceName()+")("+temp.getType()+")arrived and waiting");
        }
        else{
            System.out.println("- ("+temp.getDeviceName()+")("+temp.getType()+")arrived");
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
        System.out.println("- Connection "+(order+1)+": "+temp.getDeviceName()+" Occupied");
        System.out.println("- Connection "+(order+1)+": "+temp.getDeviceName()+" Log in");
        temp.connect();
        System.out.println("- Connection "+(order+1)+": "+temp.getDeviceName()+" performs online activity");
        temp.onlineActivity();
        System.out.println("- Connection "+(order+1)+": "+temp.getDeviceName()+ " Logged out");
        temp.disconnect();
        mutux.waiting();
        size--;
        devices[order]=null;
        mutux.signal();
        connections.signal();
    }


}


