package oddEvenPrinter;

public class Main {
    public static void main(String[] args) {
        SharedClass sharedObj = new SharedClass(10);

        Thread oddThread = new Thread(new Printer(sharedObj, 0), "Odd Printer");
        Thread evenThread = new Thread(new Printer(sharedObj, 1), "Even Printer");

        oddThread.start();
        evenThread.start();        
    }
    
}
