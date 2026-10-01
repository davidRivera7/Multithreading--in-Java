package A_B_C_wait_notify_3n_times_best;

public class Printer implements Runnable {

    private final int idThread;
    private final int n; // number of times 'ABC' will be printed
    private final SharedClass sharedClass;

    Printer(int idThread, int n, SharedClass sharedClass) {
        this.idThread = idThread;
        this.n = n;
        this.sharedClass = sharedClass;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (sharedClass) {
                while (sharedClass.numberOfLettersPrinted < n * 3 && sharedClass.turn != idThread) {
                    try {
                        sharedClass.wait();
                    } catch (InterruptedException e) {}
                }

                if (sharedClass.numberOfLettersPrinted == n * 3) break;

                sharedClass.print(idThread);
                sharedClass.notifyAll();
            }
        }
    }

}
