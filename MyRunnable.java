public class MyRunnable implements Runnable {
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
