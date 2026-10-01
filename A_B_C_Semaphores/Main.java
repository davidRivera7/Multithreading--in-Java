package A_B_C_Semaphores;

import java.util.concurrent.Semaphore;

class SharedClass {
    static volatile int counter = 0;
}

public class Main {
    public static void main(String[] args) {        
        int MAX_LIMIT_LAPS = 10;
        Semaphore aSemaphore = new Semaphore(1);
        Semaphore bSemaphore = new Semaphore(0);
        Semaphore cSemaphore = new Semaphore(0);

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    aSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                if (SharedClass.counter >= MAX_LIMIT_LAPS) {
                    bSemaphore.release();
                    break;
                }

                System.out.println(++SharedClass.counter + ": A -> " + Thread.currentThread().getName());                
                bSemaphore.release();
            }                                                                                               
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    bSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                if (SharedClass.counter >= MAX_LIMIT_LAPS) {
                    cSemaphore.release();
                    break;                
                }

                System.out.println(++SharedClass.counter + ": B -> " + Thread.currentThread().getName());                                                
                cSemaphore.release();
            }                                             
        });

        Thread t3 = new Thread(() -> {
            while (true) {
                try {
                    cSemaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                if (SharedClass.counter >= MAX_LIMIT_LAPS) {
                    aSemaphore.release();
                    break;
                }
                
                System.out.println(++SharedClass.counter + ": C -> " + Thread.currentThread().getName());                                                
                aSemaphore.release();
            }                                             
        });

        t1.start();
        t2.start();
        t3.start();                         
    }
}
