package A_B_C_Semaphores;

import java.util.concurrent.Semaphore;

class SharedClass {
    static volatile int counter = 0;    
}

public class Main2 {
    public static void main(String[] args) {        
        int N = 3;
        Semaphore aSemaphore = new Semaphore(1);
        Semaphore bSemaphore = new Semaphore(0);
        Semaphore cSemaphore = new Semaphore(0);
        
        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    aSemaphore.acquire();
                } catch (InterruptedException e) {}

                if (SharedClass.counter >= N) {
                    bSemaphore.release();
                    break;
                }
                                
                System.out.println("A");                                
                bSemaphore.release();
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    bSemaphore.acquire();
                } catch (InterruptedException e) {}
                
                if (SharedClass.counter >= N) {
                    cSemaphore.release();
                    break;
                }
                
                System.out.println("B");                                
                cSemaphore.release();
            }
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                try {
                    cSemaphore.acquire();
                } catch (InterruptedException e) {}

                if (SharedClass.counter >= N) break;                
                                
                System.out.println("C");                
                SharedClass.counter++;
                aSemaphore.release();
            }
        });

        t1.start();
        t2.start();
        t3.start();     
        
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {}

        System.out.println();
        System.out.println("Permits:");
        System.out.println(aSemaphore.availablePermits());
        System.out.println(bSemaphore.availablePermits());
        System.out.println(cSemaphore.availablePermits());
    }
    
}
