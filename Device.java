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
        int rand = random.nextInt(8000)+1;
        try {
            sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void disconnect(){
         Random random = new Random() ;
        int rand = random.nextInt(1000)+1;
        try {
            sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void connect(){
        Random random = new Random() ;
        int rand = random.nextInt(8000)+1 ;
        try {
            sleep(rand);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
