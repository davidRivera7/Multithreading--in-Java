package A_B_C_wait_notify_3n_times_best;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(0, "A");
        map.put(1, "B");
        map.put(2, "C");

        SharedClass sharedClass = new SharedClass(map);

        for (int i = 0; i < 3; i++) {
            Thread t = new Thread(new Printer(i, 3, sharedClass));
            t.start();
        }
    }   
}
