package A_B_C_wait_notify_n_times;

public class Main {
    public static void main(String[] args) {
        SharedClass shObj = new SharedClass(10);

        Thread t1 = new Thread(new Printer(shObj));
        Thread t2 = new Thread(new Printer(shObj));
        Thread t3 = new Thread(new Printer(shObj));

        t1.start();
        t2.start();
        t3.start();        
    }    
}
