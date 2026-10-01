package A_B_C_wait_notify_3n_times;

public class SharedClass {    
    volatile int counter = 0; //It has control over how many times ABC is being printed.
    final int n; // number of times ABC should be printed   
    private volatile char letter = 'A';

    SharedClass(int n) {
        this.n = n;
    }

    public void print() {
        System.out.println(counter + 1 + ": " + letter + " - " + Thread.currentThread().getName());                
        changeLetter();
    }

    private void changeLetter() {
        if (letter == 'C') {
            letter = 'A';
            incrementCounter();
        } else {
            letter++;
        }        
    }

    private void incrementCounter() {
        counter++;
    }

}
