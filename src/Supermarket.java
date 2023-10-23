import javax.swing.*;
import java.util.concurrent.CountDownLatch;

public class Supermarket {

    static SupermarketSwing ui;
    static Store store = new Store(4,6,5,0.6);
    static boolean storeIsFull = false;
    static final double SPEED = 0.5;        //to make slower or faster time pass
    static int openingHour = 8;
    static int closingHour = 16;

    public static void main(String args[]) {
        store.open = true;
        store.generateNewClients = true;
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ui = new SupermarketSwing();
                latch.countDown();
            }
        });

        try {
            latch.await();
        } catch (InterruptedException exc) {
            System.out.println("Przerwane oczekiwanie na otwarcie aplikacji.");
        }

        startManagerAndGeneratorThreads();
        runStoreThread();

        store.open = false;
    }

    private static void startManagerAndGeneratorThreads() {
        var manager = new CheckoutManager();
        Thread managerThread = new Thread(manager);
        managerThread.start();

        var generator = new ClientGenerator();
        Thread generatorThread = new Thread(generator);
        generatorThread.start();
    }

    private static void runStoreThread() {
        int hourNow = openingHour;
        int minNow = 0;

        try {
            while (hourNow < closingHour || store.clientsInStoreAndAtCheckouts != 0) {

                Thread.sleep((long) (1000 * (1/Supermarket.SPEED)));
                minNow += 10;
                if(minNow == 60) {
                    minNow = 0;
                    hourNow++;
                }
                Supermarket.ui.updateTimeAndPrintStoreState(hourNow, minNow);
                checkIfWeCanGenerateClients(hourNow);
            }
        } catch(InterruptedException exc) {
            System.out.println("Przerwany wątek wyświetlania.");
        }
    }

    private static void checkIfWeCanGenerateClients(int hourNow) {
        if(storeIsFull == true &&
                store.clientsInStoreAndAtCheckouts < store.numberOfCientsForChechout * store.numberOfCheckouts) {
            storeIsFull = false;
        }

        if(hourNow>=closingHour-1) store.generateNewClients = false;

        if(store.clientsInStoreAndAtCheckouts >= store.numberOfCientsForChechout * store.numberOfCheckouts) {
            storeIsFull = true;
        }
    }
}
