package pingPongSemaphores;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore pingSemaphore = new Semaphore(1);
        Semaphore pongSemaphore = new Semaphore(0);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    pingSemaphore.acquire();
                } catch (InterruptedException e) {}
                System.out.println("Ping -> " + Thread.currentThread().getName());
                pongSemaphore.release();
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    pongSemaphore.acquire();
                } catch (InterruptedException e) {}
                System.out.println("Pong -> " + Thread.currentThread().getName());
                pingSemaphore.release();
            }
        });

        t1.start();
        t2.start();
    }
}