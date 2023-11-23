import java.util.Random;

public class Device extends Thread{
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
        int rand = random.nextInt() ;
        System.out.println("performs online activity"); ;
        try {
            sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect(){
        System.out.println(deviceName + "Logged out") ;
    }
    public void connect(){
        System.out.println(deviceName + "Log in")  ;
        Random random = new Random() ;
        int rand = random.nextInt() ;
        try {
            sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
