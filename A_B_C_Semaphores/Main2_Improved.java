package A_B_C_Semaphores;

import java.util.concurrent.Semaphore;

public class Main2_Improved {
    public static void main(String[] args) {
        Semaphore aSemaphore = new Semaphore(1);
        Semaphore bSemaphore = new Semaphore(0);
        Semaphore cSemaphore = new Semaphore(0);

        int n = 3;

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    aSemaphore.acquire();
                } catch (Exception e) {}

                System.out.println("A");
                bSemaphore.release();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    bSemaphore.acquire();
                } catch (Exception e) {}

                System.out.println("B");
                cSemaphore.release();
            }
        });

        Thread t3 = new Thread(() -> {
            for (int i = 0; i < n; i++) {
                try {
                    cSemaphore.acquire();
                } catch (Exception e) {}

                System.out.println("C");
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
        } catch (Exception e) {}

        System.out.println();
        System.out.println("Permits");
        System.out.println(aSemaphore.availablePermits());
        System.out.println(bSemaphore.availablePermits());
        System.out.println(cSemaphore.availablePermits());
    }
    
}
