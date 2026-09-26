package print_1_n_using_m_threads;

public class Printer implements Runnable {
    
    private static int numberOfThreads = 0; //(n)
    private final int idThread; //this works from 0 to (n-1)    
    private final SharedClass sharedObj;    

    public Printer(SharedClass sharedObj) {
        this.sharedObj = sharedObj;
        idThread = numberOfThreads++;
    }

    @Override
    public void run() {
        while(true) {
            synchronized(sharedObj) {
                while(sharedObj.counter() <= sharedObj.MAX_LIMIT() && (sharedObj.counter() - 1) % numberOfThreads != idThread) {
                    try {
                        sharedObj.wait();
                    }
                    catch(InterruptedException e) {}
                }
                
                if(sharedObj.counter() > sharedObj.MAX_LIMIT())
                    break;
                
                sharedObj.printCounter();                
                sharedObj.notifyAll();            
            }
        }
    }
    
}
