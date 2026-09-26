package print_1_n_using_m_threads;

public class SharedClass {
    private int counter = 1;
    private int MAX_LIMIT;

    public SharedClass(int MAX_LIMIT) {
        this.MAX_LIMIT = MAX_LIMIT;
    }

    public void printCounter() {
        System.out.printf("%d was printed by %s\n", counter, Thread.currentThread().getName());
        counter++;
    }

    public int counter() {
        return counter;
    }

    public int MAX_LIMIT() {
        return MAX_LIMIT;
    }    
}
