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
