public class Router {
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
        System.out.print("- ("+temp.getDeviceName()+")("+temp.getType()+")arrived");
        if(size==maxsize){
            System.out.println(" and waiting");
        }
        else System.out.println();
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
        size++;
        mutux.signal();
        System.out.println("- Connection "+(order+1)+": "+temp.getDeviceName()+" Occupied");
        System.out.print("- Connection "+(order+1)+": ");
        temp.connect();
        System.out.print("- Connection "+(order+1)+": ");
        temp.onlineActivity();
        System.out.print("- Connection "+(order+1)+": ");
        temp.disconnect();
        mutux.waiting();
        size--;
        devices[order]=null;
        mutux.signal();
        connections.signal();
    }


}
