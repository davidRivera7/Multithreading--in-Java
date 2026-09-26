package oddEvenPrinter;

public class Printer implements Runnable {
    private SharedClass sharedObj;
    private int idThread;

    public Printer(SharedClass sharedObj, int idThread) {
        this.sharedObj = sharedObj;
        this.idThread = idThread;
    }

    @Override
    public void run() {
        while(true) {
            synchronized(sharedObj) {
                while(sharedObj.counter() <= sharedObj.MAX_LIMIT() && (sharedObj.counter() - 1) % 2 != idThread) {
                    try {
                        sharedObj.wait();
                    }
                    catch(InterruptedException e) {}
                }

                if(sharedObj.counter() > sharedObj.MAX_LIMIT()) 
                    break;

                sharedObj.printCounter();
                sharedObj.notify();
            }
        }        
    }

}
