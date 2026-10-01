package A_B_C_wait_notify_3n_times_best;

import java.util.Map;

public class SharedClass {
    private final Map<Integer, String> map;
    int turn = 0;
    int numberOfLettersPrinted = 0; 

    SharedClass(Map<Integer, String> map) {
        this.map = map;
    }

    public void print(int idThread) {
        System.out.println(map.get(idThread) + " -> " + Thread.currentThread().getName());                
        numberOfLettersPrinted++;
        turn = (turn + 1) % 3;        
    }
    
}
