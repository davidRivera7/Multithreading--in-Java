package A_B_C_wait_notify_3n_times;

public class Printer implements Runnable {    
    private static volatile int turn = 0; //It has control over which thread should continue printing.
    private static int totalThreads = 0;    
    private int id;        
    private SharedClass shObj;

    Printer(SharedClass shObj) {        
        this.id = totalThreads++;
        this.shObj = shObj;                
    }

    @Override
    public void run() {
        synchronized (shObj) {
            while (true) {
                while (shObj.counter < shObj.n && turn % totalThreads != id) {
                    try {
                        shObj.wait();
                    } catch (InterruptedException e) {}
                }                

                if (shObj.counter == shObj.n)                   
                    break;                
                
                shObj.print();
                shObj.notifyAll();
                turn++;
            }        
        }
    }        
}
