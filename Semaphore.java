public class Semaphore {
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
