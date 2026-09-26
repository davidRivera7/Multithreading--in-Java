package print_1_n_using_m_threads;

public class Main {
    public static void main(String[] args) {
        int n = 100;
        int m = 4;

        SharedClass sharedObj = new SharedClass(n);

        for(int i = 0; i < m; i++) {            
            Thread t = new Thread(new Printer(sharedObj), "Thread-" + (i + 1));
            t.start();            
        }        

    }    
}
