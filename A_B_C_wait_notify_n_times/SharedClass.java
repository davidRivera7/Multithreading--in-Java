package A_B_C_wait_notify_n_times;

public class SharedClass {    
    volatile int counter = 0;
    final int n;    
    private volatile char letter = 'A';

    SharedClass(int n) {
        this.n = n;
    }

    public void print() {
        System.out.println(counter + 1 + ": " + letter + " - " + Thread.currentThread().getName());                
        changeLetter();
        incrementCounter();
    }

    private void changeLetter() {
        letter = (letter == 'C') ? 'A' : ++letter;
    }

    private void incrementCounter() {
        counter++;
    }

}
